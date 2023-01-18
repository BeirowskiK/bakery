import java.util.ArrayList;
import java.util.Date;

public class Order {
    Integer id;
    private Date date_of_order;
    private Date date_of_receipt;
    private ArrayList<Cake> cakes;
    private Client client;

    private boolean paid;
    private double total;

    public Order(Integer id, Date date_of_order, Date date_of_receipt, Cake cake, Client client, boolean paid) {
        this.setId(id);
        this.setDate_of_order(date_of_order);
        this.setDate_of_receipt(date_of_receipt);
        this.setCakes();
        this.addCakeToOrder(cake);
        this.setClient(client);
        this.setPaid(paid);
        this.total = this.calculate();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }

    public Date getDate_of_order() {
        return this.date_of_order;
    }

    public void setDate_of_receipt(Date date_of_receipt) {
        this.date_of_receipt = date_of_receipt;
    }

    public Date getDate_of_receipt() {
        return this.date_of_receipt;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setCakes() {
        this.cakes = new ArrayList<Cake>();
    }

    public ArrayList<Cake> getCakes() {
        return this.cakes;
    }

    public void addCakeToOrder(Cake cake) {
        this.cakes.add(cake);
        double total = this.calculate();
        this.setTotal(total);
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean getPaid() {
        return this.paid;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return this.total;
    }

    public double calculate() {
        double sum = 0;
        for (Cake c: this.cakes) {
            sum += c.getWeight() * c.getValueForKg();
        }
        return sum;
    }
}
