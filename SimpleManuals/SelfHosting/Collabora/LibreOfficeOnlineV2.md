Host LibreOffice in your website:
0. Install docker from:
https://hub.docker.com/?overlay=onboarding
1. Then install nextcloud in docker
```
docker run -d -p 8080:80 nextcloud
docker run -d -p 127.0.0.1:8080:80 nextcloud
```
2. Then install collabora/code = form of Libreoffice Online
```
Works locally
docker run -t -d -p 9980:9980 -e "extra_params=--o:ssl.enable=false" collabora/code
Fails
docker run -t -d -p 9980:9980 -e 'domain=example1\\.hiveminds\\.eu' --restart always --cap-add MKNOD collabora/code
docker run -t -d -p 127.0.0.1:9980:9980 -e 'domain=example1\\.hiveminds\\.eu' --restart always --cap-add MKNOD collabora/code
docker run -t -d -p 127.0.0.1:9980:9980 -e 'domain=cloud\\.DOMAIN\\.com' --restart always --cap-add MKNOD collabora/code
```

3. Get your local ipv4 adress on which both collabora and docker will run: (can't be the docker ipv4)
3.1 open cmd and type:
```
ipconfig
```
3.2 Copy paste an ipv4 adress. Note there are some constraints on which ipv4-adress you can pick:
3.2.1 The ipv4 adress for nextcloud must be the same as for collabora
3.2.2 The ipv4 adress of docker does not work for collabora so you must pick another one.
3.2.3 (for me the nat ipv4 adress of an ethernet adapter worked)
3.2.4 I am trying the public ipv4 adress right now.
3.3 The ipv4-adress you chose will now be referred to as: `<your ipv4-adress>`.
3. Activate/setup Nextcloud by opening a browser and going to (with e.g.):
```
http://`<your ipv4-adress>`:8080
http://10.8.1.28:8080/
```
4. After installing, visit:
```
http://`<your ipv4-adress>`:8080
http://10.8.1.28:8080/settings/apps
```
5. and search for:
```
collabora
```
5. Click `download and install` collabora.
6. Then visit:
```
http://<your docker-ipv4-adress>:8080/settings/admin/richdocuments
http://10.8.1.28:8080/settings/admin/richdocuments
```
And at: "URL (and Port) of Collabora Online-server", enter:
```
http://<Not your docker-ipv4-adress>:9980
http://`<your ipv4-adress>`:9980
http://10.0.75.1:9980
```

not all ip4 adresses work. This is a log of the tried combinations: