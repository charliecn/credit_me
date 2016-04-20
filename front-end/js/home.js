var userPwd = {};

window.addEventListener('load', function(){
	// var btnWidth = $("#signup-btn").width();
	// $(".ui-btn").css("width", btnWidth);
	// alert(btnWidth);
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

	document.getElementById("changepwd-form").addEventListener('submit', function(e){
		e.preventDefault();
		changepwd();
	}, false);

	// document.getElementById("delete-order").addEventListener('tap', function(e){
	// 	e.preventDefault();
	// 	alert("taped");
	// 	//addSignUp();
	// }, false);
});


//user information
var email;
var contact = "000-000-0000";
var username = "lmhly";
var password;
var subscribe = false;//boolean
var history; //history is a list of strings?

//other variables.
var prevName;
var prevEmail;
var prevContact;
var btnWidth;

function checkLoginEmail(email) {
	var emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	if (!email.match(emailPattern)) {
		$("#login-error").html('<span style="font-weight: bold">Email is not valid</span><br>Please enter a valid brown email address');
		return false;
	}
	var edu = email.substring(email.length-10, email.length);
	console.log("edu: " + edu);
	if (edu != "@brown.edu") {
		$("#login-error").html('<span style="font-weight: bold">Email is not valid</span><br>Please enter a valid brown email address');
		return false;
	}
	return true;
}

function checkSignupEmail(email) {
	var emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	if (!email.match(emailPattern)) {
		$("#signup-error").html('<span style="font-weight: bold">Email is not valid</span><br>Please enter a valid brown email address');
		return false;
	}
	var edu = email.substring(email.length-10, email.length);
	console.log("edu: " + edu);
	if (edu != "@brown.edu") {
		$("#signup-error").html('<span style="font-weight: bold">Email is not valid</span><br>Please enter a valid brown email address');
		return false;
	}
	return true;
}

// function checkLoginPwd(pwd) {
// 	if (pwd == "") {
// 		$("#login-error").html('<span style="font-weight: bold">Password is not valid</span><br>Password cannot be empty');
// 		return false;
// 	}
// }

function checkSignupPwd(pwd) {
	if (pwd == "") {
		$("#signup-error").html('<span style="font-weight: bold">Password is not valid</span><br>Password cannot be empty');
		return false;
	}
	return true;
}


//only accepts characters, underscore and numbers yet.
function checkSignupUsername(username) {
	var pattern = /^\w+$/;
	if (!username.match(pattern)) {
		$("#signup-error").html('<span style="font-weight: bold">Username is not valid</span><br>It should only contains underline and characters');
		return false;
	}
	return true;
}

$(document).on("pagecreate", "#login-page", function(){
	$("#login-submit").click(function(){
		$("#login-error").html('');
		var enteredEmail = $("#login-user").val();
		var pwd = $.trim($("#login-pwd").val()).replace(/\s/g, '+');
		if (!checkLoginEmail(enteredEmail)) {
			console.log('email invalid');
			return;
		}
		//email = enteredEmail;
		console.log(email + " " + pwd);
		var postParameters = {email: enteredEmail, pwd: pwd};

		//this variable is used to indicate if the login is valid.
		// var validlogin;
		// $.post("/userlogin",postParameters,function(responseJSON){
		// 	validlogin = JSON.parse(responseJSON).validlogin;
		// 	if (!validlogin) {
		// 		$("#login-error").html('<span style="font-weight: bold">Login failed</span>Please check your email and password.<br>');
		// 	}
		// 	username = JSON.parse(responseJSON).username;
		// 	contact = JSON.parse(responseJSON).contact;
		// 	history = JSON.parse(responseJSON).history;
		// 	subscribe = JSON.parse(responseJSON).subscribe;
			email = enteredEmail;
			password = pwd;
		// })

		$.mobile.changePage($("#delivery-page"));
	})
});

$(document).on("pagecreate", "#signup-page", function(){
	//e.preventDefault();
	$("#signup-submit").click(function(){
		$("#signup-error").html('');
		var username = $.trim($("#signup-name").val()).replace(/\s/g, '+');
		var enteredEmail = $.trim($("#signup-user").val()).replace(/\s/g, '+');
		var pwd = $.trim($("#signup-pwd").val()).replace(/\s/g, '+');
		var subscribe = document.getElementById("subscribe").checked;
		console.log(username + " " + enteredEmail + " " + pwd + " " + subscribe);
		
		if (!checkSignupEmail(enteredEmail)) {
			console.log('email invalid');
			return;
		} else if (!checkSignupPwd(pwd)) {
			console.log('pwd invalid');
			return;
		} else if (!checkSignupUsername(username)) {
			console.log('username invalid');
			return;
		}
		email = enteredEmail;

		var postParameters = {username: username, email: email, pwd: pwd, subscribe: subscribe};
		// $.post("/usersignup",postParameters,function(responseJSON){

		// })
	})
});

$(document).on("pagecreate", "#forgetpwd-page", function(){
	//e.preventDefault();
	$("#forgetpwd-submit").click(function(){
		var email = $.trim($("#signup-user").val()).replace(/\s/g, '+');
		console.log(email);
		var postParameters = {email: email};
		// $.post("/userforgetpwd",postParameters,function(responseJSON){

		// })
	})
});

