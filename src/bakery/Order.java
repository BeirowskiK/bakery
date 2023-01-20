package bakery;

import bakery.Cake;
import bakery.Client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Order {
    Integer id;
    private Calendar date_of_order;
    private Calendar date_of_receipt;
    private ArrayList<Cake> cakes;
    private Client client;

    private boolean paid;
    private double total;

    public Order(Integer id, int order_year, int order_month, int order_day, int receipt_year, int receipt_month, int receipt_day, Client client, boolean paid) {
        this.setId(id);
        this.setDate_of_order(order_year, order_month, order_day);
        this.setDate_of_receipt(receipt_year, receipt_month, receipt_day);
        this.setCakes();
        this.setClient(client);
        this.setPaid(paid);
        this.total = this.calculate();
    }

    public Order(Integer id, Calendar date_of_order, Calendar date_of_receipt, Client client, ArrayList<Cake> cakes, boolean paid) {
        this.setId(id);
        this.date_of_order = date_of_order;
        this.date_of_receipt = date_of_receipt;
        this.setCakes();
        this.setClient(client);
        this.setPaid(paid);
        this.cakes = cakes;
        this.total = this.calculate();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDate_of_order(int year, int month, int day) {
        Calendar orderDate = new GregorianCalendar(year, month - 1, day);
        this.date_of_order = orderDate;
    }

    public Calendar getDate_of_order() {
        return this.date_of_order;
    }

    public void setDate_of_receipt(int year, int month, int day) {
        Calendar receiptDate = new GregorianCalendar(year, month - 1, day);
        this.date_of_receipt = receiptDate;
    }

    public Calendar getDate_of_receipt() {
        return this.date_of_receipt;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setCakes() {
        this.cakes = new ArrayList<Cake>();
    }

    public ArrayList<Cake> getCakes() {
        return this.cakes;
    }

    public void addCakeToOrder(Cake cake) {
        this.cakes.add(cake);
        double total = this.calculate();
        this.setTotal(total);
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean getPaid() {
        return this.paid;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return this.total;
    }

    public double calculate() {
        double sum = 0;
        for (Cake c: this.cakes) {
            sum += c.getWeight() * c.getValueForKg();
        }
        return sum;
    }
}
