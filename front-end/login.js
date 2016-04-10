window.addEventListener('load', function(){
	document.getElementById("login_form").addEventListener('submit', function(e){
		e.preventDefault();
		handleInput();
	}, false);
});

function handleInput() {
	var username = document.getElementById("username_field").value;
	var pwd = document.getElementById("password_field").value;

	//alert(username + " " + pwd);
	//do some kind of match here.
	window.location = 'userinput.html';
}