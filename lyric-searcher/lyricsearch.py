import csv
import re
import pandas as pd

# with open('../lyric-grabber/output.csv', newline='', encoding="utf8") as csvfile:
#     spamreader = csv.reader(csvfile)
#     for row in spamreader:
#     	if "love" in row[2]:
#     		print (row)
#     		print ("\n")
user_input="love"
df = pd.read_csv("../lyric-grabber/output.csv")
searched_list = []
for i in range(df.shape[0]):
    count = 0;
    words = df.iloc[i]["Lyrics"]
    print(df.iloc[i])
    words = re.findall(r"[\w']+|[.,!?;]", words)
    for word in words:
        if user_input in word:
            count += 1
    searched_list.append([df.iloc[i][" Song Name"], df.iloc[i]["Artist"], count])

searched_list.sort(key = lambda r:r[2], reverse = True)
print (searched_list)
