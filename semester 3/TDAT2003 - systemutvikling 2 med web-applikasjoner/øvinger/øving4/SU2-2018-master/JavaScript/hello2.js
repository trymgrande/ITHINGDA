let movie = "";

document.querySelector("#quoteButton").addEventListener("click", e => {
  fetch("http://bigdata.stud.iie.ntnu.no/sentiment/webresources/quote", {
    method: "GET"
  })
    .then(response => response.json())
    .then(json => handleResponse(json))
    .catch(error => console.error("Error: ", error));
});

function handleResponse(json) {
  document.querySelector("#myQuote").innerHTML = json.quote;
  movie = json.movie;
  document.querySelector("#myMovie").innerHTML = "Which movie?";
}

document.querySelector("#movieButton").addEventListener("click", e => {
  let myMovie = (document.querySelector("#myMovie").innerHTML = movie);
});
