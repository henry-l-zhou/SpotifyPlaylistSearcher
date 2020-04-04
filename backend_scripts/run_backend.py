import os
import time
# list = [
# "5yT0DxT7WONbDQQBmrtgiL",
# "0Wou4BLxlWeNhswjKAd3bn",
# "1p7aCCYPFxj01AY7Sexaha",
# "52gw4YtVcOHG699cho5pHK",
# "07bSHE5UKtVRxXtArIazJS",
# "3LZxKOF6YZG2eaNs2ZJscR",
# "6JTjZYkJtfDLvFF30GU0if",
# "4Q8FtsVA5mvhaeMuVgqwGA",
# "0RpC5VDn7jNr3C6EBYKsIg",
# "2AdieyzUOt87wTMJNuYqwp",
# "66UMsfBXCReX9ISaWbt7rF",
# "1elML6yfu3QSghfpBiL3Ce",
# "5FsOnMva6vkdOfexAzN6Vx",]
start_time = time.time()

list = [line.rstrip('\n') for line in open("input.txt")]

for i in range(len(list)):
	try:
		#runs all the backend files
		os.system("run_backend.bat " + list[i])

		#deletes the playlist id in input.txt
		with open('input.txt', 'r') as fin:
		    data = fin.read().splitlines(True)
		with open('input.txt', 'w') as fout:
		    fout.writelines(data[1:])
	except:
		print (str(i) + " Playlist IDS were inserted into db.")
		e = int(time.time() - start_time)
		print('{:02d}:{:02d}:{:02d}'.format(e // 3600, (e % 3600 // 60), e % 60))
		break
#os.system("run_backend.bat 4Q8FtsVA5mvhaeMuVgqwGA")