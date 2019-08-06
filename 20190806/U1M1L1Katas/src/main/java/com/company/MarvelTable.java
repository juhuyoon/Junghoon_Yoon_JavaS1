package com.company;

public class MarvelTable {
    private String lines = "-------------------------------------------------------------";

    public static void main(String[] args) {
        MarvelTable table = new MarvelTable();

        System.out.println(table.lines);
        System.out.println("|   Hero              |   Affiliation     |   Power         |");
        System.out.println(table.lines);
        System.out.println("|   Captain America   |   The Avengers    |   Peak Human    |");
        System.out.println(table.lines);
        System.out.println("|   Wolverine         |   The X-Men       |   Healing/Claws |");
        System.out.println(table.lines);
        System.out.println("|   Cyclops           |   The X-Men       |   Eye Beam      |");
        System.out.println(table.lines);
        System.out.println("|   Domino            |   X-Force         |   Luck          |");
        System.out.println(table.lines);
    }
}
