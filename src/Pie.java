public class Pie extends  Pastry{

    boolean isVegan;
    boolean isFitOffer;

    public Pie(Integer id, String name, double weight, double value_for_kg) {
        super(id, name, weight, value_for_kg);
        this.setIsVegan(false);
        this.setIsFitOffer(false);
    }

    public Pie(Integer id, String name, double weight, double value_for_kg, boolean isFitOffer, boolean isVegan) {
        super(id, name, weight, value_for_kg);
        this.setIsVegan(isVegan);
        this.setIsFitOffer(isFitOffer);
    }

    public void setIsVegan(boolean isVegan) {
        this.isVegan = isVegan;
    }

    public boolean getIsVegan() {
        return this.isVegan;
    }

    public void setIsFitOffer(boolean isFitOffer) {
        this.isFitOffer = isFitOffer;
    }

    public boolean getIsFitOffer() {
        return this.isFitOffer;
    }
}
