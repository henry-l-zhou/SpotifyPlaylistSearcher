import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SpotifyAPIPlaylist extends SpotifyAPI {
  //  private static final String accessToken =
  //      "BQDAtQzJc0gJS-qYuNdlMEU9SDGFlr0N3voJYsw5rOuXBas85_Ctciifx1X0smRd0m0xBSrlzfOupo9IxYM";
  private static final String playlistId = "6hCtSk1sPDFctoc9cvuWqO";

  public SpotifyAPIPlaylist() {

  }

  private static final SpotifyApi spotifyApi =
      new SpotifyApi.Builder().setAccessToken(SpotifyAPI.clientCredentials_Sync()).build();
  private static final GetPlaylistsTracksRequest getPlaylistsTracksRequest =
      spotifyApi.getPlaylistsTracks(playlistId)
          //          .fields("description")
          .limit(100)
          .offset(0)
          //          .market(CountryCode.SE)
          .build();

  public static void getPlaylistsTracks_Sync() {
    try {
      final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();

      System.out.println("Total: " + playlistTrackPaging.getTotal());
      for (PlaylistTrack playlistTrack : playlistTrackPaging.getItems()) {
        System.out.print(playlistTrack.getTrack().getName() + " ||| ");
        System.out.print(playlistTrack.getTrack().getArtists()[0].getName());

        System.out.println();
      }
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getPlaylistsTracks_Async() {
    try {
      final CompletableFuture<Paging<PlaylistTrack>> pagingFuture =
          getPlaylistsTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<PlaylistTrack> playlistTrackPaging = pagingFuture.join();

      System.out.println("Total: " + playlistTrackPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getPlaylistsTracks_Sync();
  }
}
