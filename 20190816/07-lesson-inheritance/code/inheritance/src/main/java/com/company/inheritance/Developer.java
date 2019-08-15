package com.company.inheritance;

public class Developer extends StaffMember {

    public void submitTimeCard() {
        System.out.println("Submitting time card...");
    }

    public void estimateStoryPoints() {
        System.out.println("Estimating my own Developer story points.");
    }

    public void checkInCode() {
        // code here
    }

    private void privateMethod() {
        // code here
    }

    protected void protectedMethod() {
        // code here
    }
}
