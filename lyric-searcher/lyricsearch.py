import csv
import re
import pandas as pd

def main():
	user_input="love"
	df = pd.read_csv("../lyric-grabber/output.csv")
	searched_list = []
	for i in range(df.shape[0]):
	    count = 0;
	    words = df.iloc[i]["Lyrics"]
	    
	    words = re.findall(r"[\w']+|[.,!?;]", words)
	    for word in words:
	        if user_input in word:
	            count += 1
	    searched_list.append([df.iloc[i][" Song Name"], df.iloc[i]["Artist"], count])

	searched_list.sort(key = lambda r:r[2], reverse = True)

	print (searched_list)

if __name__ == '__main__':
	main()
