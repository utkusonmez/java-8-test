package test.java.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BuildingStreamTest {

    @Test
    public void shouldGenerateStreams() {
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).sum();
        assertThat(sum, equalTo(15));
    }
}
