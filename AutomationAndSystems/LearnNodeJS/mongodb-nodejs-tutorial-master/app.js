require('./db');
const Person = require('./models/person');

const test = async function(){
    const data = {
        name: "Jane Doe",
        age: 24,
        gender: "Female"
    }
    await Person.addPerson(data);
    const p = await Person.listPersons();
    console.log(p);
}

test();