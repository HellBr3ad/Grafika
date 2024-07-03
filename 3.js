//zadanie3

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lab 2, Exercise 1</title>
<style>
    /* This style section is here to make the canvas more obvious on the
       page.  It is white on a light gray page background, with a thin
       black border. */
    body {
        background-color: #DDDDDD;
    }
    canvas {
        background-color: white;
        display: block;
    }
    #canvasholder {
        border:2px solid black;
        float: left; /* This makes the border exactly fit the canvas. */
    }
</style>
<script>

    "use strict";  // gives improved error-checking in scripts.

    var canvas;    // The canvas element on which we will draw.
    var graphics;  // A 2D graphics context for drawing on the canvas.
    var pixelSize; // The size of a pixel in the coordinate system; set up by
                   //    applyWindowToViewportTransform function when it is called.
    
    /**
     *  The draw() function is called by init() after the page loads,
     *  to draw the content of the canvas.  At the start, clear the canvas
     *  and save a copy of the state; restore the state at the end.  (These
     *  actions are not necessary in this program, since the function will
     *  only be called once.)
     */
    function draw() {
    
        graphics.clearRect(0,0,600,600);
		

		graphics.beginPath();
		graphics.fillStyle = "#FFA500"; 
        graphics.moveTo(50,400);  
        graphics.bezierCurveTo(50 , 60 , 550 , 60 , 550 , 400 );
		graphics.lineTo(50, 400);
		graphics.fill(); 
		graphics.stroke(); 
		
		//oczy
        graphics.beginPath();
        graphics.fillStyle = "#FFFFFF";
        graphics.strokeStyle ="#000000"; 
        graphics.fillCircle(235,250,30);
        graphics.fill();  
        graphics.stroke(); 
		graphics.fillCircle(365,250,30);
		graphics.fill();  
		graphics.stroke(); 

		
		graphics.fillStyle = "#000000";
        graphics.strokeStyle ="#000000"; 
        graphics.fillCircle(230,250,20);
        graphics.fill(); 
		graphics.fillCircle(360,250,20);
		graphics.fill();  
		
        graphics.fillStyle = "#FFFFFF"; 
		graphics.strokeStyle ="#FFFFFF";
		graphics.fillCircle(225,243,8);
        graphics.fill(); 
		graphics.fillCircle(355,243,8);
		graphics.fill();  
		

		//usta
		graphics.beginPath();
        graphics.fillStyle = "#000000";
		graphics.strokeStyle ="#000000";

        graphics.moveTo(230 , 315 );
        graphics.bezierCurveTo(230 , 350 , 370 , 350 , 370 , 315 );
        graphics.bezierCurveTo(330 , 400 , 270 , 400 , 229 , 315 );
        graphics.fill(); 
		graphics.stroke(); 
        graphics.moveTo(205 , 325 );
        graphics.bezierCurveTo(205 , 325 , 225 , 330 , 230 , 295 );
        graphics.bezierCurveTo(230 , 295 , 235 , 335 , 205 , 325 );
        graphics.fill(); 
        graphics.stroke(); 
        graphics.moveTo(395 , 325 );
        graphics.bezierCurveTo(395 , 325 , 375 , 330 , 370 , 295 );
        graphics.bezierCurveTo(370 , 295 , 365 , 335 , 395 , 325 );
        graphics.fill(); 
        graphics.stroke(); 

		
		//zęby
		graphics.beginPath();
        graphics.strokeStyle = "#000000";
		graphics.fillStyle = "#FFFFFF";
		graphics.moveTo(280, 340);
		graphics.lineTo(300, 340);
        graphics.lineTo(300, 360);
        graphics.lineTo(280, 360);
        graphics.lineTo(280, 350);
        graphics.fill();
		graphics.stroke(); 
        graphics.moveTo(300, 340);
        graphics.lineTo(320, 340);
        graphics.lineTo(320, 362);
        graphics.lineTo(300, 362);
        graphics.lineTo(300, 350);
        graphics.fill();
		graphics.stroke(); 
	    graphics.closePath();
		
    } // end of draw()
    
    
    /**
     * Sets up a transformation in the graphics context so that the canvas will
     * show x-values in the range from left to right, and y-values in the range
     * from bottom to top.  If preserveAspect is true, then one of the ranges
     * will be increased, if necessary, to account for the aspect ratio of the
     * canvas.  This function sets the global variable pixelsize to be the
     * size of a pixel in the new coordinate system.  (If preseverAspect is
     * true, pixelSize is the maximum of its horizontal and vertical sizes.)
     */
    function applyWindowToViewportTransformation(left,right,bottom,top,preserveAspect) {
        var displayAspect, windowAspect;
        var excess;
        var pixelwidth, pixelheight;
        if (preserveAspect) {
            // Adjust the limits to match the aspect ratio of the drawing area.
            displayAspect = Math.abs(canvas.height / canvas.width);
            windowAspect = Math.abs(( top-bottom ) / ( right-left ));
            if (displayAspect > windowAspect) {
                // Expand the viewport vertically.
                excess = (top-bottom) * (displayAspect/windowAspect - 1);
                top = top + excess/2;
                bottom = bottom - excess/2;
            }
            else if (displayAspect < windowAspect) {
                // Expand the viewport vertically.
                excess = (right-left) * (windowAspect/displayAspect - 1);
                right = right + excess/2;
                left = left - excess/2;
            }
        }
        graphics.scale( canvas.width / (right-left), canvas.height / (bottom-top) );
        graphics.translate( -left, -top );
        pixelwidth =  Math.abs(( right - left ) / canvas.width);
        pixelheight = Math.abs(( bottom - top ) / canvas.height);
        pixelSize = Math.max(pixelwidth,pixelheight);
    }  // end of applyWindowToViewportTransformation()


    /**
     * This function can be called to add a collection of extra drawing function to
     * a graphics context, to make it easier to draw basic shapes with that context.
     * The parameter, graphics, must be a canvas 2d graphics context.
     *
     * The following new functions are added to the graphics context:
     *
     *    graphics.strokeLine(x1,y1,x2,y2) -- stroke the line from (x1,y1) to (x2,y2).
     *    graphics.fillCircle(x,y,r) -- fill the circle with center (x,y) and radius r.
     *    graphics.strokeCircle(x,y,r) -- stroke the circle.
     *    graphics.fillOval(x,y,r1,r2) -- fill oval with center (x,y) and radii r1 and r2.
     *    graphics.stokeOval(x,y,r1,r2) -- stroke the oval
     *    graphics.fillPoly(x1,y1,x2,y2,...) -- fill polygon with vertices (x1,y1), (x2,y2), ...
     *    graphics.strokePoly(x1,y1,x2,y2,...) -- stroke the polygon.
     *    graphics.getRGB(x,y) -- returns the color components of pixel at (x,y) as an array of
     *         four integers in the range 0 to 255, in the order red, green, blue, alpha.
     *
     * (Note that "this" in a function that is called as a member of an object refers to that
     * object.  Here, this will refer to the graphics context.)
     */
    function addGraphicsContextExtras(graphics) {
        graphics.strokeLine = function(x1,y1,x2,y2) {
           this.beginPath();
           this.moveTo(x1,y1);
           this.lineTo(x2,y2);
           this.stroke();
        }
        graphics.fillCircle = function(x,y,r) {
           this.beginPath();
           this.arc(x,y,r,0,2*Math.PI,false);
           this.fill();
        }
        graphics.strokeCircle = function(x,y,radius) {
           this.beginPath();
           this.arc(x,y,radius,0,2*Math.PI,false);
           this.stroke();
        }
        graphics.fillPoly = function() { 
            if (arguments.length < 6)
               return;
            this.beginPath();
            this.moveTo(arguments[0],arguments[1]);
            for (var i = 2; i+1 < arguments.length; i = i + 2) { 
               this.lineTo(arguments[i],arguments[i+1]);
            }
            this.closePath();
            this.fill();
        }
        graphics.strokePoly = function() { 
            if (arguments.length < 4)
               return;
            this.beginPath();
            this.moveTo(arguments[0],arguments[1]);
            for (var i = 2; i+1 < arguments.length; i = i + 2) { 
               this.lineTo(arguments[i],arguments[i+1]);
            }
            this.closePath();
            this.stroke();
        }
        graphics.fillOval = function(x,y,horizontalRadius,verticalRadius) {
           this.save();
           this.translate(x,y);
           this.scale(horizontalRadius,verticalRadius);
           this.beginPath();
           this.arc(0,0,1,0,2*Math.PI,false);
           this.restore();
           this.fill();
        }
        graphics.strokeOval = function(x,y,horizontalRadius,verticalRadius) {
           this.save();
           this.translate(x,y);
           this.scale(horizontalRadius,verticalRadius);
           this.beginPath();
           this.arc(0,0,1,0,2*Math.PI,false);
           this.restore();
           this.stroke();
        }
        graphics.getRGB = function(x,y) {
            var color = this.getImageData(x,y,1,1);
            return color.data;
        }
    }    // end of addGraphicsContextExtras()
    
    /**
     * The init() funciton is called after the page has been
     * loaded.  It initializes the canvas and graphics variables.
     * It calles addGraphicsContextExtras(graphics) to add the extra
     * drawing functions to the graphics context, and it calls draw()
     * to draw on the canvas.
     */
    function init() {
        try {
            canvas = document.getElementById("canvas");
            graphics = canvas.getContext("2d");
        } catch(e) {
            document.getElementById("canvasholder").innerHTML =
               "Canvas graphics is not supported.<br>" +
               "An error occurred while initializing graphics.";
        }
        addGraphicsContextExtras(graphics);
        draw();  // Call draw() to draw on the canvas.
    }
    
</script>
</head>
<body onload="init()"> <!-- the onload attribute here is what calls the init() function -->

<h2>Lab 2, Exercise 1</h2>

<noscript>
    <!-- This message will be shown in the page if JavaScript is not available. -->
<p>JavaScript is required to use this page.</p>
</noscript>

<div id="canvasholder">
<canvas id="canvas" width="600" height="600">
    <!-- This message is shown on the page if the browser doesn't support the canvas element. -->
Canvas not supported.
</canvas>
</div>

</body>
</html>
