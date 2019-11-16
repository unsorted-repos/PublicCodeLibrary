// testdb = the name of the database
// cars = table name
// name = brand of the cars
// price = price of the cars 
// the table contains 2 columns:name,price
// the table contains 5 rows

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";
var carNames;

MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("testdb");
    var myobj = [
	{ name: 'Audi', price: '52642'},
        { name: 'Mercedes', price: '57127'},
        { name: 'Skoda', price: '9000'},
        { name: 'Volvo', price: '29000'},
        { name: 'Bentley', price: '350000'},
    ];
    
    dbo.collection("cars").insertMany(myobj, function(err, res) {
        if (err) throw err;
		console.log("inserted cars");
        db.close();
    }); 
});


MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("testdb");
    
    // function: find all cars("documents") in cars collection and ONLY LIST THEIR NAMES!
    dbo.collection("cars").find({}, { projection: { _id: 0, name: 1} }).toArray(function(err, result) {
        if (err) throw err;
	console.log("New results0");
	car_and_name = result;
	console.log(car_and_name);
        return car_and_name;
        db.close();
    });
});

MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("testdb");
    
    // delete all entries:
    dbo.collection("cars").deleteMany({}, function(err, result) {
        if (err) throw err;
		console.log("Deleted cars");
        db.close();
    });
});