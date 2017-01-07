package uk.ac.brighton.uni.ch629.catshop;

/**
 * A class which uses this interface has custom serialization and deserialization for JSON.
 */
public interface CustomSerialization {
    /**
     * Serialize the Object to a JSON String
     *
     * @return The JSON
     */
    String serializeJson();

    /**
     * Deserializes the JSON String into an object
     *
     * @param json The JSON String to Deserialize
     * @return The Object as this Interface
     */
    CustomSerialization deserializeJson(String json);

    default <T> T deserializeJson(String json, Class<T> clazz) {
        return clazz.cast(deserializeJson(json));
    }
}
