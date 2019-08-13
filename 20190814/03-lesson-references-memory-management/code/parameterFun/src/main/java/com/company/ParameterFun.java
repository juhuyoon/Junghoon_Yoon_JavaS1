package com.company;

public class ParameterFun {

    public static void main(String[] args) {

        int myNum = 5;
        System.out.println("Value of myNum before passing into method = " + myNum);
        addOneToMe(myNum);
        System.out.println("Value of myNum after being passed into method = " + myNum);

        Person myPerson = new Person("John Doe", "Astronaut");
        System.out.println("Name of myPerson before passing into method = " + myPerson.getName());
        changeMyName(myPerson);
        System.out.println("Name of myPerson after being passed into method = " + myPerson.getName());

    }

    public static void addOneToMe(int number) {
        System.out.println("Number passed in = " + number);
        number = number + 1;
        System.out.println("Number value after adding one = " + number);
    }

    public static void changeMyName(Person person) {
        System.out.println("Name of Person passed in = " + person.getName());
        person.setName("YOUR NAME HAS BEEN CHANGED");
        System.out.println("Name of Person after changing it in method = " + person.getName());
    }
}
