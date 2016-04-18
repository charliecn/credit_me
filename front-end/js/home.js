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

var prevName;
var prevEmail;

// $("[data-role=footer]").fixedtoolbar({tapToggle: false});

$(document).on("pagecreate", "#orders-page", function(){
	$("#order-id-a").on("tap", function(e){
		//alert("taped!");
		$("#order-id").remove();
	});
});

$(document).on("pagecreate", "#profile-page", function(){
	//alert('here');
	//var prevEmail
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
			$("#profile-name").html('type in your name');
		}
		$("#profile-name").css('border-bottom', '1px solid #989797');
	});

	// $("#profile-email").on("tap", function(e){
	// 	e.preventDefault();
	// 	//alert('tap');
	// 	prevEmail = $(this).text();
	// 	//alert(prevEmail);
	// 	//alert("email: " + email);
	// 	//$(this).css('border', 'none');
	// 	$(this).html('');
	// 	$('<input></input>').attr({
	// 		'type': 'text',
	// 		'name': 'profile-email',
	// 		'id': 'profile-email-in',
	// 		'value': prevEmail
	// 	}).appendTo("#profile-email");
	// 	$('#profile-email-in').focus();
	// 	$(this).css('border', 'none');
	// });

	// $(document).on('blur', '#profile-email-in', function(){
	// 	//alert(prevEmail);
	// 	var newEmail = $(this).val();
	// 	//alert(newEmail)
	// 	$("#profile-email").html('');
	// 	$("#profile-email").html(newEmail);
	// 	if (newEmail === "" && prevEmail === ""){
	// 		//alert(prevEmail);
	// 		$("#profile-email").html('type in your email');
	// 	}
	// 	$("#profile-email").css('border-bottom', '1px solid #989797');
	// });
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
