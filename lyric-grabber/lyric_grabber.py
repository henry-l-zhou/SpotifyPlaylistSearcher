import pandas as pd
import os
import requests
import re
from bs4 import BeautifulSoup
import multiprocessing as mp
import numpy as np

agent = 'Mozilla/5.0 (Windows NT 6.0; WOW64; rv:24.0) \
        Gecko/20100101 Firefox/24.0'
headers = {'User-Agent': agent}

if(os.path.exists("output.csv")):
    os.remove("output.csv")

df = pd.read_csv("file.csv")

def lyrics(artist, title):
    base = "https://azlyrics.com/"
    artistURI = re.sub('[\W_]+', '', artist.lower())
    titleURI = title.split('-', 1)[0].split('(', 1)[0]
    titleURI = re.sub('[\W_]+', '', titleURI.lower())

    url = base + 'lyrics/' + artistURI + '/' + titleURI + '.html'
    req = requests.get(url, headers=headers)
    soup = BeautifulSoup(req.content, "html.parser")
    l = soup.find_all("div", attrs={"class": None, "id": None})
    if not l:
        return "Unable to find " + title + " by " + artist
    else:
        l = [x.getText() for x in l]
        return l

def grabLyrics(row):
    print(row)
    return lyrics(row[1],row[0])

def applyGrabLyrics(df):
    lyrics = df.apply(grabLyrics,axis=1)
    return lyrics

n_proc = 4
p = mp.Pool(n_proc)
split_dfs = np.array_split(df, n_proc)
pool_res = p.map(applyGrabLyrics, split_dfs)
p.close()
p.join()

print(pool_res)

lyrics = pd.concat(pool_res, axis=0)

df["Lyrics"] = lyrics

print(df)
df.to_csv("output.csv", index=False)
