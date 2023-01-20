import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Receipt {
    private HashMap<Integer, Order> orders;
    private HashMap<Integer, Pie> pies;

    private Calendar date;

    static int number = 0;

    public Receipt() {
        this.setOrders();
        this.setPies();
        this.setDate();
        Receipt.increaseNumber();
    }

    public void setOrders() {
        this.orders = new HashMap<Integer, Order>();
    }

    public HashMap<Integer, Order> getOrders(HashMap<Integer, Order> orders) {
        return this.orders;
    }

    public void appendOrder(Order order) {
        this.orders.put(order.getId(), order);
    }

    public void setPies() {
        this.pies = new HashMap<Integer, Pie>();
    }

    public HashMap<Integer, Pie> getPies(HashMap<Integer, Pie> pies) {
        return this.pies;
    }

    public void appendPie(Pie pie) {
        this.pies.put(pie.getId(), pie);
    }

    public void setDate() {
        this.date = new GregorianCalendar();
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d_HH;mm;ss");
        return sdf.format(this.date.getTime());
    }

    public static void setNumber(int number) {
        Receipt.number = number;
    }

    public static int getNumber() {
        return Receipt.number;
    }

    public static void increaseNumber() {
        Receipt.number++;
    }

    public String prepareReceipt(HashMap<Integer, Order> orders, HashMap<Integer, Pie> pies) {
        String output = "";
        int counter = 1;
        double total = 0;
        if(pies != null) {
            output += "=============================================\n" +
                    "                   INVENTORY                    \n" +
                    "=============================================\n" +
            "No.\tproduct name\tweight * value\tsubtotal\n";
            for (Map.Entry<Integer, Pie> pie: pies.entrySet()) {
                double subtotal =  pie.getValue().getValueForKg() * pie.getValue().getWeight();
                output += counter + "\t" + pie.getValue().getName() + "\t\t" +
                        pie.getValue().getWeight() + "*" + pie.getValue().getValueForKg() + "\t\t" +
                        subtotal + "\n";
                total += subtotal;
                counter++;
            }
        }

        if(orders != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
            output += "=============================================\n" +
                    "                    ORDERS                      " +
                    "\n=============================================\n" +
            "No.\torder number\tdate of order\tsubtotal\n";;
            for (Map.Entry<Integer, Order> order: orders.entrySet()) {
                String date_of_order = sdf.format(order.getValue().getDate_of_order().getTime());
                output += counter + "\tOrder " + order.getValue().getId() + "\t\t\t" +
                       date_of_order + "\t\t" +
                        order.getValue().getTotal() + "\n";
                total += order.getValue().getTotal();
                counter++;
            }
        }

        output += "=============================================\n" +
                "                     TOTAL                     \n" +
                "=============================================\n" +
                "                     " + total;

        return output;
    }

    private boolean printReceipt(String fileName, String data) {
        File receipt = new File("./receipts/"+fileName+".txt");
        boolean success = false;
        try {
            success = receipt.createNewFile();
            PrintStream ps = new PrintStream(receipt);
            ps.println(data);
            ps.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public void generateReceipt() {
        String output = "";
        output += "=============================================\n" +
                "                    RECEIPT                       " +
                "\n=============================================\n"+
                this.getDate() +" \n" +
                "Number: "+Receipt.getNumber()+
                "\n" + "=============================================\n";

        output += this.prepareReceipt(this.orders, this.pies);

        String fileName = this.getDate() +"_"+ Receipt.getNumber();

        printReceipt(fileName, output);
    }
}
