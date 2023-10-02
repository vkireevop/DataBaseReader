package org.example.model;

public class Purchase {
    private String name;
    private double expense;

    public Purchase(String name, double expense) {
        this.name = name;
        this.expense = expense;
    }

    public String getName() {
        return name;
    }

    public Purchase() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "name='" + name + '\'' +
                ", expense=" + expense +
                '}';
    }
}
