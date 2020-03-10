import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    } catch (IOException e) {

      e.printStackTrace();
    }
  }
}
