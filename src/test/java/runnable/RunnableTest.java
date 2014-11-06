package test.java.runnable;

import org.junit.Test;

public class RunnableTest {

    @Test
    public void shouldRunWithFunctionalInterface() {
        Runnable r1 = () -> System.out.println("HELLO WORLD 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("HELLO WORLD 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("HELLO WORLD 3"));

    }

    private void process(Runnable r) {
        r.run();
    }
}
