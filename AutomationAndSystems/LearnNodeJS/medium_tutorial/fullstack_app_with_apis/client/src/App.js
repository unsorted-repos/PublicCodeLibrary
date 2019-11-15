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
      let interval = setInterval(this.getDataFromDb, 1000);
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
		
        // include dropdown: 1
		
        <select>
            <option value="volvo">Volvo</option>
            <option value="saab">Saab</option>
            <option value="mercedes">Mercedes</option>
            <option value="audi">Audi</option>
        </select>

        // include dropdown: 2 Fails:
	
        // include dropdown: 3 Fails:
        // Source: https://stackoverflow.com/questions/26218243/dynamic-dropdown-in-node-js
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
        
        // include dropdown 4:
        // Source: https://stackoverflow.com/questions/29623225/javascript-dependent-drop-down-list
        // Source: https://jsfiddle.net/v917ycp6/5/
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
        
        
        // include dropdown 5:
         <select onChange="jsFunction()" id="selectOpt1">
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
    );
  }
}

export default App;