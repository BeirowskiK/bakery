import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private Date date_of_order;
    private Date date_of_receipt;
    private ArrayList<Cake> cakes;
    private Client client;

    public Order(int id, Date date_of_order, Date date_of_receipt, Cake cake, Client client) {
        this.setId(id);
        this.setDate_of_order(date_of_order);
        this.setDate_of_receipt(date_of_receipt);
        this.setCakes();
        this.addCakeToOrder(cake);
        this.setClient(client);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
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
    }

    public ArrayList<Cake> searchOrder(int id) {
        ArrayList<Cake> outputArray = new ArrayList<Cake>();
        for (Cake cake: this.cakes) {
             if(cake.getId() == id) {
                 outputArray.add(cake);
             }
        }

        return outputArray;
    }
}
