public class Remote {

    private static Remote uniqueInstance = null;
    private Command[] slots;

    // Constructor, Restricted to this class itself
    private Remote() {
        slots = new Command[5];
    }

    // Static method to create instance of Remote Class
    public static Remote buildRemote() {
        if (uniqueInstance == null) {
            uniqueInstance = new Remote();
        }
        return uniqueInstance;
    }
    //--------------------------------------------------
    public void addCommand(int s, Command command) {
    slots[s] = command;
    }

    public void removeCommand(int s) {
        if (s > 4) {
            System.out.println("Remote has slots 0..4, you've asked to remove a command from slot " + s);
        }
        else
        {
            if (slots[s] == null) {
                System.out.println("Remote slot " + s + " does not have a command");
            }
            else {
                slots[s] = null;
            }
        }
    }

    public void executeCommand(int s) {
        if (s > 4) {
            System.out.println("Remote has slots 0..4, cannot execute a command in slot " + s);
        }
        else
        {
            if (slots[s] == null) {
                System.out.println("Remote slot " + s + " does not have a command to execute");
            }
            else {
                slots[s].executeCommand(s);
            }
        }
    }

    public String toString() {
        String zero = "no command";
        String one = "no command";
        String two = "no command";
        String three = "no command";
        String four = "no command";
        if(slots[0] != null) {
            zero = slots[0].getClass().getName() + " command";
        }
        if(slots[1] != null) {
            one = slots[1].getClass().getName() + " command";
        }
        if(slots[2] != null) {
            two = slots[2].getClass().getName() + " command";
        }
        if(slots[3] != null) {
            three = slots[3].getClass().getName() + " command";
        }
        if(slots[4] != null) {
            four = slots[4].getClass().getName() + " command";
        }
        return    "Remote:" +
                  "\nslot 0 contains " + zero
                + "\nslot 1 contains " + one
                + "\nslot 2 contains " + two
                + "\nslot 3 contains " + three
                + "\nslot 4 contains " + four + "\n";
    }
}

abstract class Command {
    public static int x = 0;
    public abstract void executeCommand(int s);
}



