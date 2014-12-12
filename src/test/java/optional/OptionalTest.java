package test.java.optional;

import main.java.apple.Apple;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class OptionalTest {

    @Test
    public void testOptional() {
        Optional<Apple> optional = Optional.empty();
        Optional<Apple> optApple = Optional.of(new Apple("red", 10));
        Optional<Apple> optNullApple = Optional.ofNullable(null);


        assertNotNull(optNullApple);
        assertNotNull(optional);
        try {
            optNullApple.get();
            fail();
        } catch (NoSuchElementException ignored) {

        }

        try {
            optional.get();
            fail();
        } catch (NoSuchElementException ignored) {

        }

        assertNotNull(optApple);
        assertNotNull(optApple.get());

        assertThat(optApple.map(Apple::getColor).get(), equalTo("red"));
        assertThat(optNullApple.map(Apple::getColor).isPresent(), equalTo(false));
    }

    @Test
    public void shouldTestNestedOptional() {
        Optional<AppleBucket> appleBucketNull = Optional.ofNullable(null);
        assertFalse(appleBucketNull.isPresent());

        Optional<AppleBucket> appleBucketEmpty = Optional.of(new AppleBucket());
        assertTrue(appleBucketEmpty.isPresent());
        assertFalse(appleBucketEmpty.get().getApple1().isPresent());
        assertFalse(appleBucketEmpty.get().getApple2().isPresent());

        Optional<String> unknownWhenNotPresent = appleBucketEmpty.map(AppleBucket::getApple1).filter(Optional::isPresent).map(o -> o.get()).map(Apple::getColor);
        assertThat(unknownWhenNotPresent.orElse("Unkown"), equalTo("Unkown"));

        Optional<AppleBucket> appleBucketFull = Optional.of(new AppleBucket(new Apple("red", 100), new Apple("green", 150)));
        assertTrue(appleBucketFull.isPresent());
        assertTrue(appleBucketFull.get().getApple1().isPresent());
        assertTrue(appleBucketFull.get().getApple2().isPresent());

        Optional<String> apple1ColorOpt = appleBucketFull.map(AppleBucket::getApple1).map(o -> o.get()).map(Apple::getColor);
        assertThat(apple1ColorOpt.get(), equalTo("red"));
    }

    private class AppleBucket {
        private Optional<Apple> apple1 = Optional.empty();
        private Optional<Apple> apple2 = Optional.empty();

        public AppleBucket() {

        }

        public AppleBucket(Apple apple1, Apple apple2) {
            this.apple1 = Optional.of(apple1);
            this.apple2 = Optional.of(apple2);
        }

        public Optional<Apple> getApple1() {
            return apple1;
        }

        public void setApple1(Optional<Apple> apple1) {
            this.apple1 = apple1;
        }

        public Optional<Apple> getApple2() {
            return apple2;
        }

        public void setApple2(Optional<Apple> apple2) {
            this.apple2 = apple2;
        }
    }

}