$(document).on("pagecreate", "#changepwd-page", function(){
	//e.preventDefault();
	$("#changepwd-submit").click(function(){
		$("#changepwd-error").html('');
		var prevPwd = $.trim($("#prev-pwd").val()).replace(/\s/g, '+');
		var newPwd = $.trim($("#new-pwd").val()).replace(/\s/g, '+');
		var reNewPwd = $.trim($("#re-new-pwd").val()).replace(/\s/g, '+');
		if (newPwd != reNewPwd) {
			$("#changepwd-error").html('<span style="font-weight: bold">Passwords do not match</span><br> You must enter the same password twice in order to confirm it.');
			return;
		} else if (password != prevPwd) {
			$("#changepwd-error").html('<span style="font-weight: bold">Current Password Not Correct</span><br> You must enter your current password correctly.');
		}
		$("#changepwd-error").html('<span style="font-weight: bold">Processing</span><br> Please wait.');
		console.log("change: " + prevPwd + " " + newPwd + " " + reNewPwd + " " + email);
		var postParameters = {prevPwd: prevPwd, newPwd: newPwd, email: email};
		// $.post("/userforgetpwd",postParameters,function(responseJSON){
			$("#changepwd-error").html('<span style="font-weight: bold">Password Changed Successfully.</span>')
		// })
	})
});

$(document).on("pagecreate", "#orders-page", function(){
	$("#changepwd-error").html('');
	$("#order-id-a").on("tap", function(e){
		//alert("taped!");
		$("#order-id").remove();
	});
});

$(document).on("pagecreate", "#profile-page", function(){
	
	//preset the returned value from login.
	$("#profile-subscribe").prop('checked', subscribe);
	$("#profile-name").text(username);
	$("#profile-contact").text(contact);
	$("#profile-email").text(email);


	$("#profile-name").on("tap", function(e){
		e.preventDefault();
		//alert('tap');
		prevName = $(this).text();
		//alert(prevEmail);
		//alert("email: " + email);
		//$(this).css('border', 'none');
		$(this).html('');
		$('<input></input>').attr({
			'type': 'text',
			'name': 'profile-name',
			'id': 'profile-name-in',
			'value': prevName
		}).appendTo("#profile-name");
		$('#profile-name-in').focus();
		$(this).css('border', 'none');
	});

	$(document).on('blur', '#profile-name-in', function(){
		//alert(prevEmail);
		var newName = $(this).val();
		//alert(newEmail)
		$("#profile-name").html('');
		$("#profile-name").html(newName);
		if (newName === "" && prevName === ""){
			//alert(prevEmail);
			//$("#profile-name").css("color", "#c2c2c2");
			$("#profile-name").html(username);
		}
		$("#profile-name").css('border-bottom', '1px solid #989797');
	});

	$("#profile-contact").on("tap", function(e){
		e.preventDefault();
		//alert('tap');
		prevContact = $(this).text();
		//alert(prevEmail);
		//alert("email: " + email);
		//$(this).css('border', 'none');
		$(this).html('');
		$('<input></input>').attr({
			'type': 'text',
			'name': 'profile-contact',
			'id': 'profile-contact-in',
			'value': prevContact
		}).appendTo("#profile-contact");
		$('#profile-contact-in').focus();
		$(this).css('border', 'none');
	});

	$(document).on('blur', '#profile-contact-in', function(){
		//alert(prevEmail);
		var newContact = $(this).val();
		//alert(newEmail)
		$("#profile-contact").html('');
		$("#profile-contact").html(newContact);
		if (newContact === "" && prevContact === ""){
			//alert(prevEmail);
			//$("#profile-contact").css("color", "#c2c2c2");
			$("#profile-contact").html(contact);
		}
		$("#profile-contact").css('border-bottom', '1px solid #989797');
	});

	$("#profile-subscribe").change(function(){
		console.log(this.checked);
		subscribe = this.checked;
		var postParameters = {subscribe: subscribe};
		// $.post("/userforgetpwd",postParameters,function(responseJSON){

		// })
	});
});

function login() {
	var email = document.getElementById("login-user").value;
	var pwd = document.getElementById("login-pwd").value;

	//alert(username + " " + pwd);
	// if ( username in userPwd) {
	// 	alert("yes!");
	// }
	// userPwd[username] = pwd;
	//alert(userPwd[username]);
	$.mobile.changePage($("#delivery-page"));
}

function addSignUp() {
	var name = document.getElementById("signup-name").value;
	var email = document.getElementById("signup-user").value;
	var pwd = document.getElementById("signup-pwd").value;
	var subscribe = document.getElementById("subscribe").checked;

	//alert(email + " " + pwd + " " + name);
	userPwd[email] = pwd;
	$.mobile.changePage($("#delivery-page"));
	//alert(userPwd[username] + " " + subscribe);
}

function resetpwd() {
	var email = document.getElementById("forgetpwd-user").value;

	//alert(username);
}

function changepwd() {
	var currPwd = document.getElementById("prev-pwd").value;
	var newPwd = document.getElementById("new-pwd").value;
	var retypeNewPwd = document.getElementById("re-new-pwd").value;
	if (newPwd == retypeNewPwd) {
		alert(currPwd + " " + newPwd);
	}
}
