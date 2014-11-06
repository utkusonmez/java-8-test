package test.java.functionalinterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;

public class FunctionTest {

    @Test
    public void shouldMapStringLengths() {
        List<Integer> lengthList = map(Arrays.asList("Java", "Rocks"), String::length);

        assertTrue(lengthList.get(0) == 4);
        assertTrue(lengthList.get(1) == 5);
    }

    private <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> resultList = new ArrayList<>();
        list.forEach((t) -> resultList.add(f.apply(t)));
        return resultList;
    }

}
