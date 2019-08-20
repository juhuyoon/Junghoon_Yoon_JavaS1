package com.company;

import java.util.ArrayList;
import java.util.List;

public class ClassmatesList {
    private List<Classmate> ClassmatesList = new ArrayList<>();


    public void add(Classmate Classmate) {
        ClassmatesList.add(Classmate);
    }

   public Classmate get(int i) {
       return ClassmatesList.get(i);
    }

    public ArrayList<Classmate> getAll() {
        return (ArrayList) this.ClassmatesList;
    }

    }

