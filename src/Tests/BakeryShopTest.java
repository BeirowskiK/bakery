package Tests;
import bakery.*;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class BakeryShopTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM d");
    File clients = new File("clientTest.txt");
    File inventory = new File("piesTest.txt");
    File orders = new File("ordersTest.txt");
    BakeryShop bs = new BakeryShop(clients, inventory, orders);


    @Test
    public void appendClientTest() {
        for (int i = 0; i < 10; i++) {
            Client c = new Client(i, "Name" + i, "Surname" + i, "City" + i, "11-111", "Street" + i, i);
            bs.appendClient(c);
        }

        for (int i = 0; i < 10; i++) {
            Client c = new Client(i, "Name" + i, "Surname" + i, "City" + i, "11-111", "Street" + i, i);

            Assert.assertEquals(bs.getClients().get(i).getName(), c.getName());
            Assert.assertEquals(bs.getClients().get(i).getSurname(), c.getSurname());
            Assert.assertEquals(bs.getClients().get(i).getId(), c.getId());
            Assert.assertEquals(bs.getClients().get(i).getAddress().getCity(), c.getAddress().getCity());
            Assert.assertEquals(bs.getClients().get(i).getAddress().getPostalCode(), c.getAddress().getPostalCode());
            Assert.assertEquals(bs.getClients().get(i).getAddress().getStreet(), c.getAddress().getStreet());
            Assert.assertEquals(bs.getClients().get(i).getAddress().getHouseNumber(), c.getAddress().getHouseNumber());
        }
    }

    @Test
    public void appendInventoryTest() {
        for (int i = 0; i < 10; i++) {
            Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * i, 23.2 * i);
            bs.appendInventory(p);
        }

        HashMap<Integer, Pie> bsInventory = bs.getInventory();

        for (int i = 0; i < 10; i++) {
            Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * i, 23.2 * i);

            Assert.assertEquals(bs.getInventory().get(i).getName(), p.getName());
            Assert.assertEquals(bs.getInventory().get(i).getWeight(), p.getWeight(), 0.1);
            Assert.assertEquals(bs.getInventory().get(i).getValueForKg(), p.getValueForKg(), 0.1);
            Assert.assertEquals(bs.getInventory().get(i).getId(), p.getId(), 0.1);

        }
    }

    @Test
    public void appendOrderTest() {
        for (int i = 0; i < 10; i++) {
            Order o = new Order(i, 2023, i+1, 5 + i, 2023, i + 2, 3 + i, bs.getClients().get(9 - i), false);
            for (int j = 0; j < 4; j++) {
                Cake cake = new Cake("My text" + j);
                o.addCakeToOrder(cake);
            }
           bs.appendOrder(o);
        }

        HashMap<Integer, Order> bsOrders = bs.getOrders();

        for (int i = 0; i < 10; i++) {
            Order o = new Order(i, 2023, i+1, 5 + i, 2023, i + 2, 3 + i, bs.getClients().get(9 - i), false);

            Assert.assertEquals(bsOrders.get(i).getPaid(), o.getPaid());
            Assert.assertEquals(bsOrders.get(i).getId(), o.getId());
            Assert.assertEquals(this.sdf.format(bsOrders.get(i).getDate_of_order().getTime()), this.sdf.format(o.getDate_of_order().getTime()));
            Assert.assertEquals(this.sdf.format(bsOrders.get(i).getDate_of_receipt().getTime()), this.sdf.format(o.getDate_of_receipt().getTime()));
        }
    }

    @Test
    public void generateClientIdTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 15;
        for (int i = 0; i < n; i++) {
            Client c = new Client(i, "Name" + i, "Surname" + i, "City" + i, "11-111", "Street" + i, i);
            bakeryShop.appendClient(c);
        }

        Assert.assertEquals(n, bakeryShop.generateClientID().intValue());
    }

    @Test
    public void generateInventoryIdTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 10;
        for (int i = 0; i < n; i++) {
            Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * i, 23.2 * i);
            bakeryShop.appendInventory(p);
        }

        Assert.assertEquals(n, bakeryShop.generateInventoryID().intValue());
    }

    @Test
    public void generateOrderIdTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 12;
        for (int i = 0; i < n; i++) {
            Order o = new Order(i, 2023, i+1, 5 + i, 2023, i + 2, 3 + i, bs.getClients().get(9 - i), false);
            for (int j = 0; j < 4; j++) {
                Cake cake = new Cake("My text" + j);
                o.addCakeToOrder(cake);
            }
            bakeryShop.appendOrder(o);
        }

        Assert.assertEquals(n, bakeryShop.generateOrderID().intValue());
    }

    @Test
    public void getOnlyFitPiesTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 11;
        for (int i = 0; i < n; i++) {
            Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * i, 23.2 * i, i%2 == 0, false);
            bakeryShop.appendInventory(p);
        }

        HashMap<Integer, Pie> fitOutput = bakeryShop.getOnlyFitPies();
        Double expectedSize = n/2.0;

        for (Map.Entry<Integer, Pie> fitPie: fitOutput.entrySet()) {
            Assert.assertTrue(fitPie.getValue().getIsFitOffer());
            Assert.assertEquals(Math.ceil(expectedSize), fitOutput.size(), 0.0);
        }
    }

    @Test
    public void getOnlyVeganTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 10;
        for (int i = 0; i < n; i++) {
            Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * i, 23.2 * i, false, i%2 == 0);
            bakeryShop.appendInventory(p);
        }

        Double expectedSize = n/2.0;
        HashMap<Integer, Pie> veganOutput = bakeryShop.getOnlyVeganPies();

        for (Map.Entry<Integer, Pie> fitPie: veganOutput.entrySet()) {
            Assert.assertTrue(fitPie.getValue().getIsVegan());
            Assert.assertEquals(Math.ceil(expectedSize), veganOutput.size(), 0.0);
        }
    }

    @Test
    public void getNonPaidOrdersTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 12;
        for (int i = 0; i < n; i++) {
            Order o = new Order(i, 2023, i+1, 5 + i, 2023, i + 2, 3 + i, bs.getClients().get(9 - i), (i%2==0));
            for (int j = 0; j < 4; j++) {
                Cake cake = new Cake("My text" + j);
                o.addCakeToOrder(cake);
            }
            bakeryShop.appendOrder(o);
        }

        Double expectedSize = n/2.0;
        HashMap<Integer, Order> nonPaidOrdersOutput = bakeryShop.getNonPaidOrders();

        for (Map.Entry<Integer, Order> npOrder: nonPaidOrdersOutput.entrySet()) {
            Assert.assertTrue(npOrder.getValue().getPaid());
            Assert.assertEquals(Math.ceil(expectedSize), nonPaidOrdersOutput.size(), 0.0);
        }
    }

    @Test
    public void getPaidOrdersTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 12;
        for (int i = 0; i < n; i++) {
            Order o = new Order(i, 2023, i+1, 5 + i, 2023, i + 2, 3 + i, bs.getClients().get(9 - i), (i%2==0));
            for (int j = 0; j < 4; j++) {
                Cake cake = new Cake("My text" + j);
                o.addCakeToOrder(cake);
            }
            bakeryShop.appendOrder(o);
        }

        Double expectedSize = n/2.0;
        HashMap<Integer, Order> paidOrdersOutput = bakeryShop.getPaidOrders();

        for (Map.Entry<Integer, Order> pOrder: paidOrdersOutput.entrySet()) {
            Assert.assertFalse(pOrder.getValue().getPaid());
            Assert.assertEquals(Math.ceil(expectedSize), paidOrdersOutput.size(), 0.0);
        }
    }

    @Test
    public void getPieByIdTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);
        int n = 13;
        for (int i = 0; i < n; i++) {
            Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * i, 23.2 * i, i%2 == 0, false);
            bakeryShop.appendInventory(p);
        }

        Integer id = 7;
        Pie pieOutput = bakeryShop.getPieById(id);

        Assert.assertEquals(id, pieOutput.getId());
        Assert.assertEquals("Name7 Pie", pieOutput.getName());
        Assert.assertEquals(2.0 * id, pieOutput.getWeight(), 0.0);
        Assert.assertEquals(23.2 * id, pieOutput.getValueForKg(), 0.0);
        Assert.assertEquals(id%2==0, pieOutput.getIsFitOffer());
        Assert.assertFalse(pieOutput.getIsVegan());
    }

    @Test
    public void generateReceiptTest() {
        BakeryShop bakeryShop = new BakeryShop(clients, inventory, orders);

        int numberOfReceipts = 5;

        for (int n = 0; n < numberOfReceipts; n++) {

            Receipt myReceipt = bakeryShop.createNewReceipt();

            for (int i = 0; i < 10; i++) {
                Client c = new Client(i, "Name" + i, "Surname" + i, "City" + i, "11-111", "Street" + i, i);
                bakeryShop.appendClient(c);
            }

            for (int i = 0; i < 5; i++) {
                Pie p = new Pie(i, "Name" + i + " Pie", 2.0 * (i + 1), 23.5 * (i + 1));
                myReceipt.appendPie(p);
            }

            for (int i = 0; i < 3; i++) {
                Order o = new Order(i, 2023, i + 1, 5 + i, 2023, i + 2, 3 + i, bakeryShop.getClients().get(9 - i), false);
                for (int j = 0; j < 4; j++) {
                    Cake cake = new Cake("My text" + j);
                    o.addCakeToOrder(cake);
                    myReceipt.appendOrder(o);
                }

            }

            myReceipt.generateReceipt();
            File exceptedReceipt = new File("./receipts/" + myReceipt.getDate() + "_" + Receipt.getNumber() + ".txt");
            Assert.assertTrue(exceptedReceipt.exists());
        }
    }

}
