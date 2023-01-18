import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class BakeryShop {

    private HashMap<Integer, Order> orders;
    private HashMap<Integer, Client> clients;

    private HashMap<Integer, Pie> inventory;

    private File clientsStorage;
    private File inventoryStorage;
    private File ordersStorage;

    public BakeryShop(File clientsStorage, File inventoryStorage, File ordersStorage) {
        this.setOrders();
        this.setClients();
        this.setInventory();
        this.clientsStorage = clientsStorage;
        this.inventoryStorage = inventoryStorage;
        this.ordersStorage = ordersStorage;
    }

    public void setOrders() {
        if(this.orders == null) {
            this.orders = new HashMap<Integer, Order>();
        }
    }

    public HashMap<Integer, Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order order) {
        this.orders.put(order.getId(), order);
    }

    public void setClients() {
        if (this.clients == null) {
            this.clients = new HashMap<Integer, Client>();
        }
    }

    public HashMap<Integer, Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        this.clients.put(client.getId(), client);
    }

    public void setInventory() {
        if (this.inventory == null) {
            this.inventory = new HashMap<Integer, Pie>();
        }

    }

    public HashMap<Integer, Pie> getInventory() {
        return this.inventory;
    }

    public void setClientsStorage(File file) {
        this.clientsStorage = file;
    }

    public File getClientsStorage() {
        return this.clientsStorage;
    }

    public void setOrdersStorage(File file) {
        this.ordersStorage = file;
    }

    public File getOrdersStorage() {
        return this.ordersStorage;
    }

    public void setInventoryStorage(File file) {
        this.inventoryStorage = file;
    }

    public File getInventoryStorage() {
        return this.inventoryStorage;
    }

    public void saveClients() {
        try {
            PrintStream clientsOutput =  new PrintStream(this.clientsStorage);

            clientsOutput.println("Client_ID\tClient_name\tClient_surname\tAddress_city\tAddress_postalCode\tAddress_street\tAddress_houseNumber");
            for (Map.Entry<Integer, Client> c: this.clients.entrySet()) {
                clientsOutput.println(c.getValue().getId().toString() + "\t" +
                        c.getValue().getName() + "\t" +
                        c.getValue().getSurname() + "\t" +
                        c.getValue().getAddress().getCity() + "\t" +
                        c.getValue().getAddress().getPostalCode() + "\t" +
                        c.getValue().getAddress().getStreet() + "\t" +
                        c.getValue().getAddress().getHouseNumber());
            }
            clientsOutput.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveOrders() {
        try {
            PrintStream ordersOutput =  new PrintStream(this.ordersStorage);

            ordersOutput.println("Order_ID\tDate_of_order\tDate_of_receipt\t[Cake_ID:Cake_name:Cake_weight:Cake_value_for_kg:Cake_customMessage;]\tClient_ID\tPaid\tTotal");
            for (Map.Entry<Integer, Order> o: this.orders.entrySet()) {
                ArrayList<Cake> cakes = o.getValue().getCakes();
                String cakesString = "";

                for (Cake cake: cakes) {
                    cakesString += cake.getId().toString() + ":" +
                        cake.getName() + ":" +
                        cake.getWeight() + ":" +
                        cake.getValueForKg() + ":" +
                        cake.getCustomMessage() + ";";
                }

                ordersOutput.println(o.getValue().getId().toString() + "\t" +
                                o.getValue().getDate_of_order().toString() + "\t" +
                                o.getValue().getDate_of_order().toString() + "\t" +
                                o.getValue().getDate_of_receipt().toString() + "\t" +
                                cakesString + "\t" +
                                o.getValue().getClient().getId() + "\t" +
                                o.getValue().getPaid() + "\t" +
                                o.getValue().getTotal());
            }
            ordersOutput.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
