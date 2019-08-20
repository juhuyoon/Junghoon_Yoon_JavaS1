package com.company;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        // Declare and initialize a Map
        Map<String, Integer> heights = new HashMap<>();

        // Creating Map entries
        heights.put("Joe", 72);
        heights.put("Jane", 63);
        heights.put("Sally", 65);

        // See how many entries there are
        System.out.println("Number of entries in the Map: " + heights.size());

        // Get Joe's height
        int joesHeight = heights.get("Joe");
        System.out.println("Joe is " + joesHeight + " inches tall.");

        // Update Joe's height with replace
        heights.replace("Joe", 74);
        joesHeight = heights.get("Joe");
        System.out.println("Joe is " + joesHeight + " inches tall.");

        // Update Joe's height with put
        heights.put("Joe", 70);
        joesHeight = heights.get("Joe");
        System.out.println("Joe is " + joesHeight + " inches tall.");

        // Now remove Jane's entry
        heights.remove("Jane");

        // See how many entries there are
        System.out.println("Number of entries in the Map: " + heights.size());

        // Now let's get all of the keys and print them out
        Set<String> myKeys = heights.keySet();
        for (String key : myKeys) {
            System.out.println(key);
        }

        // New let's get all of the values
        Collection<Integer> myValues = heights.values();
        for (Integer value : myValues) {
            System.out.println(value);
        }

        // Now let's get all of the keys and use them to print out our key/value pairs
        // We'll reuse the myKeys reference
        myKeys = heights.keySet();
        for (String key : myKeys) {
            System.out.println("Key = " + key + " : " + " Value = " + heights.get(key));
        }

        // Now let's use the entry set to print all the key/values pairs
        Set<Map.Entry<String, Integer>> myEntries = heights.entrySet();
        for (Map.Entry<String, Integer> entry : myEntries) {
            System.out.println("Key = " + entry.getKey() + " : " + " Value = " + entry.getValue());
        }
    }
}
