//Find number and buttons
let current_num = document.getElementById("real_num");
let increase = document.getElementById("increase");
let reset = document.getElementById("reset");
let decrease = document.getElementById("decrease");

//Execute a function when a button is pushed
increase.onclick = function () {
  current_num.innerHTML = Number(current_num.innerHTML) + 1;
};

reset.onclick = function () {
  current_num.innerHTML = 0;
}

decrease.onclick = function () {
  current_num.innerHTML = Number(current_num.innerHTML) - 1;
};
