var upperPrice = 0.0;
var lowerPrice = 0.0;
var upperTime = 0;
var lowerTime = 0;

function storeMessage() {
	var diningHall = document.getElementById("halls_input").value;
	var delivery;
	var radios = document.getElementsByName("delivery");
	if (radios[0].checked && radios[1].checked == false) {
		delivery = true;
	} else if (radios[1].checked && radios[0].checked == false) {
		delivery = false;
	}
	console.log(diningHall + " " + delivery + " " + upperPrice + " " + lowerPrice + " " + upperTime + " " + lowerTime);
}

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

	userinput_field.addEventListener('submit', storeMessage, false);

});