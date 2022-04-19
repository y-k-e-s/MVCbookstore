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