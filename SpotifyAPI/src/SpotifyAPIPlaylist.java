import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyAPIPlaylist extends SpotifyAPI {
<<<<<<< HEAD
  //  private static final String accessToken =
  //      "BQDAtQzJc0gJS-qYuNdlMEU9SDGFlr0N3voJYsw5rOuXBas85_Ctciifx1X0smRd0m0xBSrlzfOupo9IxYM";
  private static final String playlistId = "6hCtSk1sPDFctoc9cvuWqO";

  public SpotifyAPIPlaylist() {

  }

=======
  private static final String playlistId = "6hCtSk1sPDFctoc9cvuWqO";

  private static List<ArtistSong> tracks = new ArrayList<ArtistSong>();
  private static int totalSongs;
  private static int songsAdded;
>>>>>>> 966bd8cfc5c7d74e2e42463448cd4df36613345b
  private static final SpotifyApi spotifyApi =
      new SpotifyApi.Builder().setAccessToken(SpotifyAPI.clientCredentials_Sync()).build();

  public SpotifyAPIPlaylist() {
    getPlaylistsTracks_Sync();
    while (songsAdded < totalSongs) {
      getPlaylistsTracksRequest =
          spotifyApi.getPlaylistsTracks(playlistId).limit(100).offset(songsAdded).build();
      getPlaylistsTracks_Sync();
    }
  }
  public List<ArtistSong> getTracks() {
    return tracks;
  }
  private static GetPlaylistsTracksRequest getPlaylistsTracksRequest =
      spotifyApi.getPlaylistsTracks(playlistId)
          //          .fields("description")
<<<<<<< HEAD
          .limit(100)
          .offset(0)
=======
          .limit(100).offset(0)
>>>>>>> 966bd8cfc5c7d74e2e42463448cd4df36613345b
          //          .market(CountryCode.SE)
          .build();

  public static void getPlaylistsTracks_Sync() {
    try {
      final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();

<<<<<<< HEAD
      System.out.println("Total: " + playlistTrackPaging.getTotal());
      for (PlaylistTrack playlistTrack : playlistTrackPaging.getItems()) {
        System.out.print(playlistTrack.getTrack().getName() + " ||| ");
        System.out.print(playlistTrack.getTrack().getArtists()[0].getName());

        System.out.println();
=======
      totalSongs = playlistTrackPaging.getTotal();
      for (PlaylistTrack playlistTrack : playlistTrackPaging.getItems()) {
        //        System.out.print(playlistTrack.getTrack().getName() + " ||| ");
        //        System.out.print(playlistTrack.getTrack().getArtists()[0].getName());
        songsAdded++;
        tracks.add(new ArtistSong(playlistTrack.getTrack().getArtists()[0].getName(),
            playlistTrack.getTrack().getName()));
>>>>>>> 966bd8cfc5c7d74e2e42463448cd4df36613345b
      }
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

}
