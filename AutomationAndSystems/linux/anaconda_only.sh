#!/bin/bash
###########################
## ANACONDA ##
###########################
DIR=$PWD
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
		#echo "conda activate" >> ~/.bashrc
		
	else
		myecho "$($RED)Couldn't find Anaconda"
	fi
fi

# DO THIS MANUALLY OR REBOOT DEVICE!
# Activate conda terminal environment
source ~/.bashrc
