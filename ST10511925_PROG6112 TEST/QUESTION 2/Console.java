// Abstract Consoles class - Required abstract class [5 Marks]
// Implements IConsoles interface
public abstract class Consoles implements IConsoles {
    // Variables to store console type, store name, total sales
    private String consoleType;
    private String storeName;
    private int totalSales;

    // Constructor that accepts console type, store name, total amount
    public Consoles(String consoleType, String storeName, int totalSales) {
        this.consoleType = consoleType;
        this.storeName = storeName;
        this.totalSales = totalSales;
    }

    // Methods to get values - required by interface
    @Override
    public String getConsoleType() {
        return consoleType;
    }

    @Override
    public String getStore() {
        return storeName;
    }

    @Override
    public int getTotalSales() {
        return totalSales;
    }
}