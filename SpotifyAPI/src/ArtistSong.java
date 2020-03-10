
public class ArtistSong {
  String artist;
  String song;
  
  public ArtistSong(String artist, String song) {
    this.artist = artist;
    this.song = song;
  }
  
  public String toString() {
    return artist + " " + song;
  }
}
