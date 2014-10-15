package test.java.apple;

import main.java.apple.Apple;
import main.java.apple.AppleLister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AppleListerTest {

    private AppleLister appleLister;
    private Apple apple1 = new Apple("GREEN", 80);
    private Apple apple2 = new Apple("RED", 90);
    private Apple apple3 = new Apple("GREEN", 40);
    private Apple apple4 = new Apple("GREEN", 85);
    private Apple apple5 = new Apple("RED", 100);

    @Before
    public void before(){
        appleLister = new AppleLister();
        appleLister.addApple(apple1);
        appleLister.addApple(apple2);
        appleLister.addApple(apple3);
        appleLister.addApple(apple4);
        appleLister.addApple(apple5);
    }

    @Test
    public void shouldFilterGreenApples() {
        List<Apple> filteredList = appleLister.filterGreenApples();

        Assert.assertEquals(filteredList.size(), 3);
        Assert.assertTrue(containsItem(filteredList, apple1));
        Assert.assertTrue(containsItem(filteredList, apple3));
        Assert.assertTrue(containsItem(filteredList, apple4));
    }

    @Test
    public void shouldFilterHeavyApples() {
        List<Apple> filteredList = appleLister.filterHeavyApples();

        Assert.assertEquals(filteredList.size(), 3);
        Assert.assertTrue(containsItem(filteredList, apple2));
        Assert.assertTrue(containsItem(filteredList, apple4));
        Assert.assertTrue(containsItem(filteredList, apple5));
    }

    @Test
    public void shouldFilterTooHeavyApples() {
        List<Apple> filteredList = appleLister.filterTooHeavyApples();

        Assert.assertEquals(filteredList.size(), 1);
        Assert.assertTrue(containsItem(filteredList, apple5));
    }

    private boolean containsItem(List<Apple> filteredList, Apple apple) {
        for (Apple eachApple : filteredList) {
            if (apple.equals(eachApple)){
                return true;
            }
        }

        return false;
    }

}