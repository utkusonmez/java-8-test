package test.java.car;

import main.java.car.A6;
import main.java.car.Audi;
import main.java.car.Car;
import main.java.car.Ford;
import org.junit.Test;

public class CarTest {

    @Test
    public void shouldDriveAudi() {
        driveAndHorn(new Audi());
    }

    @Test
    public void shouldDriveFord() {
        driveAndHorn(new Ford());
    }

    @Test
    public void shouldDriveA6() {
        driveAndHorn(new A6());
    }

    private void driveAndHorn(Car car) {
        car.drive();
        car.horn();
    }
}
