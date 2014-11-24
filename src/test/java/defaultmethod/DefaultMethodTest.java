package test.java.defaultmethod;

import main.java.defaultmethod.AllInOne;
import main.java.defaultmethod.OnlyInterfaces;
import main.java.defaultmethod.Rectangle;
import main.java.defaultmethod.Resizable;
import org.junit.Test;

public class DefaultMethodTest {

    @Test
    public void shouldRun() {
        Resizable rectangle = new Rectangle();
        rectangle.draw();
    }

    @Test
    public void shouldSayHello() {
        new AllInOne().hello();
        new OnlyInterfaces().hello();
    }
}
