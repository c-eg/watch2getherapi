package uk.co.conoregan.watch2getherapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import uk.co.conoregan.watch2getherapi.exception.W2GException;
import uk.co.conoregan.watch2getherapi.http.W2GHttpClient;
import uk.co.conoregan.watch2getherapi.model.CreateRoomResponse;
import uk.co.conoregan.watch2getherapi.model.PlaylistItem;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WireMockTest
public class Watch2GetherApiTest {
    @Test
    public void testCreateRoom(WireMockRuntimeInfo wmRuntimeInfo) throws W2GException, IOException {
        String responseBody = TestUtils.readTestFile("api_responses/create_room.json");
        stubFor(post("/rooms/create.json").willReturn(ok(responseBody)));
        Watch2GetherApi api = new Watch2GetherApi(new W2GHttpClient("api_key"), wmRuntimeInfo.getHttpBaseUrl() + "/");

        CreateRoomResponse createRoomResponse = api.createRoom("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "#ff99ff", 50);
        assertNotNull(createRoomResponse);
    }

    @Test
    public void testShareItem(WireMockRuntimeInfo wmRuntimeInfo) throws W2GException {
        String streamKey = "streamKey";
        stubFor(post("/rooms/" + streamKey + "/sync_update").willReturn(ok()));
        Watch2GetherApi api = new Watch2GetherApi(new W2GHttpClient("api_key"), wmRuntimeInfo.getHttpBaseUrl() + "/");

        api.shareItem(streamKey, "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    }

    @Test
    public void testAddToPlaylist(WireMockRuntimeInfo wmRuntimeInfo) throws W2GException {
        String streamKey = "streamKey";
        stubFor(post("/rooms/" + streamKey + "/playlists/current/playlist_items/sync_update").willReturn(ok()));
        Watch2GetherApi api = new Watch2GetherApi(new W2GHttpClient("api_key"), wmRuntimeInfo.getHttpBaseUrl() + "/");

        List<PlaylistItem> items = new ArrayList<>();
        items.add(
            new PlaylistItem("https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                             "Rick Astley - Never Gonna Give You Up (Official Music Video)")
        );

        api.addToPlaylist(streamKey, items);
    }
}
