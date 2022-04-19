var lastScrollpos = window.pageYOffset;

window.onscroll = function() {
	var currentScrollPos = window.pageYOffset;

	if (lastScrollpos > currentScrollPos) {
		document.getElementById("navbar").style.top = "0";
		document.getElementById("navbar").style.opacity = "1";
	} else {
		document.getElementById("navbar").style.top = "-80px";
		document.getElementById("navbar").style.opacity = "0";
	}

	lastScrollpos = currentScrollPos;
}

const button = document.querySelector(".button")
const hiddenBox = document.querySelector(".hiddenSuccessInfo")

console.log("hello!")

	function success(){
		setTimeout(function(){
		hiddenBox.classList.replace("hiddenSuccessInfo","successInfo")
		},1000)}

function redirect(){
	setTimeout(function(){
		window.location.href = button.getAttribute('href');
	}, 2000)}




button.addEventListener("click", function(event){
	event.preventDefault();
	success()
	console.log("inside click")
	redirect()
})


let books = document.querySelectorAll("#bookInCart")
let bookQuantity = books.length

const price = document.querySelector("#sum")
price.innerText = ("sum: " + bookQuantity*21 + "$");



















