#!/bin/bash
# Run this script with:
# sudo mkdir /media/usbstick
# sudo mount /dev/sda1 /media/usbstick
# to unmount: (sudo umount /media/usbstick)
# /media/usbstick/.first_run.sh

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
echo "Setting TimeZone..."
export tz=`wget -qO - http://geoip.ubuntu.com/lookup | sed -n -e 's/.*<TimeZone>\(.*\)<\/TimeZone>.*/\1/p'` &&  timedatectl set-timezone $tz
export tz=`timedatectl status| grep Timezone | awk '{print $2}'`
echo "TimeZone set to $tz"

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
read -p "Succesfully completed update command <type enter>" next

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
read -p "Succesfully completed upgrade command <type enter>" next

# install conda
wget https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-armv7l.sh -O ~/miniconda.sh
chmod +x /home/$USER/./miniconda.sh
/home/$USER/./miniconda.sh -b -p /home/$USER/miniconda
read -p "Installed miniconda <type enter>" next

echo "PATH=$PATH:$HOME/miniconda/bin" >> .bashrc
read -p "Added miniconda to bashrc <type enter>" next