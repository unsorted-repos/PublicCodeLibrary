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
            //console.log("inserted cars");
            db.close();
        }); 
    });
};

//var listOfCars = function listCars() {
function listCars(pass) { 
    console.log("pass1:"+pass);
    car_and_name_grandparent = MongoClient.connect(url,pass, function(err, db) {
        //pass = "CHANGED";
        console.log("pass3:"+pass);
        
        if (err) throw err;
        var dbo = db.db("testdb");
        
        car_and_name_parent = dbo.collection("cars").find({}, { projection: { name: 1} });
        
        console.log("car_and_name_parent ="+car_and_name_parent.toArray());
		console.log(Object.prototype.toString.call(car_and_name_parent));
		console.log(Object.prototype.toString.call(car_and_name_parent).id);
        console.log("pass2:"+pass);
        return car_and_name_parent;
    });
    
    //console.log("car_and_name_grandparent ="+car_and_name_grandparent);
    return car_and_name_grandparent;
}; 

function test(err,result) {
    console.log("pass4:"+pass);
    //db.close();
    return result;
}

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
//console.log("listCars1="+listCars);
var pass = "HI THIS IS PASSED";
//console.log("listCars function call="+listCars(pass));
console.log("listCars function call="+eval(listCars(pass)));
console.log("passEND="+pass);