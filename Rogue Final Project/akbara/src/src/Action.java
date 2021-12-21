package src;

public class Action {
    private String msg;
    private int v;
    private char c;

    // create Action
    public Action() {
        System.out.println("Creating action");
    }

    // set Message
    public void setMessage(String msg) {
        System.out.println("set action message to: " + msg);
        this.msg = msg;
    }

    // set IntValue
    public void setIntValue(int v) {
        System.out.println("set action intValue to: " + v);
        this.v = v;
    }

    // set CharValue
    public void setCharValue(char c) {
        System.out.println("set action charValue to: " + c);
        this.c = c;
    }

    //Getters:

    // returns message
    public String getMessage() { return msg;}
    // returns int value
    public int getIntValue() { return v;}
    // returns char value
    public char getCharValue() {return c;}
}

