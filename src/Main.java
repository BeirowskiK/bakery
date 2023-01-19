import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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
        Integer clientID = bakery.addNewClient("Kacper", "Test", "Kartuzy", "83-300", "Gdańska", 143);
        bakery.addNewClient("Kamil", "Test", "Kartuzy", "83-300", "Gdańska", 143);
        bakery.addNewClient("Marek", "Test", "Kartuzy", "83-300", "Gdańska", 143);
        bakery.addNewClient("Anna", "Test", "Kartuzy", "83-300", "Gdańska", 143);

        Integer orderID = bakery.addNewOrder(2023,1,13, 2023,1,24, clientID, true);
        bakery.orderCustomCake(orderID, "Mój napis na torcie");
        bakery.orderCustomCake(orderID, "Mój napis na torcie2");
        bakery.orderCustomCake(orderID, "Mój napis na torcie3");
        bakery.addNewPie("Apple Pie", 0.4, 25.0, true, false);
        bakery.saveOrders();
        bakery.saveClients();
        bakery.saveInventory();


//        Address a2 = new Address("Gdańsk", "83-300", "Gdańska", 152);
//        Client c2 = new Client(45,"Kacper", "Polak", a2);
//        bakery.appendClient(c2);
//
//        Client c3 = new Client(445,"Kacper", "Polak", a1);
//        bakery.appendClient(c3);
//
//        Client c4 = new Client(55,"Kacper", "Polak", a2);
//        bakery.appendClient(c1);
//        bakery.appendClient(c2);
//        bakery.appendClient(c3);
//        bakery.appendClient(c4);

//        Cake cake = new Cake(5);
        //Order order1 = new Order(1,new Date(2023,1,18),new Date(2023,1,20), cake, c3, false);

       // bakery.appendOrder(order1);
        //order1.addCakeToOrder(cake);

        //bakery.saveClients();
        //bakery.saveOrders();
    }
}