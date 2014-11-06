package test.java.constructorreferences;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConstructorReferenceTest {

    @Test
    public void shouldConstructWhenCalled() {
        Supplier<TestClass> supplier = TestClass::new;

        assertNotNull(supplier.get());
        assertNotNull(supplier.get());
    }

    @Test
    public void shouldConstructWithParameter() {
        Function<Integer, TestClass> supplier = TestClass::new;

        TestClass object = supplier.apply(1);
        assertNotNull(object);
        assertTrue(object.getProperty() == 1);
    }

    @Test
    public void shouldConvertToObjectList() {
        Function<Integer, TestClass> supplier = TestClass::new;

        List<TestClass> list = map(Arrays.asList(1, 2, 3, 4), supplier);
        assertTrue(list.get(0).getProperty() == 1);
        assertTrue(list.get(1).getProperty() == 2);
        assertTrue(list.get(2).getProperty() == 3);
        assertTrue(list.get(3).getProperty() == 4);
    }

    @Test
    public void shouldBuildWithTwoParams() {
        BiFunction<Integer, String, TestClass> supplier = TestClass::new;

        TestClass testClass = supplier.apply(2, "AAA");
        assertTrue(testClass.getProperty() == 2);
        assertTrue(testClass.getValue().equals("AAA"));
    }

    private List<TestClass> map(List<Integer> propertyList, Function<Integer, TestClass> f) {
        List<TestClass> list = new ArrayList<>();
        propertyList.forEach((Integer t) -> list.add(f.apply(t)));
        return list;
    }

    public class TestClass {

        private int property = 0;
        private String value;

        public TestClass() {
            System.out.println("Constructing test class");
        }

        public TestClass(int property) {
            System.out.println("Constructing test class with property");
            this.property = property;
        }

        public TestClass(Integer property, String value) {

            this.property = property;
            this.value = value;
        }

        public int getProperty() {
            return property;
        }

        public void setProperty(int property) {
            this.property = property;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
