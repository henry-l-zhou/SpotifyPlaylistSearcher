
public class ArtistSong {
  String artist;
  String song;

  public ArtistSong(String artist, String song) {
    this.artist = artist;
    this.song = song;

    //sanitizeSongs();
  }

  public String toString() {
    return artist + " " + song;
  }

//  public void sanitizeSongs() {
//    StringBuilder sb = new StringBuilder();
//    for (int i = 0; i < song.length(); i++) {
//      if (song.substring(i, i + 1).equals("-") || song.substring(i, i + 1).equals("/")) {
//        song = sb.toString();
//        break;
//      }
//      sb.append(song.substring(i, i + 1));
//    }
//  }
}
