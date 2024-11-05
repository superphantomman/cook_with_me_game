package org.game;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//TODO complete ProductController
public class ProductController {
    private static String filePath = "items.csv";

    public static Map<String, Integer> intializeProducts() {
        // Define the path to the CSV file

        // Create a HashMap to store the data from CSV
        Map<String, Integer> productMap = new HashMap<>();

        // Use ClassLoader to get the file from resources
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("products_list.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;

            // Skip the header line
            br.readLine();

            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Split the line by comma
                String[] values = line.split(",");

                // Parse name and cost, and add to the HashMap
                String name = values[0];
                int cost = Integer.parseInt(values[1]);

                productMap.put(name, cost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productMap;
    }

    public static int getMaxCost() {
        return 500;
    }
}
