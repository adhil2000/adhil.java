package game;

import org.xml.sax.SAXException;
import src.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Rogue implements Runnable {

    private int PosX;
    private int PosY;
    private static ObjectDisplayGrid displayGrid = null;
    private static Dungeon dungeon = null;
    private Thread PlayerMovement;
    private static Player player;

    // Display Passages
    public void displayPassage(Passage passage) {
        ArrayList<Integer> passageX = passage.getPassageX();
        ArrayList<Integer> passageY = passage.getPassageY();
        for (int i = 0; i < passageX.size() - 1; i++) {
            Integer posX1 = passageX.get(i);
            Integer posY1 = passageY.get(i);
            Integer posX2 = passageX.get(i + 1);
            Integer posY2 = passageY.get(i + 1);
            // Draw X Axis
            if (posX1.equals(posX2)) {
                while (!posY1.equals(posY2)) {
                    addChar(posX1, posY1);
                    if (posY1 < posY2) {
                        posY1++;
                    } else {
                        posY1--;
                    }
                }
                addChar(posX1, posY1);
                // Draw Y Axis
            } else if (posY1.equals(posY2)) {
                while (!posX1.equals(posX2)) {
                    addChar(posX1, posY1);
                    if (posX1 < posX2) {
                        posX1++;
                    } else {
                        posX1--;
                    }
                }
                addChar(posX1, posY1);
            }
        }
    }

    // Add Char
    public void addChar(Integer posX, Integer posY) {
        Object currObj = displayGrid.getDisplayObject(posX, posY + displayGrid.getTopMessageHeight());
        if (currObj instanceof Char) {
            if (((Char)currObj).getChar() == 'X') {
                displayGrid.addDisplayObject(new Char('+'), posX, posY + displayGrid.getTopMessageHeight());
            } else {
                displayGrid.addDisplayObject(new Char('#'), posX, posY + displayGrid.getTopMessageHeight());
            }
        }
    }

    // run
    @Override
    public void run() {
        // Not Working without this
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Must Display Rooms First
        Room room;
        int hp = 0;
        int core = 0;
        ArrayList<Room> rooms = dungeon.getRooms();
        for (int r = 0; r < rooms.size(); r++) {
            room = rooms.get(r);
            // Tricky
            int roomTop = room.getPosY() + displayGrid.getTopMessageHeight();
            for (int line = roomTop; line < room.getPosY() + room.getHeight() + displayGrid.getTopMessageHeight(); line++) {
                if (line == roomTop || line == roomTop + room.getHeight() - 1) {
                    for (int border = room.getPosX(); border < room.getPosX() + room.getWidth(); border++) {
                        displayGrid.addDisplayObject(new Char('X'), border, line);
                    }
                } else {
                    displayGrid.addDisplayObject(new Char('X'), room.getPosX(), line);
                    for ( int border = room.getPosX() + 1; border < room.getPosX() + room.getWidth() - 1; border++) {
                        displayGrid.addDisplayObject(new Char('.'), border, line);
                    }
                    displayGrid.addDisplayObject(new Char('X'), room.getPosX() + room.getWidth() - 1, line);
                }
            }

            // Display Items
            ArrayList<Item> items = room.getItems();
            Item item;
            for (int i = 0; i < items.size(); i++) {
                item = items.get(i);
                int roomX = room.getPosX() + item.getPosX();
                int roomY = room.getPosY() + item.getPosY() + displayGrid.getTopMessageHeight();
                displayGrid.addDisplayObject(item, roomX, roomY);
            }

            // Display Creatures
            ArrayList<Creature> creatures = room.getCreatures();
            Creature creature;
            for (int c = 0; c < creatures.size(); c++) {
                creature = creatures.get(c);
                if (creature instanceof Player) {
                    hp = creature.getHp();
                    PosX = creature.getPosX() + room.getPosX();
                    PosY = creature.getPosY() + room.getPosY() + displayGrid.getTopMessageHeight();
                    displayGrid.addDisplayObject(new Char('.'), PosX, PosY);
                    player = (Player) creature;
                }
                displayGrid.addDisplayObject(creature, room.getPosX() + creature.getPosX(),
                        room.getPosY() + creature.getPosY() + displayGrid.getTopMessageHeight());
            }
        }

        // Display Passages
        ArrayList<Passage> passages = dungeon.getPassages();
        Passage passage;
        for (int i = 0; i < passages.size(); i++) {
            passage = passages.get(i);
            displayPassage(passage);
        }

        int height = displayGrid.getHeight();
        // Display HP and CORE Text
        String hpString = "HP: " + hp + "  core:  " + core;
        for (int x = 0; x < hpString.length(); x++) {
            displayGrid.addDisplayObject(new Char(hpString.charAt(x)), x, 0);
        }
        // Display Pack Text
        String pack = "Pack:";
        for (int y = 0; y < pack.length(); y++) {
            displayGrid.addDisplayObject(new Char(pack.charAt(y)), y, height - 3);
        }
        // Display Info Text
        String info = "Info:";
        for (int z = 0; z < info.length(); z++) {
            displayGrid.addDisplayObject(new Char(info.charAt(z)), z, height - 1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Ensure file name passed in
        String fileName = null;
        if (args.length == 1) {
            fileName = "xmlFiles/" + args[0];
        } else {
            System.out.println("java Test <xmlfilename>");
            return;
        }

        // From Sample Code
        // Parse XML FILE
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        DungeonXMLHandler handler = new DungeonXMLHandler();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File(fileName), handler);
            // This will change depending on what kind of XML we are parsing
            Dungeon dungeon = handler.getDungeons();
            ObjectDisplayGrid objdispgrid = handler.getObjectDisplayGrids();
            // print out all of the students.  This will change depending on
            // what kind of XML we are parsing
            System.out.println(dungeon);
            System.out.println(objdispgrid);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
            handler = null;
        }

        if (handler == null) {
            return;
        }

        // Get Dungeon
        dungeon = handler.getDungeons();
        if (dungeon == null) {
            return;
        }

        // Get DisplayGrid
        displayGrid = handler.getObjectDisplayGrids();
        if (displayGrid == null) {
            return;
        }

        // New Rogue Thread
        Rogue rogue = new Rogue();
        Thread Thread1 = new Thread(rogue);
        Thread1.start();

        Thread1.join();
        rogue.PlayerMovement = new Thread(new PlayerMovement(displayGrid, rogue.PosX, rogue.PosY, player));
        rogue.PlayerMovement.start();
        rogue.PlayerMovement.join();
    }
}
