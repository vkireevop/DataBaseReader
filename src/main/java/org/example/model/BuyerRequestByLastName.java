package org.example.model;

public class BuyerRequestByLastName extends Request{
    public String last_name;

    public BuyerRequestByLastName(String last_name) {
        this.last_name = last_name;
    }
    public BuyerRequestByLastName()
    {

    }
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "BuyerRequestByLastName{" +
                "last_name='" + last_name + '\'' +
                '}';
    }
}
