//var userPwd = {};

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
var buysell = {};
var buyorsell;
//var resetEmail = "";

function resetProfile(email, contact, username, subscribe) {
	var postParameters = {email: email, contact: contact, username: username, subscribe: subscribe};
	$.post("/changeinfo", postParameters, function(responseJSON) {
		var done = JSON.parse(responseJSON).done;
	});
}

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

$(document).on("pagecreate", "#home-page", function(){
	console.log('in home page');
	$("#buy-button").click(function(){
		console.log('buybutton clicked');
		buyorsell = 1;
		$.mobile.changePage($("#login-page"));
	});
	$("#sell-button").click(function(){
		buyorsell = 0;
		$.mobile.changePage($("#login-page"));
	});
});

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
				subscribe = JSON.parse(responseJSON).user.subscribe;

				// subscribe = JSON.parse(responseJSON).subscribe;
				sessionStorage.removeItem("user_email");
				sessionStorage.user_email = enteredEmail;

				sessionStorage.removeItem("contact");
				sessionStorage.contact = contact;
				console.log("newest contact :" + contact);

				sessionStorage.removeItem("subscribe");
				sessionStorage.subscribe = subscribe;

				sessionStorage.removeItem("user_name");
				sessionStorage.user_name = username;

				console.log(enteredEmail + " " + username + " " + subscribe + " " +contact);

				email = enteredEmail;
				console.log('in userlogin: ' + email);
				password = pwd;

				//var buy = true;
				//var postParameters = {email: email};
				//send a request to server to change to Hong's buy page.
				// $.get("/buy", postParameters, function(responseJSON){
				// 	//var done = JSON.parse(responseJSON).done;
				// });

				//$.redirect("/buy", {email: email});
				//buysell[email] = "login";
				//$(location).attr('href', '/home#page_buy_1');
				console.log('buyorsell: ' + buyorsell);
				if (buyorsell == 1) {
					buysell[email] = 1;
					$.mobile.changePage($("#page_buy_1"));
				} else {
					buysell[email] = 0;
					$.mobile.changePage($("#page_sell_1"));
				}
				//$.mobile.changePage($("#page_buy_1"));
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
		$("#forgetpwd-processing").css('display', 'block');
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
	$("#profile-name").text(sessionStorage.user_name);
	console.log('init sessionStorage contact' + sessionStorage.contact);
	if (sessionStorage.contact == '0000000000') {
		console.log('in profile contact');
		$("#profile-contact").html('tap to add contact');
		$("#profile-contact").css('color', 'grey');
	} else {
		console.log('in contact true');
		$("#profile-contact").css('color', 'black');
		$("#profile-contact").html(sessionStorage.contact);
	}
	// $("#profile-contact").css('color', 'black');
	// $("#profile-contact").text(sessionStorage.contact);
	$("#profile-email").text(sessionStorage.user_email);


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
		username = newName;

		resetProfile(email, contact, username, subscribe);

	});

	$("#profile-contact").on("tap", function(e){
		$("#profile-contact").css('color', 'black');
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
		if (newContact === ""){
			//alert(prevEmail);
			//$("#profile-contact").css("color", "#c2c2c2");
			//$("#profile-contact").html(contact);
			$("#profile-contact").html('tap to add contact');
			$("#profile-contact").css('color', 'grey');
		}
		$("#profile-contact").css('border-bottom', '1px solid #989797');

		contact = newContact;
		resetProfile(email, contact, username, subscribe);

	});

	$("#profile-subscribe").change(function(){
		console.log(this.checked);
		subscribe = this.checked;
		resetProfile(email, contact, username, subscribe);
		//var postParameters = {subscribe: subscribe};
		// $.post("/userforgetpwd",postParameters,function(responseJSON){

		// })
	});
});





