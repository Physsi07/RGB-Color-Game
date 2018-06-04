var numberSquare = 6;
var colors = [];
var pickedColor;
var squares = document.querySelectorAll(".square");
var colorDisplay = document.querySelector("#colorDisplay");
var messageDisplayed = document.querySelector("#message");
var h1 = document.querySelector("h1");
var resetBtn = document.querySelector("#reset");
var modeBtns = document.querySelectorAll(".mode");

init();

function init(){
  setUpModeBtns();
  setUpSquares();
  reset();
}

function setUpModeBtns(){
  // MODE BUTTONS EVENT LISTENER
  for (var i = 0; i < modeBtns.length; i++) {
    modeBtns[i].addEventListener("click", function(){
      modeBtns[0].classList.remove("selected");
      modeBtns[1].classList.remove("selected");
      this.classList.add("selected");

      this.textContent === "Easy" ? numberSquare = 3: numberSquare = 6;
      reset();
    });
  }
}

function setUpSquares(){
  for(var i = 0; i < squares.length; i++){
    // ADD INITIAL COLOR TO square
    squares[i].style.backgroundColor = colors[i];

    // LOGINC FOR WHEN A COLOR IS CLICKED
    squares[i].addEventListener("click", function(){

      // GRAB THE COLOR THAT IS BEING CLICKED
      var clickedColor = this.style.backgroundColor;

      console.log(clickedColor, pickedColor);
      //COMPARE THAT COLOR TO PICKEDCOLOR
      if(clickedColor === pickedColor){
        messageDisplayed.textContent = "Correct";
        changeColor(clickedColor);
        h1.style.backgroundColor = clickedColor;
        resetBtn.textContent = "Play Again?"
      }else{
        this.style.backgroundColor = "#232323";
        messageDisplayed.textContent = "Try Again";
      }
    });
  }
}

// RESET FUNCTION
function reset(){
  // GENERATES ALL NEW Colors
  colors = generateRandomColor(numberSquare);
  // PICK A NEW RANDOM COLOR FROM ARRAYS
  pickedColor = pickColor();

  // CHANGE THE DISPLAY COLOR TO MATCH THE NEW pickedColor
  colorDisplay.textContent = pickedColor;
  messageDisplayed.textContent = " ";
  resetBtn.textContent = "New Colors";

  // CHANGE COLORS OF SQUARES BY REFLECTING THE NEW 6 COLOR IN THE PAGE
  for (var i = 0; i < squares.length; i++) {
    if (colors[i]) {
      squares[i].style.display = "block";
      squares[i].style.backgroundColor = colors[i];
    }else{
    squares[i].style.display = "none";
    }
  }
  h1.style.backgroundColor = "steelblue";
}

// SETTING THE RESET BUTTON EVENT LISTENER
resetBtn.addEventListener("click", function(){
  reset();
});

// FUNCTION THAT CHANGES THE COLOR
function changeColor(color){
  //LOOP THROUGH ALL SQUARES
  for(var i = 0; i < squares.length; i++){
    // CHANGE EACH COLOR TO MATCH THE GIVEN COLOR
    squares[i].style.backgroundColor = color;
  }
}

// A RANDOM FUNCTION FOR RANDOM COLORS
function pickColor(){
  var random = Math.floor(Math.random() * colors.length);
  return colors[random];
}

function generateRandomColor(number){
  // MAKE AN ARRAY
  var array = [];
  // ADD NUMBER TO THE RANDOM COLOR TO THE ARRAYS
  for(var i = 0; i < number; i++){
    //GET RANDOM COLOR AND PUSH INTO ARRAY
    array.push(randomColor());
  }
  // AND THE RETURN THAT ARRAY
  return array;
}

// FUNCTION THAT RETURNS A RANDOM COLOR
function randomColor(){
  // PICK A 'RED' FROM 0 - 255
  var r = Math.floor(Math.random() * 256);

  // PICK A 'GREEN' FROM 0 - 255
  var g = Math.floor(Math.random() * 256);

  // PICK A 'BLUE' FROM 0 - 255
  var b = Math.floor(Math.random() * 256);

  // SYNTISIZE INTO IT RGB FORMATH -> rgb(r, g, b);
  return "rgb(" + r + ", " + g + ", " + b + ")";

}
