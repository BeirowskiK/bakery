package bakery;

public final class Cake extends Pastry {

    private String customMessage;

    public Cake() {
        super("Standard b-day cake",2.0, 100.0);
        this.setCustomMessage("Happy Birthday");
    }

    public Cake(String customMessage) {
        super( "Custom b-day cake",2.0, 110.0);
        this.setCustomMessage(customMessage);
    }

    public Cake( double weight, double value_for_kg, String customMessage) {
        super("Special cake", weight, value_for_kg);
        this.setCustomMessage(customMessage);
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }
}
