package src;

public class Displayable {

    // DISPLAYABLE -----------------------------------------
    public Displayable() {
        //System.out.println("Creating Displayable");
    }

    // VISIBLE  --------------------------------------------
    private boolean visible;

    public void setInvisible() {
        System.out.println("invisible set");
        visible = false;
    }
    public void setVisible() {
        System.out.println("visible set");
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    // MAX HIT --------------------------------------------
    private int maxHit;

    public void setMaxHit(int maxHit) {
        System.out.println("set MaxHit to: " + maxHit);
        this.maxHit = maxHit;
    }

    public int getMaxHit() {
        return maxHit;
    }

    // HP --------------------------------------------
    private int hpMoves;
    private int hp;

    public void setHpMoves(int hpMoves) {
        System.out.println("set HpMoves to: " + hpMoves);
        this.hpMoves = hpMoves;
    }
    public void setHp(int hp) {
        System.out.println("set Hp to: " + hp);
        this.hp = hp;
    }

    public int getHpMoves() {
        return hpMoves;
    }
    public int getHp() {
        return hp;
    }

    // TYPES  --------------------------------------------
    private char type;

    public void setType(char type) {
        System.out.println("set type to: " + type);
        this.type = type;
    }

    public char getType() {
        return type;
    }

    // VALUE  --------------------------------------------
    private int value;

    public void setValue(int value) {
        System.out.println("set value to: " + value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // POS XY --------------------------------------------
    private int posX;
    private int posY;

    public void setPosX(int posX) {
        System.out.println("set PosX to: " + posX);
        this.posX = posX;
    }
    public void setPosY(int posY) {
        System.out.println("set PosY to: " + posY);
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

    // WIDTH, HEIGHT ---------------------------------------
    private int width;
    private int height;

    public void setWidth(int width) {
        System.out.println("set width to: " + width);
        this.width = width;
    }
    public void setHeight(int height) {
        System.out.println("set height to: " + height);
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // Display XY -------------------------------------------
    private int displayX;
    private int displayY;

    public void setDisplayX(int dispPosX) {
        this.displayX = dispPosX;
    }
    public void setDisplayY(int dispPosY) {
        this.displayY = dispPosY;
    }

    public int getDisplayX() {
        return displayX;
    }
    public int getDisplayY() {
        return displayY;
    }

    // HALLUCINATE -------------------------------------------
    private boolean hallucinate = false;
    private char hallucinateChar = 'X';

    public void setHallucinate(boolean val) {
        hallucinate = val;
    }
    public void setHallucinateChar(char character) {
        hallucinateChar = character;
    }

    public char getHallucinateChar() {
        return hallucinateChar;
    }

    // CHAR ------------------------------------------------
    public char getChar() {
        return (hallucinate) ? getHallucinateChar() : getType();
    }
}
