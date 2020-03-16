package Controller;

import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Statments.IStmt;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepo;

import javax.swing.text.html.ListView;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepo repo;
    private ExecutorService executor;

    public List<PrgState> getPrgStateList() {
        return repo.getPrgList();
    }

    private List<PrgState> prgStateList;

    public Controller(IRepo r)
    {
        repo = r;
        executor = Executors.newFixedThreadPool(10);
    }


    public PrgState getPrgStateById(int id)
    {
        return repo.getPrgStateById(id);
    }

    public List<Integer> getIdList()
    {
        return repo.getListOfIds();
    }

    Map<Integer, Value> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer,Value> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e->addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable (Collection<Value> symTableValues, Collection<Value> heap){
        List<Integer> l = Stream.concat(
                heap.stream()
                        .filter(v-> v instanceof RefValue)
                        .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();}),
                symTableValues.stream()
                        .filter(v-> v instanceof RefValue)
                        .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
        )
                .collect(Collectors.toList());

        return l;
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream().filter(v->v.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws MyException{
        prgList.forEach(p-> {
            try {
                repo.logPrgStateExec(p);
                System.out.println(p.toString());
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        });
        List<Callable<PrgState>> callList = prgList.stream().
                map((PrgState p) -> (Callable<PrgState>)(()->{return p.oneStep();})).collect(Collectors.toList());
        try {
            List<PrgState> newProgramList = executor.invokeAll(callList).stream()
                    .map(programStateFuture -> {try{
                          return programStateFuture.get();
                        } catch (ExecutionException e) {
                            throw new RuntimeException("Thread excep: " + e.getMessage());
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Thread excep: " + e.getMessage());
                        }
                    })
                    .filter(programState -> programState != null)
                    .collect(Collectors.toList());
            prgList.addAll(newProgramList);
            prgList.forEach(p-> {
                try {
                    repo.logPrgStateExec(p);
                    System.out.println(p.toString());
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
            });
            repo.setPrgList(prgList);
        }
        catch (InterruptedException e)
        {
            throw new MyException("Thread excep");
        }
        catch (RuntimeException e)
        {
            throw new MyException("Thread exception");
        }
    }

    public void allStep() throws MyException
    {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());

        while(prgList.size() > 0){
            Collection<Value> allSymTbl = new ArrayList<>();
            prgList.forEach(v-> allSymTbl.addAll(v.getSymTable().getContent().values()));
            List<Integer> addr = getAddrFromSymTable(allSymTbl, prgList.get(0).getHeap().getContent().values());
            Map<Integer, Value> newHeap = safeGarbageCollector(addr, prgList.get(0).getHeap().getContent());
            prgList.get(0).getHeap().setContent(newHeap);
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    public void removePrg()
    {
        repo.setPrgList(removeCompletedPrg(getPrgStateList()));
    }

    public boolean oneStepGUI() throws MyException
    {
        List<PrgState> prg = removeCompletedPrg(repo.getPrgList());
        if(prg.size()>0)
        {
            oneStepForAllPrg(prg);
            removeCompletedPrg(repo.getPrgList());
            return true;
        }
        else {
            executor.shutdownNow();
            repo.setPrgList(prg);
            return false;
        }
    }
}
