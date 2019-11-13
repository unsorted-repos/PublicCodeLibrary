0. Source:
https://www.mongodb.com/dr/fastdl.mongodb.org/win32/mongodb-win32-x86_64-2012plus-4.2.1-signed.msi/download
0. Download not the atlas, but click tab server and download the software.

1. (THIS IS NOT NECESSARY FOR NODE, to prevent error, you should add node to path, but you can skip it for node) Verify mongoDB is installed correctly by opening cmd and entering:
mongo --version 
This should return a version, not an error.
1.a Open the MongoDB Compass Comunity and click "Connect" to localserver.

2. Source:
?
2. First install mongodb for node, open cmd and type:
npm install mongodb

4. Source:
https://www.w3schools.com/nodejs/nodejs_mongodb_create_db.asp
4. after pressing connect in MongoDb Compass Community, Create database:
demo_create_mongo_db.js
4.1 Open cmd and browse to path with the demo_mongodb_insert.js file and enter:
node demo_create_mongo_db.js

5. Source:
https://www.w3schools.com/nodejs/nodejs_mongodb_insert.asp
5. Create and add a record/document/entry into mongodb:
demo_mongodb_insert.js
5.1 Open cmd and browse to path with the demo_mongodb_insert.js file and enter:
node demo_mongodb_insert.js

6. Source:
https://www.w3schools.com/nodejs/nodejs_mongodb_insert.asp
6. Add multiple records:
node demo_mongodb_insert_multiple.js

7. Source:
https://www.w3schools.com/nodejs/nodejs_mongodb_insert.asp
7. You can also explicitly specify the ids as long as they are unique:
node demo_mongodb_insert_id.js

