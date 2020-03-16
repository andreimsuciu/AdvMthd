package View.Utils;

import Model.Values.Value;

public class SymTblObj {

    String Name;
    Value Value;

    public SymTblObj(String name, Value value) {
        this.Name = name;
        this.Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Value getValue() {
        return Value;
    }

    public void setValue(Value value) {
        this.Value = value;
    }
}
