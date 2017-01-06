package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;

public class JsonHelper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode objectToNode(Object object) {
        return objectMapper.valueToTree(object);
    }

    public static String objectToString(Object object) {
        if (object instanceof CustomSerialization) return ((CustomSerialization) object).serializeJson();
        else return objectToNode(object).toString();
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
        if (CustomSerialization.class.isAssignableFrom(clazz)) {
            try {
                return (T) ((CustomSerialization) clazz.newInstance()).deserializeJson(json);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace(); //Invalid JSON for this class
        }
        return null;
    }

    public static <T> T jsonToCollectionObject(String json, Class<? extends Collection> genericClass, Class<?> otherClass) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(genericClass, otherClass));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}