package test.java.functionalinterface;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void shouldAcceptList() {
        forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);
    }

    private <T> void forEach(List<T> list, Consumer<T> consumer) {
        list.forEach(consumer::accept);
    }

}
