import pandas as pd
import os
import re
import multiprocessing as mp
import numpy as np
import lyricsgenius

df = pd.read_csv("../SpotifyAPI/ArtistAndSongs.csv", encoding = 'utf-8')
def grabLyrics(row):
    client_access_token = "LUl9sVGxJr623jLbPw79kFft3tdgwAu2ijT534W_2w6soOoR2H81SWT6xSIBo2J7"
    genius = lyricsgenius.Genius(client_access_token)
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

if __name__ == '__main__':
    n_proc = np.minimum(8, df.shape[0])
    split_dfs = np.array_split(df, n_proc)
    p = mp.Pool(n_proc)
    pool_res = p.map(applyGrabLyrics, split_dfs)
    p.close()
    p.join()

    lyrics = pd.concat(pool_res, axis=0)

    df["Lyrics"] = lyrics
    df = df.dropna()

    #add minimum lyric count of 10characters
    lyricsOutliers = df.Lyrics.apply(len) > 4000
    df = df[lyricsOutliers==False]

    print(df)
    df.to_csv("output.csv", index=False)