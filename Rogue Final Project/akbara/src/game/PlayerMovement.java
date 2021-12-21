package game;

import src.*;

import java.util.*;
import java.util.concurrent.*;

public class PlayerMovement implements InputObserver, MovementSubject, Runnable {

    private int posX;
    private int posY;

    private boolean isDead = false; // is Dead
    private boolean isHelping = false; // Press H
    private boolean isReading = false; // Press R
    private boolean isTaking = false; // Press T
    private boolean isWearing = false; // Press W

    private static Queue<Character> queue = null;
    private ObjectDisplayGrid displayGrid;
    private Player player;
    private ArrayList<MovementObserver> movementObservers = new ArrayList<MovementObserver>();

    public PlayerMovement(ObjectDisplayGrid grid, int _posX, int _posY, Player _player) {
        queue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        posX = _posX;
        posY = _posY;
        player = _player;
        registerMovementObserver(player);
    }

    @Override
    public void observerUpdate(char ch) {
        queue.add(ch);
    }

    @Override
    public void registerMovementObserver(MovementObserver observer) {
        movementObservers.add(observer);
    }

    // Notify MoveObservers
    private void notifyMoveObservers(int moves) {
        for (MovementObserver observer : movementObservers) {
            if(observer != null) {
                observer.observerUpdate(moves);
            }
        }
    }

    // Process Input
    private boolean processInput() {
        char input;
        boolean processedInput = true;
        while (processedInput) {
            if (queue.peek() == null) {
                processedInput = false;
            } else {
                input = queue.poll();
                if (isDead) {
                    System.out.println("GAME OVER, PLAYER DIED");
                    return false;
                } else {
                    // MOVEMENT KEYS ------------------------------------------------------------------------
                    if (input == 'h' && posX > 0) {
                        moveTo(posX - 1, posY);
                    } else if (input == 'l' && posX < displayGrid.getWidth() - 1) {
                        moveTo(posX + 1, posY);
                    } else if (input == 'j' && posY < displayGrid.getHeight() - 1) {
                        moveTo(posX, posY + 1);
                    } else if (input == 'k' && posY > displayGrid.getTopMessageHeight()) {
                        moveTo(posX, posY - 1);
                        // ITEM KEYS ------------------------------------------------------------------------
                    } else if (isTaking) {
                        isTaking = isTaking(input);
                        // IF WEARING ------------------------------------------------------------------------
                    } else if (isWearing) {
                        isWearing = isWearing(input);
                        // IF READING  ------------------------------------------------------------------------
                    } else if (isReading) {
                        isReading = isReading(input);
                        // IF TAKING ------------------------------------------------------------------------
                    } else if (input == 'p') {
                        pickUp();
                    } else if (input == 'd') {
                        displayGrid.viewPack(player.getPack());
                        displayGrid.displayInfo("Enter # of the Item to Drop: ");
                        // INVENTORY KEYS ------------------------------------------------------------------------
                    } else if (input == 'i') {
                        displayGrid.viewPack(player.getPack());
                        if (player.emptyPack()) {
                            displayGrid.displayInfo("Pack is Empty!");
                        } else {
                            displayGrid.displayInfo("Pack is Displayed");
                        }
                    } else if (48 <= input && input <= 57) {
                        displayGrid.displayInfo("");
                        drop(input - 49);
                        // Change Armor ------------------------------------------------------------------------
                    } else if (input == 'c') {
                        takeOffArmor();
                        // Read Scroll ------------------------------------------------------------------------
                    } else if (input == 'r') {
                        displayGrid.displayInfo("Enter the # of the Scroll to Read");
                        isReading = true;
                        // Wield Sword ------------------------------------------------------------------------
                    } else if (input == 'T') {
                        displayGrid.displayInfo("Enter the # of the Sword to Wield");
                        isTaking = true;
                        // Wear Armor ------------------------------------------------------------------------
                    } else if (input == 'w') {
                        displayGrid.displayInfo("Enter the # of the Armor to Wear");
                        isWearing = true;
                        // ENDGAME ------------------------------------------------------------------------
                    } else if (input == 'E') {
                        System.out.println("GAME IS ENDING");
                        displayGrid.displayInfo("You Ended the Game by Pressing E");
                        return false;
                        // LEARNING KEYS ------------------------------------------------------------------------
                    } else if (input == '?') {
                        displayGrid.displayInfo("h,l,k,j,i,?,H,c,d,p,R,T,w,E,0-9. H <cmd> for more in");
                    } else if (input == 'H') {
                        displayGrid.displayInfo("Enter the key you want to learn");
                        isHelping = true;
                    } else {
                        // 'H' KEY SELECTED ------------------------------------------------------------------
                        if (isHelping) {
                            isHelping = isHelping(input);
                        }
                    }
                    // Print Key Pressed
                    System.out.println("You Pressed " + input);
                }
            }
        }
        return true;
    }

