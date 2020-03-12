import pandas as pd
import os
import re
import multiprocessing as mp
import numpy as np
import lyricsgenius

agent = 'Mozilla/5.0 (Windows NT 6.0; WOW64; rv:24.0) \
        Gecko/20100101 Firefox/24.0'
headers = {'User-Agent': agent}
client_access_token = "LUl9sVGxJr623jLbPw79kFft3tdgwAu2ijT534W_2w6soOoR2H81SWT6xSIBo2J7"
genius = lyricsgenius.Genius(client_access_token)

if(os.path.exists("output.csv")):
    os.remove("output.csv")

df = pd.read_csv("../SpotifyAPI/ArtistAndSongs.csv")

def grabLyrics(row):
    song = genius.search_song(row[1], row[0])
    if song is not None:
        lyrics = re.sub("\\n", " ", song.lyrics)
        lyrics = re.sub("[\(\[].*?[\)\]]", "", lyrics)
        lyrics = re.sub('"', '', lyrics)
        return lyrics
    else:
        return None

def applyGrabLyrics(df):
    lyrics = df.apply(grabLyrics,axis=1)
    return lyrics

n_proc = np.minimum(mp.cpu_count() * 10, df.shape[0])
p = mp.Pool(n_proc)
split_dfs = np.array_split(df, n_proc)
pool_res = p.map(applyGrabLyrics, split_dfs)
p.close()
p.join()

lyrics = pd.concat(pool_res, axis=0)

df["Lyrics"] = lyrics
df = df.dropna()

#add minimum lyric count of 10characters
lyricsOutliers = df.Lyrics.apply(len) > 4000
print("median",df.Lyrics.apply(len).median())
print(df[lyricsOutliers==True])
df = df[lyricsOutliers==False]

print(df)
df.to_csv("output.csv", index=False)

