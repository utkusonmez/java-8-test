package test.java.functionalinterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;

public class PredicateTest {

    @Test
    public void shouldFilterNotEmptyStrings() {
        List<String> filteredList = filter(Arrays.asList("aa", "bb", "", "cc", ""), (String s) -> !s.isEmpty());
        assertTrue(filteredList.size() == 3);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

}
