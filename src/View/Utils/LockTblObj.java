package View.Utils;

import Model.Values.Value;

public class LockTblObj {
	Integer Name;
	Integer Value;

	 public LockTblObj(Integer name, Integer value) {
	        this.Name = name;
	        this.Value = value;
	    }

	    public Integer getName() {
	        return Name;
	    }

	    public void setName(Integer name) {
	        this.Name = name;
	    }

	    public Integer getValue() {
	        return Value;
	    }

	    public void setValue(Integer value) {
	        this.Value = value;
	    }
	    }


