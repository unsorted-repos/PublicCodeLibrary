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
libreoffice \
firefox \
git \
gnome-screenshot \
keepass2 \
openjdk-8-jre \
openvpn \
p7zip-full \
privoxy \
python3-pip \
python-pip \
remmina \
slack \
searchmonkey \
texlive-xetex \
texmaker \
vlc \
wine \
-y


myecho () {
	echo $1
	echo $($NC)
}


	
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
# to (or whatever your local ip adress is iso 192.168.0.208):
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
# address=/dan.com/users

# Source: https://itsfoss.com/no-sound-libreoffice-impress/
# ensure libreoffice is able to export impress presentations as video
sudo apt-get install libreoffice-avmedia-backend-gstreamer


# Source:https://askubuntu.com/questions/237027/how-to-record-screen-and-internal-audio
# screenrecorder with audio
yes | sudo add-apt-repository ppa:maarten-baert/simplescreenrecorder
sudo apt-get update
sudo apt-get install simplescreenrecorder

# rufus equivalent for linux
yes | sudo add-apt-repository ppa:gezakovacs/ppa
sudo apt-get update
sudo apt-get install unetbootin


# Optional: virtualbox
sudo apt-get install virtualbox
curl https://download.virtualbox.org/virtualbox/6.1.16/virtualbox-6.1_6.1.16-140961~Ubuntu~eoan_amd64.deb

# Eclipse
yes | sudo apt update
yes | sudo apt install default-jre
yes | sudo snap install --classic eclipse

# notepad++
sudo snap install notepad-plus-plus

# discord
sudo snap install discord

# install audacity to be able to apply manual noise reduction
yes | sudo snap install audacity
sudo snap connect audacity:alsa
# Example of noise reduction with audacity
# https://www.techsmith.com/blog/not-late-reduce-audio-noise-recordings-free/
# TODO: setup automatic noise reduction on calls

# Skype
#sudo snap install skyp --classic

# audacity

#  instal auto generator of python documentation docstrings
pip install pyment

# install auto generator of python html documentation based on docstrings in .py code.
pip install pdoc3

# Signal
# NOTE: These instructions only work for 64 bit Debian-based
# Linux distributions such as Ubuntu, Mint etc.

# 1. Install our official public software signing key
wget -O- https://updates.signal.org/desktop/apt/keys.asc |\
  sudo apt-key add -

# 2. Add our repository to your list of repositories
echo "deb [arch=amd64] https://updates.signal.org/desktop/apt xenial main" |\
  sudo tee -a /etc/apt/sources.list.d/signal-xenial.list

# 3. Update your package database and install signal
yes | sudo apt update && yes | sudo apt install signal-desktop

# Addblock
firefox addon-1865-latest.xpi

###########################
## ANACONDA ##
###########################
DIR=$HOME/Downloads
CONDADIR=$HOME/anaconda3
myecho "$($CYANE) Installing anaconda"
if [ -d $CONDADIR ]; then
	myecho "$($GREEN)Anaconda is already installed"
else
	wget -nc https://repo.anaconda.com/archive/Anaconda3-5.3.1-Linux-x86_64.sh -O Anaconda.sh
	echo "SAY YES WHEN ANACONDA ASKS IF YOU WANT TO ADD IT TO BASHRC!"
	if [ -f $DIR/Anaconda.sh ]; then
		chmod +x $DIR/Anaconda.sh && $DIR/Anaconda.sh
		bash Anaconda.sh
		export PATH=$CONDADIR/bin:$PATH
		export PATH=/home/$(whoami)/anaconda3/bin:$PATH
		#sudo ln -s $CONDADIR/etc/profile.d/conda.sh /etc/profile.d/conda.sh
		echo "conda activate" >> ~/.bashrc
		
	else
		myecho "$($RED)Couldn't find Anaconda"
	fi
fi

# DO THIS MANUALLY OR REBOOT DEVICE!
# Activate conda terminal environment
source ~/.bashrc


# to edit videos from bash
yes |  sudo conda install ffmpeg
