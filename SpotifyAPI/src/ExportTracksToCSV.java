import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

    ArrayList<ArtistSong> tracks = (ArrayList<ArtistSong>) playlist.getTracks();

    try {
      Writer csvWriter = new OutputStreamWriter(new FileOutputStream("ArtistAndSongs.csv"),
          StandardCharsets.UTF_8);
      csvWriter.append("Artist,Song Name");
      csvWriter.append("\n");
      for (ArtistSong data : tracks) {
        csvWriter.append('"' + data.artist + '"' + ',' + '"' + data.song + '"');
        System.out.println('"' + data.artist + '"' + ',' + '"' + data.song + '"');
        csvWriter.append("\n");
      }

      csvWriter.flush();
      csvWriter.close();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}
