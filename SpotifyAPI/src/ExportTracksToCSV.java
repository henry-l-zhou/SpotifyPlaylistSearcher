import java.io.FileWriter;
import java.util.ArrayList;
/**
 * Exports the songs in (ARTIST, SONG) format to a .csv
 * @author zhouh
 *
 */
public class ExportTracksToCSV {
  public static void main(String[] args) {
    SpotifyAPIPlaylist playlist = new SpotifyAPIPlaylist();
    ArrayList<ArtistSong> tracks = (ArrayList<ArtistSong>) playlist.getTracks();

    try {
      FileWriter csvWriter = new FileWriter("ArtistAndSongs.csv");
      csvWriter.append("Artist, Song Name");
      csvWriter.append("\n");
      for (ArtistSong data : tracks) {
        csvWriter.append('"' + data.artist + '"' + ',' + '"' + data.song + '"');
        csvWriter.append("\n");
      }

      csvWriter.flush();
      csvWriter.close();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}
