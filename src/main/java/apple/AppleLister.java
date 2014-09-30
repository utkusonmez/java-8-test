package main.java.apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleLister {

    private List<Apple> apples = new ArrayList<>();

    public void addApple(Apple apple) {
        apples.add(apple);
    }

    public List<Apple> filterGreenApples() {
        return filterApples(apples, Apple::isGreen);
    }

    public List<Apple> filterHeavyApples() {
        return filterApples(apples, Apple::isHeavy);
    }

    public List<Apple> filterTooHeavyApples() {
        return filterApples(apples, (Apple a) -> a.getWeight() > 99);
    }

    public List<Apple> filterApples(List<Apple> appleList, Predicate<Apple> p) {
        List<Apple> newApples = new ArrayList<>();
        for (Apple each : appleList) {
            if(p.test(each)){
                newApples.add(each);
            }
        }

        return newApples;
    }
}
