#!/bin/bash
add-apt-repository ppa:jonathonf/python-3.6
echo 1
apt update
echo 2
yes | sudo apt install python3.6 python3-pip
echo 3
sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.5 1
echo 4
sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.6 2
echo 5
sudo -H pip3 install --upgrade pip
echo 6
sudo -H pip3 install virtualenv virtualenvwrapper
echo 7
export WORKON_HOME=$HOME/.virtualenvs
echo 8
export PROJECT_HOME=/mnt/c/Users/a/Code
echo 9
export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3.6
echo 10
source /usr/local/bin/virtualenvwrapper.sh
echo 11
python3 -V
echo 12
cd "$(/home/testlinuxname/maintenance/ "$0")"
echo 13
git clone https://github.com/bergercookie/taskw_gcal_sync.git
echo 14
cd taskw_gcal_sync
echo 15
cd "$(/home/testlinuxname/maintenance/taskw_gcal_sync "$0")"
echo 16
pip3 install --user --upgrade requirements.txt
echo 17
pip3 install --user --upgrade taskw_gcal_sync
echo 18
/home/testlinuxname/maintenance/taskw_gcal_sync/tw_gcal_sync --help
echo 19
tw_gcal_sync --help
echo 20
sudo ./tw_gcal_sync --help
echo 21
echo 22
task add due:2019-06-01T13:01 tag:remindme testtask
echo 23
./tw_gcal_sync -c "TW Reminders" -t remindme
echo 24
python3 /home/testlinuxname/taskw_gcal_sync/tw_gcal_sync -c "TW Reminders" -t remindme
echo 25
