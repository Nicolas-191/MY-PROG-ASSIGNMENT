// ConsoleSales subclass that extends abstract class - [10 Marks]
public class ConsoleSales extends Consoles {

    // Constructor to accept console type, store name, total sales
    public ConsoleSales(String consoleType, String storeName, int totalSales) {
        super(consoleType, storeName, totalSales);
    }

    // printReport method that prints console type, store name, total amount
    public void printReport() {
        System.out.println("\nCONSOLE SALES REPORT");
        System.out.println("********************");
        System.out.println("CONSOLE TYPE: " + getConsoleType());
        System.out.println("STORE: " + getStore());
        System.out.println("TOTAL SALES: " + getTotalSales());
    }
}