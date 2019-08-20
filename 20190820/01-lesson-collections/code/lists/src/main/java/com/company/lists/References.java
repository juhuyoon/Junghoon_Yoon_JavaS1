package com.company.lists;

import java.util.ArrayList;
import java.util.List;

public class References {

    public static void main(String[] args) {
        // Create a list of Employees
        List<Employee> staff = new ArrayList<>();

        // Add some employees to our list
        Employee emp = new Employee("Joe Smith", "HR");
        Employee emp2 = new Employee("Jane Doe", "Finance");

        staff.add(emp);
        staff.add(emp2);

        // Now reuse the emp reference to add another Employee to the list
        emp = new Employee("George Washington", "Operations");

        staff.add(emp);

        System.out.println("How many employees do we have? " + staff.size());

        for (Employee temp : staff) {
            System.out.println(temp.getName());
        }

        Employee testEmployee = staff.get(0);

        System.out.println(testEmployee.getName());

        testEmployee.setName("Joseph J. Smith");

        System.out.println(testEmployee.getName());

        for (Employee temp : staff) {
            System.out.println(temp.getName());
        }
    }
}
