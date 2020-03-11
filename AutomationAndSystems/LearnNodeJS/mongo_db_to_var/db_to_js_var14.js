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
function listCars() { 
    car_and_name_grandparent = MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbo = db.db("testdb");
        
        car_and_name_parent = dbo.collection("cars").find({}, { projection: { _id: 0, name: 1} }).toArray(function(err, result) {
            if (err) throw err;
            car_and_name = result;
            db.close();
            
            console.log("car_and_name="+car_and_name);
            return car_and_name;
        });
        
        console.log("car_and_name_parent ="+car_and_name_parent);
        return car_and_name_parent;
    });
	
    console.log("car_and_name_grandparent ="+car_and_name_grandparent);
    return car_and_name_grandparent;
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
console.log("listCars1="+listCars);
console.log("listCars function call="+listCars());
