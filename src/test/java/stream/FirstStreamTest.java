package test.java.stream;

import main.java.dishes.Dish;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FirstStreamTest {

    @Test
    public void shouldFilterSortAndMapName() {
        BiFunction<Integer, String, Dish> supplier = Dish::new;

        List<Dish> dishes = new ArrayList<>();
        dishes.add(supplier.apply(300, "Fruit"));
        dishes.add(supplier.apply(800, "Chips"));
        dishes.add(supplier.apply(350, "Desert"));

        List<String> names = dishes.stream()
                .filter((Dish d) -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        assertThat(names.get(0), equalTo("Fruit"));
        assertThat(names.get(1), equalTo("Desert"));
    }

}
