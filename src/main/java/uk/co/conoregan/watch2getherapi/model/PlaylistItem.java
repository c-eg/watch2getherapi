package uk.co.conoregan.watch2getherapi.model;

/**
 * Model for data needed when adding an item to a playlist.
 *
 * @param url the url of the video.
 * @param title the title of the video.
 */
public record PlaylistItem(String url, String title) {

}
