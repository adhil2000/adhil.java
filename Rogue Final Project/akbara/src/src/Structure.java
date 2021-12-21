package src;

public class Structure extends Displayable {
    private String name;

    public Structure() {
        System.out.println("Creating Structure");
    }

    public void setName(String name) {
        System.out.println("set name to: " + name);
        this.name = name;
    }

    public String getName() { return name; }
}