    boolean isHelping( char input) {
        switch (input) {
            case ('h'):
                displayGrid.displayInfo("h: Move Left");
            case ('l'):
                displayGrid.displayInfo("l: Move Right");
            case ('j'):
                displayGrid.displayInfo("j: Move Down");
            case ('k'):
                displayGrid.displayInfo("k: Move Up");
            case ('p'):
                displayGrid.displayInfo("p: Pick up an Item");
            case ('d'):
                displayGrid.displayInfo("d: Drop an Item");
            case ('i'):
                displayGrid.displayInfo("i: Display Inventory");
            case ('c'):
                displayGrid.displayInfo("c: Change/Remove Armor");
            case ('r'):
                displayGrid.displayInfo("r: Read Scroll");
            case ('T'):
                displayGrid.displayInfo("T: Wield Sword");
            case ('w'):
                displayGrid.displayInfo("w: Wear Armor");
            case ('E'):
                displayGrid.displayInfo("E: End Game");
            case ('?'):
                displayGrid.displayInfo("?: Show Commands");
            case ('H'):
                displayGrid.displayInfo("H: Show Command Info");
        }
        return false;
    }

    boolean isReading(char input) {
        if (48 <= input && input <= 57) {
            if (player.readScroll(input - 49, displayGrid, this)) {
                displayGrid.displayInfo("Scroll Activated");
            } else {
                displayGrid.displayInfo("Item is NOT a Scroll");
            }
        }
        return false;
    }

    boolean isWearing(char input) {
        if (48 <= input && input <= 57) {
            if (player.setArmor(input - 49)) {
                displayGrid.displayInfo("Armor Equipped");
            } else {
                displayGrid.displayInfo("Item is NOT an Armor");
            }
        }
        return false;
    }

    boolean isTaking(char input) {
        if (48 <= input && input <= 57) {
            if (player.setWeapon(input - 49)) {
                displayGrid.displayInfo("Sword Equipped");
            } else {
                displayGrid.displayInfo("Item is NOT a Sword");
            }
        }
        return false;
    }
    // Press 'c' change/remove Armor
    private void takeOffArmor() {
        if (player.swapArmor()) {
            displayGrid.displayInfo("Armor is Taken Off");
        } else {
            displayGrid.displayInfo("Armor is NOT Currently being Worn");
        }
    }

    // attack
    public void attack(Player player, Monster monster) {
        // Player attacks monster
        int PlayHit = player.attack(monster, displayGrid);

        if (monster.getHp() < 1 && monster.isAlive()) {

            displayGrid.displayInfo("Player KILLED Monster, DAMAGE: " + PlayHit);
            monster.setAlive(false);

            int monsterX = monster.getDisplayX();
            int monsterY = monster.getDisplayY();

            if (monster.getMaxHit() != 10) {
                displayGrid.removeDisplayObj(monsterX, monsterY);
            }
        } else if (monster.isAlive()) {
            displayGrid.displayInfo("Player ATTACKED Monster, DAMAGE: " + PlayHit);

            // TA Suggestion
            try {
                Thread.sleep(500); // Perfect
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Monster attacks player
            int MonHit = monster.attack(player, displayGrid);

            if (player.getHp() < 1) {
                displayGrid.displayInfo("Monster KILLED Player, DAMAGE: " + MonHit);
                player.setAlive(false);
                displayGrid.displayInfo("Player is killed!");

                int playerX = player.getDisplayX();
                int playerY = player.getDisplayY();
                displayGrid.removeDisplayObj(playerX, playerY);

                isDead = true;
            } else {
                displayGrid.displayInfo("Monster ATTACKED Player, DAMAGE: " + MonHit);
            }
        }
        displayGrid.displayHp(player.getHp());
    }

    // Allows Player Movement
    public void moveTo(int tempX, int tempY) {
        Object newPos = displayGrid.getDisplayObject(tempX, tempY);
        char newChar = ((Displayable) newPos).getChar();
        notifyMoveObservers(1);
        // ATTACK MONSTER
        if (newPos instanceof Monster) {
            attack((Player)displayGrid.getDisplayObject(posX, posY), (Monster)newPos);
        } else if (newChar != 'X') { // Stops from Wall
            if (newChar != ' ') {

                displayGrid.displayHp(player.getHp());
                displayGrid.removeDisplayObj(posX, posY);
                displayGrid.addDisplayObject(player, tempX, tempY);

                posX = tempX;
                posY = tempY;
            }
        }
    }

    // Allows Player to Pickup Items
    private void pickUp() {
        int posX = player.getDisplayX();
        int posY = player.getDisplayY();
        if (displayGrid.getDisplayItem(posX, posY) instanceof Item) {
            Item item = (Item) displayGrid.removeDisplayItem(posX, posY);
            if (player.addPack(item) == -1) { // if can't pick up because pack is full
                displayGrid.displayInfo("Pack full!");
            }
        }
    }

    // Allows Player to Drop Items
    private void drop(int i) {
        int posX = player.getDisplayX();
        int posY = player.getDisplayY();
        Item item = player.removePack(i);

        // "Drop an item outside the range of pack items."
        if (item != null) {
            displayGrid.addDisplayItem(item, posX, posY);
        }
        else {
            displayGrid.displayInfo("No Item Found");
        }
    }

    // run
    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            working = (processInput( ));
        }
    }
}
