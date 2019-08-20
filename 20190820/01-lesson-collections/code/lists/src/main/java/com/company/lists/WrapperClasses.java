package com.company.lists;

import java.util.ArrayList;
import java.util.List;

public class WrapperClasses {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();

        // Creating a wrapper for an int

        // Explicit - this is deprecated
        Integer myInteger = new Integer(42);

        // Another approach to being explicit - not necessary
        myInteger = Integer.valueOf(67);

        // Autoboxing - the way to go
        myInteger = 55;

        // Unboxing - automatic
        int myPrimitive = myInteger;

        // Using primitives with collections

        // Adding an Integer object
        integerList.add(myInteger);

        // Adding an int primitive - uses autoboxing
        integerList.add(myPrimitive);
    }

}
