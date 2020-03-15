@ECHO OFF
cd SpotifyAPI
java -jar ExportTracksToCSV.jar 1icxxGoB6C0jeOcbMabZLV
cd ..\lyric-grabber
python lyric_grabber_henry.py
cd ..\lyric-searcher
python lyricsearch.py
PAUSE