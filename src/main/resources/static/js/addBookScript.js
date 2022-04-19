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


let authNo = 1;
const names = document.querySelectorAll(".authorsFields");
document.querySelector(".input").value = 1;


	let j = 1;
	for (let name of names) {
		if (j > 1) {
			name.remove();
		}
		j++;
	}


document.querySelector(".button").addEventListener("click", function() {
	authNo = document.querySelector(".input").value;
	calculateAuthorsNo();
})

const calculateAuthorsNo = function() {
	let i = 1;
	for (let name of names) {

		if (i > authNo) {
			name.remove();
			console.log("authNo: " + authNo + " " + "i: " + i);
		}
		if (i <= authNo) {
			const addedName = document.querySelector(".authContainer");
			addedName.append(name);
		}
		i++;
	}
}
