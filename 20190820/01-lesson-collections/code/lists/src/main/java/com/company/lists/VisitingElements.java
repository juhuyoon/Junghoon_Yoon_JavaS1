package com.company.lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisitingElements {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        // Add a bunch of names to the list
        names.add("Joe");
        names.add("Sally");
        names.add("John");
        names.add("Sue");
        names.add("Jane");
        names.add("Mike");
        names.add("James");
        names.add("Jeff");
        names.add("Curt");
        names.add("Dana");

        System.out.println("How many names do we have? " + names.size());

        // Visit each name using a regular for loop
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }

        System.out.println("============================");

        // Visit each name using an enhanced for loop
        for (String currentName : names) {
            System.out.println(currentName);
        }

        System.out.println("============================");

        // Visit each element using an iterator

        // We first need to get an iterator from the names List
        Iterator<String> iter = names.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
