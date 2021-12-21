package src;

import java.util.ArrayList;

public class Passage extends Structure {

    private String name;
    private String id;
    private ArrayList<Integer> passageX = new ArrayList();
    private ArrayList<Integer> passageY = new ArrayList();

    public Passage() {
        System.out.println("Creating Passage");
    }

    public void setName(String name) {
        System.out.println("set name to: " + name);
        this.name = name;
    }
    public void setID(int room1, int room2) {
        System.out.println("set ID to: " + room1 + room2);
        id = room1 + "-" + room2;
    }

    @Override
    public void setPosX(int posX) {
        System.out.println("Set PassageX to: " + posX);
        passageX.add(posX);
    }

    @Override
    public void setPosY(int posY) {
        System.out.println("Set PassageY to: " + posY);
        passageY.add(posY);
    }

    public String getName() { return name; }

    public String getID() { return id; }

    public ArrayList<Integer> getPassageX() {
        return passageX;
    }

    public ArrayList<Integer> getPassageY() {
        return passageY;
    }
}
