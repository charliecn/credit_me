var upperPrice = 0.0;
var lowerPrice = 0.0;
var upperTime = 0;
var lowerTime = 0;

window.addEventListener('load', function(){
	var upperRange = document.getElementById("upper_range");
	var lowerRange = document.getElementById("lower_range");
	upperRange.addEventListener('change', function(){
		upperPrice = (this.value/50) + 7;
		lowerRange.max = this.value;
		document.getElementById("upper_price").innerHTML = upperPrice;
	});
	lowerRange.addEventListener('change', function(){
		lowerPrice = (this.value/50) + 7;
		upperRange.min = this.value;
		document.getElementById("lower_price").innerHTML = lowerPrice;
	});
	var upperTimeRange = document.getElementById("upper_time_range");
	var lowerTimeRange = document.getElementById("lower_time_range");
	upperTimeRange.addEventListener('change', function(){
		upperTime = this.value;
		lowerTimeRange.max = this.value;
		document.getElementById("upper_time").innerHTML = upperTime;
	});
	lowerTimeRange.addEventListener('change', function(){
		lowerTime = this.value;
		upperTimeRange.min = this.value;
		document.getElementById("lower_time").innerHTML = lowerTime;
	});

	document.getElementById("userinput_field").addEventListener('submit', function(e){
		e.preventDefault();
		handleInput();
	}, false);

	document.getElementById("find_button").addEventListener('click', function(e){
		window.location = 'waiting.html';
	}, false);

});

function handleInput() {
	//alert('handleinput');
	//e.preventDefault();
	//alert('here!');
	storeMessage();

	document.getElementById("confirm").style.display = "block";
	//window.location = '';
}

function storeMessage() {
	//alert("here!");
	var diningHall = document.getElementById("halls_input").value;
	var delivery;
	var radios = document.getElementsByName("delivery");
	if (radios[0].checked && radios[1].checked == false) {
		delivery = 'yes';
	} else if (radios[1].checked && radios[0].checked == false) {
		delivery = 'no';
	}

	document.getElementById("confirm_info").innerHTML = 
		'<p>Name: username<br>' + 
		'Dining Hall: ' + diningHall + '<br>' + 
		'Delivery: ' + delivery + '<br>' + 
		'price span: from ' + upperPrice + ' to ' + lowerPrice + '<br>' + 
		'time span: from ' + upperTime + ' to ' + lowerTime + '</p>' + 
		'<p> Find me a match!</p>';
	//alert('here!');
	//document.getElementById("confirm").style.display = "block";
	//alert(diningHall + " " + delivery + " " + upperPrice + " " + lowerPrice + " " + upperTime + " " + lowerTime);
	//window.location = 'creditMe.html';
}