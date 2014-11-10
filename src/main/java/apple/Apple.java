package main.java.apple;


public class Apple {

    private String color;
    private Integer weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public static boolean isGreen(Apple apple){
        return "GREEN".equals(apple.getColor());
    }

    public static boolean isHeavy(Apple apple){
        return apple.getWeight() > 80;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apple apple = (Apple) o;

        if (weight != apple.weight) return false;
        if (color != null ? !color.equals(apple.color) : apple.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + weight;
        return result;
    }
}
