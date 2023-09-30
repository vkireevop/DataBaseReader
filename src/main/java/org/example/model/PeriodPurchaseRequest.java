package org.example.model;

public class PeriodPurchaseRequest extends Request {
    private double minExpenses;
    private double maxExpenses;

    public PeriodPurchaseRequest(double minExpenses, double maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    public PeriodPurchaseRequest() {
    }

    public double getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(double minExpenses) {
        this.minExpenses = minExpenses;
    }

    public double getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(double maxExpenses) {
        this.maxExpenses = maxExpenses;
    }

    @Override
    public String toString() {
        return "PeriodPurchaseRequest{" +
                "minExpenses='" + minExpenses + '\'' +
                ", maxExpenses='" + maxExpenses + '\'' +
                '}';
    }
}
