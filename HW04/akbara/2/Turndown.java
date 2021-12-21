public class Turndown implements Command {
    static int x = 0;
    public void executeCommand(int s) {
        System.out.println("Volume turned down to " + --x);
    }
}
