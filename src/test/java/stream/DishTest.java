package test.java.stream;

import main.java.dishes.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class DishTest {

    private List<Dish> menu;
    private Dish doner;
    private Dish seasonFruit;
    private Dish beef;
    private Dish chicken;
    private Dish frenchFies;
    private Dish rice;
    private Dish pizza;
    private Dish prawns;
    private Dish salmon;

    @Before
    public void before() {
        doner = new Dish("doner", false, 800, Dish.Type.MEAT);
        seasonFruit = new Dish("season fruit", true, 120, Dish.Type.OTHER);
        beef = new Dish("beef", false, 700, Dish.Type.MEAT);
        chicken = new Dish("chicken", false, 400, Dish.Type.MEAT);
        frenchFies = new Dish("french fries", true, 530, Dish.Type.OTHER);
        rice = new Dish("rice", true, 350, Dish.Type.OTHER);
        pizza = new Dish("pizza", true, 550, Dish.Type.OTHER);
        prawns = new Dish("prawns", false, 300, Dish.Type.FISH);
        salmon = new Dish("salmon", false, 450, Dish.Type.FISH);
        menu = Arrays.asList(doner, seasonFruit, beef, chicken, frenchFies, rice, pizza, prawns, salmon);
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
    public void shouldThrowException() {
        List<String> words = Arrays.asList("Hello", "stream");
        Stream<String> s = words.stream();

        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }

    @Test
    public void shouldForEach() {
        List<String> words = Arrays.asList("Hello", "cruel", "stream");
        words.stream().forEach(System.out::println);
    }

    @Test
    public void shouldCount() {
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();

        assertThat(count, equalTo(3L));
    }

    @Test
    public void shouldFilterVegetarianFood() {
        List<Dish> list = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

        assertThat(list, containsInAnyOrder(seasonFruit, pizza, frenchFies, rice));
    }

    @Test
    public void shouldFilterFirstTwoMeatDishes() {
        List<Dish> list = menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2).collect(Collectors.toList());

        assertThat(list, containsInAnyOrder(doner, beef));
    }

    @Test
    public void shouldCheckIfAnyOneMatchesInList() {
        boolean matches = menu.stream().anyMatch(Dish::isVegetarian);
        assertThat(matches, equalTo(true));
    }

    @Test
    public void shouldAllMatch() {
        boolean matches = menu.stream().allMatch(d -> d.getCalories() > 100);
        assertThat(matches, equalTo(true));
    }

    @Test
    public void shouldNotAllMatch() {
        boolean matches = menu.stream().allMatch(d -> d.getCalories() > 150);
        assertThat(matches, equalTo(false));
    }

    @Test
    public void shouldNotNoneMatch() {
        boolean matches = menu.stream().noneMatch(d -> d.getCalories() > 150);
        assertThat(matches, equalTo(false));
    }

    @Test
    public void shouldNoneMatch() {
        boolean matches = menu.stream().noneMatch(d -> d.getCalories() > 1500);
        assertThat(matches, equalTo(true));
    }

    @Test
    public void shouldFirstMatch() {
        Optional<Dish> first = menu.stream().filter(d -> d.getCalories() < 200).findFirst();
        assertThat(first.get(), equalTo(seasonFruit));
    }

    @Test
    public void shouldCalculateAllCaloriesInMenu() {
        Integer sum = menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);
        assertThat(sum, equalTo(4200));
    }

    @Test
    public void shouldCalculateAllCaloriesInMenuWithMethodReferences() {
        Integer sum = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        assertThat(sum, equalTo(4200));
    }

    @Test
    public void shouldCalculateAllCaloriesInMenuWithMethodReferencesWithPrimitiveStream() {
        Integer sum = menu.stream().mapToInt(Dish::getCalories).sum();
        assertThat(sum, equalTo(4200));
    }

    @Test
    public void shouldFindMaxCalories() {
        Optional<Integer> sum = menu.stream().map(Dish::getCalories).reduce(Integer::max);
        assertThat(sum.get(), equalTo(800));
    }

    @Test
    public void shouldFindMinCalories() {
        Optional<Integer> sum = menu.stream().map(Dish::getCalories).reduce(Integer::min);
        assertThat(sum.get(), equalTo(120));
    }

    @Test
    public void shouldCountMenu() {
        long count = menu.stream().collect(Collectors.counting());
        assertThat(count, equalTo(9L));
    }

    @Test
    public void shouldFindMaxCaloriesDish() {
        Comparator<Dish> maxComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> sum = menu.stream().collect(Collectors.maxBy(maxComparator));
        assertThat(sum.get().getCalories(), equalTo(800));
    }

    @Test
    public void shouldSumAllCalories() {
        int sum = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        assertThat(sum, equalTo(4200));
    }

    @Test
    public void shouldSumAverageCalories() {
        double average = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        assertThat(average, equalTo(4200D / 9));
    }

    @Test
    public void shouldJoinNames() {
        String names = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        assertThat(names, equalTo("doner,season fruit,beef,chicken,french fries,rice,pizza,prawns,salmon"));
    }

    @Test
    public void shouldGroupBy() {
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType)));

        System.out.println(menu.stream().collect(Collectors.groupingBy(d -> {
            if (d.getCalories() <= 400) {
                return "DIET";
            } else if (d.getCalories() <= 400) {
                return "NORMAL";
            }
            return "FAT";
        })));

        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(d -> {
            if (d.getCalories() <= 400) {
                return "DIET";
            } else if (d.getCalories() <= 400) {
                return "NORMAL";
            }
            return "FAT";
        }))));

        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByTypeWithoutOptional =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));
        System.out.println(mostCaloricByTypeWithoutOptional);
    }

    @Test
    public void shouldPartioningBy() {
        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian)));

        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,
                Collectors.groupingBy(Dish::getType))));

        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get))));

    }
}
