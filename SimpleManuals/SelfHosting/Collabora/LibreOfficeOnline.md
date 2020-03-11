How to host your own opensource google sheets/docs/etc by hosting an in-browser libreoffice (liberoffice online):

Either use:
0. https://www.collaboraoffice.com/code/
1. https://hub.docker.com/r/libreoffice/online/
docker pull libreoffice/online
docker pull libreoffice/online:master
2. https://hub.docker.com/r/collabora/code
docker pull collabora/code
3. https://hub.docker.com/r/cibsoftware/libreoffice-online
docker pull cibsoftware/libreoffice-online
4. https://download.kopano.io/community/libreofficeonline/
5. https://github.com/EGroupware/egroupware/wiki/19.1--Installation-using-egroupware-docker-RPM-DEB-package


https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly
a. Then install wsl Ubuntu 16.04 (tried with 18.04) and enter:
sudo apt install docker.io
b. Make sure the docker deamon is running with:
docker pull libreoffice/online

c. install docker:
sudo apt-get update -y

sudo apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common
	
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo apt-key fingerprint 0EBFCD88

sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"


# MAKE SURE YOUR WINDOWS INSTALLATION OF DOCKER IS CLOSED/not running.
# also reboot your wsl to close all instances of docker running in there.
# Install the latest version of Docker CE.
sudo apt-get install -y docker-ce

# Allow your user to access the Docker CLI without needing root access.
sudo usermod -aG docker $USER


echo "export DOCKER_HOST=tcp://localhost:2375" >> ~/.bashrc && source ~/.bashrc

# NOW OPEN YOUR WINDOWS DOCKER and in wsl terminal run(it sould return 20 lines).
docker info 


In windows:
cd "/mnt/e/18-09-19 Document structure/personal/Programming/git/PublicCodeLibrary/AutomationAndSystems/LearnNodeJS/living-lab-app-repo/Linux Containers"
curl -OutFile release.zip https://github.com/linuxkit/lcow/releases/download/v4.14.35-v0.3.9/release.zip



https://www.offidocs.com/loleaflet/dist/loleaflet.html?service=owncloudservice01&file_path=file:///var/www/html/weboffice/mydata/jonhie/NewDocuments/test-example.xls&username=jonhie&ext=yes

Open cmd in admin mode:
change "10.8.1.28" with the value you find at the ip4 adress of the network adapter that you use to connect with the internet, e.g. wifi, when you open cmd and enter: ipconfig<enter>.
docker run -d -p 8080:80 nextcloud
http://10.8.1.28:8080/
Visit:
http://10.8.1.28:8080/settings/apps 
search collabora
click "install and activate"
In admin cmd enter:
docker run -t -d -p 9980:9980 -e "extra_params=--o:ssl.enable=false" collabora/code
Now your nextcloud is acceible at port 8080, and your collabora is at port 9980.
Then in cmd type:
ipconfig
copy the ipv4 adress that is listed at: 
Ethernet adapter vEthernet (DockerNAT)
10.0.75.1
That ip4-adress is from now on referred to as <your docker-ipv4-adress>
Also note the ethernet adresses named:
"Ethernet adapter Ethernet 2" with value:
10.8.8.39
"Ethernet adapter vEthernet (nat):" with value:
172.30.16.1
and WIfi adapter with:
145.94.199.172



Wait 5 mins and Goto (with example):
http://<your docker-ipv4-adress>:8080
http://10.8.1.28:8080
Goto:
http://<your docker-ipv4-adress>:8080/settings/apps
http://10.8.1.28:8080/settings/apps
And search:"collabora"
Select "Download and enable"


Go to 
http://<your docker-ipv4-adress>:8080/settings/admin/richdocuments
http://10.8.1.28:8080/settings/admin/richdocuments
And at: "URL (and Port) of Collabora Online-server", not all ip4 adresses work. This is a log of the tried combinations:
Ip4 used at Nextcloud setup - Collabora entered ip4
ipv4-docker+ipv4-docker: can't access collabora
ipv4-docker+ethernet adapter 2: accessing collabora, but can't access file.
""-nat: connecting collabora cant access file
""- wifi: cant access collabora

Now remove the admin entry of docker at: 
ethernet adapter 2+ethernet adapter 2  =?(Not connected if docker is not installed)

nat - nat=Works!! So the file and collabora should be in the same location.


http://10.0.75.1:8080/apps/files/?dir=/Documents&fileid=7

 enter(with exmaple):
http://<Not your docker-ipv4-adress>:9980
http://10.0.75.1:9980

10.8.8.39

Click apply
Then you can go to:
http://<your docker-ipv4-adress>:8080/apps/files/?dir=/Documents&fileid=14
http://10.8.1.28:8080/apps/files/?dir=/Documents&fileid=14
And open a text file which is shown in the browser, which you can edit with collabora
http://10.8.1.28:8080/apps/files/?dir=/Documents&fileid=14
Faa-kin Epic!

http://10.8.4.28:9990

http://10.8.4.5:8090/settings/admin/richdocuments
http://10.8.4.5:8090/apps
or:
(localhost:8080)
localhost:9980
localhost:8080/settings/admin/richdocuments

http://10.8.0.8:8080/s/ipDQaFkkadmKL2d




Now I am looking into 
7.0 https://geek-cookbook.funkypenguin.co.nz/recipes/collabora-online/
7.1 https://www.getfilecloud.com/supportdocs/display/cloud/Installing+Collabora+CODE+On+Ubuntu+16.04
7.2 https://www.collaboraoffice.com/code/quick-tryout-owncloud-docker/
7.3 https://www.linuxbabe.com/cloud-storage/integrate-collabora-online-server-nextcloud-ubuntu-16-04
To see if I can get the nextcloud/owncloud integration running to get an MWE where I can edit an .ods file in a browser.
8. If that works, I'll automate the link generation for automatically generated .ods files and automatic adaptation of html/js files linking to show those .ods files in the website.




TO edit the config.php you can ssl into your own image of the nextcloud docker with:
```
docker run --privileged -it -v /var/run/docker.sock:/var/run/docker.sock jongallant/ubuntu-docker-client 
docker run --net=host --ipc=host --uts=host --pid=host -it --security-opt=seccomp=unconfined --privileged --rm -v /:/host alpine /bin/sh
chroot /host
```

Then search the config.php file with: 
```
find . -name "config.php"
```