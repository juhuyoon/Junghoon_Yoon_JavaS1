package com.company.inheritance;

public abstract class StaffMember {
    protected String name;
    protected String department;

    public abstract void submitTimeCard();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