localStorage.removeItem("request");
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
  	 open: function(event, ui) {
        $('.ui-autocomplete').off('menufocus hover mouseover mouseenter');
     },
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
    request.eatery = "Andrews Commons";  
    localStorage.request = JSON.stringify(request);
    //console.log(request);
  });

  $(".blueroom").click(function(){
    request = JSON.parse(localStorage.request);
    request.eatery = "Blueroom";  
    localStorage.request = JSON.stringify(request);
    //console.log(request);
  });

  $(".josiahs").click(function(){
    request = JSON.parse(localStorage.request);
    request.eatery = "Josiah's";  
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

  console.log(request);
  $("#price_span_2").text("$"+request.price);
  localStorage.request = JSON.stringify(request);


  var contact = sessionStorage.contact;
  console.log(contact);

  if(contact.length==10){
  	console.log("contact true");
  	$('#tel-1').val(contact);
  };

  var tap_counter = 0;

  $('#payment_suggestion').tap(function(){
    if (tap_counter%2==0){
      $('#payment_suggestion_detail').css("display","block");
    } else{
      $('#payment_suggestion_detail').css("display","none");
    }
    tap_counter+=1;
  })

  $("#final_confirm").tap(function(){
    
    var phone = $('#tel-1').val().trim();
    console.log(phone);
    console.log("email:" +sessionStorage.user_email);

    
    if(phone.match(/\d/g).length===10 && phone.length==10){

      sessionStorage.contact = phone;
      $("#profile-contact").html(sessionStorage.contact);


      $('#wrong_number').css("display","none");

      console.log("phone number"+phone);

      var postParameters = {email: sessionStorage.user_email, contact: phone, username: sessionStorage.user_name, subscribe: sessionStorage.subscribe};

      $.post("/changeinfo",postParameters,function(data){
      	console.log(data);
      })

      request = JSON.parse(localStorage.request);
      request.phone=phone;
      request.duration = $('#duration').val();
      request.bound = $('#bound').val();
      console.log(request);
      localStorage.request = JSON.stringify(request)

      $.post("/placeorder",{user:sessionStorage.user_email,address:request.address,eatery:request.eatery,menu:request.menu,duration:request.duration,price:request.price,priceBound:request.bound,},function(response){
        data = JSON.parse(response);
        console.log(response);

        var address = window.location.href;
         console.log(address);


         for(var i = address.length; i>0; i--){
	     		if (address.charAt(i) == "#"){
	     			address = address.substring(0,i);
	     			break;
	     		}
	     }

         console.log(address);


        if (data.result == "nothing"){

        	$(location).attr('href', address+"#page_confirm_false");

	    } else if(data.result == "match"){
	         	//var li = document.createElement("li");
         	//console.log('match successful');
         	//console.log(data.buyerName);
         	//console.log(data.buyerContact);
         	var li = '<li><a href="#">' + 
         			'<h3>Buy Record:</h3><p style="font-size: 15px; text-align: left">Seller: ' + 
         			data.sellerName + 
         			'<br> Seller Contact:' + data.sellerContact + '</p>' + 
         			'</a><a href="#delete-popup" data-rel="popup" data-icon="delete"></a></li>';
         	$("#orders-ul").append(li);

         	$(location).attr('href', address+"#page_confirm_true");
         } else{

         }
      })
    } else{
      $('#wrong_number').css("display","block");
    }
  })

});



	
//
//SELL PART
//
//

offer = JSON.parse(localStorage.offer||'{}');
localStorage.offer = JSON.stringify(offer);

$( document ).on( "pageinit", "#page_sell_1", function(event) {
  $("#progress_bar_sell").progressbar({
     value: 35
  });

  $(".meet_up_confirm").tap(function(){
    offer = JSON.parse(localStorage.offer);
    offer.method="meet";
    offer.north_deliver = false;
    offer.south_deliver = false;
    offer.center_deliver = false;

    localStorage.offer = JSON.stringify(offer);

    console.log(offer)
  })

  $(".deliver_confirm").tap(function(){
    offer = JSON.parse(localStorage.offer);
    offer.method="meet_deliver";
    localStorage.offer = JSON.stringify(offer);

    console.log(offer)
  })

});

