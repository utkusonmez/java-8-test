package main.java.car;

public class Ford implements Car {

    @Override
    public void horn() {
        System.out.println("Ford horn");
    }

    @Override
    public void drive() {
        System.out.println("Driving ford");
    }

}
