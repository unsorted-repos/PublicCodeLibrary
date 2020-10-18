#!/bin/bash

# first reset Ubuntu to factory defaults:
# https://ostechnix.com/reset-ubuntu-factory-defaults/

# Then open terminal in this directory and type:
# chmod +x ubuntu20_postsetup.sh
# to make this script executable

# Define colors for human readible outputs
RED='tput setaf 1'
GREEN='tput setaf 2'
CYANE='tput setaf 6'
NC='tput sgr0'
BOOL=true

sudo apt update && upgrade

sudo apt install \
anki \
compiz \
compiz-plugins \
discord \
libreoffice \
firefox \
git \
gnome-screenshot \
keepass2 \
openjdk-8-jre \
openvpn \
p7zip-full \
privoxy \
remmina \
slack \
texlive-xetex \
texmaker \
vlc \
-y


myecho () {
	echo $1
	echo $($NC)
}


	###########################
	## ANACONDA ##
	###########################
	DIR=$HOME/Downloads
	CONDADIR=$HOME/anaconda3
	myecho "$($CYANE) Installing anaconda"
	if [ -d $CONDADIR ]; then
		myecho "$($GREEN)Anaconda is already installed"
	else
		wget -nc https://repo.continuum.io/archive/Anaconda3-5.0.0.1-Linux-x86_64.sh -O Anaconda.sh
		if [ -f $DIR/Anaconda.sh ]; then
			chmod +x $DIR/Anaconda.sh && $DIR/Anaconda.sh
		else
			myecho "$($RED)Couldn't find Anaconda"
		fi
	fi
	
# TODO:
# veracrypt

# EnergizedPro
# first clone the repo
#sudo chmod +x energized.sh
#-p
#-s

# Source: https://askubuntu.com/questions/150135/how-to-block-specific-domains-in-hosts-file#150180
## privoxy setup
#sudo nano /etc/privoxy/config
# change:
#listen-address localhost:8118
# to:
#listen-adress 192:168.0.208:8118
# restart proxy server
#sudo /etc/init.d/privoxy restart
# Doesnt work with energized

# dnsmasq
# sudo apt-get install dnsmasq
# Doesnt work with energized, so run `energized.sh` and type c`. Then you can use dnsmasq. 
# sudo nano -c /etc/dnsmasq.conf 
# add line:`port=5353` or whatever port you wanna use if `sudo dnsmasq`throws an error.
# add line:`address=127.0.0.1` if `sudo dnsmasq`throws an error.
# Allow:
# https://dan.com/search?terms=
# Block:
# https://dan.com/users/login
# then enter:
address=/dan.com/users
