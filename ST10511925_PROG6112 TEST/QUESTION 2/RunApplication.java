import java.util.Scanner;

// RunApplication class to instantiate ConsoleSales - Report as per sample [5 Marks]
public class RunApplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Display console options
        System.out.println("Select the console type");
        System.out.println("1) PS5");
        System.out.println("2) XBOX");
        System.out.println("3) SWITCH");
        System.out.println();

        int choice = input.nextInt();
        input.nextLine(); // clear buffer

        String consoleType = "";
        if (choice == 1) {
            consoleType = "PS5";
        } else if (choice == 2) {
            consoleType = "XBOX";
        } else if (choice == 3) {
            consoleType = "SWITCH";
        } else {
            consoleType = "PS5"; // default
        }

        System.out.print("Enter the store: ");
        String storeName = input.nextLine();

        System.out.print("Enter the total sales of " + consoleType + " consoles for " + storeName + ":");
        int totalSales = input.nextInt();

        // Instantiate ConsoleSales class
        ConsoleSales sale = new ConsoleSales(consoleType, storeName, totalSales);

        // Print report
        sale.printReport();

        input.close();
    }
}