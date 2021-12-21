package src;

import java.util.ArrayList;

public class Item extends Displayable {
    private String name;
    private String id;
    private int intValue = 0;
    private ArrayList<ItemAction> itemAction = new ArrayList<ItemAction>();

    public Item() {
        System.out.println("Creating Item");
    }

    public void setName(String name) {
        System.out.println("set item_name to: " + name);
        this.name = name;
    }

    public void setID(int room, int serial) {
        System.out.println("Set item_ID to: " + room + serial);
        id = room + "-" + serial;
    }

    public void setAction(ItemAction ia) {
        System.out.println("set ItemAction to: " + ia);
        itemAction.add(ia);
    }

    public void setIntValue(int v) {
        System.out.println("intValue set to: " + v);
        intValue = v;
    }

    public String getName() {
        return name;
    }

    public String getID() {return id; }

    public int getIntValue() {
        return intValue;
    }

    public ArrayList<ItemAction> getItemActions() {
        return itemAction;
    }
}