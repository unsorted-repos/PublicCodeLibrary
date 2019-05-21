package autoInstallTaskwarrior;

public class ExtraFeatures {

	public static void installGoogleCalendarSync() {
//		SUCCESSFULL on fresh install (after automated tw install)! retry: (Important, run this before modifying .bashrc with the tw installation)
//		https://blog.smallsec.ca/2017/09/07/installing-python3-6-on-wsl/
//		cd ~
//		sudo nano .bashrc
//		(Clear the programmatically added lines of the automated installation process (till "task sync"))
//		sudo add-apt-repository ppa:jonathonf/python-3.6
//		sudo apt update
//		yes | sudo apt install python3.6 python3-pip
//		sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.5 1
//		sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.6 2
//		sudo -H pip3 install --upgrade pip
//		sudo -H pip3 install virtualenv virtualenvwrapper
//		export WORKON_HOME=$HOME/.virtualenvs                    
//		export PROJECT_HOME=/mnt/c/Users/a/Code         
//		export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3.6       
//		source /usr/local/bin/virtualenvwrapper.sh
//		source ~/.bashrc
//		python3 -V
//		
//		cd ~
//		mkdir gCalSync
//		cd gCalSync
//		git clone https://github.com/bergercookie/taskw_gcal_sync.git
//		cd taskw_gcal_sync
//		SKIPPED: pip3 install --user --upgrade requirements.txt (returns error: could not find version that satisfies requirements.txt)
//		pip3 install --user --upgrade taskw_gcal_sync
//		./tw_gcal_sync --help (when installed as root)
//		tw_gcal_sync --help (SUCCESSFULL! for non-root)
//		tw_gcal_sync	
//		task add due:2019-06-01T13:01 tag:remindme testtask
//		./tw_gcal_sync -c "TW Reminders" -t remindme
//		python3 /home/a/gCalSync/taskw_gcal_sync/tw_gcal_sync -c "TW Reminders" -t remindme
		//Completely successfull and verified in Ubuntu18.04 (problem, tw not automatically installed after script.)
	}

	// auto install timewarrior

	// export installation data to log (without certificate key generation, without
	// ansi-random art).

	// auto install adb android
	/**
	 * source: http://sonntam.github.io/build-adb/ cd ~ mkdir android cd android
	 * sudo apt-get install git build-essential libncurses5-dev git clone
	 * https://android.googlesource.com/platform/system/core.git system/core git
	 * clone https://android.googlesource.com/platform/build.git git clone
	 * https://android.googlesource.com/platform/external/zlib.git external/zlib git
	 * clone https://android.googlesource.com/platform/bionic.git git clone
	 * https://android.googlesource.com/platform/external/stlport.git
	 * external/stlport git clone
	 * https://android.googlesource.com/platform/external/libcxx.git external/libcxx
	 * git clone https://android.googlesource.com/platform/external/openssl.git
	 * external/openssl Problem: requires python version of windows to be identical.
	 * (which requires users to enter additional commands outside the WSL, or reboot
	 * their entire pc)
	 * 
	 */

	/**
	 * retry: sudo apt-get install adb
	 */

}
