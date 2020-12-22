#!/bin/bash

# Source: https://askubuntu.com/questions/533043/bluetooth-not-working-on-ubuntu-14-04-with-dell-inspiron-15-3521
# ans by: Diabolik2

# 0. Read the ID of your bluetooth adapter from command:
lsusb | grep Bluetooth
# returns  format of:
# Bus 001 Device 005: ID 0a5c:21d7 Broadcom Corp. BCM43142 Bluetooth 4.0
# With ID=
# 0a5c:21d7

#Source: same
# ans by: Florin C
# 1. Curl/downlaod cab file from: 
# http://drivers.softpedia.com/get/BLUETOOTH/Broadcom/Broadcom-43142-Bluetooth-40-Adapter-Driver-12007030-for-Windows-8.shtml#download
# 2. rmb extract here

# 3. go into the unpacked folder
# 4. Open the bcbtums-win8x86-brcm.inf file
# 5. Search for:21D7 till you find:

#;;;;;;;;;;;;;RAMUSB21D7;;;;;;;;;;;;;;;;;
#
#[RAMUSB21D7.CopyList]
#bcbtums.sys
#btwampfl.sys
#BCM43142A0_001.001.011.0122.0126.hex

# 6. Make sure you found the BCM43142A0_001.001.011.0122.0126.hex file

#Source: same
# ans by sb.

# 7. 
git clone git://github.com/jessesung/hex2hcd.git
cd hex2hcd
make
cd ..

# 8.  Convert the following command to work for you: ./hex2hcd /path/to/extracted.hex /where/you/want/your_new.hcd
#./hex2hcd /path/to/extracted.hex /where/you/want/your_new.hcd
# cd'ed out of the hex2hcd directory so need to prepend it to the path of the executable
./hex2hcd/hex2hcd BCM43142A0_001.001.011.0122.0126.hex fw-0a5c_21d7.hcd

# 9.a copy the new hcd file to: /lib/firmware as lib/firmware/fw-0a5c_21d7.hcd (wrong? see step 9.a)
cp  fw-0a5c_21d7.hcd "/lib/firmware as lib/firmware/"

# Source: same
# comment by: david6
# 9.b copy the new hcd file to: /lib/firmware as lib/firmware/fw-0a5c_21d7.hcd

sudo cp  fw-0a5c_21d7.hcd /lib/firmware/brcm/