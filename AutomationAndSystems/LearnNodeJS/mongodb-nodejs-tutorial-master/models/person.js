const mongoose = require("mongoose");
var Schema = new mongoose.Schema({
    name: {type:String, required:true},
    age: {type: Number, required: true},
    gender: {type: String, enum: ['Male', 'Female', 'Other']}
}, { timestamps: true});

Schema.statics.addPerson = async function(person){
    var Person = new this(person);
    var result =  await Person.save(person);
    return result;
}

Schema.statics.listPersons = async function(){
    return await this.find();
}

module.exports = mongoose.model('person', Schema);