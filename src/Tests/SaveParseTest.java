package Tests;
import bakery.*;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveParseTest {
    HashMap<Integer, Client> clients = new HashMap<Integer, Client>();
    HashMap<Integer, Pie> pies = new HashMap<Integer, Pie>();
    HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

    File clientsTestFile = new File("clientTest.txt");
    File piesTestFile = new File("piesTest.txt");
    File ordersTestFile = new File("ordersTest.txt");

    Parsers parsers = new Parsers();
    Savers savers = new Savers();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM d");

    private void prepare() {
        // ** Prepare clients

        for (int i = 0; i < 10; i++) {
            Client c = new Client(i, "Name" + i, "Surname" + i, "City" + i, "11-111", "Street" + i, i);
            this.clients.put(c.getId(), c);
        }
        // ** Prepare pies

        for (int i = 0; i < 10; i++) {
            Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * i, 23.2 * i);
            this.pies.put(p.getId(), p);
        }

        // ** Prepare orders

        for (int i = 0; i < 10; i++) {
            Order o = new Order(i, 2023, i+1, 5 + i, 2023, i + 2, 3 + i, this.clients.get(9 - i), false);
            for (int j = 0; j < 4; j++) {
                Cake cake = new Cake("My text" + j);
                o.addCakeToOrder(cake);
            }
            this.orders.put(o.getId(), o);
        }
    }

    @Test
    public void clientsSaveAndParseTest() {

        this.prepare();
        this.savers.saveClients(this.clientsTestFile, this.clients);
        HashMap<Integer, Client> output = this.parsers.parseClients(clientsTestFile);

        for (Map.Entry<Integer, Client> outputClient: output.entrySet()) {
            Client client = this.clients.get(outputClient.getValue().getId());
            Assert.assertEquals(client.getName(), outputClient.getValue().getName());
            Assert.assertEquals(client.getSurname(), outputClient.getValue().getSurname());
            Assert.assertEquals(client.getId(), outputClient.getValue().getId());
            Assert.assertEquals(client.getAddress().getCity(), outputClient.getValue().getAddress().getCity());
            Assert.assertEquals(client.getAddress().getStreet(), outputClient.getValue().getAddress().getStreet());
            Assert.assertEquals(client.getAddress().getPostalCode(), outputClient.getValue().getAddress().getPostalCode());
            Assert.assertEquals(client.getAddress().getHouseNumber(), outputClient.getValue().getAddress().getHouseNumber());
        }
    }

    @Test
    public void piesSaveAndParseTest() {

        this.prepare();
        this.savers.saveInventory(this.piesTestFile, this.pies);
        HashMap<Integer, Pie> output = this.parsers.parseInventory(piesTestFile);

        for (Map.Entry<Integer, Pie> outputPie: output.entrySet()) {
            Pie pie = this.pies.get(outputPie.getValue().getId());
            Assert.assertEquals(pie.getName(), outputPie.getValue().getName());
            Assert.assertEquals(pie.getId(), outputPie.getValue().getId());
            Assert.assertEquals(pie.getWeight(), outputPie.getValue().getWeight(), 0.1);
            Assert.assertEquals(pie.getValueForKg(), outputPie.getValue().getValueForKg(), 0.1);

        }
    }

    @Test
    public void ordersSaveAndParseTest() {

        this.prepare();
        this.savers.saveOrders(this.ordersTestFile, this.orders);
        HashMap<Integer, Order> output = this.parsers.parseOrders(ordersTestFile, this.clients);

        for (Map.Entry<Integer, Order> outputOrder: output.entrySet()) {
            Order order = this.orders.get(outputOrder.getValue().getId());
            Assert.assertEquals(order.getId(), outputOrder.getValue().getId());
            Assert.assertEquals(sdf.format(order.getDate_of_order().getTime()), sdf.format(outputOrder.getValue().getDate_of_order().getTime()));
            Assert.assertEquals(sdf.format(order.getDate_of_receipt().getTime()), sdf.format(outputOrder.getValue().getDate_of_receipt().getTime()));
            Assert.assertEquals(order.getTotal(), outputOrder.getValue().getTotal(), 0.1);
            Assert.assertEquals(order.getClient().getId(), outputOrder.getValue().getClient().getId());

            ArrayList<Cake> outputCakes = outputOrder.getValue().getCakes();
            ArrayList<Cake> cakes = order.getCakes();

            int index = 0;
            for (Cake c: outputCakes) {
                Cake orderedCake = cakes.get(index);

                Assert.assertEquals(c.getName(), orderedCake.getName());
                Assert.assertEquals(c.getWeight(), orderedCake.getWeight(), 0.1);
                Assert.assertEquals(c.getValueForKg(), orderedCake.getValueForKg(), 0.1);
                Assert.assertEquals(c.getCustomMessage(), orderedCake.getCustomMessage());

                index++;
            }
        }
    }
}



