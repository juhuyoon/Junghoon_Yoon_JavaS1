package com.company.inheritance;

public class TeamLead extends Developer {

    public void planSprint() {
        // code here
    }

    public void assignWork(Developer dev) {
        // code here
    }

    public void estimateStoryPoints() {
        System.out.println("Estimating my story points (TeamLead)");
        System.out.println("Make sure everyone on my team has estimated their story points.");
    }
}
