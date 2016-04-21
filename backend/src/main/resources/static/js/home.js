var userPwd = {};

// window.addEventListener('load', function(){
// 	// var btnWidth = $("#signup-btn").width();
// 	// $(".ui-btn").css("width", btnWidth);
// 	// alert(btnWidth);
// 	document.getElementById("login-form").addEventListener('submit', function(e){
// 		e.preventDefault();
// 		login();
// 	}, false);

// 	document.getElementById("signup-form").addEventListener('submit', function(e){
// 		e.preventDefault();
// 		addSignUp();
// 	}, false);

// 	document.getElementById("forgetpwd-form").addEventListener('submit', function(e){
// 		e.preventDefault();
// 		resetpwd();
// 	}, false);

// 	document.getElementById("changepwd-form").addEventListener('submit', function(e){
// 		e.preventDefault();
// 		changepwd();
// 	}, false);

// 	// document.getElementById("delete-order").addEventListener('tap', function(e){
// 	// 	e.preventDefault();
// 	// 	alert("taped");
// 	// 	//addSignUp();
// 	// }, false);
// });


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
//var resetEmail = "";

$(document).on("pagecreate", "#resetpwd-page", function(){
	//console.log('here in resetpwd-page');
	var resetEmail = $("#reset-email").text();
	//console.log(resetEmail);
	$("#resetpwd-submit").click(function(){
		var resetPwd = $("#resetpwd-pwd").val();
		console.log("newpwd: " + resetPwd + " email: " + resetEmail);
		var postParameters = {email: resetEmail, pwd: resetPwd};
		$.post("/userforgetpwd",postParameters,function(responseJSON){
			//console.log('here in user forget pwd');
			var done = JSON.parse(responseJSON).done;
			$("#reset-scd").css('display', 'block');
		});
	});

	$("#backtologin-submit").click(function(){
		$(location).attr('href', '/home#login-page');
		//alert('here!');
	});
});

$(document).on("pagecreate", "#verify-page", function(){
	$("#backtologin-submit").click(function(){
		$(location).attr('href', '/home#login-page');
		//alert('here!');
	});
});

// $(document).on("pagecreate", "#resetpwd-page", function(){
// 	$("#backtologin-submit").click(function(){
// 		$(location).attr('href', '/home#login-page');
// 		//alert('here!');
// 	});
// });

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
	console.log('1 in email');
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
	console.log('in email');
	return true;
}

// function checkLoginPwd(pwd) {
// 	if (pwd == "") {
// 		$("#login-error").html('<span style="font-weight: bold">Password is not valid</span><br>Password cannot be empty');
// 		return false;
// 	}
// }

function checkSignupPwd(pwd) {
	console.log('1 in pwd');
	if (pwd == "") {
		$("#signup-error").html('<span style="font-weight: bold">Password is not valid</span><br>Password cannot be empty');
		return false;
	}
	console.log('in pwd');
	return true;
}


//only accepts characters, underscore and numbers yet.
function checkSignupUsername(username) {
	console.log('1 in username');
	var pattern = /^\w+$/;
	if (!username.match(pattern)) {
		$("#signup-error").html('<span style="font-weight: bold">Username is not valid</span><br>It should only contains underline and characters');
		return false;
	}
	console.log('in username');
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
		var validlogin;
		$.post("/userlogin",postParameters,function(responseJSON){
			//username = JSON.parse(responseJSON).user.name;
			if (JSON.parse(responseJSON).error != undefined) {
				$("#login-error").html('<span style="font-weight: bold">Login failed</span>Please check your email and password.<br>');
			} else {
				username = JSON.parse(responseJSON).user.name;
				console.log('login username: ' + username);
				contact = JSON.parse(responseJSON).user.contact;
				history = JSON.parse(responseJSON).user.history;
				// subscribe = JSON.parse(responseJSON).subscribe;
				email = enteredEmail;
				console.log('in userlogin: ' + email);
				password = pwd;

				//var buy = true;
				var postParameters = {email: email};
				//send a request to server to change to Hong's buy page.
				// $.get("/buy", postParameters, function(responseJSON){
				// 	//var done = JSON.parse(responseJSON).done;
				// });

				//$.redirect("/buy", {email: email});

				//$(location).attr('href', '/home#page_buy_1');
				$.mobile.changePage($("#page_buy_1"));
			}
		});

		//$.mobile.changePage($("#delivery-page"));
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
		$.post("/signupemail", postParameters, function(responseJSON){
			var done = JSON.parse(responseJSON).done;
			console.log("signup: " + done);
		});


		$.mobile.changePage($("#signupwait-page"));

		// console.log('after email');
		// var postParameters = {username: username, email: email, pwd: pwd, subscribe: subscribe};
		// console.log(postParameters);
		// $.post("/usersignup",postParameters,function(responseJSON){
		// 	console.log('reaches here');
		// 	var success = JSON.parse(responseJSON).success;
		// 	console.log(success);
		// })
	})
});

