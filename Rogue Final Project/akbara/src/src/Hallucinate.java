package src;
import game.*;

public class Hallucinate extends ItemAction implements MovementObserver {
    private int count = 0;
    private ObjectDisplayGrid object = null;
    private PlayerMovement movement = null;

    public Hallucinate(Item _owner) {
        super(_owner);
        System.out.println("Hallucination in Progress: " + _owner);
    }

    @Override
    public void observerUpdate(int moves) {
        count += moves;
        int pos = getIntValue();
        if(count <= pos) {
            if(object != null) {
                object.hallucinate();
            }
        } else if (movement != null) {
            object.stopHallucination();
        }
    }

    public ObjectDisplayGrid getHallucinateObject() { return object; }

    @Override
    public void initialize(Player player, ObjectDisplayGrid _object, PlayerMovement _movement) {
        object = _object;
        movement = _movement;
        movement.registerMovementObserver(this);
        object.initializeSpaces();
    }

    public int getCount() { return count; }
}
