package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<JsonNode> readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        List<JsonNode> nodes = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(file);
        JsonNode criteriesNode = rootNode.get("criterias");
        System.out.println(criteriesNode.toString());
        for (JsonNode node : criteriesNode) {
            nodes.add(node);
        }
        return nodes;
    }
}
