package uk.co.conoregan.watch2getherapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import uk.co.conoregan.watch2getherapi.exception.W2GException;
import uk.co.conoregan.watch2getherapi.http.HttpResponse;
import uk.co.conoregan.watch2getherapi.http.RequestType;
import uk.co.conoregan.watch2getherapi.http.W2GHttpClient;
import uk.co.conoregan.watch2getherapi.http.W2GUrlReader;
import uk.co.conoregan.watch2getherapi.model.CreateRoomResponse;
import uk.co.conoregan.watch2getherapi.model.PlaylistItem;
import uk.co.conoregan.watch2getherapi.util.JsonUtils;

/**
 * The class to call Watch2Gether API endpoints. See the
 * <a href="https://community.w2g.tv/t/watch2gether-api-documentation/133767">documentation</a> for more info.
 */
public class Watch2GetherApi {
    private static final String DEFAULT_W2G_API_BASE_URL = "https://api.w2g.tv/";

    private final W2GUrlReader urlReader;

    private final String baseUrl;

    /**
     * Default constructor.
     *
     * @param apiKey your Watch2Gether API key.
     */
    public Watch2GetherApi(String apiKey) {
        this(new W2GHttpClient(apiKey), DEFAULT_W2G_API_BASE_URL);
    }

    /**
     * Default constructor.
     *
     * @param apiKey  your Watch2Gether API key.
     * @param baseUrl the w2g base url.
     */
    public Watch2GetherApi(String apiKey, String baseUrl) {
        this(new W2GHttpClient(apiKey), baseUrl);
    }

    /**
     * Constructor to use a custom url reader. Use this constructor if you want to use a different http client, rather than
     * {@link W2GHttpClient}.
     *
     * @param urlReader the url reader implementation.
     */
    public Watch2GetherApi(W2GUrlReader urlReader) {
        this(urlReader, DEFAULT_W2G_API_BASE_URL);
    }

    /**
     * Constructor to use a custom url reader. Use this constructor if you want to use a different http client, rather than
     * {@link W2GHttpClient}.
     *
     * @param urlReader the url reader implementation.
     * @param baseUrl   the w2g base url.
     */
    public Watch2GetherApi(W2GUrlReader urlReader, String baseUrl) {
        this.urlReader = urlReader;
        this.baseUrl = baseUrl;
    }

    /**
     * Creates a room. All rooms that you create using the key will be owned by the account associated with the key.
     *
     * @param shareUrl          optional - the URL of a video that is preloaded in the room.
     * @param backgroundColour  optional - the background colour of the room in HTML notation, e.g. "#00ff00".
     * @param backgroundOpacity optional - the opacity of the background 0 - 100.
     * @return the details of the room created.
     *     The value of "streamkey" can be used to build the URL of the new Watch2Gether room: <code>https://w2g.tv/rooms/{streamkey}</code>.
     * @throws W2GException if there was an error making the request or mapping the response.
     */
    public CreateRoomResponse createRoom(String shareUrl, String backgroundColour, Integer backgroundOpacity) throws W2GException {
        if (backgroundOpacity != null && (backgroundOpacity < 0 || backgroundOpacity > 100)) {
            throw new IllegalArgumentException("backgroundOpacity must be between 0 and 100, if set.");
        }

        String endPoint = baseUrl + "rooms/create.json";

        Map<String, Object> jsonBody = new HashMap<>();
        Optional.ofNullable(shareUrl).ifPresent(value -> jsonBody.put("share", shareUrl));
        Optional.ofNullable(backgroundColour).ifPresent(value -> jsonBody.put("bg_color", value));
        Optional.ofNullable(backgroundOpacity).ifPresent(value -> jsonBody.put("bg_opacity", value));

        HttpResponse response = urlReader.request(endPoint, jsonBody, RequestType.POST);
        if (response.statusCode() != 200) {
            throw new W2GException(
                "There was an error creating a new room, status code: " + response.statusCode() + "body: " + response.body());
        }

        try {
            return JsonUtils.objectMapper.readValue(response.body(), CreateRoomResponse.class);
        }
        catch (JsonProcessingException e) {
            throw new W2GException(e);
        }
    }

    /**
     * Share a new item to a room to be played immediately.
     *
     * @param streamKey the room's stream key.
     * @param itemUrl   the url to add.
     * @throws W2GException if there was an error making the request or the item could not be shared to the room.
     */
    public void shareItem(String streamKey, String itemUrl) throws W2GException {
        if (streamKey == null) {
            throw new IllegalArgumentException("streamKey must not be null");
        }
        if (itemUrl == null) {
            throw new IllegalArgumentException("itemUrl must not be null");
        }

        String endPoint = baseUrl + "rooms/" + streamKey + "/sync_update";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("item_url", itemUrl);

        HttpResponse response = urlReader.request(endPoint, jsonBody, RequestType.POST);
        if (response.statusCode() != 200) {
            throw new W2GException(
                "There was an error sharing a new item, status code: " + response.statusCode() + "body: " + response.body());
        }
    }

    /**
     * Add an item to an active playlist in a room.
     *
     * @param streamKey  the room's stream key.
     * @param itemsToAdd the items to add (up to 50 at once).
     * @throws W2GException if there was an error making the request or the items could not be added to the room.
     */
    public void addToPlaylist(String streamKey, List<PlaylistItem> itemsToAdd) throws W2GException {
        if (streamKey == null) {
            throw new IllegalArgumentException("streamKey must not be null");
        }
        if (itemsToAdd == null || itemsToAdd.isEmpty()) {
            throw new IllegalArgumentException("itemsToAdd must not be null or empty");
        }
        if (itemsToAdd.size() > 50) {
            throw new IllegalArgumentException("itemsToAdd must not contain more than 50 items");
        }

        String endPoint = baseUrl + "rooms/" + streamKey + "/playlists/current/playlist_items/sync_update";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("add_items", itemsToAdd);

        HttpResponse response = urlReader.request(endPoint, jsonBody, RequestType.POST);
        if (response.statusCode() != 200) {
            throw new W2GException(
                "There was an error adding an item to the playlist, status code: " + response.statusCode() + "body: " + response.body());
        }
    }
}
