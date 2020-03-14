@ECHO OFF
cd SpotifyAPI
java -jar ExportTracksToCSV.jar
cd ..\lyric-grabber
python lyric_grabber_henry.py
PAUSE