// $(document).on("pagecreate", "#signupwait-page", function(){

// });

$(document).on("pagecreate", "#forgetpwd-page", function(){
	//e.preventDefault();
	$("#forgetpwd-submit").click(function(){
		var email = $("#forgetpwd-user").val();
		//resetEmail = email;
		console.log(email);
		var postParameters = {email: email};
		$.post("/passwordemail",postParameters,function(responseJSON){
			var done = JSON.parse(responseJSON).done;
			$.mobile.changePage($("#resetpwdwait-page"));
			//var done = JSON.parse(responseJSON).done;
		})
	});
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
	contact = "000-000-0000";//placeholder
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






request = JSON.parse(localStorage.request||'{}');
localStorage.request = JSON.stringify(request);

$( document ).on( "pageinit", "#page_buy_1", function(event) {
	console.log('in pagebuy1: ' + email);
  $("#progress_bar").progressbar({
     value: 25
  });

  $(".meet_up_confirm").tap(function(){
    request = JSON.parse(localStorage.request);
    request.method="meet";
    delete request.address;

    localStorage.request = JSON.stringify(request);
  })

  $(".deliver_confirm").tap(function(){
    request = JSON.parse(localStorage.request);
    request.method="deliver";
    localStorage.request = JSON.stringify(request);
  })

});


$( document ).on( "pageinit", "#page_deliver", function(event) {
  
  $("#progress_bar_deliver").progressbar({
     value: 37.5
  });

  locations=["Young Orchard Ave 010", "Marcy House: Wriston Quad", "Vartan Gregorian Quad A (New Dorm A)", "Richmond St 233", "Graduate Ctr A", "Davol Sq 003", "Young Orchard Ave 002", "Barbour Hall", "Sharpe House", "George St 180", "Peter Green House", "Hegeman Hall", "Brown St 068.5", "Caswell Hall", "Geo-Chem Bldg", "Manning Hall", "Ship St 070", "New Pembroke No. 4", "Bio-Med Grimshaw-Gudewicz", "Brown Stadium", "Perkins Hall", "Jameson-Mead: Keeney Quad", "Littlefield Hall", "University Hall", "List (Albert & Vera) Art Building", "Maddock Alumni Center", "Harkness House: Wriston Quad", "Sears House: Wriston Quad", "Watson Center For Information Tech", "Morrison-Gerard Studio", "Slater Hall", "George St 163", "Blistein House", "Waterman St 133", "Norwood House", "Goddard House: Wriston Quad", "Hope College", "Machado (Antonio) House", "Fones Alley 008", "Charlesfield St 059", "Miller Hall", "Metcalf Hall", "Bio-Med ACF", "Chapin House: Wriston Quad", "Dyer St 200", "Benevolent St 026", "Barus & Holley", "Manning St 029", "Faculty Club", "Giddings House", "Stimson Ave 002", "Emery: Pembroke Quad", "Rhode Island Hall", "Horace Mann House", "Robinson Hall", "Steinert Center", "Brook St 341", "Sciences Library", "Waterman St 094", "Bowen St 219", "Benevolent St 005", "OMAC Athletic Center", "Graduate Ctr D", "Prospect House", "Nelson Fitness Center", "Manning St 037", "Lincoln Field Building", "Lyman Hall", "Barus Building", "Metcalf Research Building", "Kassar (Edward W.) House", "Archibald-Bronson: Keeney Quad", "Granoff Ctr For The Creative Arts", "Walter Hall", "South Main St 121", "Sidney E. Frank Hall Life Sciences", "Meehan Auditorium", "Hoppin (Thomas P.) House", "New Pembroke No. 2", "Haffenreffer Barn", "Wilson Hall", "Diman House: Wriston Quad", "Meiklejohn House", "Graduate Ctr E", "Minden Hall", "Brown St 111", "Benevolent St 020", "West House", "Angell St 195", "Verney-Woolley Hall (VDub)", "Marston Hall", "Broadway 233", "Olney House: Wriston Quad", "George St 067", "Bio-Med Ctr", "MacMillan Hall", "Angell St 169", "Marston Boathouse", "Rochambeau House", "Waterman St 137", "MacFarlane House", "Brown Office Bldg", "Grant Fulton", "John Hay Library", "Morriss Hall: Pembroke Quad", "Arnold Lab", "Salomon Hall (Main Green)", "Medical Research Lab", "Faunce House", "Rockefeller Library", "Brown St 070", "Dyer House", "Urban Environmental Lab", "Gerard House, Samuel N.", "Thayer St 315", "Pizzitola", "Benoni Cooke House", "Orwig Music Hall", "Ladd Observatory", "5th Ave 500", "Andrews Hall", "Brook St 333", "Smith-Buonanno Hall", "King House", "Montgomery St 601", "Young Orchard Ave 004", "Graduate Ctr C", "Alumnae Hall", "Maxcy Hall", "George St 025", "Buxton House: Wriston Quad", "New Pembroke No. 3", "Waterman St 085", "Lippitt House", "Thayer St 135", "Nicholson House", "Graduate Ctr B", "Haffenreffer Outing Reservation Fac", "John St 050", "Pembroke Hall", "J. Walter Wilson Building", "Champlin: Pembroke Quad", "Vartan Gregorian Quad B (New Dorm B)", "Hemisphere Bldg", "T.F. Green Hall","Annmary Brown Memorial Library", "Everett-Poland: Keeney Quad", "Hope St 190", "Elm St 110", "Waterman St 131", "George St 182", "Waterman St 070", "Woolley Hall: Pembroke Quad", "Wayland House: Wriston Quad", "Partridge Hall & Annex", "Shirley Miller House", "Haffenreffer Museum Collections Res", "Churchill House", "John Carter Brown Library", "Sharpe Refectory (Ratty)", "Corliss-Brackett", "Feinstein", "Sayles Hall", "Prince Engineering Lab", "Richmond St 222 (Med Ed)", "Watson Institute", "George St 155", "New Pembroke No. 1", "Mencoff Hall", "Wilbour Hall"];

  $("#autocorrect").autocomplete({
     minLength:3,
     source: locations,
     select:function(){
      $("#address_confirm").css("display","block");
     }
  });

  $("#autocorrect").keyup(function(){
    var text =  $("#autocorrect").val().trim();
    if(locations.indexOf(text)!=-1){
      $("#address_confirm").css("display","block");
    } else{
      $("#address_confirm").css("display","none");
    }
  })

  $('.ui-menu-item').click(function(){
    var text = $('.ui-menu-item').text()
    $("autocorrect").text(text);
    $("#address_confirm").css("display","block");
  })

  $('.ui-autocomplete').css({'fontSize': '1em', 'width': $(window).width()-16*2 ,'height':"auto",'background-color':'none'});

  $('#address_confirm').click(function(){
    request = JSON.parse(localStorage.request);
    request.address = $("#autocorrect").val();  
    localStorage.request = JSON.stringify(request);
  })

});


$( document ).on( "pageinit", "#page_buy_2", function(event) {

  $("#progress_bar_2").progressbar({
    value: 50
  });

  $(".andrews").click(function(){
    request = JSON.parse(localStorage.request);
    request.eatery = "andrews";  
    localStorage.request = JSON.stringify(request);
    //console.log(request);
  });

  $(".blueroom").click(function(){
    request = JSON.parse(localStorage.request);
    request.eatery = "blueroom";  
    localStorage.request = JSON.stringify(request);
    //console.log(request);
  });

  $(".josiahs").click(function(){
    request = JSON.parse(localStorage.request);
    request.eatery = "jos";  
    localStorage.request= JSON.stringify(request);
    //localStorage.request = JSON.stringify(request);
  })


});

$( document ).on( "pageshow", "#page_buy_3", function(event) {

  $("#progress_bar_3").progressbar({
    value: 75
  });

  $("#menu_fieldset").empty();
  
  //$("#menu_fieldset").append("<legend>What do you wanna eat?</legend>");
  //$("#menu_fieldset").append("<legend style='margin-top: 10px;margin-bottom: 10px;''>Total: <span style='color:#9D8189'>$0.00</span></legend>");

  //$('#page_buy_3').append("<p>"+request.eatery+"</p>");
  request = JSON.parse(localStorage.request);
  var eatery = request.eatery;
  var date = new Date();
  var day = date.getDay();

  var hour = date.getHours();
  var minute = date.getMinutes();
  var time = (hour-2)*60+minute;

  request.day=day;
  request.time=time;

  //var map = {eatery:eatery,day:day,time:time}
  localStorage.request = JSON.stringify(request);

  var menu={
       "food-1":{
              "name":"Pho",
              "price":7.30,
              "id":"food-1"
       },
       "food-2":{
              "name":"Cheese Pizza Slice",
              "price": 2.40,
              "id":"food-2"
       },
       "food-3":{
              "name":"MYO Panini",
              "price": 5.95,
              "id":"food-3"
       },
       "food-4":{
              "name":"Fresh Salad",
              "price": 4.00,
              "id":"food-4"
       },
       "food-5":{
              "name":"Sushi",
              "price": 2.40,
              "id":"food-5"
       },
  };



  var items = Object.keys(menu);
  for (var i = 0;i<items.length;i++){
    var food = menu[items[i]];
    var id = food.id;
    var name = food.name;
    var price = food.price;

    var label = "<div class='ui-checkbox'> <label for="+ id + " style='font-weight: 400;'class='ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-right ui-checkbox-off ui-first-child'>"+ name + ": $"+ price+ "</label>";
    var input = "<input type='checkbox' name=" + id + " id=" + id + "></div>";
    $(label+input).appendTo($('#menu_fieldset')).trigger("create");
  }

  var menu_order = {};
  var checked_count = 0;
  var total_price = 0.00;
  $('#price_span').text("$"+total_price);
  
  $('#menu :checkbox').click(function() {
    var $this = $(this);
    var id = $this.attr('name');
    // $this will contain a reference to the checkbox   
    if ($this.is(':checked')) {
        checked_count +=1;
        $('#menu_confirm').css("display","block");
        menu_order[id]=true;

        total_price += menu[id].price;
        total_price = Math.abs(total_price.toFixed(2));

        
        $('#price_span').text("$"+total_price);

    } else {
         checked_count-=1;
         if(checked_count==0){
          $('#menu_confirm').css("display","none");
         }
         delete menu_order[id];
         
         total_price -= menu[id].price;
         total_price=Math.abs(total_price.toFixed(2));

         $('#price_span').text("$"+ total_price);
    }
  });

  $('#menu_confirm').tap(function(){
    request = JSON.parse(localStorage.request);
    var menu_string = Object.keys(menu_order).join(' ');
    request.menu=menu_string;
    request.price = total_price;
    localStorage.request= JSON.stringify(request);
    console.log(request);
  })


})

$( document ).on( "pageshow", "#page_buy_4", function(event) {
  $("#progress_bar_4").progressbar({
     value: 95
  });

  $('#wrong_number').css("display","none");

  request = JSON.parse(localStorage.request);

  var tap_counter = 0;

  $('#payment_suggestion').tap(function(){
    if (tap_counter%2==0){
      $('#payment_suggestion_detail').css("display","block");
    } else{
      $('#payment_suggestion_detail').css("display","none");
    }
    tap_counter+=1;
  })

  $("#final_confirm").click(function(){
    
    var phone = $('#tel-1').val().trim();
    console.log(phone);
    
    if(phone.match(/\d/g).length===10 && phone.length==10){
      $('#wrong_number').css("display","none");
      request.phone=phone;
      request.duration = $('#duration').val();
      request.bound = $('#bound').val();
      console.log(request);
      localStorage.request = JSON.stringify(request)

      $.post("/placeorder",{user:email,address:request.address,eatery:request.eatery,menu:request.menu,duration:request.duration,price:request.price,priceBound:request.bound,},function(response){
        data = JSON.parse(response);
        console.log(response);
      })
    } else{
      $('#wrong_number').css("display","block");
    }
  })

  request = JSON.stringify(localStorage.request);
});

// function login() {
// 	var email = document.getElementById("login-user").value;
// 	var pwd = document.getElementById("login-pwd").value;

// 	//alert(username + " " + pwd);
// 	// if ( username in userPwd) {
// 	// 	alert("yes!");
// 	// }
// 	// userPwd[username] = pwd;
// 	//alert(userPwd[username]);
// 	$.mobile.changePage($("#delivery-page"));
// }

// function addSignUp() {
// 	var name = document.getElementById("signup-name").value;
// 	var email = document.getElementById("signup-user").value;
// 	var pwd = document.getElementById("signup-pwd").value;
// 	var subscribe = document.getElementById("subscribe").checked;

// 	//alert(email + " " + pwd + " " + name);
// 	userPwd[email] = pwd;
// 	$.mobile.changePage($("#delivery-page"));
// 	//alert(userPwd[username] + " " + subscribe);
// }

// function resetpwd() {
// 	var email = document.getElementById("forgetpwd-user").value;

// 	//alert(username);
// }

// function changepwd() {
// 	var currPwd = document.getElementById("prev-pwd").value;
// 	var newPwd = document.getElementById("new-pwd").value;
// 	var retypeNewPwd = document.getElementById("re-new-pwd").value;
// 	if (newPwd == retypeNewPwd) {
// 		alert(currPwd + " " + newPwd);
// 	}
// }
