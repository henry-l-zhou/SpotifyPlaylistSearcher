import pandas as pd
import os
import requests
import re
from bs4 import BeautifulSoup

agent = 'Mozilla/5.0 (Windows NT 6.0; WOW64; rv:24.0) \
        Gecko/20100101 Firefox/24.0'
headers = {'User-Agent': agent}

if(os.path.exists("output.csv")):
    os.remove("output.csv")

df = pd.read_csv("file.csv")

def lyrics(artist, title):
    base = "https://azlyrics.com/"
    artistURI = re.sub('[\W_]+', '', artist.lower())
    titleURI = re.sub('[\W_]+', '', title.lower())

    url = base + 'lyrics/' + artistURI + '/' + titleURI + '.html'
    req = requests.get(url, headers=headers)
    soup = BeautifulSoup(req.content, "html.parser")
    l = soup.find_all("div", attrs={"class": None, "id": None})
    if not l:
        return "Unable to find " + title + " by " + artist
    else:
        l = [x.getText() for x in l]
        l = re.sub('', '', l)
        return l

def grabLyrics(row):
    return lyrics(row[1],row[0])
lyrics = df.apply(grabLyrics, axis=1)

df["Lyrics"] = lyrics

print(df)
df.to_csv("output.csv", index=False)
