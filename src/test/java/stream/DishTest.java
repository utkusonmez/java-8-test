package test.java.stream;

import main.java.dishes.Dish;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

public class DishTest {

    List<Dish> menu;

    @Before
    public void before() {
        menu = Arrays.asList(
                new Dish("doner", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }
}
