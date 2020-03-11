// First create the database:
// testdb = the name of the database
// cars = table name
// name = brand of the cars
// price = price of the cars 
// the table contains 2 columns:name,price
// the table contains 5 rows

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("db1");
  var myobj = [
	{ name: 'Audi', price: '52642'},
    { name: 'Mercedes', price: '57127'},
    { name: 'Skoda', price: '9000'},
    { name: 'Volvo', price: '29000'},
    { name: 'Bentley', price: '350000'},
  ];
  
  dbo.collection("cars").insertMany(myobj, function(err, res) {
    if (err) throw err;
    console.log("all 5 cars added");
    db.close();
  });
});



// db1.js
var MongoClient = require('mongodb').MongoClient;
                       
module.exports = {
  FindinCol1: function() {
    return MongoClient.connect('mongodb://localhost:27017/db1').then(function(db) {
      var collection = db.collection('cars');
      
      return collection.find().toArray();
    }).then(function(items) {
      console.log(items);
      return items;
    });
  }
};



// app.js
//var db = require('./db1');
var db = db.db("db1")
var db = db.db(mongodb://localhost:27017/db1)

    
db.FindinCol1().then(function(items) {
  console.info('The promise was fulfilled with items!', items);
}, function(err) {
  console.error('The promise was rejected', err, err.stack);
});
