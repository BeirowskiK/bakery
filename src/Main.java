import java.io.File;
import java.io.IOException;
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
        Address a1 = new Address("Kartuzy", "83-300", "Gdańska", 52);
        Client c1 = new Client(5,"Kacper", "Polak", a1);
        bakery.addClient(c1);

        Address a2 = new Address("Gdańsk", "83-300", "Gdańska", 152);
        Client c2 = new Client(45,"Kacper", "Polak", a2);
        bakery.addClient(c2);

        Client c3 = new Client(445,"Kacper", "Polak", a1);
        bakery.addClient(c3);

        Client c4 = new Client(55,"Kacper", "Polak", a2);
        bakery.addClient(c1);
        bakery.addClient(c2);
        bakery.addClient(c3);
        bakery.addClient(c4);

        Cake cake = new Cake(5);
        Order order1 = new Order(1,new Date(2023,1,18),new Date(2023,1,20), cake, c3, false);

        bakery.addOrder(order1);
        order1.addCakeToOrder(cake);

        bakery.saveClients();
        bakery.saveOrders();
    }
}