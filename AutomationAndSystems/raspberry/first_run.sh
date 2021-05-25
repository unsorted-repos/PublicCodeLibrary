#!/bin/bash
# Run this script with:
# sudo mkdir /media/usbstick
# sudo mount /dev/sda1 /media/usbstick
# to unmount: (sudo umount /media/usbstick)
# /media/usbstick/.first_run.sh

# Verify internet access is available
while [ $(ping -q -w1 -c1 google.com &>/dev/null && echo online || echo offline) == offline ]
do

	sleep 1
	# get wifi module
	module=$(ls /sys/class/net)
	module_list=($module)
	wlan_module=${module_list[-1]}
	echo $wlan_module

	# get wifi configuration file
	config_filename=$(ls /etc/netplan)
	echo $config_filename
	config_filepath="/etc/netplan/$$config_filename"
	
	# check if the wifi is already added.
	if ! grep -q "$wlan_module" "$config_filepath"; then
		echo "have to add wifi"

		# ask  wifi pwd ssid
		read -p "What is the wifi ssid you want to connect to?" ssid

		# ask wifi pwd
		read -p "What is the wifi pwd?" pwd

		# append content
		sudo echo "    wifis:" >> $config_filepath 
		sudo echo "        wlan0:" >> $config_filepath
		sudo echo "            dhcp4: true" >> $config_filepath
		sudo echo "            optional: true" >> $config_filepath
		sudo echo "            access-points:" >> $config_filepath
		sudo echo "                \"$ssid\":" >> $config_filepath
		sudo echo "                    password: \"$pwd\"" >> $config_filepath

		# generate a config file
		sudo netplan generate

		# apply the config file
		sudo netplan apply
		
	fi
done

# get git
cd ~
git clone https://github.com/HiveMinds-EU/Productivity-setup.git

# update and upgrade
sudo apt update
sudo apt upgrade

# update and upgrade twice to allow for delays
sudo apt update
sudo apt upgrade

# install conda
wget https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-armv7l.sh -O ~/miniconda.sh
/home/$USER/./miniconda.sh