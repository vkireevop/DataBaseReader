package org.example.model;

import java.util.List;

public class BuyerResponse {
    private String type;
    private Request criteria;
    private List<Buyer> result;
    public BuyerResponse(String type, List<Buyer> result, Request criteria) {
        this.type = type;
        this.criteria = criteria;
        this.result = result;

    }

    @Override
    public String toString() {
        return "BuyerResponse{" +
                "type='" + type + '\'' +
                ", result=" + result +
                ", criteria=" + criteria +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Buyer> getResult() {
        return result;
    }

    public void setResult(List<Buyer> result) {
        this.result = result;
    }

    public BuyerResponse() {
    }

    public Request getCriteria() {
        return criteria;
    }

    public void setCriteria(Request criteria) {
        this.criteria = criteria;
    }
}
