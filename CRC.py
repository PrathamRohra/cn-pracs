
def xor(a, b):


	result = []


	for i in range(1, len(b)):
		if a[i] == b[i]:
			result.append('0')
		else:
			result.append('1')

	return ''.join(result)



def mod2div(dividend, divisor):


	pick = len(divisor)


	tmp = dividend[0: pick]

	while pick < len(dividend):

		if tmp[0] == '1':


			tmp = xor(divisor, tmp) + dividend[pick]

		else:
			tmp = xor('0'*pick, tmp) + dividend[pick]


		pick += 1


	if tmp[0] == '1':
		tmp = xor(divisor, tmp)
	else:
		tmp = xor('0'*pick, tmp)

	checkword = tmp
	return checkword




def encodeData(data, key):

	l_key = len(key)


	appended_data = data + '0'*(l_key-1)
	remainder = mod2div(appended_data, key)


	codeword = data + remainder
	print("Remainder : ", remainder)
	print("Encoded Data (Data + Remainder) : ",
		codeword)



print("enter data and key")
print("Data :")
data = input()
print("Key:")
key = input()
encodeData(data, key)



# OUTPUT
# D:\Varun\CN\venv\Scripts\python.exe D:\Varun\CN\CRC.py

# enter data and key
# Data :
# 1001001
# Key:
# 1011
# Remainder :  010
# Encoded Data (Data + Remainder) :  1001001010