import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

/**
 * Exports the songs in (ARTIST, SONG) format to a .csv
 * @author zhouh
 *
 */
public class ExportTracksToCSV {
  public static void main(String[] args) {

    // if no command line argument, creates playlist based on rachel's god playlist
    // if command line present with playlistID in it, uses that instead
    SpotifyAPIPlaylist playlist =
        args.length == 0 ? new SpotifyAPIPlaylist() : new SpotifyAPIPlaylist(args[0]);



    try {
      Writer csvWriter = new OutputStreamWriter(new FileOutputStream("ArtistAndSongs.csv"),
          StandardCharsets.UTF_8);
      csvWriter.append("Artist,Song Name,Popularity");
      csvWriter.append("\n");
      for (PlaylistTrack data : playlist.getTracks()) {

        String artist = data.getTrack().getArtists()[0].getName();
        String name = data.getTrack().getName();
        Integer popularity = data.getTrack().getPopularity();

        csvWriter
            .append('"' + artist + '"' + ',' + '"' + name + '"' + ',' + '"' + popularity + '"');
        System.out
            .println('"' + artist + '"' + ',' + '"' + name + '"' + ',' + '"' + popularity + '"');
        csvWriter.append("\n");
      }

      csvWriter.flush();
      csvWriter.close();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}
