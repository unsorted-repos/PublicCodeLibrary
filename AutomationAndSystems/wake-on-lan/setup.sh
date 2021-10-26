#!bin/bash
# Source: https://help.ubuntu.com/community/WakeOnLan

# 0. Install prerequisites
sudo apt install ethtool
sudo apt install net-tools
sudo apt-get install wakeonlan -y

# 0. Get the device ids that have ethernet
# Source: https://www.techrepublic.com/article/how-to-enable-wake-on-lan-in-ubuntu-server-18-04/
ifconfig -a
# shoutld output: 
#<the code/name of your NIC>: some status , mac address of the NIC, your ip,
#<some other network NICs>:..
# where <the code/name of your NIC> could be eth0, or eno1 
# Assume the NIC name is eno1 moving forwards:

# 1. Check if wake on lan is supported:
sudo ethtool eno1
# should output:
# Supports Wake-on: pubmg
# where the pubmg or whatever letters should contain the letter "g" for it to allow wake on lan

# 2. Enable wake on lan(WOL) if it is not supported:
sudo ethtool -s eno1 wol g

# 3. Get the mac address of the NIC (not of the device, but of the NIC) with:
ifconfig -a
# shoutld output: 
#<the code/name of your NIC>: some status , mac address of the NIC, your ip,
# inet6 fe80::8120:37ac:1c40:fecf
# ether 38:d5:47:79:ab:0b

# other:
# 1c:bf:ce:2a:f2:58

# 4. get the IP of the sleeping device
hostname -I

# 5. Put the device you want to wake up to sleep:
# Source: https://necromuralist.github.io/posts/enabling-wake-on-lan/
sudo systemctl suspend

# 6. REBOOT YOUR PC and AND ENABLE WAKE ON LAN IN BIOS!
# e.g. search for "LAN Option ROM" and "enable" it.

# 7. Reboot AGAIN then go to Settings, an option "wake up events" should appear
# 8. Go into that and enable "resume on PCIe" (and "resume on LAN")
# 9. Reboot again.

# 10. send the WOL command from another pc:
sudo apt install wakeonlan
wakeonlan 38:d5:47:79:ab:0b
#wakeonlan -i <ip of the sleeping device> <mac of network card of sleeping device>


# 11. If that does not work, try it with local IP address:
wakeonlan -i 123.451.2.3 38:d5:47:79:ab:0b

# 12. If that does not work, try it with gateway IP address:
wakeonlan -i 123.451.2.3 38:d5:47:79:ab:0b

# You can also inspect if the signals are coming in on the device you want to wake up by running:
sudo tcpdump -i enp4s0 '(udp and port 7) or (udp and port 9)'
# and seeing if any messages pop up if you send the wakeonlan commands.