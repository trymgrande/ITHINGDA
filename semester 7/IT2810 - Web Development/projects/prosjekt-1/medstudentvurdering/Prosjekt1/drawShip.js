// CANVAS ART
let c = document.getElementById("canvasBoat");
let ctx = c.getContext("2d");

function drawHull(color){
    // Skråg
    ctx.beginPath()
    ctx.moveTo(10,200);
    ctx.lineTo(100,280);
    ctx.lineTo(300,280);
    ctx.lineTo(350,220);
    ctx.lineTo(350,160);
    ctx.lineTo(260,160);
    ctx.lineTo(260,190);
    ctx.lineTo(240,220);
    ctx.lineTo(80,220);
    ctx.lineTo(50,200);
    ctx.lineTo(10,200);

    ctx.fillStyle = "#612601";

    if (color !== ""){
        ctx.fillStyle = color;
    }

    ctx.fill();

    ctx.stroke();

    console.log("The hull has been colored " + color)
}

function drawMast(color){
    // Mast
    ctx.beginPath()
    ctx.moveTo(180,220);
    ctx.lineTo(180,20);
    ctx.lineTo(185,20);
    ctx.lineTo(185,220);
    ctx.lineTo(180,220);

    ctx.fillStyle = "#8f4313";

    if (color !== ""){
        ctx.fillStyle = color;
    }
 
    ctx.fill();

    ctx.stroke();

    console.log("The mast has been colored " + color)
}


function drawLeftSail(){
    // Venstre seil
    ctx.beginPath()
    ctx.moveTo(180,180);
    ctx.lineTo(30,180);
    ctx.lineTo(10,200);
    ctx.lineTo(30,180);
    ctx.lineTo(180,30);
    ctx.lineTo(175,35);
    ctx.lineTo(175,180);
    ctx.lineTo(180,180);

    ctx.fillStyle = "beige";
    ctx.fill();

    ctx.stroke();
}


function drawRightSail(){
    // Høyre seil
ctx.beginPath()
ctx.moveTo(185,30);
ctx.lineTo(250,150);
ctx.lineTo(260,160);
ctx.lineTo(250,150);
ctx.lineTo(185,150);
ctx.lineTo(190,150);
ctx.lineTo(190,40);
ctx.lineTo(185,30);

ctx.fillStyle = "beige";
ctx.fill();

ctx.stroke();
}

function drawBoat(hullColor, mastColor, sailColor){

    ctx.clearRect(0, 0, c.width, c.height);

    drawHull(hullColor)
    drawMast(mastColor)
    drawLeftSail()
    drawRightSail()
    console.log("the boat has been drawn")
}

drawBoat()
