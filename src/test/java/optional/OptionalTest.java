package test.java.optional;

import main.java.apple.Apple;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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

}