$( document ).on( "pageshow", "#page_sell_deliver", function(event) {
  
  offer = JSON.parse(localStorage.offer);

  offer.south_deliver = false;
  offer.north_deliver = false;
  offer.center_deliver=false;

  localStorage.offer = JSON.stringify(offer);


  $('#address_confirm_sell').css('display','none');

  $("#progress_bar_deliver_sell").progressbar({
     value: 60
  });

  var width = $(window).width()-16*2;

  var north_height = 360*width/564.0 + "px";
  var center_height = 335*width/564.0 +"px";
  var south_height = 299*width/564.0 +"px";

  $('#north_shadow').css("height",north_height);
  $('#center_shadow').css("height",center_height);
  $('#south_shadow').css("height",south_height);



  var north_counter = 0;
  var south_counter = 0;
  var center_counter =0;

  $('#north_shadow').click(function(){
  	if(north_counter%2==0){
  		$('#north_shadow').css("background","linear-gradient(rgba(240, 240, 240, 0), rgba(240, 240, 240, 0)),url('../image/north_new.jpeg')");
  		$('#north_shadow').css("background-size","100%");

  		$('#north_click').attr('src','../image/confirm.png');
  		//$('#north_check').css('display','block');
  	} else{
  		$('#north_shadow').css("background","linear-gradient(rgba(240, 240, 240, 0.5), rgba(240, 240, 240, 0.5)),url('../image/north_new.jpeg')");
		$('#north_shadow').css("background-size","100%");

		$('#north_click').attr('src','../image/click.png');

		//$('#north_check').css('display','none');
  		//$('#north_des').css('display','block');
  	}

  	north_counter+=1;

  	if(north_counter%2==0 && south_counter%2 == 0 && center_counter%2 == 0){
  		  $('#address_confirm_sell').css('display','none');
  	} else{
  		  $('#address_confirm_sell').css('display','block');
  	}
  });

  $('#center_shadow').click(function(){
  	if(center_counter%2==0){
  		$('#center_shadow').css("background","linear-gradient(rgba(240, 240, 240, 0), rgba(240, 240, 240, 0)),url('../image/center_new.jpeg')");
  		$('#center_shadow').css("background-size","100%");

  		$('#center_click').attr('src','../image/confirm.png');

  		//$('#center_des').css('display','none');
  		//$('#center_check').css('display','block');
  	} else{
  		$('#center_shadow').css("background","linear-gradient(rgba(240, 240, 240, 0.5), rgba(240, 240, 240, 0.5)),url('../image/center_new.jpeg')");
	  	$('#center_shadow').css("background-size","100%");

		$('#center_click').attr('src','../image/click.png');
		//$('#center_check').css('display','none');
  		//$('#center_des').css('display','block');
  	}
  	center_counter+=1;

  	if(north_counter%2==0 && south_counter%2 == 0 && center_counter%2 == 0){
  		  $('#address_confirm_sell').css('display','none');
  	} else{
  		  $('#address_confirm_sell').css('display','block');
  	}
  });

  $('#south_shadow').click(function(){
  	if(south_counter%2==0){
  		$('#south_shadow').css("background","linear-gradient(rgba(240, 240, 240, 0), rgba(240, 240, 240, 0)),url('../image/south_new.jpeg')");
    	$('#south_shadow').css("background-size","100%");

      	$('#south_click').attr('src','../image/confirm.png');

  		//$('#south_des').css('display','none');
  		//$('#south_check').css('display','block');
  	} else{
  		$('#south_shadow').css("background","linear-gradient(rgba(240, 240, 240, 0.5), rgba(240, 240, 240, 0.5)),url('../image/south_new.jpeg')");
		$('#south_shadow').css("background-size","100%");

		$('#south_click').attr('src','../image/click.png');
		//$('#south_check').css('display','none');
  		//$('#south_des').css('display','block');
  	}
  	south_counter+=1;

  	if(north_counter%2==0 && south_counter%2 == 0 && center_counter%2 == 0){
  		  $('#address_confirm_sell').css('display','none');
  	} else{
  		  $('#address_confirm_sell').css('display','block');
  	}
  });


  $('#address_confirm_sell').click(function(){
    offer = JSON.parse(localStorage.offer);
    if (north_counter % 2==1){
    	offer.north_deliver = true;
    } else{
    	offer.north_deliver = false;
    };

    if (south_counter % 2==1){
    	offer.south_deliver = true;
    } else{
    	offer.south_deliver = false;
    };

    if (center_counter % 2==1){
    	offer.center_deliver = true;
    } else{
    	offer.center_deliver = false;
    };

    localStorage.offer = JSON.stringify(offer);

    console.log(offer);
  });

});


