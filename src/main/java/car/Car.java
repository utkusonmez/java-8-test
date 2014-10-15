package main.java.car;

public interface Car {

    void horn();

    default void drive() {
        System.out.println("Driving car");
    }
}
