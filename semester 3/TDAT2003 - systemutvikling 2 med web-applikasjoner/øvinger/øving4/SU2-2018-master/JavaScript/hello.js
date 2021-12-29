let quoteButton = document.querySelector("#quoteButton");

quoteButton.addEventListener("click", e => {
  fetch("http://bigdata.stud.iie.ntnu.no/sentiment/webresources/quote", {
    method: "GET"
  })
    .then(response => response.json())
    .then(json => handleResponse(json))
    .catch(error => console.error("Error: ", error));
});

function handleResponse(json) {
  let myQuote = document.querySelector("#myQuote");
  myQuote.innerHTML = json.movie + ": " + json.quote;
  myQuote.className = "niceParagraph"; // Change style to something nice
}
