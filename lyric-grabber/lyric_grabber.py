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
    return song.lyrics if song is not None else None

def applyGrabLyrics(df):
    lyrics = df.apply(grabLyrics,axis=1)
    print(type(lyrics))
    return lyrics

n_proc = mp.cpu_count() * 10
p = mp.Pool(n_proc)
split_dfs = np.array_split(df, n_proc)
pool_res = p.map(applyGrabLyrics, split_dfs)
p.close()
p.join()

lyrics = pd.concat(pool_res, axis=0)

df["Lyrics"] = lyrics

print(df)
df.to_csv("output.csv", index=False)
