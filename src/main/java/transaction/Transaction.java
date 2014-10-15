package main.java.transaction;

public class Transaction {

    private double price = 0d;
    private CurrencyType currenyType;

    public Transaction(double price, CurrencyType currenyType) {
        this.price = price;
        this.currenyType = currenyType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CurrencyType getCurrenyType() {
        return currenyType;
    }

    public void setCurrenyType(CurrencyType currenyType) {
        this.currenyType = currenyType;
    }
}
