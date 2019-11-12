Install nodejs
Open this folder in cmd
run JavaScript file (.js) by: node "Show Node.js"

0. Source:
https://www.w3schools.com/nodejs/nodejs_get_started.asp
The myfirst.js Runs a node server on you r pc on port 8080, which you can visit at: http://localhost:8080
The cmd will just be blank, pause where it is, because it is just running the host.

1. source: 
https://www.w3schools.com/nodejs/nodejs_modules.asp
myfirstmodule.js is a module=library= class. It's function is, return the time.
then demo_module.js is the main, that hosts the node server, and imports `myfirstmodule.js`.

2. Source:
https://www.w3schools.com/nodejs/nodejs_http.asp
2. Files:
demo_http.js
http://localhost:8080

3. Source:
https://www.w3schools.com/nodejs/nodejs_http.asp
3. Files:
demo_http_url.js
This returns the relative path of the url that is being visited e.g. http://localhost:8080/summer displays "/summer" on the website.

4. Uploading files:
4. Source:
https://www.w3schools.com/nodejs/nodejs_uploadfiles.asp
4.1 First install formidable package
npm install formidable
4.2 Then create upload form:
uploadform.js
4.3 Then process that upload file:
process_upload.js

4.4 To store the file to the internal pc, then use:
uploadToPc.js
AND ENSURE the backslashes in the target destination are forward slashes. So move from \ to: / 
Verified that it works.

5. Populate dropdownbox from .txt file