package src;

public abstract class CreatureAction extends Action {

    private Creature owner;

    public CreatureAction(Creature _owner) {
        System.out.println("Creating CreatureAction: " + _owner);
        owner = _owner;
    }

    public Creature getOwner() {
        return owner;
    }

    abstract public void initialize(ObjectDisplayGrid object);
}
