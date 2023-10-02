package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<JsonNode> readJsonFile(File file)  throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonNode> nodes = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode criteriesNode = rootNode.get("criterias");
            for (JsonNode node : criteriesNode) {
                nodes.add(node);
            }
            return nodes;
        } catch (IOException | NullPointerException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            throw e;
        }
    }
}
