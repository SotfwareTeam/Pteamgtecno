const container = document.querySelector(".container");
const btnSinIn = document.getElementById("btn-sign-in");
const btnSinUp = document.getElementById("btn-sign-up");

btnSinIn.addEventListener("click",()=>{
    container.classList.remove("toggle");
});
btnSinUp.addEventListener("click",()=>{
    container.classList.add("toggle")
});