package src;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class DungeonXMLHandler extends DefaultHandler {
    // the two lines that follow declare a DEBUG flag to control
    // debug print statements and to allow the class to be easily
    // printed out.  These are not necessary for the parser.
    private static final int DEBUG = 1;
    private static final String CLASSID = "DungeonXMLHandler";

    // data can be called anything, but it is the variables that
    // contains information found while parsing the xml file
    private StringBuilder data = null;

    // When the parser parses the file it will add references to
    // Student objects to this array so that it has a list of
    // all specified students.  Had we covered containers at the
    // time I put this file on the web page I would have made this
    // an ArrayList of Students (ArrayList<Student>) and not needed
    // to keep tract of the length and maxStudents.  You should use
    // an ArrayList in your project.
    private Dungeon dungeon = null;
    private ObjectDisplayGrid object = null;

    // The XML file contains a list of Students, and within each
    // Student a list of activities (clubs and classes) that the
    // student participates in.  When the XML file initially
    // defines a student, many of the fields of the object have
    // not been filled in.  Additional lines in the XML file
    // give the values of the fields.  Having access to the
    // current Student and Activity allows setters on those
    // objects to be called to initialize those fields.
    private Room roomBeingParsed = null;
    private Passage passageBeingParsed = null;
    private Creature creatureBeingParsed = null;
    private Action actionBeingParsed = null;
    private Item itemBeingParsed = null;
    private Stack<Displayable> displayBeingParsed = new Stack();

    // Used by code outside the class to get the list of Student objects
    // that have been constructed.
    public Dungeon getDungeons() {
        return dungeon;
    }

    public ObjectDisplayGrid getObjectDisplayGrids() {
        return object;
    }


    // A constructor for this class.  It makes an implicit call to the
    // DefaultHandler zero arg constructor, which does the real work
    // DefaultHandler is defined in org.xml.sax.helpers.DefaultHandler;
    // imported above, and we don't need to write it.  We get its
    // functionality by deriving from it!
    public DungeonXMLHandler() {
        // What is this supposed to do?
    }

    // Private bools
    private boolean visible_TF = false;
    private boolean maxHit_TF = false;
    private boolean hpMove_TF = false;
    private boolean hp_TF = false;
    private boolean type_TF = false;
    private boolean itemInt_TF = false;
    private boolean posX_TF = false;
    private boolean posY_TF = false;
    private boolean width_TF = false;
    private boolean height_TF = false;
    private boolean actionMsg_TF = false;
    private boolean actionVal_TF = false;
    private boolean actionChar_TF = false;

    // startElement is called when a <some element> is called as part of
    // <some element> ... </some element> start and end tags.
    // Rather than explain everything, look at the xml file in one screen
    // and the code below in another, and see how the different xml elements
    // are handled.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if (qName.equalsIgnoreCase("Dungeon")) {

            //PARSE DUNGEON ATTRIBUTES
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            dungeon = Dungeon.getDungeon(name, width, gameHeight);
            try {
                object = ObjectDisplayGrid.getObjectDisplayGrid(gameHeight, width, topHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //DISPLAYABLE ATTRIBUTES
        } else if (qName.equalsIgnoreCase("visible")) {
            visible_TF = true;
        } else if (qName.equalsIgnoreCase("maxhit")) {
            maxHit_TF = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            hpMove_TF = true;
        } else if (qName.equalsIgnoreCase("hp")) {
            hp_TF = true;
        } else if (qName.equalsIgnoreCase("type")) {
            type_TF = true;
        } else if (qName.equalsIgnoreCase("ItemIntValue")) {
            itemInt_TF = true;
        } else if (qName.equalsIgnoreCase("posX")) {
            posX_TF = true;
        } else if (qName.equalsIgnoreCase("posY")) {
            posY_TF = true;
        } else if (qName.equalsIgnoreCase("width")) {
            width_TF = true;
        } else if (qName.equalsIgnoreCase("height")) {
            height_TF = true;

            //PARSE STRUCTURE.ROOM ATTRIBUTES
        } else if (qName.equalsIgnoreCase("Room")) {
            int id = Integer.parseInt(attributes.getValue("room"));
            Room room = new Room("");
            room.setID(id);
            roomBeingParsed = room;
            displayBeingParsed.push(room);
            dungeon.addRoom(room);

            //PARSE STRUCTURE.PASSAGE ATTRIBUTES
        } else if (qName.equalsIgnoreCase("Passage")) {
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage();
            passage.setID(room1, room2);
            passageBeingParsed = passage;
            displayBeingParsed.push(passage);
            dungeon.addPassage(passageBeingParsed);
            //PARSE CREATURE ATTRIBUTES
        } else if (qName.equalsIgnoreCase("Player") || qName.equalsIgnoreCase("Monster")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));

            Creature creature = null;
            if (qName.equalsIgnoreCase("Player")) {
                creature = new Player();
            } else if (qName.equalsIgnoreCase("Monster")) {
                creature = new Monster();
            }
            creature.setName(name);
            creature.setID(room, serial);
            creatureBeingParsed = creature;
            roomBeingParsed.setCreature(creature);
            creatureBeingParsed = creature;
            displayBeingParsed.push(creature);

            //PARSE ITEM ATTRIBUTES
        } else if (qName.equalsIgnoreCase("Scroll") || qName.equalsIgnoreCase("Armor") || qName.equalsIgnoreCase("Sword")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));

            Item item = null;
            if (qName.equalsIgnoreCase("Scroll")) {
                item = new Scroll(name);
            } else if (qName.equalsIgnoreCase("Armor")) {
                item = new Armor(name);
            } else if (qName.equalsIgnoreCase("Sword")) {
                item = new Sword(name);
            }
            item.setID(room, serial);
            itemBeingParsed = item;
            // TYPE OF ITEM BEING PARSED
            if (displayBeingParsed.peek() instanceof Room) {
                roomBeingParsed.addItem(item);
            } else if (displayBeingParsed.peek() instanceof Player) {
                if (itemBeingParsed instanceof Sword) {
                    ((Player) creatureBeingParsed).addPack(itemBeingParsed);
                } else if (itemBeingParsed instanceof Armor) {
                    ((Player) creatureBeingParsed).addPack(itemBeingParsed);
                }
            }
            displayBeingParsed.push(item);

            //PARSE ACTION ATTRIBUTES
        } else if (qName.equalsIgnoreCase("CreatureAction") || qName.equalsIgnoreCase("ItemAction")) {
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");

            Action action = null;
            if (qName.equalsIgnoreCase("CreatureAction")) {
                //CREATURE ACTION ATTRIBUTES
                if (type.equals("death")) {
                    creatureBeingParsed.setDeathAction((CreatureAction) action);
                } else if (type.equals("hit")) {
                    creatureBeingParsed.setHitAction((CreatureAction) action);
                }
                if (name.equals("Remove")) {
                    action = new Remove(name, creatureBeingParsed);
                } else if (name.equals("YouWin")) {
                    action = new YouWin(name, creatureBeingParsed);
                } else if (name.equals("UpdateDisplay")) {
                    action = new UpdateDisplay(name, creatureBeingParsed);
                } else if (name.equals("Teleport")) {
                    action = new Teleport(name, creatureBeingParsed);
                } else if (name.equals("ChangeDisplayedType")) {
                    action = new ChangedDisplayedType(name, creatureBeingParsed);
                } else if (name.equals("DropPack")) {
                    action = new DropPack(name, creatureBeingParsed);
                } else if (name.equals("EndGame")) {
                    action = new EndGame(name, creatureBeingParsed);
                }
            } else if (qName.equalsIgnoreCase("ItemAction")) {
                //ITEM ACTION ATTRIBUTES
                if (name.equals("BlessArmor")) {
                    action = new BlessArmor(itemBeingParsed);
                } else if (name.equals("BlessCurseOwner")) {
                    action = new BlessCurseOwner(itemBeingParsed);
                } else if (name.equals("Hallucinate")) {
                    action = new Hallucinate(itemBeingParsed);
                }
                ItemAction newaction = (ItemAction) action;
                itemBeingParsed.setAction(newaction);
            }
            actionBeingParsed = action;

            //ACTION ATTRIBUTES
        }  else if (qName.equalsIgnoreCase("actionMessage")) {
            actionMsg_TF = true;
        } else if (qName.equalsIgnoreCase("actionIntValue")) {
            actionVal_TF = true;
        } else if (qName.equalsIgnoreCase("actionCharValue")) {
            actionChar_TF = true;
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        //END FOR ROOM
        if (qName.equalsIgnoreCase("Room")) {
            roomBeingParsed = null;
            displayBeingParsed.pop();
            //END FOR PASSAGE
        } else if (qName.equalsIgnoreCase("Passage")) {
            passageBeingParsed = null;
            displayBeingParsed.pop();
            //END FOR CREATURE TYPE(S)
        } else if (qName.equalsIgnoreCase("Player") || qName.equalsIgnoreCase("Monster")) {
            creatureBeingParsed = null;
            displayBeingParsed.pop();
            //END FOR ACTION TYPE(S)
        } else if (qName.equalsIgnoreCase("CreatureAction") || qName.equalsIgnoreCase("ItemAction")) {
            actionBeingParsed = null;
        } else if (qName.equalsIgnoreCase("Scroll") || qName.equalsIgnoreCase("Armor") || qName.equalsIgnoreCase("Sword")) {
            itemBeingParsed = null;
            displayBeingParsed.pop();
        }

        // SETTERS
        if (visible_TF) {
            if (data.toString().equals("1")) {
                displayBeingParsed.peek().setVisible();
            } else {
                displayBeingParsed.peek().setInvisible();
            }
            visible_TF = false;
        } else if (maxHit_TF) {
            creatureBeingParsed.setMaxHit(Integer.parseInt(data.toString()));
            maxHit_TF = false;
        } else if (hpMove_TF) {
            creatureBeingParsed.setHpMoves(Integer.parseInt(data.toString()));
            hpMove_TF = false;
        } else if (hp_TF) {
            creatureBeingParsed.setHp(Integer.parseInt(data.toString()));
            hp_TF = false;
        } else if (type_TF) {
            creatureBeingParsed.setType(data.toString().charAt(0));
            type_TF = false;
        } else if (itemInt_TF) {
            itemBeingParsed.setIntValue(Integer.parseInt(data.toString()));
            itemInt_TF = false;
        } else if (posX_TF) {
            displayBeingParsed.peek().setPosX(Integer.parseInt(data.toString()));
            posX_TF = false;
        } else if (posY_TF) {
            displayBeingParsed.peek().setPosY(Integer.parseInt(data.toString()));
            posY_TF = false;
        } else if (width_TF) {
            displayBeingParsed.peek().setWidth(Integer.parseInt(data.toString()));
            width_TF = false;
        } else if (height_TF) {
            displayBeingParsed.peek().setHeight(Integer.parseInt(data.toString()));
            height_TF = false;
        } else if (actionMsg_TF) {
            actionBeingParsed.setMessage(data.toString());
            actionMsg_TF = false;
        } else if (actionVal_TF) {
            actionBeingParsed.setIntValue(Integer.parseInt(data.toString()));
            actionVal_TF = false;
        } else if (actionChar_TF) {
            actionBeingParsed.setCharValue(data.toString().charAt(0));
            actionChar_TF = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }
}
