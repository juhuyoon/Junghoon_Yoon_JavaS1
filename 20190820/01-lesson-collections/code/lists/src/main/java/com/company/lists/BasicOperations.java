package com.company.lists;

import java.util.ArrayList;
import java.util.List;

public class BasicOperations {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        System.out.println("How many names do we have? " + names.size());

        // Adding an element
        names.add("Joe");
        System.out.println("How many names do we have? " + names.size());

        // Accessing an element - notice that this looks a lot like accessing
        // an array element
        String tempName = names.get(0);
        System.out.println("The first name in the list is: " + tempName);

        // Add another element
        names.add("Sally");
        System.out.println("How many names do we have? " + names.size());

        // Demonstrate that elements get added to the back of the list by default
        tempName = names.get(0);
        System.out.println("The first name in the list is: " + tempName);

        tempName = names.get(1);
        System.out.println("The second name in the list is: " + tempName);

        // What happens if we try to access the third (non-existent) element?
        // OOPS!!!  A big error...
        // tempName = names.get(2);
        // System.out.println("The third element in the list is: " + tempName);

        // Removing an element
        names.remove(0);
        System.out.println("How many names do we have? " + names.size());

        tempName = names.get(0);
        System.out.println("The first name in the list is: " + tempName);

        // Clearing a list
        names.clear();
        System.out.println("How many names do we have? " + names.size());

    }
}
