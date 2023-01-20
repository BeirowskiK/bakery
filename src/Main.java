import bakery.BakeryShop;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        File clients = new File("clients.txt");
        File inventory = new File("inventory.txt");
        File orders = new File("orders.txt");

        if(!clients.exists()) {
            try {
                clients.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        if(!inventory.exists()) {
            try {
                inventory.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        if(!orders.exists()) {
            try {
                orders.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        BakeryShop bakery = new BakeryShop(clients, inventory, orders);

    }
}