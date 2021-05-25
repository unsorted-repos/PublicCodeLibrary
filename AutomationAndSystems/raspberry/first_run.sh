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
	config_filepath="/etc/netplan/$config_filename"
	
	# check if the wifi is already added.
	if ! grep -q "$wlan_module" "$config_filepath"; then
		echo "have to add wifi"

		# ask  wifi pwd ssid
		read -p "What is the wifi ssid you want to connect to(Case sensitive)?" ssid

		# ask wifi pwd
		read -p "What is the wifi pwd?" pwd

		# append content
		echo "    wifis:" | sudo tee -a $config_filepath 
		echo "        wlan0:" | sudo tee -a $config_filepath
		echo "            dhcp4: true" | sudo tee -a $config_filepath
		echo "            optional: true" | sudo tee -a $config_filepath
		echo "            access-points:" | sudo tee -a $config_filepath
		echo "                \"$ssid\":" | sudo tee -a $config_filepath
		echo "                    password: \"$pwd\"" | sudo tee -a $config_filepath

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
yes | sudo apt update
yes | sudo apt upgrade

# update and upgrade twice to allow for delays
yes | sudo apt update
yes | sudo apt upgrade

# install conda
wget https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-armv7l.sh -O ~/miniconda.sh
/home/$USER/./miniconda.sh