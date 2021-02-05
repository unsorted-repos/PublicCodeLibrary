## Usage: 
"""
python
from decode_url import get_url
url(https://www.someurl.com3%F2...)
"""
# 0. open termina
# 1. browse to this directory
# 2. run python with command: 
# python
# 3. import the decoding function with command: 
# from decode_url import get_url
# 4. Decode the url you're interested in by passing it to the function
# url(https://www.someurl.com3%F2...)
# copy or click the output url.
# 5. exit the shell with command:
# exit()

encodeDic = {
		"%21": "!",
		"%23": "#",
		"%24": "$",
		"%26": "&",
		"%27": "'",
		"%28": "(",
		"%29": ")",
		"%2A": "*",
		"%2B": "+",
		"%2C": ",",
		"%2F": "/",
		"%3A": ":",
		"%3B": ";",
		"%3D": "=",
		"%3F": "?",
		"%40": "@",
		"%5B": "[",
		"%5D": "]", 
		"%20": " ",
		"%22": "\"",
		"%25": "%",
		"%2D": "-",
		"%2E": ".",
		"%3C": "<",
		"%3E": ">",
		"%5C": "\\",
		"%5E": "^",
		"%5F": "_",
		"%60": "`",
		"%7B": "{",
		"%7C": "|",
		"%7D": "}",
		"%7E": "~",
	}
    
def get_url(url):    
    for key, value  in encodeDic.items():
        #print(key,'->',value)
        url = url.replace(key,value)
    print(url)
    #return url