package bakery;

import bakery.Cake;
import bakery.Client;
import bakery.Pie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Savers {
    public void saveClients(File clientsStorage, HashMap<Integer, Client> clients) {
        try {
            PrintStream clientsOutput =  new PrintStream(clientsStorage);

            clientsOutput.println("Client_ID\tClient_name\tClient_surname\tAddress_city\tAddress_postalCode\tAddress_street\tAddress_houseNumber");
            for (Map.Entry<Integer, Client> c: clients.entrySet()) {
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

    public void saveOrders(File ordersStorage, HashMap<Integer, Order> orders) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM d");
            PrintStream ordersOutput =  new PrintStream(ordersStorage);

            ordersOutput.println("Order_ID\tDate_of_order\tDate_of_receipt\t[Cake_name::Cake_weight::Cake_value_for_kg::Cake_customMessage;]\tClient_ID\tPaid\tTotal");
            for (Map.Entry<Integer, Order> o: orders.entrySet()) {
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

    public void saveInventory(File inventoryStorage, HashMap<Integer, Pie> inventory) {
        try {
            PrintStream inventoryOutput =  new PrintStream(inventoryStorage);

            inventoryOutput.println("Pie_ID\tPie_name\tPie_weight\tPie_value_for_kg");

            for (Map.Entry<Integer, Pie> p: inventory.entrySet()) {
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
}
