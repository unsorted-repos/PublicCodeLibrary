#!/bin/bash

# ask wifi name

# ask wifi password

# get wifi module
module=$(ls /sys/class/net)
module_list=($module)
wlan_module=${module_list[-1]}
echo $wlan_module

# get wifi configuration file
config_filename=$(ls /etc/netplan)
echo $config_filename
config_filepath="/etc/netplan/$$config_filename"
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

# get git
cd ~
git clone https://github.com/HiveMinds-EU/Productivity-setup.git