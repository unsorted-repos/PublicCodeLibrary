#get root
if [ ! -f /home/a/getRootBool ]; then
   echo "Getting sudo rights now."
   touch /home/a/maintenance/getRootBool
   sudo -s
fi
# remove got root boolean for next time you boot up Unix
sudo rm /home/a/maintenance/getRootBool
#Start cron service
sudo -i service cron start
#Startup taskwarrior
export TASKDDATA=/var/taskd
cd $TASKDDATA
sudo taskd config --data $TASKDDATA
taskdctl start
task sync
