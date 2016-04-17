window.addEventListener('load', function(){
	var buyButton = document.getElementById("buy_button");
	buyButton.addEventListener('click', function(){
		window.location = 'userinput.html';
	});

	var sellButton = document.getElementById("sell_button");
	sellButton.addEventListener('click', function(){
		window.location = 'login.html';
	})
});