// testdb = the name of the database
// cars = table name
// name = brand of the cars
// price = price of the cars 
// the table contains 2 columns:name,price
// the table contains 5 rows

/*
var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("mydb");
  var myobj = [
    { _id: 154, name: 'Chocolate Heaven'},
    { _id: 155, name: 'Tasty Lemon'},
    { _id: 156, name: 'Vanilla Dream'}
  ];
  dbo.collection("products").insertMany(myobj, function(err, res) {
    if (err) throw err;
    console.log(res);
    db.close();
  });
});
*/


MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("testdb");
  var myobj = { name: "Audi", price: "52642" };
  var myobj = [
    { name: 'Audi', price: '52642'},
    { name: 'Mercedes', price: '57127'},
    { name: 'Skoda', price: '9000'},
    { name: 'Volvo', price: '29000'},
    { name: 'Bentley', price: '350000'}
  ];
  
  dbo.collection("cars").insertOne(myobj, function(err, res) {
    if (err) throw err;
    console.log("1 document inserted");
    db.close();
  });
});
