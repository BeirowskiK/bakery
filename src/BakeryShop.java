import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        this.clients = this.parseClients(clientsStorage);
        this.inventory = this.parseInventory(inventoryStorage);
        this.orders = this.parseOrders(ordersStorage);
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




    // *** EXPROT DATA TO FILE ***

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM d");
            PrintStream ordersOutput =  new PrintStream(this.ordersStorage);

            ordersOutput.println("Order_ID\tDate_of_order\tDate_of_receipt\t[Cake_name::Cake_weight::Cake_value_for_kg::Cake_customMessage;]\tClient_ID\tPaid\tTotal");
            for (Map.Entry<Integer, Order> o: this.orders.entrySet()) {
                ArrayList<Cake> cakes = o.getValue().getCakes();
                String cakesString = "";

                for (Cake cake: cakes) {
                    cakesString += cake.getName() + "::" +
                        cake.getWeight() + "::" +
                        cake.getValueForKg() + "::" +
                        cake.getCustomMessage() + ";";
                }

                String date_of_order = sdf.format(o.getValue().getDate_of_order().getTime());
                String date_of_receipt = sdf.format(o.getValue().getDate_of_receipt().getTime());

                ordersOutput.println(o.getValue().getId().toString() + "\t" +
                                date_of_order + "\t" +
                                date_of_receipt + "\t" +
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

    public void saveInventory() {
        try {
            PrintStream inventoryOutput =  new PrintStream(this.inventoryStorage);

            inventoryOutput.println("Pie_ID\tPie_name\tPie_weight\tPie_value_for_kg");

            for (Map.Entry<Integer, Pie> p: this.inventory.entrySet()) {
                inventoryOutput.println(
                        p.getValue().getId().toString() + "\t" +
                                p.getValue().getName() + "\t" +
                                p.getValue().getWeight() + "\t" +
                                p.getValue().getValueForKg()
                );
            }
            inventoryOutput.close();

        } catch(FileNotFoundException e) {
                e.printStackTrace();
        }
    }



    // *** PARSE DATA FROM FILE

    public HashMap<Integer, Client> parseClients(File file) {
        HashMap<Integer, Client> output = new HashMap<Integer, Client>();
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t");

                Address address = new Address(fields[3], fields[4], fields[5], Integer.parseInt(fields[6]));
                Client client = new Client(Integer.parseInt(fields[0]), fields[1], fields[2], address);

                output.put(client.getId(), client);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return output;
    }

    public HashMap<Integer, Pie> parseInventory(File file) {
        HashMap<Integer, Pie> output = new HashMap<Integer, Pie>();
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t");

                Pie pie = new Pie(Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));

                output.put(pie.getId(), pie);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return output;
    }

    public HashMap<Integer, Order> parseOrders(File file) {
        HashMap<Integer, Order> output = new HashMap<Integer, Order>();
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t");
                String[] cakes = fields[3].split(";");
                ArrayList<Cake> cakesList = new ArrayList<Cake>();


                for (String cake: cakes) {
                    String[] cakeProperty = cake.split("::");

                    if(cakeProperty[0].equals("Standard b-day cake")) {
                        Cake c = new Cake();
                        cakesList.add(c);
                    } else if (cakeProperty[0].equals("Custom b-day cake")) {
                        Cake c = new Cake(cakeProperty[3]);
                        cakesList.add(c);
                    }else if (cakeProperty[0].equals("Special cake")) {
                        Cake c = new Cake(Double.parseDouble(cakeProperty[1]), Double.parseDouble(cakeProperty[2]), cakeProperty[3]);
                        cakesList.add(c);
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM d");
                Calendar date_of_order = Calendar.getInstance();
                date_of_order.setTime(sdf.parse(fields[1]));

                Calendar date_of_receipt = Calendar.getInstance();
                date_of_order.setTime(sdf.parse(fields[2]));


                Order order = new Order(Integer.parseInt(fields[0]), date_of_order, date_of_receipt,
                        this.clients.get(Integer.parseInt(fields[4])), cakesList, Boolean.parseBoolean(fields[5]));

                output.put(order.getId(), order);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return output;
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


}

