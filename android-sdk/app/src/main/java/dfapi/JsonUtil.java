package dfapi;


import com.fasterxml.jackson.databind.*;

/**
 * Initializing the Jackson mapper is very expensive. This class provides it as a singleton
 * for the duration of the application so you only have to pay the initialization cost once.
 */
public class JsonUtil {
    public static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static ObjectMapper getJsonMapper() {
        return mapper;
    }
}


