// /client/App.js
import React, { Component } from 'react';
import axios from 'axios';


class App extends Component {
    
    // initialize our state
    state = {
        data: [],
        id: 0,
        message: null,
        intervalIsSet: false,
        idToDelete: null,
        idToUpdate: null,
        objectToUpdate: null,
    };
    
    // when component mounts, first thing it does is fetch all existing data in our db
    // then we incorporate a polling logic so that we can easily see if our db has
    // changed and implement those changes into our UI
    componentDidMount() {
        this.getDataFromDb();
        if (!this.state.intervalIsSet) {
            let interval = setInterval(this.getDataFromDb, 2000);
            this.setState({ intervalIsSet: interval });
        }
    }
    
    // never let a process live forever
    // always kill a process everytime we are done using it
    componentWillUnmount() {
        if (this.state.intervalIsSet) {
            clearInterval(this.state.intervalIsSet);
            this.setState({ intervalIsSet: null });
        }
    }
    
    // just a note, here, in the front end, we use the id key of our data object
    // in order to identify which we want to Update or delete.
    // for our back end, we use the object id assigned by MongoDB to modify
    // data base entries
    
    // our first get method that uses our backend api to
    // fetch data from our data base
    getDataFromDb = () => {
        fetch('http://localhost:3001/api/getData')
                .then((data) => data.json())
                .then((res) => this.setState({ data: res.data }));
    };
    
    // our put method that uses our backend api
    // to create new query into our data base
    putDataToDB = (message) => {
        let currentIds = this.state.data.map((data) => data.id);
        let idToBeAdded = 0;
        while (currentIds.includes(idToBeAdded)) {
            ++idToBeAdded;
        }
        
        axios.post('http://localhost:3001/api/putData', {
            id: idToBeAdded,
            message: message,
        });
    };
    
    // our delete method that uses our backend api
    // to remove existing database information
    deleteFromDB = (idTodelete) => {
        parseInt(idTodelete);
        let objIdToDelete = null;
        this.state.data.forEach((dat) => {
            if (dat.id === idTodelete) {
                objIdToDelete = dat._id;
            }
        });
        
        axios.delete('http://localhost:3001/api/deleteData', {
            data: {
                id: objIdToDelete,
            },
        });
    };
    
    // our update method that uses our backend api
    // to overwrite existing data base information
    updateDB = (idToUpdate, updateToApply) => {
        let objIdToUpdate = null;
        parseInt(idToUpdate);
        this.state.data.forEach((dat) => {
            if (dat.id === idToUpdate) {
                objIdToUpdate = dat._id;
            }
        });
        
        axios.post('http://localhost:3001/api/updateData', {
            id: objIdToUpdate,
            update: { message: updateToApply },
        });
    };
    
    // option 9:
//    getOption(){
//        var select = document.getElementById("dynamic-select");
//        console.log(select);
//        if(document.getElementById("dynamic-select").options.length > 0) {
//            var option = document.getElementById("dynamic-select").options[document.getElementById("dynamic-select").selectedIndex];
//            alert("Text: " + option.text + "\nValue: " + option.value);
//        } else {
//            window.alert("Select box is empty");
//        }
//    }
    
    addOption(){
        var inputElemAdd = document.getElementsByTagName('select');
        var selectBox = document.getElementById("dynamic-select");
        
        //alert("ID props="+Object.keys(selectBox)); // returns 3 props
        //alert("ID props 2="+Object.keys(selectBox[2])); // returns 3rd array element (index 2)
        //alert("ID props 3 error="+Object.keys(selectBox[3])); // returns 3 props
        //alert("label="+ selectBox[2].label); // returns 3rd array element (index 2)
        
        selectBox[0].label = "Wrote 0";
        selectBox[2].label = "Wrote 2";
        selectBox[3] = new Option('hi, added last label', 'id0',false,false); // add option
    }
    
    removeOption(){
        var inputElem = document.getElementsByTagName('select');
        for(var i = 0; i < inputElem.length; i++) {
               inputElem[i].options[inputElem[i].selectedIndex] = null; // remove option
           }
    }
    
    // when component mounts, first thing it does is fetch all existing data in our db
    // then we incorporate a polling logic so that we can easily see if our db has
    // changed and implement those changes into our UI
    queryCarDB() {
        this.getDataFromDb();
        if (!this.state.intervalIsSet) {
            let interval = setInterval(this.getDataFromDb, 2000);
            this.setState({ intervalIsSet: interval });
        }
    }
    getCarDataFromDb = () => {
        fetch('http://localhost:3001/api/getCarData')
                .then((data) => data.json())
                .then((res) => this.setState({ data: res.data }));
    };
    
    // here is our UI
    // it is easy to understand their functions when you
    // see them render into our screen
    render() {
    const { data } = this.state;
    return (
      <div>
        <ul>
          {data.length <= 0
            ? 'NO DB ENTRIES YET'
            : data.map((dat) => (
                <li style={{ padding: '10px' }} key={data.message}>
                  <span style={{ color: 'gray' }}> id: </span> {dat.id} <br />
                  <span style={{ color: 'gray' }}> data: </span>
                  {dat.message}
                </li>
              ))}
        </ul>
        <div style={{ padding: '10px' }}>
          <input
            type="text"
            onChange={(e) => this.setState({ message: e.target.value })}
            placeholder="add something in the database"
            style={{ width: '200px' }}
          />
          <button onClick={() => this.putDataToDB(this.state.message)}>
            ADD
          </button>
        </div>
        <div style={{ padding: '10px' }}>
          <input
            type="text"
            style={{ width: '200px' }}
            onChange={(e) => this.setState({ idToDelete: e.target.value })}
            placeholder="put id of item to delete here"
          />
          <button onClick={() => this.deleteFromDB(this.state.idToDelete)}>
            DELETE
          </button>
        </div>
        <div style={{ padding: '10px' }}>
          <input
            type="text"
            style={{ width: '200px' }}
            onChange={(e) => this.setState({ idToUpdate: e.target.value })}
            placeholder="id of item to update here"
          />
          <input
            type="text"
            style={{ width: '200px' }}
            onChange={(e) => this.setState({ updateToApply: e.target.value })}
            placeholder="put new value of the item here"
          />
          <button
            onClick={() =>
              this.updateDB(this.state.idToUpdate, this.state.updateToApply)
            }
          >
            UPDATE
          </button>
          

         // Source: https://memorynotfound.com/dynamically-add-remove-options-select-javascript<br></br>-->
        <select id="dynamic-select">
                <option value="1">one</option>
                <option value="2">two</option>
                <option value="3">three</option>
        </select>

        {/*<button onClick={this.getOption()}>get item</button>*/}
        <button onClick={this.addOption}>add item</button> // remove the brackets to make it happen at onclick
        {/*<button type="button" onClick={this.addOption}>Go</button>
        <button onClick={this.removeOption()}>remove item</button>
        <button onClick={this.removeAllOptions}>remove all</button>*/}
        
        
        <br></br>
        {/*//option 10
        // source: https://stackoverflow.com/questions/27834226/add-event-listener-to-collection-of-html-elements*/}
        <input class="inputs" type="submit" value="Hello" />
        
                
        </div>
      </div>
    );
  }
}

export default App;
    

