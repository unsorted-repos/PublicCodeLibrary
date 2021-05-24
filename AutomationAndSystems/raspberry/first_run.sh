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

# ask  wifi pwd ssid
read -p "What is the wifi ssid you want to connect to?" ssid

# ask wifi pwd
read -p "What is the wifi pwd?" pwd

# append content
echo "    wifis:" >> $config_filename 
echo "        wlan0:" >> $config_filename
echo "            dhcp4: true" >> $config_filename
echo "            optional: true" >> $config_filename
echo "            access-points:" >> $config_filename
echo '                "$ssid":' >> $config_filename
echo '                    password: "$pwd"' >> $config_filename

# generate a config file
sudo netplan generate

# apply the config file
sudo netplan apply