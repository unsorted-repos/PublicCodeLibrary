# Importing library
import qrcode

# Data to be encoded
data = "something"

# Encoding data using make() function
img = qrcode.make(data)

# Saving as an image file
img.save("qr.png")
