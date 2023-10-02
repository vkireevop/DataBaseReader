package org.example.model;

import java.util.List;

public class BuyerForStat {
    private int id;
    private String first_name;
    private String last_name;
    private List<Purchase> purchases;
    private double totalExpense;

    public BuyerForStat(int id, String first_name, String last_name, double totalExpense) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.totalExpense = totalExpense;
    }

    public String getFirst_name() {
        return first_name;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public BuyerForStat()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
