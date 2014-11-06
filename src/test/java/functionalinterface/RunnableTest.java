package test.java.functionalinterface;

import org.junit.Test;

public class RunnableTest {

    @Test
    public void shouldUseOuterVariable() {
        int a = 13;
        Runnable r = () -> System.out.println(a); // efficiently final
        r.run();
    }

    @Test
    public void shouldNotCompileCodeWhenCommentsRemoved() {
//        int a = 13;
//        Runnable r = () -> System.out.println(a);
//        a = 1;
//        r.run();
    }
}
