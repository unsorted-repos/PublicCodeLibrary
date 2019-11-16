// /client/App.js
import React, { Component } from 'react';
import axios from 'axios';

class App extends Component {
    super() {
    this.dt=new Date();
    this.dt_month =this.dt.getmonth()+1;
    }
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
    
    // Source: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes
    speak() {
        console.log(`barks.`);
    };
    
    // Source: 
    // Include function to make dependent dropdown boxes:
    jsFunction(){
        console.log("CHANGING")
        var list1 = document.getElementById("selectOpt1");
        var myselect = list1.options[list1.selectedIndex].value;
        var list2 = document.getElementById('selectOpt2');
        var list3 = document.getElementById('selectOpt3');
        var i;
        for (i = 0; i < list2.options.length; i++) {
            list2.options[i] = null;
        }
        for (i = 0; i < list3.options.length; i++) {
            list3.options[i] = null;
        }
        if(myselect==="1"){
            var opt = document.createElement('option');
            opt.value = "1";
            opt.innerHTML = "FIRST";
            list2.appendChild(opt);
            opt = document.createElement('option');
            opt.value = "1";
            opt.innerHTML = "FIRST2";
            list3.appendChild(opt);
        }
        else{
            var opt = document.createElement('option');
            opt.value = "2";
            opt.innerHTML = "SECOND";
            list2.appendChild(opt);
            opt = document.createElement('option');
            opt.value = "1";
            opt.innerHTML = "SECOND2";
            list3.appendChild(opt);
        }
    }
    
    // try returning a function instead of a string:
    returnFunction() {
        return this.jsFunction();
    }
    
    // Source: https://www.plus2net.com/javascript_tutorial/list-adding.php
    //this.dt=new Date();
    //this.dt_month=dt.getMonth() +1;
    
   
    addOption(selectbox,text,value )
    {
        var optn = document.createElement("OPTION");
        optn.text = text;
        optn.value = value;
        selectbox.options.add(optn);
    }
    
    addOption_list(){
        var month = new Array("January","February","March","April","May","June","July","August",
        "September","October","November","December");
        for (var i=0; i < month.length;++i){
            this.addOption(document.drop_list.Month_list, month[i], month[i]);
            if(i== this.dt_month){document.drop_list.Month_list.options[i].selected=true;}
        }
    }
    
    
    // when component mounts, first thing it does is fetch all existing data in our db
    // then we incorporate a polling logic so that we can easily see if our db has
    // changed and implement those changes into our UI
    componentDidMount() {
        this.getDataFromDb();
        if (!this.state.intervalIsSet) {
            let interval = setInterval(this.getDataFromDb, 10009);
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
            if (dat.id == idTodelete) {
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
            if (dat.id == idToUpdate) {
                objIdToUpdate = dat._id;
            }
        });
        
        axios.post('http://localhost:3001/api/updateData', {
            id: objIdToUpdate,
            update: { message: updateToApply },
        });
    };
    
    // attempt 9:
    getOption(){
        var select = document.getElementById("dynamic-select");
        if(select.options.length > 0) {
            var option = select.options[select.selectedIndex];
            alert("Text: " + option.text + "\nValue: " + option.value);
        } else {
            window.alert("Select box is empty");
        }
    }

    addOption(){
        var select = document.getElementById("dynamic-select");
        select.options[select.options.length] = new Option('New Element', '0', false, false);
    }

    removeOption(){
        var select = document.getElementById("dynamic-select");
        select.options[select.selectedIndex] = null;
    }

    removeAllOptions(){
        var select = document.getElementById("dynamic-select");
        select.options.length = 0;
    }

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
        </div>
		
        <br></br>
        <br></br>
        // include dropdown: 1<br></br>
        <div>	
            <select>
                <option value="volvo">Volvo</option>
                <option value="saab">Saab</option>
                <option value="mercedes">Mercedes</option>
                <option value="audi">Audi</option>
                <option value="test"> "this.jsFunction()"</option>
                
            </select>
        </div>
        
        <br></br>
        <br></br>
        // include dropdown: 2 Fails:<br></br>
        
	
        <br></br>
        <br></br>
        // include dropdown: 3 Fails:<br></br>
        // Source: https://stackoverflow.com/questions/26218243/dynamic-dropdown-in-node-js<br></br>
        <div>
            <select id="skill_category">
                <option value="communication">Communication</option>
                <option value="teamwork">Team Work</option>
                <option value="technical">Technical</option>
            </select> 

