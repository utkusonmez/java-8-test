package test.java.stream;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class NumericStreamTest {

    @Test
    public void shouldReturnEvenNumberCount(){
        IntStream stream = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);

        assertThat(stream.count(), equalTo(50L));
    }



}
