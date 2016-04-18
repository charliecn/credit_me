var userPwd = {};

window.addEventListener('load', function(){
	document.getElementById("login-form").addEventListener('submit', function(e){
		e.preventDefault();
		login();
	}, false);
	document.getElementById("signup-form").addEventListener('submit', function(e){
		e.preventDefault();
		addSignUp();
	}, false);
	document.getElementById("forgetpwd-form").addEventListener('submit', function(e){
		e.preventDefault();
		resetpwd();
	}, false);
});

function login() {
	var username = document.getElementById("login-user").value;
	var pwd = document.getElementById("login-pwd").value;

	alert(username + " " + pwd);
	// if ( username in userPwd) {
	// 	alert("yes!");
	// }
	// userPwd[username] = pwd;
	//alert(userPwd[username]);
	$.mobile.changePage($("#delivery-page"));
}

function addSignUp() {
	var username = document.getElementById("signup-user").value;
	var pwd = document.getElementById("signup-pwd").value;
	var subscribe = document.getElementById("subscribe").checked;

	//alert(username + " " + pwd);
	userPwd[username] = pwd;
	alert(userPwd[username] + " " + subscribe);
}

function resetpwd() {
	var username = document.getElementById("forgetpwd-user").value;

	alert(username);
}
