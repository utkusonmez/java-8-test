package test.java.functionalinterface;

import main.java.functionalinterface.WriterProcessor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FunctionalInterfaceTest {

    private ArrayList<String> list = new ArrayList<>(Arrays.asList("utku", "sonmez"));

    @Test
    public void shouldTestSingleLambda() {
        process(System.out::println);
    }

    @Test
    public void shouldTestTwiceLambda() {
        process((String s) -> {
            System.out.println("First " + s);
            System.out.println("Second " + s);
        });
    }

    @Test
    public void shouldTestPrefixSuffixLambda() {
        process((String s) -> System.out.println("-*-" + s + "-*-"));
    }

    @Test
    public void shouldNotBeCompiledWhenCommentsRemoved() {
        //process((String s) -> s);
        //process((String s) -> "ANY STRING");
        //process((String s) -> {return 1;});
    }

    private void process(WriterProcessor wp) {
        wp.process("UTKU");
    }

//    public void process(WriterProcessor wp) {
//        System.out.println("PROCESS starting");
//        list.forEach(wp::process);
//        System.out.println("PROCESS ending");
//    }

}
