from azlyrics import lyrics
import pandas as pd
import os

if(os.path.exists("output.csv")):
    os.remove("output.csv")

df = pd.read_csv("file.csv")

def grabLyrics(row):
    return lyrics(row[1],row[0])
lyrics = df.apply(grabLyrics, axis=1)

df["Lyrics"] = lyrics

print(df)
df.to_csv("output.csv", index=False)
