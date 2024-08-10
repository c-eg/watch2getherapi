package uk.co.conoregan.watch2getherapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json utility class.
 */
public final class JsonUtils {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {

    }
}
