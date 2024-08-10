package uk.co.conoregan.watch2getherapi.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.conoregan.watch2getherapi.exception.W2GException;
import uk.co.conoregan.watch2getherapi.util.JsonUtils;

/**
 * The Watch2Gether http client. Default implementation used for {@link W2GUrlReader}.
 */
public class W2GHttpClient implements W2GUrlReader {
    private static final Logger logger = LoggerFactory.getLogger(W2GHttpClient.class);

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    private static final ObjectWriter mapWriter = JsonUtils.objectMapper.writerFor(Map.class);

    private final String apiKey;

    /**
     * Constructor.
     *
     * @param apiKey your w2g api key.
     */
    public W2GHttpClient(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public HttpResponse request(String url, Map<String, Object> bodyParams, RequestType requestType) throws W2GException {
        logger.info("W2G API: making {} request to: {}, bodyParams: {}", requestType, url, bodyParams);

        bodyParams.put("w2g_api_key", apiKey);
        String jsonBody;
        try {
            jsonBody = mapWriter.writeValueAsString(bodyParams);
        }
        catch (JsonProcessingException e) {
            throw new W2GException(e);
        }

        URI uri = URI.create(url);
        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
            .uri(uri)
            .header("Accept", "application/json")
            .header("Content-type", "application/json; charset=utf-8");

        switch (requestType) {
            case GET -> httpRequestBuilder.GET();
            case POST -> httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(jsonBody));
            case DELETE -> httpRequestBuilder.DELETE();
            default -> throw new RuntimeException("Invalid request type: " + requestType);
        }

        java.net.http.HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequestBuilder.build(), java.net.http.HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e) {
            throw new W2GException(e);
        }

        return new HttpResponse(httpResponse.statusCode(), httpResponse.body());
    }
}
