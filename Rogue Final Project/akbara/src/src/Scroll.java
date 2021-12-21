package src;

public class Scroll extends Item {

    private String id;

    public Scroll(String name) {
        System.out.println("Creating Scroll: " + name);
        this.setName(name);
        setType('?');
    }

    public void setID(int room, int serial) {
        System.out.println("set ID to: " + room + serial);
        id = room + "-" + serial;
    }

    public String getID() { return id; }

    @Override
    public char getChar() {
        return '?';
    }
}