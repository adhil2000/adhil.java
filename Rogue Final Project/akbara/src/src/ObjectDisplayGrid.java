package src;

import asciiPanel.*;
import game.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {
    // ObjectDisplayGrid private attributes
    private int gameHeight;
    private int width;
    private int height;
    private int topHeight;
    private static ObjectDisplayGrid odg = null;

    // From Sample Code
    private static final int DEBUG = 0;
    private static final String CLASSID = "ObjectDisplayGrid";
    private static AsciiPanel terminal;
    private Stack[][] objectGrid;
    private List<InputObserver> inputObservers = null;

    // New Addition
    private boolean gameOver = false;
    private Random random = new Random();
    private ArrayList<Char> characters = new ArrayList<>();
    ArrayList<Displayable> spaces = new ArrayList<Displayable>();

    private ObjectDisplayGrid(int _gameHeight, int _width, int _topHeight) {
        System.out.println("Creating an ObjectDisplayGrid: " + _gameHeight + _width + _topHeight);
        gameHeight = _gameHeight;
        width = _width;
        topHeight = _topHeight;
        height = gameHeight + topHeight;

        // Sample Code
        terminal = new AsciiPanel(width, height);
        objectGrid = new Stack[width][height];

        // Create Stack
        for (int posX = 0; posX < objectGrid.length; posX++) {
            for (int posY = 0; posY < objectGrid[0].length; posY++) {
                objectGrid[posX][posY] = new Stack();
            }
        }

        // New Addition
        characters.add(new Char('@'));
        characters.add(new Char('T'));
        characters.add(new Char('S'));
        characters.add(new Char('X'));
        characters.add(new Char(')'));
        characters.add(new Char(']'));
        characters.add(new Char('?'));
        characters.add(new Char('.'));
        characters.add(new Char('H'));

        // Sample Code
        initializeDisplay();
        super.add(terminal);
        super.setSize(width * 9, height * 17);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    // Get a Place to Teleport To
    public Displayable teleportTo() {
        ArrayList<Displayable> spaces = new ArrayList<Displayable>();
        Displayable space;
        char openSpace;
        for (int x = 0; x < objectGrid.length; x++) {
            for (int y = 0; y < objectGrid[0].length; y++) {
                space = getDisplayObject(x, y);
                openSpace = space.getChar();
                if (openSpace == '#' || openSpace == '.' || openSpace == '+') {
                    spaces.add(space);
                }
            }
        }
        return spaces.get(random.nextInt(spaces.size()));
    }

    // getObjectDisplayGrid
    public static ObjectDisplayGrid getObjectDisplayGrid(int gameHeight, int width, int topHeight) {
        System.out.println("Getting ObjectDisplayGrid:");
        if (odg == null) { // create dungeon with name, width, and gameHeight
            System.out.println("ObjectDisplayGrid is NULL, Creating:");
            odg = new ObjectDisplayGrid(gameHeight, width, topHeight);
            return odg;
        }
        return odg;
    }

    // set TopMessageHeight
    public void setTopMessageHeight(int topHeight) {
        System.out.println("set topHeight to: " + topHeight);
        this.topHeight = topHeight;
    }

    // Sample Code Functions Begins
    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                addDisplayObject(new Char(' '), i, j);
            }
        }
        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void writeToTerminal(int x, int y) {
        char ch = ((Displayable)objectGrid[x][y].peek()).getChar();
        terminal.write(ch, x, y);
        terminal.repaint();
    }
    // End of Sample Code Functions End

    //Getters for Functions:
    public int getGameHeight() {
        return gameHeight;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getTopMessageHeight(){
        return topHeight;
    }

    // Code for Adding Objects
    public void addDisplayObject(Displayable object, int posX, int posY) {
        if ((-1 < posX) && (posX < objectGrid.length)) {
            if ((-1 < posY) && (posY < objectGrid[0].length)) {
                object.setDisplayX(posX);
                object.setDisplayY(posY);
                objectGrid[posX][posY].push(object);
                writeToTerminal(posX, posY);
            }
        }
    }

    // Code for Adding Items
    public void addDisplayItem(Item item, int posX, int posY) {
        if ((0 <= posX) && (posX < objectGrid.length)) {
            if ((0 <= posY) && (posY < objectGrid[0].length)) {
                item.setDisplayX(posX);
                item.setDisplayY(posY);
                Displayable present = (Displayable) objectGrid[posX][posY].pop();
                objectGrid[posX][posY].push(item);
                objectGrid[posX][posY].push(present);
                writeToTerminal(posX, posY);
            }
        }
    }

    // Code for Removing Objects
    public Displayable removeDisplayObj(int posX, int posY) {
        if ((0 <= posX) && (posX < objectGrid.length)) {
            if ((0 <= posY) && (posY < objectGrid[0].length)) {
                if (objectGrid[posX][posY].size() > 1) {
                    Displayable remove = (Displayable) objectGrid[posX][posY].pop();
                    writeToTerminal(posX, posY);
                    return remove;
                }
            }
        }
        return new Char(' ');
    }

    // Code for Removing Items
    public Object removeDisplayItem(int posX, int posY) {
        Displayable present = null;
        Displayable remove = null;
        if ((0 <= posX) && (posX < objectGrid.length)) {
            if ((0 <= posY) && (posY < objectGrid[0].length)) {
                if (objectGrid[posX][posY].size() > 1) {
                    present = (Displayable) objectGrid[posX][posY].pop();
                    if (objectGrid[posX][posY].size() > 1) {
                        remove = (Displayable) objectGrid[posX][posY].pop();
                    }
                    objectGrid[posX][posY].push(present);
                    writeToTerminal(posX, posY);
                    return remove;
                }
            }
        }
        return new Char(' ');
    }

    // Displays Pack info
    public void viewPack(ArrayList<Item> pack) {
        String viewPack = "Pack: ";
        int size = pack.size();
        if (0 < size) {
            int i;
            for (i = 0; i < size - 1; i++) {
                viewPack = viewPack + (i + 1) + ". " + pack.get(i).getName() + ", ";
            }
            viewPack = viewPack + (i + 1) + ". " + pack.get(i).getName();
        }
        printLine(viewPack, getHeight() - 3); // Problem with testDrawing.xml???
    }

    public Displayable getDisplayObject(int posX, int posY) {
        if ((0 <= posX) && (posX < objectGrid.length)) {
            if ((0 <= posY) && (posY < objectGrid[0].length)) {
                return (Displayable)objectGrid[posX][posY].peek();
            }
        }
        return new Char('0');
    }

    // Getter for Display Item
    public Item getDisplayItem(int posX, int posY) {
        Item temp = null;
        if ((0 <= posX) && (posX < objectGrid.length)) {
            if ((0 <= posY) && (posY < objectGrid[0].length)) {
                if (objectGrid[posX][posY].size() > 1) {
                    Displayable curr = (Displayable) objectGrid[posX][posY].pop();
                    if (objectGrid[posX][posY].size() > 1) {
                        temp = (Item) objectGrid[posX][posY].peek();
                    }
                    objectGrid[posX][posY].push(curr);
                    writeToTerminal(posX, posY);
                }
            }
        }
        return temp;
    }

    // Displays a Single Line
    public void printLine(String string, int posY) {
        int pos;
        for (pos = 0; pos < string.length(); pos++) {
            removeDisplayObj(pos, posY);
            addDisplayObject(new Char(string.charAt(pos)), pos, posY);

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        while (pos < objectGrid.length) {
            removeDisplayObj(pos, posY);
            pos++;
        }
    }

    // Displays Info String
    public void displayInfo(String info) {
        String infoString = "Info: " + info;
        printLine(infoString, getHeight() - 1);
    }

    // Displays HP String
    public void displayHp(int hp) {
        String hpString = "HP: " + hp + " Core:  0";
        printLine(hpString, 0);
    }

    // HALLUCINATE
    public boolean isChar(char spaceChar) {
        for (Char chars : characters) {
            if( chars != null) {
                if (chars.getChar() == spaceChar) {
                    return true;
                }
            }
        }
        return false;
    }

    public void hallucinate() {
        for (Displayable displaySpaces : spaces) {

            int index = random.nextInt(characters.size());
            Char randChar = characters.get(index);
            char newChar = randChar.getChar();

            displaySpaces.setHallucinate(true);
            displaySpaces.setHallucinateChar(newChar);

            int displayX = displaySpaces.getDisplayX();
            int displayY = displaySpaces.getDisplayY();

            terminal.write(newChar, displayX, displayY);
            terminal.repaint();
        }
    }

    public void stopHallucination() {
        for (Displayable changeSpace : spaces) {

            changeSpace.setHallucinate(false);

            int displayX = changeSpace.getDisplayX();
            int displayY = changeSpace.getDisplayY();

            writeToTerminal(displayX, displayY);
        }
    }

    public void initializeSpaces() {
        Displayable currSpace = null;
        char newSpace;

        for (int x = 0; x < objectGrid.length; x++) {
            for (int y = 0; y < objectGrid[0].length; y++) {

                currSpace = getDisplayObject(x, y);
                newSpace = currSpace.getChar();

                if (isChar(newSpace)) {
                    this.spaces.add(currSpace);
                }
            }
        }
    }

    // END OF HALLUCINATION

    public void isGame(boolean val) {
        gameOver = val;
    }
}
