package org.example.model;

import java.util.List;

public class StatByPeriod {
    private String type;
    private int totalDays;
    private List<BuyerForStat> buyer;
    private double totalExpense;
    private double averageExpense;

    public List<BuyerForStat> getBuyer() {
        return buyer;
    }

    public void setBuyer(List<BuyerForStat> buyer) {
        this.buyer = buyer;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public StatByPeriod(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public StatByPeriod() {
    }

    public double getAverageExpense() {
        return averageExpense;
    }

    public void setAverageExpense(double averageExpense) {
        this.averageExpense = averageExpense;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
