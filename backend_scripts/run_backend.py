import os

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

list = [line.rstrip('\n') for line in open("input.txt")]

for i in range(len(list)):
	try:
		with open('input.txt', 'r') as fin:
		    data = fin.read().splitlines(True)
		with open('input.txt', 'w') as fout:
		    fout.writelines(data[1:])
		os.system("run_backend.bat " + list[i])
	except:
		pass
#os.system("run_backend.bat 4Q8FtsVA5mvhaeMuVgqwGA")