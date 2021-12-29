let myButton = document.querySelector("#myButton");
let someText = document.querySelector('#someText');
let loginButton = document.querySelector('#loginButton');
let jwt = null;

loginButton.addEventListener("click", e => {
    console.log("fikk klikk-event");
    // let username = "hei";
    // let password = "secret";
    let url = "login";
    fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "brukernavn": "hei",
                "passord": "secret"
            })
        })
        .then(response => response.json())
        .then(json => {
            console.log(JSON.stringify(json));
            localStorage.setItem('jwt', json.jwt);
        })
        .catch(error => console.error("Error: ", error));
})

myButton.addEventListener("click", e => {
    console.log("Fikk klikk-event");
    let persId = 1;

    hentNyToken();


    let url = "api/person/" + persId;
    fetch(url, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'x-access-token': localStorage.getItem('jwt')
            }
        })
        .then(response => response.json())
        .then(json => console.log(JSON.stringify(json)))
        .catch(error => console.error("Error: ", error));

});


function hentNyToken() {

    let url = "token";
    fetch(url, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'x-access-token': localStorage.getItem('jwt')
            }
        })
        .then(response => response.json())
        .then(json => localStorage.setItem('jwt', json.jwt))
        .catch(error => console.error("Error: ", error));

}