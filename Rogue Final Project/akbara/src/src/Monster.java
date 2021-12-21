package src;

public class Monster extends Creature {
    private String name;
    private String id;
    private String serial;

    public Monster() {
        System.out.println("Creating Monster");
    }

    public void setName(String name) {
        System.out.println("set name to: " + name);
        this.name = name;
    }

    public void setID(int room, int serial) {
        System.out.println("set ID to: " + room + serial);
        id = room + "-" + serial;
    }

    public String getName() { return name; }

    public String getID() { return id; }

    public String getSerial() { return serial; }
}