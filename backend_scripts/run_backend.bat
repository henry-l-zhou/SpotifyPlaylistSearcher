@ECHO OFF
set arg1 = %1
cd..\
cd SpotifyAPI
java -jar ExportTracksToCSV.jar %1
cd ..\lyric-grabber
python lyric_grabber_henry.py
python send_song_info.py