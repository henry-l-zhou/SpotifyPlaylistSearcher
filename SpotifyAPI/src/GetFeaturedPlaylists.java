import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.browse.GetListOfFeaturedPlaylistsRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;

public class GetFeaturedPlaylists {
  private static final SpotifyApi spotifyApi =
      new SpotifyApi.Builder().setAccessToken(SpotifyAPI.clientCredentials_Sync()).build();

  private static GetListOfFeaturedPlaylistsRequest getListOfFeaturedPlaylistsRequest;

  public static FeaturedPlaylists getListOfFeaturedPlaylists_Sync() {
    try {
      final FeaturedPlaylists featuredPlaylists = getListOfFeaturedPlaylistsRequest.execute();

      return featuredPlaylists;
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return null;
  }

  public static void main(String[] args) throws IOException {
    HashSet<String> ids = new HashSet<String>();

    Writer csvWriter = new OutputStreamWriter(new FileOutputStream("FeaturedPlaylistIDS.csv", true),
        StandardCharsets.UTF_8);


    for (int i = 0; i < 30; i++) {
      long day = 1579478400000L - (90000000L * i);
      getListOfFeaturedPlaylistsRequest = spotifyApi.getListOfFeaturedPlaylists()
          .country(CountryCode.US).limit(50).timestamp(new Date(day)).build();
      for (PlaylistSimplified playlist : getListOfFeaturedPlaylists_Sync().getPlaylists()
          .getItems()) {
        ids.add(playlist.getId());
      }
    }

    for (String id : ids) {
      csvWriter.append(id);
      csvWriter.append("\n");
    }

    csvWriter.flush();
    csvWriter.close();
  }

}
