import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import java.io.IOException;
// import java.util.concurrent.*;

public class CurrentUsersPlaylists {
  private static final String userId = "1285004217";
  private static final SpotifyApi spotifyApi =
      new SpotifyApi.Builder().setAccessToken(SpotifyAPI.clientCredentials_Sync()).build();
  private static int playlistsAdded = 0;
  private static GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest;

  public static void getListOfUsersPlaylists_Sync() {
    try {
      Paging<PlaylistSimplified> playlistSimplifiedPaging;
      do {
        getListOfUsersPlaylistsRequest =
            spotifyApi.getListOfUsersPlaylists(userId).limit(20).offset(playlistsAdded).build();
        playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();
        
        for (PlaylistSimplified item : playlistSimplifiedPaging.getItems()) {
          System.out.println(item.getId());
          playlistsAdded++;
        }
      } while (playlistsAdded < playlistSimplifiedPaging.getTotal());

      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    getListOfUsersPlaylists_Sync();
  }
}
