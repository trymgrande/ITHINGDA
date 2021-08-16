var express = require("express");
var mysql = require("mysql");
var bodyParser = require("body-parser");
var app = express();
app.use(bodyParser.json()); // for å tolke JSON

var pool = mysql.createPool({
  connectionLimit: 2,
  host: "mysql.stud.iie.ntnu.no",
  user: "nilstesd",
  password: "lqqWcMzq",
  database: "nilstesd",
  debug: false
});

app.get("/hello", (req, res) => {
  res.send("Hello World");
});

app.get("/hello2", (req, res) => {
  res.json({ message: "Hello world" });
});

app.get("/person", (req, res) => {
  console.log("Fikk request fra klient");
  pool.getConnection((err, connection) => {
    console.log("Connected to database");
    if (err) {
      console.log("Feil ved kobling til databasen");
      res.json({ error: "feil ved ved oppkobling" });
    } else {
      connection.query(
        "select navn, alder, adresse from person",
        (err, rows) => {
          connection.release();
          if (err) {
            console.log(err);
            res.json({ error: "error querying" });
          } else {
            console.log(rows);
            res.json(rows);
          }
        }
      );
    }
  });
});

app.get("/person/:personId", (req, res) => {
  console.log("Fikk request fra klient");
  pool.getConnection((err, connection) => {
    console.log("Connected to database");
    if (err) {
      console.log("Feil ved kobling til databasen");
      res.json({ error: "feil ved ved oppkobling" });
    } else {
      connection.query(
        "select navn, alder, adresse from person where id=?",
        [req.params.personId],
        (err, rows) => {
          connection.release();
          if (err) {
            console.log(err);
            res.json({ error: "error querying" });
          } else {
            console.log(rows);
            res.json(rows);
          }
        }
      );
    }
  });
});

app.post("/test", (req, res) => {
  console.log("Fikk POST-request fra klienten");
  console.log("Navn: " + req.body.navn);
  res.status(200);
  res.json({ message: "success" });
});

app.post("/person", (req, res) => {
  console.log("Fikk POST-request fra klienten");
  console.log("Navn: " + req.body.navn);
  pool.getConnection((err, connection) => {
    if (err) {
      console.log("Feil ved oppkobling");
      res.json({ error: "feil ved oppkobling" });
    } else {
      console.log("Fikk databasekobling");
      var val = [req.body.navn, req.body.adresse, req.body.alder];
      connection.query(
        "insert into person (navn,adresse,alder) values (?,?,?)",
        val,
        err => {
          if (err) {
            console.log(err);
            res.status(500);
            res.json({ error: "Feil ved insert" });
          } else {
            console.log("insert ok");
            res.send("");
          }
        }
      );
    }
  });
});

var server = app.listen(8080);
