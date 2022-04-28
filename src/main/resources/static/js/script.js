const authors = document.querySelectorAll('.authors span');


for (const author of authors) {
	const id = author.getAttribute("id");
	
	const div = document.createElement("div");
	const idAtt = document.createElement("id");
	div.append(idAtt);
	div.setAttribute("id", "box")

	if (id.substring(6, 7).includes("1")) {
		author.textContent = ", (...)";
		author.classList.add("showAuthors");
		div.innerHTML = `<i class="fa-solid fa-angles-right">see more</i>`;
		author.append(div);
	} else if (id.substring(6, 7) > 1) {
		author.textContent = '';
	}
}

const removes = document.querySelectorAll(".delete");
	
	for (var i = 0; i < removes.length; i++) {
		removes[i].addEventListener('click', function(event) {
			event.preventDefault();
			var answer = window.confirm("Are you sure you want to delete this book?");
			if (answer) {
				window.location.href = this.getAttribute('href');
			}
		});
	}
	
const quotes = document.querySelectorAll(".quotesContainer p");
let j = 0;

function toggleQuotes(){
	setTimeout(function(){

		if(j < quotes.length - 1){
			
			quotes[j].classList.remove("change");
			
			quotes[j+1].classList.add("change");
			
			console.log("j < quotes.length");
			j++;
			toggleQuotes();
			
		}else{
			console.log("else");
			j = 0;
			quotes[quotes.length - 1].classList.remove("change");
			quotes[0].classList.add("change");
			toggleQuotes();
		}

	}, 10000)
}

toggleQuotes();