$( document ).on( "pageshow", "#page_sell_2", function(event) {

  $("#progress_bar_2_sell").progressbar({
    value: 70
  });

  console.log("arrived")

  var eateries = {};
  var checked_count = 0;

  $(':checkbox').change(function() {
  	console.log("get");
    var $this = $(this);
    var id = $this.attr('name');

    if ($this.is(':checked')) {

    	checked_count +=1;
        $('#menu_confirm_sell').css("display","block");

        eateries[id]=true;

    } else{

    	checked_count-=1;
        
        if(checked_count==0){
          $('#menu_confirm_sell').css("display","none");
        }
    }

   });

  $('#menu_confirm_sell').tap(function(){
    offer = JSON.parse(localStorage.offer);
    var eatery_string = Object.keys(eateries).join(',');
    offer.eatery=eatery_string;
    localStorage.offer= JSON.stringify(offer);
    console.log(offer);
  })

});

$( document ).on( "pageshow", "#page_sell_3", function(event) {
	$("#progress_bar_3_sell").progressbar({
	    value: 90
	 });

	$('#wrong_number').css("display","none");

	var contact = sessionStorage.contact;
	console.log(contact);


  	if(contact!=null){
  		$('#tel-1_sell').val(contact);
  	};


	var tap_counter = 0;

	$('#payment_suggestion_sell').tap(function(){
	  if (tap_counter%2==0){
	    $('#payment_suggestion_detail_sell').css("display","block");
	  } else{
	    $('#payment_suggestion_detail_sell').css("display","none");
	  }
	  tap_counter+=1;
	});

	$("#final_confirm_sell").click(function(){
    
	    var phone = $('#tel-1_sell').val().trim();
	    console.log(phone);
	    sessionStorage.contact = phone;
	    
	    if(phone.match(/\d/g).length===10 && phone.length==10){

	       console.log("sessionS conta: " + sessionStorage.contact);
	       $("#profile-contact").html(sessionStorage.contact);

	       var postParameters = {email: sessionStorage.user_email, contact: sessionStorage.contact, username: sessionStorage.user_name, subscribe: sessionStorage.subscribe};

	       $.post("/changeinfo",postParameters,function(data){
	      	 console.log(data);
	       })

	      offer = JSON.parse(localStorage.offer);
	      $('#wrong_number_sell').css("display","none");
	      offer.phone=phone;
	      offer.creditNum = $("input[name=creditNum]:checked").val();
	      console.log(offer.creditNum)
	      offer.duration = $('#duration_sell').val();
	      offer.bound = $('#bound_sell').val();
	      console.log(offer);
	      localStorage.offer = JSON.stringify(offer)

	      //$(location).attr('href', 'http://stackoverflow.com')
	      
	     $.post("/placeoffer",{user:sessionStorage.user_email,address:offer.address,eatery:offer.eatery,menu:offer.menu,duration:offer.duration,price:offer.price,bound:offer.bound,creditNum:offer.creditNum},function(response){
	         
	         data = JSON.parse(response);

	         var address = window.location.href;
	         console.log(address);

	         for(var i = address.length; i>0; i--){
	         		if (address.charAt(i) == "#"){
	         			address = address.substring(0,i);
	         			break;
	         		}
	         }

	         console.log(address);


	         if (data.result == "nothing"){

	         	$(location).attr('href', address+"#page_confirm_false");

	         } else if(data.result == "match"){
	         	//var li = document.createElement("li");
	         	console.log('match successful');
	         	console.log(data.buyerName);
	         	console.log(data.buyerContact);
	         	var li = '<li><a href="#">' + 
	         			'<h3>Sell Record:</h3><p style="font-size: 15px; text-align: left">Buyer: ' + 
	         			data.buyerName + 
	         			'<br> Buyer Contact:' + data.buyerContact + '</p>' + 
	         			'</a><a href="#delete-popup" data-rel="popup" data-icon="delete"></a></li>';
	         	$("#orders-ul").append(li);

	         	$(location).attr('href', new_address+"#page_confirm_true");
	         } else{

	         }

	         console.log(response);
	      })
    } else{
      		$('#wrong_number_sell').css("display","block");
    };
  })

})

