#!/bin/bash
# Run this script with:
# sudo mkdir /media/usbstick
# sudo mount /dev/sda1 /media/usbstick
# /media/usbstick/.first_run.sh

# To unmount the USB drive: 
# sudo umount /media/usbstick


# Assumes both strings have equal lengths
equal_strings() {
	left=$1
	right=$2
	
	# Initialise the default test result to false
	test_result=false
	
	# Set the test result to true if the left and right string are equal
	if [ "$left" = "$right" ]; then
		echo "Strings are equal."
		test_result=true
	else
	    	echo "Strings are not equal."
	fi
	
	# Output true or false to pass the equality test result to parent function
	echo $test_result
}


# Assumes the actual result is shorter than the allowed result
right_is_in_tail_of_left() {
	left=$1
	right=$2
	right_size=${#right}
	left_tail_chars=$(echo -n $left | tail -c $right_size)

	# Output true or false to pass test result to parent function
	echo $(equal_strings "$left_tail_chars" "$right")
}


# Makes the main script runnable, removes the log file and runs main file.
actual_result_has_any_allowed_result_in_tail() {
	# declare default function output
	test_result=false
	
	# get the actual result
	actual_result=$1
	shift # Shift all arguments to the left (original $1 gets lost)
	
	# get the list of allowed results and list size
	allowed_results=("$@") 
	list_size=${#allowed_results}

	# Only engage this function if the list size is greater than 1
	if [ $list_size -gt 1 ]; then echo "error";
	
		# if the actual result is in the acceptable list ensure function returns true
		for allowed_result in "${allowed_results[@]}"
		do
			if [ $test_result != true ]; then
				# compute lengths of result strings
				allowed_result_size=${#allowed_result}
				actual_result_size=${#actual_result}
				
				# TODO: remove this if condition and directly go to Else by switching lt to ge
				if [ $actual_result_size -lt $allowed_result_size ]; then echo "error";
					echo "The actual size is:"
					echo $actual_result_size
					echo "WHEREAS allowed_result_size=" 
					echo $allowed_result_size
					echo "so actual is smaller than allowed, so do nothing"
				else 
					# test if left string is in the tail of the allowed result string
					# TODO: include contains option in separate function
					temp_test_result=$(right_is_in_tail_of_left "$actual_result" "$allowed_result");
					if [ $(echo -n $temp_test_result | tail -c 4) == true ]; then
						test_result=true
					fi
				fi
			fi
		done
	fi
		
	# Ensure the last 4/5 characters of the output of this function contains the true false evaluation.
	echo $test_result
}




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

# https://askubuntu.com/questions/323131/setting-timezone-from-terminal
#echo "Setting TimeZone..."
#export tz=`wget -qO - http://geoip.ubuntu.com/lookup | sed -n -e 's/.*<TimeZone>\(.*\)<\/TimeZone>.*/\1/p'` &&  timedatectl set-timezone $tz
#export tz=`timedatectl status| grep Timezone | awk '{print $2}'`
#echo "TimeZone set to $tz"

# A loop that checks whether the output of the update command is as expected after successfull completion.
check_output_update_command() {
	#test_result=false
	while [[ $test_result != true ]]
	do
		update_output=$(yes | sudo apt update)
		echo $update_output

		allowed_results=("Reading package lists... Building dependency tree... Reading state information... All packages are up to date."
				"packages can be upgraded. Run 'apt list --upgradable' to see them."
			)
			
		test_result=$(actual_result_has_any_allowed_result_in_tail "$update_output" "${allowed_results[@]}")
		test_result=${test_result: -4}
		echo $test_result
	done
}
check_output_update_command "$@"
#read -p "Succesfully completed update command <type enter>" next

# A loop that checks whether the output of the upgrade command is as expected after successfull completion.
check_output_upgrade_command() {
	#output_ending=false
	while [[ $output_ending != " upgraded." ]]
	do
		upgrade_output=$(yes | sudo apt upgrade)
		echo $upgrade_output

		
		#output_ending=$(tail -c 11 $upgrade_output)
		output_ending=${upgrade_output: -10}
		echo $output_ending
	done
}
check_output_upgrade_command "$@"
#read -p "Succesfully completed upgrade command <type enter>" next


# Set up robust ssh
# Append hiddenservice for ssh to torrc file
# TODO: check if line already exists
yes | sudo apt install net-tools
yes | sudo apt install tor

echo -e "\n\nPlease wait a minute while we set up ssh access over tor for you, you will be asked how to proceed when we're done."
last_two_lines=$(sudo tail -n 2 /etc/tor/torrc)
second_last_line=$(echo $last_two_lines | sudo head -n 1)
last_line=$(sudo tail -n 1 /etc/tor/torrc)
if [ "$second_last_line" != "HiddenServiceDir /var/lib/tor/other_hidden_service/" ]; then
	if [ "$last_line" != "HiddenServicePort 22" ]; then
		echo 'HiddenServiceDir /var/lib/tor/other_hidden_service/' | sudo tee -a /etc/tor/torrc
		echo 'HiddenServicePort 22' | sudo tee -a /etc/tor/torrc
	fi
fi

# Verify tor application is started
delay_counter=0
while [ "${grepped_activated_tor:0:3}" != "tcp" ]; do
	# start tor service
	sudo systemctl restart tor
	sudo killall tor
	nohup sudo tor &
	# TODO: read nohup.txt to determine when 100% is in file, indicating tor is bootstrapped
	# TODO: remove the hardcoded 5 second pause
	
	netstat_response=$(netstat -ano)
	grep_listen=$(echo "$netstat_response" | grep LISTEN)
	grepped_activated_tor=$(echo "$netstat_response" | grep 9050)
	#echo "grepped_activated_tor=$grepped_activated_tor" >&2
	
	((delay_counter=delay_counter+1))
	sleep $delay_counter
done

# verify tor connection is established
while [ "$grepped" != "Congratulations." ]; do

	response=$(curl --socks5 localhost:9050 --socks5-hostname localhost:9050 -s https://check.torproject.org/)
	grepped=$(echo "$response" | tr ' ' '\n' | grep -m 1 Congratulations)
	#echo "grepped=$grepped"
	sleep 10
done

# Ask if user wants to display address to copy it manually, or physically or using non-tor ssh
echo -e "\nNice, you have set up tor connections successfully. Now you can ssh into/control this SUB-device from another CONTROLLER-device anywhere in the world, though its onion address. "
echo "IMPORTANT: this address is like your password, keep it SECRET. "
echo -e "To get it, you have the following options:\n"
echo -e " - (0) Print the onion address to screen now, and manually type it over in your other CONTROLLER-device.\n"
echo " - (1) Copy the file with the onion address to a USB drive that you plugged into this SUB-device. To do this you can use the following commands, assuming you mounted the USB drive with:sudo mount /dev/sda1 /media"
echo "            sudo cp /var/lib/tor/other_hidden_service/hostname /media/hostname.txt"
echo "            sudo umount /dev/sda1"
echo "then unplug your USB drive."
echo -e "Next, put the USB drive in the CONTROLLER-device,  and read/copy the onion address that is in the file hostname.txt in the USB drive.\n"
echo " - (2) You can also get the onion domain  directly SSH into this SUB-device from another CONTROLLER-device if they are on the same network. To do that, open a terminal on your other device and type:"
echo "            ssh $(whoami)@$(echo $(hostname -I) | cut -f1 -d' ')"
echo "or, if that does not work, try:"
echo "            ssh $(whoami)@$(echo $(hostname -I) | cut -d' ' -f2-)"
echo "when prompted for login, type y and/or Enter the password of the Ubuntu account of this SUB-device to login."
echo "After logging in, type (in the ssh terminal of your CONTROLLER-device (that is actually accessing your SUB-device)):"
echo "            sudo cp /var/lib/tor/other_hidden_service/hostname /media/hostname.txt"
echo -e "then copy that <someonionaddress.onion> address.\n"


while true; do
	read -p "Do you want to do option 0(y/n) (watch out for shoulder peeking)?" yn
	case $yn in
	[Yy]* ) 
		sudo cat /var/lib/tor/other_hidden_service/hostname;
		break;;
	[Nn]* ) 
			echo "Ok, you can use option 1 or 2  yourself to get the ssh onion address of this device."; 
			break;;
	* ) echo "Please answer yes or no.";;
	esac
done

while true; do
	read -p "Do you have the onion address of this device(y/n)?" yn
	case $yn in
	[Yy]* ) 
		echo "excellent, to safely and anonymously control this SUB-device from from wherever, use the following commands on a MAIN-device:"
		echo "            sudo apt install tor"
		echo "            sudo apt install torify"
		echo "            sudo systemctl restart tor"
		echo "            sudo tor"
		echo "            torify ssh $(whoami)@<your_onion_domain>.onion"
		break;;
	[Nn]* ) 
		echo "You can use option 1 or 2  yourself to get the ssh onion address of this device.  Please do that.";
		#break;;
		;;
	* ) echo "Please answer yes or no.";;
	esac
done