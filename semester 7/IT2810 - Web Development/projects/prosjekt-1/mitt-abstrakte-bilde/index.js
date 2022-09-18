// hide doc
$(document).ready(function() {
    $(".documentation").hide();
})

// toggle doc
$(document).ready(function() {
    $("button").click(function() {
        $(".documentation").toggle();
    })
})

// click interaction
$(document).ready(function() {
    $("#stem").click(function() {
        $(this).css("fill", getRandomColor());
    })
})

// hover interaction spots
$(document).ready(function() {
    $("#spot1").hover(function() {
        $(this).css("fill", getRandomColor()); 
    })
    $("#spot2").hover(function() {
        $(this).css("fill", getRandomColor()); 
    })
    $("#spot3").hover(function() {
        $(this).css("fill", getRandomColor()); 
    })
})

// hover interaction grass
$(document).ready(function() {
    $("#grass").hover(function() {
        $(this).css("fill", getRandomColor()); 
    })
})

const getRandomColor = () => {
    return '#' + Math.floor(Math.random()*16777215).toString(16);
}

function draw() {
    var canvas = document.getElementById('canvas');
    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');

        // resolution: 300 x 150

        // sky
        ctx.fillStyle = "lightblue";
        ctx.fillRect(0, 0, canvas.width, canvas.height);

        // grass
        ctx.fillStyle = 'green';
        ctx.fillRect(0, canvas.height, canvas.width, -25);

        // stem
        ctx.fillStyle = 'beige';
        ctx.fillRect(132, 55, 36, 85);

        // cap
        ctx.beginPath();
        ctx.arc(150, 75, 45, 0, Math.PI, true);
        ctx.fillStyle = 'red';
        ctx.fill();

        // spots
        let growing = true;
        let size = 0;
        setInterval(() => {
            //clear
            ctx.beginPath();
            ctx.arc(150, 75, 45, 0, Math.PI, true);
            ctx.fillStyle = 'red';
            ctx.fill();

            //draw
            ctx.beginPath();
            ctx.arc(145, 50, size, 0, 2 * Math.PI);
            ctx.fillStyle = 'white';
            ctx.fill();

            ctx.beginPath();
            ctx.arc(180, 65, size, 0, 2 * Math.PI);
            ctx.fillStyle = 'white';
            ctx.fill();
    
            ctx.beginPath();
            ctx.arc(125, 60, size, 0, 2 * Math.PI);
            ctx.fillStyle = 'white';
            ctx.fill();

            // regulate animation
            if (size == 5) {growing = false}
            else if (size == 0) {growing = true}

            if (growing) {size++}
            else {size--}
        }, 125);
    }
}