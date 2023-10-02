package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.model.*;
import org.example.query.QueriesForSearch;
import org.example.util.ExceptionWritter;
import org.example.util.JsonReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

public class SearchService {
    public void search(File inputFile, File outputFile) {
        List<JsonNode> nodes = null;
        try {
            nodes = JsonReader.readJsonFile(inputFile);
        } catch (IOException e) {
            ExceptionWritter.sendException(outputFile,e.getClass().getName(), e.getLocalizedMessage());
            System.exit(1);
        }
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode rootNode = mapper.createArrayNode();
        for (JsonNode node : nodes) {
            if (node.has("last_name")) {
                BuyerRequestByLastName lastName = null;
                try {
                    lastName = new ObjectMapper().treeToValue(node, BuyerRequestByLastName.class);
                } catch (JsonProcessingException e) {
                    ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                            e.getLocalizedMessage());
                    System.exit(1);
                }
                List<Buyer> result = QueriesForSearch.getBuyersByLastname(lastName.getLast_name());
                ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search", result, lastName),
                        ObjectNode.class);
                rootNode.add(objectNode);
            } else if (node.has("productName") && node.has("minTimes")) {
                ProductNameAndCountRequest productAndCount = null;
                try {
                    productAndCount = new ObjectMapper().treeToValue(node,
                            ProductNameAndCountRequest.class);
                } catch (JsonProcessingException e) {
                    ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                            e.getLocalizedMessage());
                    System.exit(1);
                }
                List<Buyer> result = QueriesForSearch.findBuyersByProductPurchaseCount(productAndCount.getProductName(),
                        productAndCount.getMinTimes());
                ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search", result, productAndCount),
                        ObjectNode.class);
                rootNode.add(objectNode);

            } else if (node.has("minExpenses") && node.has("maxExpenses")) {
                PeriodPurchaseRequest period = null;
                try {
                    period = new ObjectMapper().treeToValue(node, PeriodPurchaseRequest.class);
                } catch (JsonProcessingException e) {
                    ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                            e.getLocalizedMessage());
                    System.exit(1);
                }
                List<Buyer> result = QueriesForSearch.findBuyersByTotalCostRange(period.getMinExpenses(),
                        period.getMaxExpenses());
                ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search", result, period),
                        ObjectNode.class);
                rootNode.add(objectNode);
            } else if (node.has("badCustomers")) {
                BadCustomersRequest badCustomersRequest = null;
                try {
                    badCustomersRequest = new ObjectMapper().treeToValue(node, BadCustomersRequest.class);
                } catch (JsonProcessingException e) {
                    ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                            e.getLocalizedMessage());
                    System.exit(1);
                }
                List<Buyer> result = QueriesForSearch.findInactiveBuyers(badCustomersRequest.getBadCustomers());
                ObjectNode objectNode = mapper.convertValue(new BuyerResponse("search", result, badCustomersRequest),
                        ObjectNode.class);
                rootNode.add(objectNode);
            }

        }
        try {
            mapper.writeValue(outputFile, rootNode);
        } catch (IOException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }
    }
}
