public class Cake extends Pastry {

    private String customMessage;

    public Cake(Integer id) {
        super(id,"Standard b-day cake",2.0, 100.0);
        this.setCustomMessage("Happy Birthday");
    }

    public Cake(Integer id, String customMessage) {
        super(id, "Custom b-day cake",2.0, 110.0);
        this.setCustomMessage(customMessage);
    }

    public Cake(Integer id, double weight, double value_for_kg, String customMessage) {
        super(id, "Special cake", weight, value_for_kg);
        this.setCustomMessage(customMessage);
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }
}
