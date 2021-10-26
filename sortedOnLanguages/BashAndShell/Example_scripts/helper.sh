#!/bin/sh

# takes in a list that is a command with multiple spaces and concatenates them to write them to file.
append_command_to_file() {
	
	# get the actual result
	filepath=$1
	shift # Shift all arguments to the left (original $1 gets lost)
	
	# get the list of allowed results and list size
	commands=("$@") 
	
	for command in "${commands[@]}"
	do
		concatenated_command=${concatenated_command:+$concatenated_command }"$command"
	done
	
	echo $concatenated_command | sudo tee -a $filepath
	echo "Added $concatenated_command to $filepath" >&2
	concatenated_command=""
			
}

# Passes commands with spaces that are appended to a file by the append_command_to_file() function.
append_restart_commands_to_file() {
	filepath=$1
	linux_username=$2
	echo "linux_username=$linux_username"
	
	command_one="sudo -i -u  $linux_username export TASKDDATA=/var/taskd"
	command_two="sudo -i -u  $linux_username taskdctl start"
	
	#source src/append_command_to_file.sh
	
	# TODO: check if file already contains command one
	append_command_to_file $filepath $command_one
	
	# TODO: check if file already contains command two
	append_command_to_file $filepath $command_two
}