package test.java.apple;

import main.java.apple.Apple;
import main.java.lister.Lister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleGenericListerTest {

    private Lister<Apple> appleLister;
    private Apple apple1 = new Apple("GREEN", 80);
    private Apple apple2 = new Apple("RED", 90);
    private Apple apple3 = new Apple("GREEN", 40);
    private Apple apple4 = new Apple("GREEN", 85);
    private Apple apple5 = new Apple("RED", 100);
    private List<Apple> appleList;

    @Before
    public void before() {
        appleLister = new Lister<>();
        appleList = new ArrayList<>();
        appleList.add(apple1);
        appleList.add(apple2);
        appleList.add(apple3);
        appleList.add(apple4);
        appleList.add(apple5);
    }

    @Test
    public void shouldFilterGreenApples() {
        List<Apple> filteredList = appleLister.filter(appleList, Apple::isGreen);

        Assert.assertEquals(filteredList.size(), 3);
        Assert.assertTrue(containsItem(filteredList, apple1));
        Assert.assertTrue(containsItem(filteredList, apple3));
        Assert.assertTrue(containsItem(filteredList, apple4));
    }

    @Test
    public void shouldFilterHeavyApples() {
        List<Apple> filteredList = appleLister.filter(appleList, Apple::isHeavy);

        Assert.assertEquals(filteredList.size(), 3);
        Assert.assertTrue(containsItem(filteredList, apple2));
        Assert.assertTrue(containsItem(filteredList, apple4));
        Assert.assertTrue(containsItem(filteredList, apple5));
    }

    @Test
    public void shouldFilterTooHeavyApples() {
        List<Apple> filteredList = appleLister.filter(appleList, a -> a.getWeight() > 99);

        Assert.assertEquals(filteredList.size(), 1);
        Assert.assertTrue(containsItem(filteredList, apple5));
    }

    @Test
    public void shouldAlsoTestEvenNumbers() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        Lister<Integer> numberLister = new Lister<>();
        List<Integer> evenNumbers = numberLister.filter(numbers, i -> i % 2 == 0);

        containsItem(evenNumbers, 0);
        containsItem(evenNumbers, 2);
        containsItem(evenNumbers, 4);
        containsItem(evenNumbers, 6);
        containsItem(evenNumbers, 8);
    }

    private <T> boolean containsItem(List<T> filteredList, T item) {
        for (T each : filteredList) {
            if (item.equals(each)) {
                return true;
            }
        }

        return false;
    }

}
