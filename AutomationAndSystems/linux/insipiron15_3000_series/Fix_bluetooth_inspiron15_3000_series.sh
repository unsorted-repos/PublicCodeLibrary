#Verified
# source: https://askubuntu.com/questions/1091888/bluetooth-not-finding-any-devices-ubuntu-18-04
dmesg | grep -i 'blue'
# says:
# [   13.077437] Bluetooth: hci0: BCM: Patch brcm/BCM43142A0-0a5c-21d7.hcd not found
curl: https://github.com/winterheart/broadcom-bt-firmware/blob/master/brcm/BCM43142A0-0a5c-21d7.hcd?raw=true
#BCM43142A0-0a5c-21d7.hcd
sudo cp  BCM43142A0-0a5c-21d7.hcd /lib/firmware/brcm/BCM43142A0-0a5c-21d7.hcd

sudo modprobe -r btusb
sudo modprobe btusb

# restart again WORKED (no reboot need, works already without reboot).
