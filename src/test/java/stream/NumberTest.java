package test.java.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class NumberTest {

    @Test
    public void shouldFindUniqueEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        List<Integer> collectedNumbers = numbers.stream().filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());

        assertThat(collectedNumbers, containsInAnyOrder(2, 4));
    }

    @Test
    public void shouldSortAndLimitFirst3Item() {
        List<Integer> numbers = Arrays.asList(5, 9, 3, 7, 4, 2, 10);

        List<Integer> collectedNumbers = numbers.stream()
                .filter(i -> i > 4)
                .limit(3)
                .sorted((x, y) -> y - x)
                .collect(Collectors.toList());

        assertThat(collectedNumbers, contains(9, 7, 5));
    }

    @Test
    public void shouldFirstSortAndLimit3Item() {
        List<Integer> numbers = Arrays.asList(5, 9, 3, 7, 4, 2, 10);

        List<Integer> collectedNumbers = numbers.stream()
                .filter(i -> i > 4)
                .sorted((x, y) -> y - x)
                .limit(3)
                .collect(Collectors.toList());

        assertThat(collectedNumbers, contains(10, 9, 7));
    }

    @Test
    public void shouldFirstSortAndLimit3ItemSameWithFilter() {
        List<Integer> numbers = Arrays.asList(5, 9, 3, 7, 4, 2, 10);

        List<Integer> collectedNumbers = numbers.stream()
                .sorted((x, y) -> y - x)
                .limit(3)
                .collect(Collectors.toList());

        assertThat(collectedNumbers, contains(10, 9, 7));
    }

    @Test
    public void shouldSkipFirstTwoItem() {
        List<Integer> numbers = Arrays.asList(5, 9, 3, 7, 4, 2, 10);

        List<Integer> collectedNumbers = numbers.stream()
                .filter(i -> i > 4)
                .skip(2)
                .sorted((x, y) -> y - x)
                .collect(Collectors.toList());

        assertThat(collectedNumbers, contains(10, 7));
    }

}
