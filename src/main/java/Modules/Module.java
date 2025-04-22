package Modules;

public class Module {
    private int id;
    private String name;
    private int coefficient;

    public Module() {}
    
    public Module(int id, String name, int coefficient) {
        this.id = id;
        this.name = name;
        this.coefficient = coefficient;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCoefficient() { return coefficient; }
    public void setCoefficient(int coefficient) { this.coefficient = coefficient; }
}