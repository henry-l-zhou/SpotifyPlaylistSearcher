import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyAPIPlaylist extends SpotifyAPI {
  // playlistID used
  private static final String playlistId = "6hCtSk1sPDFctoc9cvuWqO";

  private static List<ArtistSong> tracks = new ArrayList<ArtistSong>();
  private static int totalSongs;
  private static int songsAdded;
  private static final SpotifyApi spotifyApi =
      new SpotifyApi.Builder().setAccessToken(SpotifyAPI.clientCredentials_Sync()).build();

  public SpotifyAPIPlaylist() {
    do {
      getPlaylistsTracksRequest =
          spotifyApi.getPlaylistsTracks(playlistId).limit(100).offset(songsAdded).build();
      getPlaylistsTracks_Sync();
    } while (songsAdded < totalSongs);
  }

  public List<ArtistSong> getTracks() {
    return tracks;
  }

  private static GetPlaylistsTracksRequest getPlaylistsTracksRequest =
      spotifyApi.getPlaylistsTracks(playlistId)
          .limit(100).offset(0)
          .build();

/**
 * Calls the Spotify API to get tracks from a playlist. (MAX 100 songs can be called at once)
 */
  public static void getPlaylistsTracks_Sync() {
    try {
      final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();

      totalSongs = playlistTrackPaging.getTotal();
      for (PlaylistTrack playlistTrack : playlistTrackPaging.getItems()) {
        songsAdded++;
        tracks.add(new ArtistSong(playlistTrack.getTrack().getArtists()[0].getName(),
            playlistTrack.getTrack().getName()));
      }
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

}
