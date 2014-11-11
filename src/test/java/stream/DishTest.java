package test.java.stream;

import main.java.dishes.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DishTest {

    List<Dish> menu;

    @Before
    public void before() {
        menu = Arrays.asList(
                new Dish("doner", false, 800, Dish.Type.MEAT),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @Test
    public void shouldFilterLimitAndGetNames() {
        List<String> names = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        assertThat(names.get(0), equalTo("doner"));
        assertThat(names.get(1), equalTo("beef"));
        assertThat(names.get(2), equalTo("chicken"));
    }

    @Test
    public void shouldPrintAndFilterLimitAndGetNames() {
        List<String> names = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map((t) -> {
                    System.out.println("mapping " + t.getName());
                    return t.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

        assertThat(names.get(0), equalTo("doner"));
        assertThat(names.get(1), equalTo("beef"));
        assertThat(names.get(2), equalTo("chicken"));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException(){
        List<String> words = Arrays.asList("Hello", "stream");
        Stream<String> s = words.stream();

        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }

    @Test
    public void shouldForEach(){
        List<String> words = Arrays.asList("Hello", "cruel", "stream");
        words.stream().forEach(System.out::println);
    }

    @Test
    public void shouldCount(){
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();

        assertThat(count, equalTo(3L));
    }

}
