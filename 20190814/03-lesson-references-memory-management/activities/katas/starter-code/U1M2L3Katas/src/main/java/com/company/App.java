package com.company;

public class App {
    TV newTv = new TV("Zenith", "U2121H", 83, "NBC", 55, false);
    Radio newRadio = new Radio("Sony", "V32", 2, "WUNV", 30, true);
    Microwave newMicrowave = new Microwave("Haier", "X1200w", 45, "12:00", false);
    CoffeeMaker newCoffeeMaker = new CoffeeMaker("Sunbeam", "C12", 12, 8, true);
    Car newCar = new Car("Honda", "Accord", "Sedan", "Blue", "2.6L V6", "CVT", 4, 31.7, 25218);

    int[] array = new int[]{0,0};

    ComputerMouse newComputerMouse = new ComputerMouse("Razer", "Naga", 960, 540, array);

    public TV createTV() {
       return newTv;
    }

    public Radio createRadio() {
        return newRadio;
    }

    public Microwave createMicrowave() {
        return newMicrowave;
    }

    public CoffeeMaker createCoffeeMaker() {
        return newCoffeeMaker;
    }

    public Car createCar() {
        return newCar;
    }

    public ComputerMouse createComputerMouse() {
        return newComputerMouse;
    }



}
