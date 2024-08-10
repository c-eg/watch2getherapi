package uk.co.conoregan.watch2getherapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for response returned by the Watch2Gether API when creating a room.
 */
public class CreateRoomResponse extends AbstractJsonMapping {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("streamkey")
    private String streamKey;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("persistent")
    private Boolean persistent;

    @JsonProperty("persistent_name")
    private String persistentName;

    @JsonProperty("deleted")
    private Boolean deleted;

    @JsonProperty("moderated")
    private Boolean moderated;

    @JsonProperty("location")
    private String location;

    @JsonProperty("stream_created")
    private Boolean streamCreated;

    @JsonProperty("background")
    private String background;

    @JsonProperty("moderated_background")
    private Boolean moderatedBackground;

    @JsonProperty("moderated_playlist")
    private Boolean moderatedPlaylist;

    @JsonProperty("bg_color")
    private String backgroundColor;

    @JsonProperty("bg_opacity")
    private Double backgroundOpacity;

    @JsonProperty("moderated_item")
    private Boolean moderatedItem;

    @JsonProperty("theme_bg")
    private String themeBackground;

    @JsonProperty("playlist_id")
    private Integer playlistId;

    @JsonProperty("members_only")
    private Boolean membersOnly;

    @JsonProperty("moderated_suggestions")
    private Boolean moderatedSuggestions;

    @JsonProperty("moderated_chat")
    private Boolean moderatedChat;

    @JsonProperty("moderated_user")
    private Boolean moderatedUser;

    @JsonProperty("moderated_cam")
    private Boolean moderatedCam;

    public Integer getId() {
        return id;
    }

    public String getStreamKey() {
        return streamKey;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Boolean getPersistent() {
        return persistent;
    }

    public String getPersistentName() {
        return persistentName;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Boolean getModerated() {
        return moderated;
    }

    public String getLocation() {
        return location;
    }

    public Boolean getStreamCreated() {
        return streamCreated;
    }

    public String getBackground() {
        return background;
    }

    public Boolean getModeratedBackground() {
        return moderatedBackground;
    }

    public Boolean getModeratedPlaylist() {
        return moderatedPlaylist;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public Double getBackgroundOpacity() {
        return backgroundOpacity;
    }

    public Boolean getModeratedItem() {
        return moderatedItem;
    }

    public String getThemeBackground() {
        return themeBackground;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public Boolean getMembersOnly() {
        return membersOnly;
    }

    public Boolean getModeratedSuggestions() {
        return moderatedSuggestions;
    }

    public Boolean getModeratedChat() {
        return moderatedChat;
    }

    public Boolean getModeratedUser() {
        return moderatedUser;
    }

    public Boolean getModeratedCam() {
        return moderatedCam;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CreateRoomResponse that = (CreateRoomResponse) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CreateRoomResponse{" +
            "id=" + id +
            ", streamKey='" + streamKey + '\'' +
            ", createdAt='" + createdAt + '\'' +
            ", persistent=" + persistent +
            ", persistentName='" + persistentName + '\'' +
            ", deleted=" + deleted +
            ", moderated=" + moderated +
            ", location='" + location + '\'' +
            ", streamCreated=" + streamCreated +
            ", background='" + background + '\'' +
            ", moderatedBackground=" + moderatedBackground +
            ", moderatedPlaylist=" + moderatedPlaylist +
            ", backgroundColor='" + backgroundColor + '\'' +
            ", backgroundOpacity=" + backgroundOpacity +
            ", moderatedItem=" + moderatedItem +
            ", themeBackground='" + themeBackground + '\'' +
            ", playlistId=" + playlistId +
            ", membersOnly=" + membersOnly +
            ", moderatedSuggestions=" + moderatedSuggestions +
            ", moderatedChat=" + moderatedChat +
            ", moderatedUser=" + moderatedUser +
            ", moderatedCam=" + moderatedCam +
            '}';
    }
}
