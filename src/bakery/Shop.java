package bakery;

import java.util.HashMap;

public interface Shop {
    public void appendOrder(Order o);
    public void appendInventory(Pie p);
    public void appendClient(Client c);

    public Integer generateInventoryID();
    public Integer generateClientID();
    public Integer generateOrderID();
    public Receipt createNewReceipt();
}
