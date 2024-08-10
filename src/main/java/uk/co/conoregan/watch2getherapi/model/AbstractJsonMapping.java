package uk.co.conoregan.watch2getherapi.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * The base class for all JSON mappings, ensuring any extra fields added will be accessible.
 */
public abstract class AbstractJsonMapping implements Serializable {
    @Serial
    private static final long serialVersionUID = 342593311842368214L;

    private final Map<String, Object> newItems = new HashMap<>();

    /**
     * Gets the new items that were not mapped to any field from a model extending this class.
     *
     * @return the new items.
     */
    @JsonAnyGetter
    public Map<String, Object> getNewItems() {
        return newItems;
    }

    /**
     * Sets the new items that were not mapped to any field from a model extending this class.
     *
     * @param key the key.
     * @param value the value.
     */
    @JsonAnySetter
    public void setNewItems(String key, Object value) {
        newItems.put(key, value);
    }
}
