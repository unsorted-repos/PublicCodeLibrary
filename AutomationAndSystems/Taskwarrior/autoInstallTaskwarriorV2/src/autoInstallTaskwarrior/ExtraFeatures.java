package autoInstallTaskwarrior;

public class ExtraFeatures {
		
	

	
	/**
	 * yes | sudo apt install python-pip
	 * yes sudo | pip install --upgrade pip
	 * Do not effect version 8 of pip iso version 19.
	 * 
	 * yes | sudo apt install python3-pip
	 * pip3 install --user git+https://github.com/bergercookie/taskw_gcal_sync 
	 * 
	 * Retry:
	 * cd ~
	 * mkdir gCalSync
	 * cd gCalSync
	 * python3 -V
	 * sudo apt-get install -y python3-pip
	 * sudo apt-get -y upgrade
	 * git clone https://github.com/bergercookie/taskw_gcal_sync.git
	 * pip3 install --user --upgrade requirements.txt # from the repo root dir
	 * 
	 * 
	 */
	public static void installGoogleCalendarSync() {
		
	}
	
	// install timewarrior
	
	
	// export installation data to log (without certificate key generation, without ansi-random art).
	
	// auto install adb android
	/**
	 * source: http://sonntam.github.io/build-adb/
	 * cd ~
	 * mkdir android
	 * cd android
	 * sudo apt-get install git build-essential libncurses5-dev
	 * git clone https://android.googlesource.com/platform/system/core.git system/core
	 * git clone https://android.googlesource.com/platform/build.git
	 * git clone https://android.googlesource.com/platform/external/zlib.git external/zlib
	 * git clone https://android.googlesource.com/platform/bionic.git
	 * git clone https://android.googlesource.com/platform/external/stlport.git external/stlport
	 * git clone https://android.googlesource.com/platform/external/libcxx.git external/libcxx
	 * git clone https://android.googlesource.com/platform/external/openssl.git external/openssl
	 * 
	 */
	
	/**
	 * retry:
	 * sudo apt-get install adb
	 */
	
}

