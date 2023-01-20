package Tests;
import bakery.Cake;
import bakery.Client;
import bakery.Order;
import org.junit.Test;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrderTest {
    Cake c1 = new Cake();
    Cake c2 = new Cake("My text");
    Cake c3 = new Cake(5.1, 200.0, "My special text");

    Integer id = 332;
    Client client = new Client(this.id, "Test", "Testowy", "City", "12-123", "Street", 111);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM d");
    Calendar date = new GregorianCalendar(2023, 0, 20);
    Calendar receipt_date = new GregorianCalendar(2023, 0, 24);

    Order order = new Order(id, 2023, 1, 20, 2023, 1, 24, client, false);

    @Test
    public void getIdTest() {
        Assert.assertEquals(this.id, this.order.getId());
    }

    @Test
    public void getDateOfOrder() {
        Assert.assertEquals(this.sdf.format(this.date.getTime()), this.sdf.format(this.order.getDate_of_order().getTime()));
    }

    @Test
    public void getDateOfReceipt() {
        Assert.assertEquals(this.sdf.format(this.receipt_date.getTime()), this.sdf.format(this.order.getDate_of_receipt().getTime()));
    }

    @Test
    public void getClientTest() {
        Assert.assertEquals(this.client, this.order.getClient());
    }

    @Test
    public void getCakesTest() {
        Assert.assertEquals(new ArrayList<Cake>(), this.order.getCakes());
    }

    @Test
    public void getPaidTest() {
        Assert.assertFalse(this.order.getPaid());
    }

    @Test
    public void addCakeToOrderTest() {
        this.order.addCakeToOrder(this.c1);
        this.order.addCakeToOrder(this.c2);
        this.order.addCakeToOrder(this.c3);

        ArrayList<Cake> cakes = this.order.getCakes();

        int index = 0;
        for (Cake cake: cakes) {
            switch(index) {
                case 0:
                    Assert.assertEquals(this.c1, cake);
                    break;
                case 1:
                    Assert.assertEquals(this.c2, cake);
                    break;
                case 2:
                    Assert.assertEquals(this.c3, cake);
                    break;
            }

            index++;
        }
    }

    @Test
    public void calculateTest() {
        this.order.setCakes();
        this.order.addCakeToOrder(this.c1);
        this.order.addCakeToOrder(this.c2);
        this.order.addCakeToOrder(this.c3);

        Assert.assertEquals(1440, this.order.getTotal(), 0.1);
        Assert.assertEquals(1440, this.order.calculate(), 0.1);
    }
}
