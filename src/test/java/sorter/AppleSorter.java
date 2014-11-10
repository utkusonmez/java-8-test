package test.java.sorter;


import main.java.apple.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppleSorter {

    private List<Apple> apples = new ArrayList<>();

    @Before
    public void before() throws Exception {
        apples.add(new Apple("green", 150));
        apples.add(new Apple("red", 150));
        apples.add(new Apple("green-red", 150));
        apples.add(new Apple("red", 100));
        apples.add(new Apple("green", 50));
        apples.add(new Apple("red", 110));
    }

    @Test
    public void shouldSortApplesByWeightUsingClass() {
        apples.sort(new AppleComparator());
        assertAppleList();
    }

    @Test
    public void shouldSortApplesByWeightUsingAnonymousClass() {
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        assertAppleList();
    }

    @Test
    public void shouldSortApplesByWeightUsingLambda() {
        apples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        assertAppleList();
    }

    @Test
    public void shouldSortApplesByWeightUsingLambdaAndComparing() {
        apples.sort(Comparator.comparing((a) -> a.getWeight()));
        assertAppleList();
    }

    @Test
    public void shouldSortApplesByWeightUsingComparingAndMethodReference() {
        apples.sort(Comparator.comparing(Apple::getWeight));
        assertAppleList();
    }

    @Test
    public void shouldSortApplesAndGetReverse() {
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        assertTrue(apples.get(5).getWeight() == 50);
        assertTrue(apples.get(4).getWeight() == 100);
        assertTrue(apples.get(3).getWeight() == 110);
        assertTrue(apples.get(2).getWeight() == 150);
        assertTrue(apples.get(1).getWeight() == 150);
        assertTrue(apples.get(0).getWeight() == 150);
    }

    @Test
    public void shouldSortApplesAndGetReverseAndCompareAgain() {
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        assertTrue(apples.get(5).getWeight() == 50);
        assertTrue(apples.get(4).getWeight() == 100);
        assertTrue(apples.get(3).getWeight() == 110);
        assertTrue(apples.get(2).getWeight() == 150);
        assertTrue(apples.get(1).getWeight() == 150);
        assertTrue(apples.get(0).getWeight() == 150);
        assertTrue(apples.get(2).getColor().equals("red"));
        assertTrue(apples.get(1).getColor().equals("green-red"));
        assertTrue(apples.get(0).getColor().equals("green"));
    }

    private void assertAppleList() {
        assertTrue(apples.get(0).getWeight() == 50);
        assertTrue(apples.get(1).getWeight() == 100);
        assertTrue(apples.get(2).getWeight() == 110);
        assertTrue(apples.get(3).getWeight() == 150);
        assertTrue(apples.get(4).getWeight() == 150);
        assertTrue(apples.get(5).getWeight() == 150);
    }

    public class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

}
