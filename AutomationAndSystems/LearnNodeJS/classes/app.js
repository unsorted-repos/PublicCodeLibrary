import TestClass from '.test.js';
import InheritedTestClass from '.test2.js';

let test1 = new TestClass(10);
let test2 = new InheritedTestClass(12, 'Red');

test1.doSomething();
test2.doSomething();

test2.val = 30;
console.log(test2.val);

if (test1 instanceof TestClass) {
    console.log(true);
}
if (test2 instanceof TestClass) {
    console.log(true);
}
if (test2 instanceof InheritedTestClass) {
    console.log(true);
}
if (!(test1 instanceof InheritedTestClass)) {
    console.log(true);
}