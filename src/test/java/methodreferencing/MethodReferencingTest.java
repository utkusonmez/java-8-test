package test.java.methodreferencing;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MethodReferencingTest {

    @Test
    public void shouldUseForStaticMethod(){
        Function<String, Integer> stringToInteger = (t) -> {
            return Integer.parseInt(t);
        };

        assertTrue(stringToInteger.apply("3") == 3);
    }

    @Test
    public void shouldUseForInstanceMethodWithParameter(){
        BiFunction<String, String, String> concatter = String::concat;

        assertTrue(concatter.apply("3", "4").equals("34"));
    }

    @Test
    public void shouldContainsInList(){
        BiPredicate<List<String>, String> contains = List::contains;

        assertTrue(contains.test(Arrays.asList("utku", "sonmez"), "utku"));
        assertFalse(contains.test(Arrays.asList("utku", "sonmez"), "maksut"));
    }

}
