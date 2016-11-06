package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JsonHelper { //TODO: Maybe make another dependency for everything, with this in it?
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectNode objectToNode(Object object) {
        return objectMapper.valueToTree(object);
    }

    public static <T> T jsonNodeToObject(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace(); //Invalid JSON for this class
        }
        return null;
    }
}