            <select class="skill" id="communciation">
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select> 
            <select  class="skill" id="teamwork">
                <option value="a">One</option>
                <option value="b">Two</option>
                <option value="c">Three</option>
            </select> 
            <select  class="skill" id="technical">
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select> 
        </div>
        
        <br></br>
        <br></br>
        // include dropdown 4:<br></br>
        // Source: https://stackoverflow.com/questions/29623225/javascript-dependent-drop-down-list<br></br>
        // Source: https://jsfiddle.net/v917ycp6/5/<br></br>
        <div>
            <form id="formname" name="formname" method="post" action="submitform.asp" >
                <table width="50%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td width="41%" align="right" valign="middle">Category1 :</td>
                        <td width="59%" align="left" valign="middle">
                            <select name="category1" id="category1">
                                <option value="">Select Category1</option>
                                <option value="home_ware">Home Ware</option>
                                <option value="education">Education</option>
                                <option value="books">Books</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" valign="middle">Category2 :</td>
                        <td align="left" valign="middle">
                            <select disabled="disabled" id="category2" name="category2">
                                <option value>Select Category2</option>

                                <option rel="home_ware" value="air-conditioners_coolers">Air-Conditioners/Coolers</option>
                                <option rel="home_ware" value="audio-video">Audio/Video</option>
                                <option rel="home_ware" value="beddings">Beddings</option>
                                <option rel="home_ware" value="camera">Camera</option>
                                <option rel="home_ware" value="cell-phones">Cell Phones</option>

                                <option rel="Education" value="Colleges">Colleges</option>
                                <option rel="Education" value="Institutes">Institutes</option>
                                <option rel="Education" value="Schools">Schools</option>
                                <option rel="Education" value="Tuitions">Tuitions</option>
                                <option rel="Education" value="Universities">Universities</option>

                                <option rel="Books" value="College Books">College Books</option>
                                <option rel="Books" value="Engineering">Engineering</option>
                                <option rel="Books" value="Magazines">Magazines</option>
                                <option rel="Books" value="Medicine">Medicine</option>
                                <option rel="Books" value="References">References</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        
        <br></br>
        <br></br>
        // include dropdown 5:
        <div>
            <select onChange="this.returnFunction" id="selectOpt1">
                <option value="1" selected="selected">1</option>
                <option value="2">2</option>
            </select>
            <select id="selectOpt2">
                 <option value="1">FIRST</option>
            </select>
            <select id="selectOpt3">
                <option value="1">FIRST</option>
            </select>
        </div>
                
        <br></br>
        <br></br>
        // include dropdown 6:
        <div>
            <button onClick={() => this.addOption_list()}>
            ADD
          </button>
        </div>
            
        // include dropdown 7:
        <div onClick="this.addOption_list()">
            You can see the view-> Source of this page. 
            <br></br>
            <form name="drop_list" action="yourpage.php" method="POST" >
                <select NAME="Month_list">
                    <option value="" >Month list</option>
                </select>
            </form>
        </div>
        
        
        <br></br>
        <br></br>
        // include dropdown 8:
        <form id="tableForm" action="/getJson" method="get">
            <select class="selectpicker" data-style="btn-info" name="selectpicker">
                <optgroup label="Select Table">
                    <option name="" value="0">Select table</option>
                    <option name="table1" value="1">Table 1</option>
                    <option name="table2" value="2">Table 2</option>
                    <option name="table3" value="3">Table 3</option>
                </optgroup>
            </select>
            <input type="submit" />
        </form>
        
        <br></br>
        <br></br>
        // include dropdown 9<br></br>
        // source: https://memorynotfound.com/dynamically-add-remove-options-select-javascript/<br></br>
        <select id="dynamic-select">
            <option value="1">one</option>
            <option value="2">two</option>
            <option value="3">three</option>
        </select>

        <button onclick="this.getOption()">get item</button>
        <button onclick="this.addOption()">add item</button>
        <button onclick="this.removeOption()">remove item</button>
        <button onclick="this.removeAllOptions()">remove all</button>    
        
    </div>
    );
  }
}

// Source: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes
//let d = new App('Mitzie');
//d.speak();

export default App;