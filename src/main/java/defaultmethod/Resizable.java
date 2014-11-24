package main.java.defaultmethod;

public interface Resizable {
    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);

    default void draw() {
        System.out.println("bazri");
    }

}