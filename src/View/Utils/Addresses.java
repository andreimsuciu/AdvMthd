package View.Utils;

import Model.ADTs.MyIStack;
import Model.ADTs.MyStack;

public class Addresses {
	private Integer address = 1;
	private static MyIStack<Integer> freeAddress = new MyStack<>();

		    public Integer getFreeAddress() {
		    	if (freeAddress.isEmpty())
		    		return this.address++;
		    	else
		    		return freeAddress.pop();
		    }
}
