package uk.co.conoregan.watch2getherapi.http;

/**
 * Record for storing the http response.
 *
 * @param statusCode the http status code.
 * @param body the body contents.
 */
public record HttpResponse(int statusCode, String body) {

}
