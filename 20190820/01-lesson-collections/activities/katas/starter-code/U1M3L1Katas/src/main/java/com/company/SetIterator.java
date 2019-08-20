package com.company;

import java.util.HashSet;
import java.util.Iterator;

public class SetIterator {
    public void printSet(int num, int num2, int num3, int num4, int num5) {
        HashSet<Integer> sets = new HashSet<>();
        sets.add(num);
        sets.add(num2);
        sets.add(num3);
        sets.add(num4);
        sets.add(num5);


        Iterator<Integer> i = sets.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}

