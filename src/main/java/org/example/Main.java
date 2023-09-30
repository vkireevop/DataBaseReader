package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.model.*;
import org.example.query.QueriesForSearch;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        List<JsonNode> nodes = JsonReader.readJsonFile("src/main/resources/test.json");
        ObjectMapper mapper = new ObjectMapper();
        File outputFile = new File("src/main/resources/result.json");
        ArrayNode rootNode = mapper.createArrayNode();
        for (JsonNode node : nodes)
        {
            if (node.has("last_name")) {
                BuyerRequestByLastName lastName = new ObjectMapper().treeToValue(node, BuyerRequestByLastName.class);
                List<Buyer> result = QueriesForSearch.getBuyersByLastname(lastName.getLast_name());
                ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search",result, lastName),
                        ObjectNode.class);
                rootNode.add(objectNode);
                } else if (node.has("productName") && node.has("minTimes")) {
                ProductNameAndCountRequest productAndCount = new ObjectMapper().treeToValue(node,
                        ProductNameAndCountRequest.class);
                List<Buyer> result = QueriesForSearch.findBuyersByProductPurchaseCount(productAndCount.getProductName(),
                        productAndCount.getMinTimes());
                ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search",result, productAndCount),
                        ObjectNode.class);
                rootNode.add(objectNode);

                } else if (node.has("minExpenses") && node.has("maxExpenses")){
                    PeriodPurchaseRequest period = new ObjectMapper().treeToValue(node, PeriodPurchaseRequest.class);
                    List<Buyer> result = QueriesForSearch.findBuyersByTotalCostRange(period.getMinExpenses(),
                        period.getMaxExpenses());
                ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search",result, period),
                        ObjectNode.class);
                rootNode.add(objectNode);
                } else if (node.has("badCustomers")) {
                    BadCustomersRequest badCustomersRequest = new ObjectMapper().treeToValue(node, BadCustomersRequest.class);
                    List<Buyer> result = QueriesForSearch.findInactiveBuyers(badCustomersRequest.getBadCustomers());
                    ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search",result, badCustomersRequest),
                        ObjectNode.class);
                    rootNode.add(objectNode);
                }
            }
        mapper.writeValue(outputFile, rootNode);
        }
    }