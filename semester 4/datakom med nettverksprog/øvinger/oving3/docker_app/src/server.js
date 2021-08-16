
let express = require('express');
let path = require('path');
let fs = require('fs');
const promise = require('bluebird');
const nodecmd = require('node-cmd');

const public_path = path.join(__dirname, '/../../client/public');
var app = express();
var cors = require("cors");
var bodyParser = require("body-parser");

app.use(bodyParser.json());
app.use(cors());
app.use(express.static(public_path));
app.use(express.json());


app.post('/compile', (req, res) => {
    (async () =>{
        await fs.writeFile('main.cpp', req.body.code, function (err) {
            console.log('Saved!');
        });
    })();
    var output = "value";


    const getAsync = promise.promisify(nodecmd.get, {
        multiArgs: true,
        context: nodecmd
    });

    let cmd0 = 'cd ..';
    let cmd1 = 'docker build . -t hello-world:1.0.0';
    let cmd2 = 'docker run hello-world:1.0.0';

    getAsync(cmd0)
        .then(result => console.log(result))
        .then(() => getAsync(cmd1))
        .then(result => console.log(result))
        .then(() => getAsync(cmd2))
        .then(result => {console.log(result); res.send({output : result[0]});})
        .catch(err => {
            console.log('cmd err', err);
            res.send({output : err.toString()});
        });
});

let server = app.listen(8080);