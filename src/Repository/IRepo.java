package Repository;

import Model.ADTs.PrgState;
import Model.Exceptions.MyException;

import java.util.List;

public interface IRepo {

    void add(PrgState state);

    void logPrgStateExec(PrgState state) throws MyException;

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> listState);

    List<Integer> getListOfIds();

    PrgState getPrgStateById(int id);


}
