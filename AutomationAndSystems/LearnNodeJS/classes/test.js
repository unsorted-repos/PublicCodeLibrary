class TestClass {
    constructor (init) {
        this._val = init;
    }
    get val () {
        return this._val;
    }
    set val (arg) {
        this._val = arg;
    }
    doSomething () {
        console.log('Wow');
    }
}

export default TestClass;