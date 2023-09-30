package org.example.model;

public class ProductNameAndCountRequest extends Request{
    private String productName;
    private int minTimes;

    public ProductNameAndCountRequest(String productName, int minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(int minTimes) {
        this.minTimes = minTimes;
    }

    @Override
    public String toString() {
        return "ProductNameAndCountRequest{" +
                "product_name='" + productName + '\'' +
                ", count=" + minTimes +
                '}';
    }

    public ProductNameAndCountRequest() {

    }
}
