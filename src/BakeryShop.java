import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class BakeryShop {

    private HashMap<Integer, Order> orders;
    private HashMap<Integer, Client> clients;
    private HashMap<Integer, Pie> inventory;

    private File clientsStorage;
    private File inventoryStorage;
    private File ordersStorage;

    private Savers savers;

    public BakeryShop(File clientsStorage, File inventoryStorage, File ordersStorage) {
        this.setOrders();
        this.setClients();
        this.setInventory();

        this.clientsStorage = clientsStorage;
        this.inventoryStorage = inventoryStorage;
        this.ordersStorage = ordersStorage;

        Parsers parsers = new Parsers();
        this.clients = parsers.parseClients(clientsStorage);
        this.inventory = parsers.parseInventory(inventoryStorage);
        this.orders = parsers.parseOrders(ordersStorage, this.clients);

        this.savers = new Savers();
    }

    public void setOrders() {
        if(this.orders == null) {
            this.orders = new HashMap<Integer, Order>();
        }
    }

    public HashMap<Integer, Order> getOrders() {
        return this.orders;
    }

    public void appendOrder(Order order) {
        this.orders.put(order.getId(), order);
    }

    public void saveOrders() {
        this.savers.saveOrders(this.ordersStorage, this.orders);
    }

    public void setClients() {
        if (this.clients == null) {
            this.clients = new HashMap<Integer, Client>();
        }
    }

    public HashMap<Integer, Client> getClients() {
        return clients;
    }

    public void appendClient(Client client) {
        this.clients.put(client.getId(), client);
    }

    public void saveClients() {
        this.savers.saveClients(this.clientsStorage, this.clients);
    }

    public void setInventory() {
        if (this.inventory == null) {
            this.inventory = new HashMap<Integer, Pie>();
        }
    }

    public HashMap<Integer, Pie> getInventory() {
        return this.inventory;
    }

    public void appendInventory(Pie pie) {
        this.inventory.put(pie.getId(), pie);
    }

    public void saveInventory() {
        this.savers.saveClients(this.clientsStorage, this.clients);
    }

    // *** ID generators ***

    public Integer generateInventoryID() {
        ArrayList<Integer> idList = new ArrayList<>(this.inventory.keySet());
        Collections.sort(idList);
        if(this.inventory.isEmpty()) {
            return 1;
        }else {
            return idList.get(idList.size() - 1) + 1;
        }
    }

    public Integer generateClientID() {
        ArrayList<Integer> idList = new ArrayList<>(this.clients.keySet());
        Collections.sort(idList);
        if(this.clients.isEmpty()) {
            return 1;
        }else {
            return idList.get(idList.size() - 1) + 1;
        }

    }

    public Integer generateOrderID() {
        ArrayList<Integer> idList = new ArrayList<>(this.orders.keySet());
        Collections.sort(idList);
        if(this.orders.isEmpty()) {
            return 1;
        }else {
            return idList.get(idList.size() - 1) + 1;
        }
    }

    // *** Add new objects ***

    public Integer addNewPie(String name, double weight, double value_for_kg, boolean isFitOffer, boolean isVegan) {
        Pie pie = new Pie(this.generateInventoryID(), name, weight, value_for_kg, isFitOffer, isVegan);
        this.appendInventory(pie);
        return pie.getId();
    }

    public Integer addNewClient(String name, String surname, String city, String postalCode, String street, int houseNumber) {
        Client client = new Client(this.generateClientID(), name, surname, city, postalCode, street, houseNumber);
        this.appendClient(client);
        return client.getId();
    }

    public Integer addNewOrder(int order_year, int order_month, int order_day, int receipt_year, int receipt_month, int receipt_day, Integer clientID, boolean paid) {
        Client client = this.clients.get(clientID);
        Order order = new Order(this.generateOrderID(), order_year, order_month, order_day, receipt_year, receipt_month, receipt_day, client, paid);
        this.appendOrder(order);
        return order.getId();
    }

    public void orderStandardCake(Integer orderID) {
        Order order = this.orders.get(orderID);
        order.addCakeToOrder(new Cake());

    }

    public void orderCustomCake(Integer orderID, String customMessage) {
        Order order = this.orders.get(orderID);
        order.addCakeToOrder(new Cake(customMessage));

    }

    public void orderSpecialCake(Integer orderID, double weight, double value_for_kg, String customMessage) {
        Order order = this.orders.get(orderID);
        order.addCakeToOrder(new Cake(weight, value_for_kg, customMessage));
    }

    // *** Storage getter, setter ***

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

    // *** SEARCH METHODS

    public HashMap<Integer, Pie> getOnlyFitPies() {
        HashMap<Integer, Pie> output = new HashMap<Integer, Pie>();
        for (Map.Entry<Integer, Pie> pie: this.inventory.entrySet()) {
               if(pie.getValue().getIsFitOffer()) {
                   output.put(pie.getKey(), pie.getValue());
               }
        }

        return output;
    }

    public HashMap<Integer, Pie> getOnlyVeganPies() {
        HashMap<Integer, Pie> output = new HashMap<Integer, Pie>();
        for (Map.Entry<Integer, Pie> pie: this.inventory.entrySet()) {
            if(pie.getValue().getIsVegan()) {
                output.put(pie.getKey(), pie.getValue());
            }
        }

        return output;
    }

    public HashMap<Integer, Order> getNonPaidOrders() {
        HashMap<Integer, Order> output = new HashMap<Integer, Order>();
        for (Map.Entry<Integer, Order> order: this.orders.entrySet()) {
            if(order.getValue().getPaid()) {
                output.put(order.getKey(), order.getValue());
            }
        }
        return output;
    }

    public HashMap<Integer, Order> getPaidOrders() {
        HashMap<Integer, Order> output = new HashMap<Integer, Order>();
        for (Map.Entry<Integer, Order> order: this.orders.entrySet()) {
            if(!order.getValue().getPaid()) {
                output.put(order.getKey(), order.getValue());
            }
        }
        return output;
    }

    public Order getOrderById(Integer id) {
        return this.orders.get(id);
    }

    public Pie getPieById(Integer id) {
        return this.inventory.get(id);
    }

    public HashMap<Integer, Order> getOrderByDate(int year, int month, int day) {
        HashMap<Integer, Order> output = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM d");
        Calendar searchingDate = new GregorianCalendar(year, month, day);
        String searchingDateString = sdf.format(searchingDate);
        for (Map.Entry<Integer, Order> order: this.orders.entrySet()) {
            if(searchingDateString.equals(sdf.format(order.getValue().getDate_of_order()))) {
                output.put(order.getValue().getId(), order.getValue());
            }
        }

        return output;
    }

    // ***

    public Receipt createNewReceipt() {
        return new Receipt();
    }

}

