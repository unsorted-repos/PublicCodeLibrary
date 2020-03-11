https://expressjs.com/en/starter/installing.html
mkdir myapp
cd myapp
npm init
then at entry point: (index.js) type:
app.js
(enter all the rest)
npm install express --save
Then create app.js in ../myapp/ with file content:
```
const express = require('express')
const app = express()
const port = 3000

app.get('/', (req, res) => res.send('Hello World!'))

app.listen(port, () => console.log(`Example app listening on port ${port}!`))
```
Source: https://expressjs.com/en/starter/hello-world.html

https://codeforgeek.com/express-complete-tutorial-part-1/
npm install -g express
npm install --save express
npm install --save express-generator
express folder_name
(failed express command not recognized)
npm install
npm start
https://codeforgeek.com/express-nodejs-tutorial/


Then https://www.youtube.com/watch?v=ZKwrOXl5TDI