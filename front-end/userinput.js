var upperPrice = 0.0;
var lowerPrice = 0.0;
var upperTime = 0;
var lowerTime = 0;

window.addEventListener('load', function(){
	var upperRange = document.getElementById("upper_range");
	upperRange.addEventListener('change', function(){
		upperPrice = (this.value/50) + 7;
		if (lowerPrice === 0.0 || lowerPrice < upperPrice) {
			document.getElementById("upper_price").innerHTML = upperPrice;
		} else {
			alert("upper should be bigger than lower!");
		}
	});
	var lowerRange = document.getElementById("lower_range");
	lowerRange.addEventListener('change', function(){
		//alert(this.value);
		//var val = 0.0;
		lowerPrice = (this.value/50) + 7;
		if (upperPrice === 0.0 || upperPrice > lowerPrice) {
			document.getElementById("lower_price").innerHTML = lowerPrice;
		} else {
			alert("upper should be bigger than lower!");
		}
		//window.location = 'userinput.html';
	});
	var upperTimeRange = document.getElementById("upper_time_range");
	upperTimeRange.addEventListener('change', function(){
		//alert(this.value);
		//var val = 0.0;
		upperTime = this.value;
		//alert(upperTime + " " + lowerTime);
		if (lowerTime === 0 || lowerTime < upperTime) {
			document.getElementById("upper_time").innerHTML = upperTime;
		} else {
			alert("upper should be bigger than lower!");
		}
		//window.location = 'userinput.html';
	});
	var lowerTimeRange = document.getElementById("lower_time_range");
	lowerTimeRange.addEventListener('change', function(){
		//alert(this.value);
		//var val = 0.0;
		lowerTime = this.value;
		//alert(upperTime + " " + lowerTime);
		if (upperTime === 0 || upperTime > lowerTime) {
			document.getElementById("lower_time").innerHTML = lowerTime;
		} else {
			alert("upper should be bigger than lower!");
		}
		//window.location = 'userinput.html';
	});

	document.getElementById("userinput_field").addEventListener('submit', function(e){
		e.preventDefault();
		handleInput();
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