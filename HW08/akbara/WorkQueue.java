import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WorkQueue implements Runnable{

    private static Queue<Command> inputQueue = new ConcurrentLinkedQueue<>();

    public synchronized void put(Command command){
        inputQueue.add(command);
    }

    public synchronized Command get(){
        return inputQueue.poll();
    }

    @Override
    public void run() {
        boolean working = true;
        while(working){
            Command command = get();
            if (command != null) {
                command.execute();
            } else {
                working = false;
            }
        }
    }
}
