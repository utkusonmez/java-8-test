package main.java.lister;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Lister<T> {

    public List<T> filter(List<T> itemList, Predicate<T> p) {
        List<T> items = new ArrayList<>();
        for (T each : itemList) {
            if (p.test(each)) {
                items.add(each);
            }
        }

        return items;
    }

}
