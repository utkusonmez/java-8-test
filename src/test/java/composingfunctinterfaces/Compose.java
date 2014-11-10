package test.java.composingfunctinterfaces;

import main.java.apple.Apple;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Compose {

    @Test
    public void shouldTestNegate() {
        Apple redApple = new Apple("red", 100);
        Apple greenApple = new Apple("green", 100);

        Predicate<Apple> redAppleTest = (Apple a) -> a.getColor().equals("red");
        Predicate<Apple> notRedAppleTest = redAppleTest.negate();

        assertFalse(notRedAppleTest.test(redApple));
        assertTrue(notRedAppleTest.test(greenApple));
    }

    @Test
    public void shouldTestNegateAndAnd() {
        Apple redApple = new Apple("red", 100);
        Apple greenApple = new Apple("green", 100);
        Apple greenAppleAndHeavy = new Apple("green", 101);

        Predicate<Apple> redAppleTest = (Apple a) -> a.getColor().equals("red");
        Predicate<Apple> heavierThan100 = (Apple a) -> a.getWeight() > 100;
        Predicate<Apple> notRedAppleTest = redAppleTest.negate().and(heavierThan100);

        assertFalse(notRedAppleTest.test(redApple));
        assertFalse(notRedAppleTest.test(greenApple));
        assertTrue(notRedAppleTest.test(greenAppleAndHeavy));
    }

    @Test
    public void shouldTestAndThan() {
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x * 2;
        Function<Integer, Integer> fog = f.andThen(g);

        assertThat(fog.apply(1), equalTo(4));
    }

    @Test
    public void shouldTestCompose() {
        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x * 2;
        Function<Integer, Integer> fog = f.compose(g);

        assertThat(fog.apply(1), equalTo(3));
    }

}
