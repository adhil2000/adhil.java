package src;

import java.util.ArrayList;

public class Dungeon {

    // dungeon parameters
    private String name;
    private int width;
    private int gameHeight;
    private static Dungeon dungeon = null;

    // ArrayLists
    private ArrayList<Room> rooms = new ArrayList();
    private ArrayList<Creature> creatures = new ArrayList();
    private ArrayList<Passage> passages = new ArrayList();
    private ArrayList<Item> items = new ArrayList();

    private Dungeon(String _name, int _width, int _gameHeight) {
        name = _name;
        width = _width;
        gameHeight = _gameHeight;
        System.out.println("Creating dungeon: \n");
        System.out.println("Dungeon Name:"+ name + "\n");
        System.out.println("Dungeon Width:"+ width + "\n");
        System.out.println("Dungeon Height:" +gameHeight);
    }

    // getDungeon Method
    public static Dungeon getDungeon(String name, int width, int gameHeight) {
        System.out.println("Getting dungeon:");
        if (dungeon == null) { // create dungeon with name, width, and gameHeight
            System.out.println("Dungeon is NULL, Creating:");
            dungeon = new Dungeon(name, width, gameHeight);
            return dungeon;
        }
        return dungeon;
    }

    public void addRoom(Room room) {
        System.out.println("Creating room");
        rooms.add(room);
    }

    public void addCreature(Creature creature) {
        System.out.println("Creating creature");
        creatures.add(creature);
    }

    public void addPassage(Passage passage) {
        System.out.println("Creating passage");
        passages.add(passage);
    }

    public void addItem(Item item) {
        System.out.println("Creating item");
        items.add(item);
    }

    public String getName() { return name; }

    public  int getWidth() { return width; }

    public  int getGameHeight() { return gameHeight; }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Passage> getPassages() {
        return passages;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}