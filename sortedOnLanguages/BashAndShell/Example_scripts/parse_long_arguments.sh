#!/bin/bash

print_usage() {
	printf "\nUsage: write:"
	printf "\n\n    ./main.sh -s\nTo decide with a y/n whether you want to install a package for each supported software package."
	printf "\n\n    ./main.sh --install <list of packages separated by spaces (example on nextline)>"
	#printf "\n./install_gitlab -w\n to do an installation of the GitLab runner that waits untill the GitLab server is running."
	printf "\n    ./main.sh --install autokey zotero"
	printf "\nTo install the autokey software and zotero."
	printf "\n\n    ./main.sh -h\n    ./main.sh --help\nTo see this menu.\n\n"
	printf "The supported package argument list is:\n"
	printf "autokey\n"
	printf "brave\n"
	printf "github\n"
	printf "keepass2\n"
	printf "nordvpn\n"
	printf "openvpn3\n"
	printf "privoxy\n"
	printf "searchmonkey\n"
	printf "signal\n"
	printf "texlive\n"
	printf "texmaker\n"
	printf "wine\n"
	printf "zotero\n"
	printf "anki\n"
	printf "notepad\n"
	printf "anaconda\n"
	printf "ep_soc\n"
	printf "ep_pn\n"
	printf "ep_extra\n"
	printf "language_tool\n"
	
	printf "\n\n"
}

eat_argument_list() {
	# create array
	local -n arr=$1             # use nameref for indirection
	shift # eat the reference variable to get the original remaining argument list
	arr=() # innitialise array with branches
	
	# Loop through the remaining arguments
	while [ "${1:-}" != "" ]; do
		case "$1" in
			"autokey")
				shift # eat the remaining argument list element
				arr+=("autokey") # add autokey to array
				;;
			"brave")
				shift # eat the remaining argument list element
				arr+=("") # add to 	 array
				;;
			"github")
				shift # eat the remaining argument list element
				arr+=("github") # add to 	 array
				;;
			"keepass2")
				shift # eat the remaining argument list element
				arr+=("keepass2") # add to 	 array
				;;
			"nordvpn")
				shift # eat the remaining argument list element
				arr+=("nordvpn") # add to 	 array
				;;
			"openvpn3")
				shift # eat the remaining argument list element
				arr+=("openvpn3") # add to 	 array
				;;
			"privoxy")
				shift # eat the remaining argument list element
				arr+=("privoxy") # add to 	 array
				;;
			"searchmonkey")
				shift # eat the remaining argument list element
				arr+=("searchmonkey") # add to 	 array
				;;
			"signal")
				shift # eat the remaining argument list element
				arr+=("signal") # add to 	 array
				;;
			"texlive")
				shift # eat the remaining argument list element
				arr+=("texlive") # add to 	 array
				;;
			"texmaker")
				shift # eat the remaining argument list element
				arr+=("texmaker") # add to 	 array
				;;
			"wine")
				shift # eat the remaining argument list element
				arr+=("wine") # add to 	 array
				;;
			"zotero")
				shift # eat the remaining argument list element
				arr+=("zotero") # add zotero to array
				;;
			"anki")
				shift # eat the remaining argument list element
				arr+=("anki") # add to 	 array
				;;
			"notepad")
				shift # eat the remaining argument list element
				arr+=("notepad") # add to 	 array
				;;
			"anaconda")
				shift # eat the remaining argument list element
				arr+=("anaconda") # add to 	 array
				;;
			"ep_soc")
				shift # eat the remaining argument list element
				arr+=("ep_soc") # add to 	 array
				;;
			"ep_pn")
				shift # eat the remaining argument list element
				arr+=("ep_pn") # add to 	 array
				;;
			"ep_extra")
				shift # eat the remaining argument list element
				arr+=("ep_extra") # add to 	 array
				;;
			"language_tool")
				shift # eat the remaining argument list element
				arr+=("language_tool") # add to 	 array
				;;
		esac
	done
}

_setArgs(){
	while [ "${1:-}" != "" ]; do
		case "$1" in
			"--install")
				shift
				eat_argument_list package_list "$@"
				declare -p package_list
				;;
			"-h" | "--help")
				help_flag=true
				;;
		esac
		shift
	done
}
_setArgs  "$@"

if [ "$help_flag" == "true" ]; then
	print_usage
fi

echo "package_list=${package_list[@]}"