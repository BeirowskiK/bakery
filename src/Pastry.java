public abstract class Pastry {
    private String name;
    private double weight;
    private double value_for_kg;

    public Pastry(String name, double weight, double value_for_kg) {
        this.setName(name);
        this.setWeight(weight);
        this.setValueForKg(value_for_kg);
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
