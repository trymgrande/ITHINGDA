let cBoat = document.getElementById("canvasBoat");
let svgBoat = document.getElementById("svgBoat");
let ctxBoat = c.getContext("2d");

let colors = ["red", "blue", "green", "#612601", "brown", "orange"]

// On mouse enter, begin infinite animation
$(cBoat).mouseover(function(){ // CANVAS
    console.log("start infinite animation")
    cBoat.style.animation = "swingAni 4s ease infinite"
})
$(svgBoat).mouseover(function(){ // SVG
    console.log("start infinite animation")
    svgBoat.style.animation = "swingAni 4s ease infinite"
})

// On mouse leave, begin final animation
$(cBoat).mouseleave(function(){ // CANVAS
    console.log("stop animation")
    cBoat.style.animationPlayState = "paused"
})
$(svgBoat).mouseleave(function(){ // SVG
    console.log("stop animation")
    svgBoat.style.animationPlayState = "paused"
})


// Change color of canvas boat on click
$(cBoat).click(function(){ // CANVAS
    console.log("click")
    drawBoat(getRandomColor(), getRandomColor())

})

// Change color of svg boat on click
$(svgBoat).click(function(){ // SVG
    console.log("click")
    document.getElementById("svgHull").setAttribute("style", "fill:" + getRandomColor())
    document.getElementById("svgMast").setAttribute("style", "fill:" + getRandomColor())

})

function getRandomColor(){
    return colors[Math.floor(Math.random()*colors.length)]
}

$("#showAboutButton").click(function(){
    $("#showAboutButton").text($("#showAboutButton").text() == "Show documentation" ? "Hide documentation": "Show documentation")
    $("#about").slideToggle()
})
