// testdb = the name of the database
// cars = table name
// name = brand of the cars
// price = price of the cars 
// the table contains 2 columns:name,price
// the table contains 5 rows

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";
var carNames;



function create() {
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
};

//var listOfCars = function listCars() {
var testReturn2 = function listCars(carNames) { 
    MongoClient.connect(url,{carNames},  testReturn =  function(err, db) {
        if (err) throw err;
        var dbo = db.db("testdb");
        
        // function: find all cars("documents") in cars collection and ONLY LIST THEIR NAMES!
        dbo.collection("cars").find({}, { projection: { _id: 0, name: 1} }).toArray(function(err, result) {
            if (err) throw err;
            car_and_name = result;
            //console.log(car_and_name);
            db.close();
            //return car_and_name;
			
			console.log("Before="+carNames);
            carNames = result;
			console.log("carNames in loop="+carNames[1].name);//=mercedes
			console.log("carNames in loop="+carNames[0].name);//=audi
				
        });
    });
	console.log("testReturn="+testReturn);
	return testReturn;
};

function deleteCars(){
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
};

create();
//console.log("listOfCars1="+listOfCars());
//console.log("listOfCars="+listOfCars);

carNames= "hi";

//console.log("listCars1="+listCars);
console.log("Carnames="+carNames);
//console.log("testReturn="+testReturn);
console.log("testReturn2="+testReturn2());
//console.log("listCars1="+listCars());
console.log("Carnames="+carNames);
//console.log("testReturn="+testReturn);
console.log("testReturn2="+testReturn2());