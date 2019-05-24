#!/bin/bash
add-apt-repository ppa:jonathonf/python-3.6
echo 1
apt update
echo 2
yes | sudo apt install python3.6 python3-pip
sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.5 1
sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.6 2
sudo -H pip3 install --upgrade pip
sudo -H pip3 install virtualenv virtualenvwrapper
export WORKON_HOME=$HOME/.virtualenvs
export PROJECT_HOME=/mnt/c/Users/a/Code
export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3.6
source /usr/local/bin/virtualenvwrapper.sh
python3 -V
cd "$(/home/testlinuxname/maintenance "$0")"
git clone https://github.com/bergercookie/taskw_gcal_sync.git
cd taskw_gcal_sync
cd "$(/home/testlinuxname/maintenance/taskw_gcal_sync "$0")"
pip3 install --user --upgrade requirements.txt
pip3 install --user --upgrade taskw_gcal_sync
/home/testlinuxname/maintenance/taskw_gcal_sync/tw_gcal_sync --help
tw_gcal_sync --help
sudo ./tw_gcal_sync --help
task add due:2019-06-01T13:01 tag:remindme testtask
./tw_gcal_sync -c "TW Reminders" -t remindme
python3 /home/testlinuxname/taskw_gcal_sync/tw_gcal_sync -c "TW Reminders" -t remindme
