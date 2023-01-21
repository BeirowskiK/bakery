package bakery;

import bakery.Address;
import bakery.Cake;
import bakery.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

public class Parsers {
    public HashMap<Integer, Client> parseClients(File file) {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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

    public HashMap<Integer, Order> parseOrders(File file, HashMap<Integer,Client> clients) {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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
                date_of_receipt.setTime(sdf.parse(fields[2]));


                Order order = new Order(Integer.parseInt(fields[0]), date_of_order, date_of_receipt,
                        clients.get(Integer.parseInt(fields[4])), cakesList, Boolean.parseBoolean(fields[5]));

                output.put(order.getId(), order);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
