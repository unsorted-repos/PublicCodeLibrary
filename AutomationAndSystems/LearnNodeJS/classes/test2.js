import TestClass from "./test.js";

const COLORS = new Set(['Red', 'Green', 'Blue']);

class InheritedTestClass extends TestClass {
    static isValidColor (color) {
        return COLORS.has(color);
    }

    constructor (init, color) {
        super(init);
        if (InheritedTestClass.isValidColor(color)) {
            this.color = color;
        } else {
            throw TypeError(`InheritedTestClass.constructor [error]: color "${color}" is undefined`);
        }
    }

    doSomething () {
        console.log("Bark");
    }

}

export default InheritedTestClass;