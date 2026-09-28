// Number 1 Electronics - Gaming Console Sales Report

public class Main {
    public static void main(String[] args) {
        // Declaration and population of single array - [6 Marks]
        String[] cities = {"CAPE TOWN", "PORT ELIZABETH", "PRETORIA"};

        // Declaration and population of two-dimensional array - [6 Marks]
        // Row = city, Column = PS5, XBOX, SWITCH
        int[][] sales = {
            {1000, 2000, 3000}, // Cape Town
            {2000, 3000, 4000}, // Port Elizabeth
            {1500, 1100, 1200} // Pretoria
        };

        // Printing rows and columns in the report - [4 Marks]
        System.out.println("-----------------------------------------------");
        System.out.println("GAMING CONSOLE REPORT");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-20s %-6s %-6s %-6s\n", "", "PS5", "XBOX", "SWITCH");
        System.out.println();

        for (int i = 0; i < cities.length; i++) {
            System.out.printf("%-20s %-6d %-6d %-6d\n", cities[i], sales[i][0], sales[i][1], sales[i][2]);
        }

        // Printing data and calculation of totals - [6 Marks]
        System.out.println("\n-----------------------------------------------");
        System.out.println("CONSOLE SALES TOTALS FOR EACH CITY");
        System.out.println("-----------------------------------------------");

        int maxSales = 0;
        String topCity = "";
        int[] cityTotals = new int[cities.length];

        for (int i = 0; i < cities.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j]; // calculate total for each city
            }
            cityTotals[i] = total;
            System.out.println(cities[i] + " " + total);

            // Determine city with most sales - [4 Marks]
            if (total > maxSales) {
                maxSales = total;
                topCity = cities[i];
            }
        }

        System.out.println("\n-----------------------------------------------");
        System.out.println("CITY WITH THE MOST SALES: " + topCity);
        System.out.println("-----------------------------------------------");
    }
}