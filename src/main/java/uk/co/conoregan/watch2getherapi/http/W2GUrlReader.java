package uk.co.conoregan.watch2getherapi.http;

import java.util.Map;

import uk.co.conoregan.watch2getherapi.exception.W2GException;

/**
 * Interface for reading URLs from Watch2Gether.
 */
public interface W2GUrlReader {
    /**
     * Makes a request and returns the response.
     *
     * @param url         the url to make the request to
     * @param bodyParams  the json body to send with the request
     * @param requestType the type of request to make
     * @return the response
     */
    HttpResponse request(String url, Map<String, Object> bodyParams, RequestType requestType) throws W2GException;
}
