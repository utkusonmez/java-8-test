package test.java.functionalinterface;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void shouldAcceptList() {
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i * i));
    }

    private <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T i : list) {
            consumer.accept(i);
        }
    }

}
