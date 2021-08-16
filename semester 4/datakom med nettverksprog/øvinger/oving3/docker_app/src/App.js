import React from 'react';
import {compilerService} from './services';
var fs = require('fs');

function App(){
    function eventHandler(){
        let input = document.getElementById("textarea").value;
        console.log(input);
        compilerService.compileCode(input)
            .then(output => {
                console.log("hei"+output);
                document.getElementById("feedback").innerHTML = output.toString();
            })
            .catch(error => console.log(error))

    }
    return (
        <div>
            <h1>pizzaen ringte</h1>
            <textarea id="textarea"></textarea>
            <button onClick={() => eventHandler()} id="submitButton">submit</button>
            <p id={"feedback"}>feedback</p>
            <div id="root"></div>
        </div>
    )
}

export default App;