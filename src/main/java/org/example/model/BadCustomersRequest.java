package org.example.model;

public class BadCustomersRequest  extends Request{
    private int badCustomers;

    public BadCustomersRequest(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    public BadCustomersRequest() {
    }

    public int getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public String toString() {
        return "BadCustomersRequest{" +
                "badCustomers=" + badCustomers +
                '}';
    }
}
