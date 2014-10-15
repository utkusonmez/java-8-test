package main.java.car;

public class Audi implements Car, AudiSample {

    @Override
    public void horn() {
        System.out.println("Audi horn");
    }

    @Override
    public void drive() {
        System.out.println("driving audi");
    }
}
