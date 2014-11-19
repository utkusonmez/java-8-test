package test.java.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FlatMapTest {

    @Test
    public void shouldFlattenString() {
        List<String> words = Arrays.asList("UTKU", "SONOO");

        List<String> list = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        assertThat(list, contains("U", "T", "K", "S", "O", "N"));
    }

    @Test
    public void shouldSquareAllItems() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squared = numbers
                .stream()
                .map(i -> i * i)
                .collect(Collectors.toList());

        assertThat(squared, contains(1, 4, 9, 16, 25));
    }

    @Test
    public void shouldDistributeEachList() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collected = numbers1
                .stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        assertThat(collected.get(0)[0], equalTo(1));
        assertThat(collected.get(0)[1], equalTo(3));
        assertThat(collected.get(1)[0], equalTo(1));
        assertThat(collected.get(1)[1], equalTo(4));
        assertThat(collected.get(2)[0], equalTo(2));
        assertThat(collected.get(2)[1], equalTo(3));
        assertThat(collected.get(3)[0], equalTo(2));
        assertThat(collected.get(3)[1], equalTo(4));
        assertThat(collected.get(4)[0], equalTo(3));
        assertThat(collected.get(4)[1], equalTo(3));
        assertThat(collected.get(5)[0], equalTo(3));
        assertThat(collected.get(5)[1], equalTo(4));
    }

    @Test
    public void shouldDistributeEachListAndFilterOnlyDisionableByThree() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collected = numbers1
                .stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .filter((i) -> (i[0] + i[1]) % 3 == 0)
                .collect(Collectors.toList());

        assertThat(collected.get(0)[0], equalTo(2));
        assertThat(collected.get(0)[1], equalTo(4));
        assertThat(collected.get(1)[0], equalTo(3));
        assertThat(collected.get(1)[1], equalTo(3));
    }

    @Test
    public void shouldDistributeEachListAndFilterOnlyDivisinableByThreeAnotherOption() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collected = numbers1
                .stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        assertThat(collected.get(0)[0], equalTo(2));
        assertThat(collected.get(0)[1], equalTo(4));
        assertThat(collected.get(1)[0], equalTo(3));
        assertThat(collected.get(1)[1], equalTo(3));
    }

}
