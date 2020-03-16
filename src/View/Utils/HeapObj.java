package View.Utils;

import Model.Values.Value;

public class HeapObj {
    int Address;
    Value Value;

    public int getAddress() {
        return Address;
    }

    public void setAddress(int address) {
        this.Address = address;
    }

    public Value getValue() {
        return Value;
    }

    public void setValue(Value value) {
        this.Value = value;
    }

    public HeapObj(int address, Value value) {
        this.Address = address;
        this.Value = value;
    }
}
