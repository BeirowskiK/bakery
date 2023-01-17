public abstract class Pastry {
    private int id;
    private String name;
    private double weight;
    private double value_for_kg;

    public Pastry(int id, String name, double weight, double value_for_kg) {
        this.setId(id);
        this.setName(name);
        this.setWeight(weight);
        this.setValueForKg(value_for_kg);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setValueForKg(double value_for_kg) {
        this.value_for_kg = value_for_kg;
    }

    public double getValueForKg() {
        return this.value_for_kg;
    }
}
