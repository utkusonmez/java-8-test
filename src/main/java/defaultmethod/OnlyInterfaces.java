package main.java.defaultmethod;

public class OnlyInterfaces implements Interface1, Interface2 {

    @Override
    public void hello() {
        Interface1.super.hello();
    }
}
