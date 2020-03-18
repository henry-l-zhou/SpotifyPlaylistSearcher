from config import config
import psycopg2
import pandas as pd

if __name__ == '__main__':	
	try:
		params = config()
		conn = psycopg2.connect(**params)
		cur = conn.cursor()

		sql = """INSERT INTO spotifysongs (name, artist, lyrics) 
				VALUES (%s, %s, %s) 
				ON CONFLICT (name)
				DO 
				UPDATE 
				SET artist = EXCLUDED.artist, lyrics = EXCLUDED.lyrics"""

		df = pd.read_csv("output.csv", encoding = 'utf-8')

		for i in range(df.shape[0]):
			cur.execute(sql, (df.iloc[i]["Song Name"], df.iloc[i]["Artist"], df.iloc[i]["Lyrics"], ))
		conn.commit()
		cur.close()
	except (Exception) as error:
		print (error)
	finally:
		if conn is not None:
			conn.close()