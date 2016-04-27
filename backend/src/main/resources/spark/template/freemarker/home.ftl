<#assign content>

<div data-role="page" id="home-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content">
    <a href="#" data-role="button" id="buy-button"class="ui-btn">Buy Credit</a>
    <a href="#" data-role="button" id="sell-button" class="ui-btn">Sell Credit</a>
  </div>
</div>


<div data-role="page" id="login-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content centered">
    <form id="login-form">
      <div class="ui-field-contain">
        <label for="login-user">Email:</label>
        <input type="text" name="login-user" id="login-user"class="half-input" value="@brown.edu">
<!--    <span id="brownedu" style="position: relative">@brown.edu</span> -->
        <label style="margin-top: 10px"for="login-pwd">Password:</label>
        <input type="password" name="login-pwd" id="login-pwd">
        <div style="margin-top: 10px" id="login-error">
        </div>
        <a href="#" id="login-submit" value="Log In" data-role="none" class="ui-btn" style="margin-top: 20px">Log In</a>
      </div>
<!--  <input type="submit" data-role="none" value="Log In" class="ui-btn" style="margin-top: 20px"> -->
    </form>
    <p style="color: grey">----------OR----------</p>
    <a href="#signup-page" data-role="none" class="ui-btn" style="margin-top: 0px" id="signup-btn">Sign Up</a>
    <a href="#forgetpwd-page" data-role="none" class="ui-btn" style="margin-top: 14px">Forget Password?</a>
  </div>
</div>


<div data-role="page" id="signup-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content centered">
    <form id="signup-form">
      <div class="ui-field-contain">
        <label for="signup-name">Username:</label>
          <input type="text" name="signup-name" id="signup-name">
        <label style="margin-top: 10px"for="signup-user">Email:</label>
          <input type="text" name="signup-user" id="signup-user" value="@brown.edu">
        <label style="margin-top: 10px"for="signup-pwd">Password:</label>
          <input type="password" name="signup-pwd" id="signup-pwd">
        <fieldset data-role="controlgroup" style="margin-top: 10px">
          <label for="subscribe">Subscribe to our mailing list?</label>
          <input type="checkbox" name="mailing-list" id="subscribe" value="Subscribe?">
        </fieldset>
        <div style="margin-top: 10px" id="signup-error">
        </div>
<!--    <p>Please check your email to confirm your login.</p> -->
        <a href="#" data-role="none" id="signup-submit" value="Sign Up" class="ui-btn" style="margin-top: 20px">Sign Up</a>
        <a href="#login-page" data-role="none" id="signup-submit" value="Login In" class="ui-btn" style="margin-top: 20px">Back To Login In</a>
      </div>
    </form>
  </div>
</div>

<div data-role="page" id="signupwait-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content centered">
    <h2>Signup In Progress</h2>
    <p>We have sent a confirmation email to your brown email address.<br>Please check your email and click the link we provide. If you still not receive it after 60 seconds, please click the following button. </p>
  </div>
</div>

<div data-role="page" id="resetpwdwait-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content centered">
    <h2>Please Check Your Email</h2>
    <p>We have sent a reset password link to your brown email address.<br>Please check your email and click the link we provide. If you still not receive it after 60 seconds, please click the following button. </p>
  </div>
</div>


<div data-role="page" id="forgetpwd-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content centered">
    <form id="forgetpwd-form">
      <div class="ui-field-contain">
        <label for="forgetpwd-user">Email:</label>
        <input type="text" name="forgetpwd-user" id="forgetpwd-user" value="@brown.edu">
        <a href="#" id="forgetpwd-submit" ata-role="none" value="Email me a link to reset password" class="ui-btn" style="margin-top: 20px">Email me a link to reset password</a>
        <a href="#login-page" data-role="none" value="Login In" class="ui-btn" style="margin-top: 20px">Back To Login In</a>
      </div>
    </form>
    <p id="forgetpwd-processing" style="display: none">Processing...</p>
  </div>
</div>


<div data-role="page" id="profile-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content centered">
    <h2>Profile<h2>
    <h3 class="pub-prvt">Public Information</h3>
    <div id="profile-name" class="profile-info">username</div>
    <div id="profile-contact" class="profile-info">000-000-0000</div>
    <ol data-role="listview" style="padding-top: 30px">
      <li><h4>Mickey</h4><p style="font-size: 15px">&#9733; &#9733; &#9733; &#9733; &#x2606;<br>&nbsp; &nbsp; &nbsp; &nbsp;*Smile face*</p></li>
      <li><h4>Minnie</h4><p style="font-size: 15px">&#9733; &#9733; &#9733; &#9733; &#x2606;<br>&nbsp; &nbsp; &nbsp; &nbsp;Thanks for the food and the credit :)</p></li>
    </ol>
    <h3 class="pub-prvt" style="margin-top: 30px">Private Information</h3>
    <div id="profile-email">yuchen_huang@brown.edu</div>
    <fieldset data-role="controlgroup" style="margin-top: 10px">
      <label for="profile-subscribe">Subscribe to our mailing list</label>
      <input type="checkbox" name="mailing-list" id="profile-subscribe">
    </fieldset>
    <ul data-role="listview" style="padding-top: 30px">
      <li data-role="none" style="font-size: 15px"><a href="#changepwd-page">Change Password</a></li>
    </ul>
  </div>

  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" position="fixed" data-id="persistent">
  <div data-role="navbar">
    <ul id="bottom_ul">
      <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
      <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

      <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
      <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

    </ul>
  </div>
  </div>
</div>


<div data-role="page" id="changepwd-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

  <div data-role="main" class="ui-content centered">
    <form id="changepwd-form">
      <div class="ui-field-contain">
        <label for="prev-pwd">Current Password:</label>
        <input type="password" name="prev-pwd" id="prev-pwd">
        <label style="margin-top: 10px" for="new-pwd">New Password:</label>
        <input type="password" name="new-pwd" id="new-pwd">
        <label style="margin-top: 10px" for="re-new-pwd">Re-type New Password:</label>
        <input type="password" name="re-new-pwd" id="re-new-pwd">
        <a href="#" id="changepwd-submit" data-role="none" value="Change Password" class="ui-btn" style="margin-top: 20px">Change Password</a>
      </div>
    </form>
    <div id="changepwd-error">
    </div>
  </div>

  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" position="fixed" data-id="persistent">
  <div data-role="navbar">
    <ul id="bottom_ul">
      <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
      <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

      <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
      <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>
    </ul>
  </div>
  </div>
</div>


<div data-role="page" id="orders-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>
  
  <div data-role="main" class="ui-content centered">
    <h2>Orders</h2>
    <h3>Current</h3>
    <ul style="text-align: left; margin-top: 10px" data-role="listview" style="padding-top: 30px" id="orders-ul">
      <li id="order-id">
        <a href="#"><h3>Offer:</h3><p style="font-size: 15px; text-align: left">Dining Hall: Andrews Common<br> Time: 2016-4-17-9-29pm</p></a><a href="#delete-popup" data-rel="popup" data-icon="delete"></a>
      </li>
    </ul>
    <div data-role="popup" id="delete-popup">
      <div data-role="main" class="ui-content">
        <h3>Are you sure you want to remove this request/offer? This action cannot be undone.</h3>
        <a href="#" id="order-id-a" class="ui-btn ui-corner-all ui-btn-inline" data-rel="back">Remove</a>
        <a href="#" class="ui-btn ui-corner-all ui-btn-inline" data-rel="back">Go Back</a>
      </div>
    </div>
    <h3 style="padding-top: 25px">History</h3>
    <ul data-role="listview" style="padding-top: 20px">
      <li id="hist-id">
        <h3>Offered 1 credit to</h3><p style="font-size: 15px">Mickey Mouse<br>Dining Hall: Blueroom<br>Time: 2016-4-17-9-29pm</p>
      </li>
    </ul>
  </div>
  
  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" position="fixed" data-id="persistent">
  	<div data-role="navbar">
    	<ul id="bottom_ul">
      		<li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
      		<li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

      		<li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
      		<li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>
    	</ul>
  	</div>
  </div>
</div>

<div data-role="page" id="waittobematched-page">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>
  
  <div data-role="main" class="ui-content centered">
    <h2>Processing...</h2>
    <p>We're Looking for a match.</p>
  </div>
  
  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" position="fixed" data-id="persistent">
    <div data-role="navbar">
      <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>
      </ul>
    </div>
  </div>
</div>
    
    
    
    
    
    
    

    
    
    
<div data-role="page" id="page_buy_1">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
    <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
  </div>

  <div data-role="main" class="ui-content">
    <div class="ui-grid-a">
      <div class="ui-block-a" style="width:100%">
         <div id="progress_bar"></div>
      </div>
    </div>
    <ul data-role="listview" data-inset="true">
      <li data-role="divider">How you wanna get the food?</li>
      <li>
        <a class="meet_up_confirm" href="#page_buy_2">
        <img src="../image/meet.png" style="height:100%">
        <h2>Meet up</h2>
        <p style="white-space:normal;">Meet with a credit provider.</p>
        </a>
        <a class="meet_up_confirm" href="#page_buy_2" data-transition="fade" data-icon="arrow-r"></a>
      </li>         
      <li>
        <a class="deliver_confirm" href="#page_deliver">
        <img src="../image/deliver.png">
        <h2>Delivery</h2>
        <p style="white-space:normal;">Feel lazy? Choose delivery!</p>
        </a>
        <a class="deliver_confirm" href="#page_deliver" data-transition="fade" data-icon="arrow-r"></a>
      </li>
    </ul>
  </div>

  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
    <div data-role="navbar">
      <ul id="bottom_ul">
        <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
        <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

        <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
        <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>
      </ul>
    </div>
  </div>
</div>


<div data-role="page" id="page_deliver">
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
    <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
  </div>

  <div data-role="main" class="ui-content">
    <div class="ui-grid-a">
      <div class="ui-block-a" style="width:20%">
        <a href="#" data-rel="back" class="back"><img src="../image/back.png"></a>
      </div>
      <div class="ui-block-b" style="width:80%">
         <div id="progress_bar_deliver"></div>
      </div>
    </div>
    <ul data-role="listview" data-inset="true">
      <li data-role="divider">Input a campus building as address</li>
      <li style="background-color: #f9f9f9;white-space:normal;">You must pick a result from autocorrect suggestion as your delivery address.</li>
      <li><input id="autocorrect" placeholder="e.g. New Pembroke"></li>
    </ul>
    <a href="#page_buy_2" class="ui-btn" id="address_confirm" style="background-color:#FFCAD4;color:#9D8189;display: none">Continue</a>
  </div>

  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
    <div data-role="navbar">
      <ul id="bottom_ul">
        <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
        <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

        <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
        <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>
      </ul>
    </div>
  </div>
</div>



  
<div data-role="page" id="page_buy_2">

<div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
  <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
</div>

  <div data-role="main" class="ui-content">

    <div class="ui-grid-a">
      <div class="ui-block-a" style="width:20%">
        <a href="#" data-rel="back" class="back"><img src="../image/back.png"></a>
      </div>
      <div class="ui-block-b" style="width:80%">
         <div id="progress_bar_2"></div>

      </div>
    </div>     

    <ul data-role="listview" data-inset="true">
      <li data-role="divider">Pick your eatery</li>
      <li>
        <a href="#page_buy_3" class="andrews">
        <img src="../image/andrews.png">
        <h2>Andrews Commons</h2>
        <p style="white-space:normal;">They say some eat Andrews 24/7.</p>
        </a>
        <a href="#page_buy_3" data-transition="fade" data-icon="arrow-r" class="andrews"></a>
      </li>
      
      <li>
        <a href="#page_buy_3" class="blueroom">
        <img src="../image/blueroom.png">
        <h2>Blue Room</h2>
        <p style="white-space:normal;">Best known to campus visitors :)</p>
        </a>
        <a href="#page_buy_3" data-transition="fade" data-icon="arrow-r" class="blueroom"></a>
      </li>
      
      <li>
        <a href="#page_buy_3" class="josiahs">
        <img src="../image/johs.png">
        <h2>Josiah's</h2>
        <p style="white-space:normal;">We talk business there after party.</p>
        </a>
        <a href="#page_buy_3" data-transition="fade" data-icon="arrow-r" class="josiahs"></a>
      </li>
    </ul>
  </div>

  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
    <div data-role="navbar">
      <ul id="bottom_ul">
        <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
        <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

        <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
        <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

      </ul>
    </div>
  </div>

</div>



<div data-role="page" id="page_buy_3">
  
  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
    <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
  </div>

  <div data-role="main" class="ui-content">

      <div class="ui-grid-a">
        <div class="ui-block-a" style="width:20%">
          <a href="#" data-rel="back" class="back"><img src="../image/back.png"></a>
        </div>
        <div class="ui-block-b" style="width:80%">
           <div id="progress_bar_3"></div>

        </div>
      </div>

      <p>Total: <span style="color:#9D8189" id="price_span">$0.00</span></p>
      <p>What do you wanna eat?</p>
      
      <form id="menu">
        <fieldset data-role="controlgroup" data-iconpos="right" id="menu_fieldset">
          <div class="ui-controlgroup-controls" id="menu_fieldset_div">
            
          </div>
        </fieldset>
    </form>

    <a href="#page_buy_4" class="ui-btn" id="menu_confirm" style="background-color:#FFCAD4;color:#9D8189;display: none;">Continue</a>

  </div>



  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
    <div data-role="navbar">
      <ul id="bottom_ul">
        <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
        <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

        <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
        <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

      </ul>
    </div>
  </div>    
</div>

<div id="page_buy_4" data-role="page">

  <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
    <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
  </div>

  <div data-role="main" class="ui-content">

      <div class="ui-grid-a">
        <div class="ui-block-a" style="width:20%">
          <a href="#" data-rel="back" class="back"><img src="../image/back.png"></a>
        </div>
        <div class="ui-block-b" style="width:80%">
           <div id="progress_bar_4"></div>

        </div>
      </div>
      <p style="font-size: 1.2em">Current order price: <span id="price_span_2" style="color: #9d8189">$15.00</span></p>

      <p style="display: none" id="deliver_note">We have added <span style="color: #9d8189">$2</span> delivery tip.</p>
      
      <div class="full_width">

        <label style="margin-bottom: 20px;margin-top: 20px;" for="bound">How much would you like to pay for this order?</label>
        <p style="color:gray" id="payment_suggestion">tap to view suggestion</p>
        <p style="display: none" id="payment_suggestion_detail">Sellers usally offer 10% discount of your entire purchase. Please choose a reasonable price and we will help you match with sellers.</p>
       <input type="range" name="bound" id="bound" value="10" min="0" max="24" step="0.1">
      
      </div>
      
      <div style="margin-bottom: 30px">

        <label for="tel-1" style="margin-top: 20px;margin-bottom:20px;font-size: 1.2em;">Your phone number <span style="color:#9d8189">&#x2193</span></label>
        <input style="height: 40px;" type="tel" data-clear-btn="true" name="tel-1" id="tel-1" value="" placeholder="seller need to contact you" >

        <p style="display: none; color:red" id="wrong_number">Phone number incorrect. Please input 10-digit number.</p>

      </div>
      

      <div class="full_width">
        <label style="margin-bottom: 20px;margin-top: 20px;" for="duration">How long can you wait for this order to be matched? (minutes)</label>
       <input type="range" name="duration" id="duration" value="30" min="0" max="120" step="10">
      </div>

      <a class="ui-btn" id="final_confirm" style="margin-top: 40px;margin-bottom:40px;background-color: #ffe5d9">Yeah, let's do it!</a>

  </div>

  <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
    <div data-role="navbar">
      <ul id="bottom_ul">
        <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
        <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>

        <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
        <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

      </ul>
    </div>
  </div> 
</div>







   <div data-role="page" id="page_sell_1">

    <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

      <div data-role="main" class="ui-content">

        <div class="ui-grid-a">
          <div class="ui-block-a" style="width:100%">
             <div id="progress_bar_sell"></div>
          </div>
        </div>

        <ul data-role="listview" data-inset="true">
          <li data-role="divider" style="white-space:normal;">How would you like to trade your credit?</li>
          <li>
            <a class="meet_up_confirm" href="#page_sell_2">
            <img src="../image/sell_meet.png" style="height:100%; margin-left: 10px;margin-bottom: 10px;">
            <h2>Meet up</h2>
            <p style="white-space:normal;">Meet with a Brunonian and swipe a credit for him.</p>
            </a>
            <a class="meet_up_confirm" href="#page_sell_2" data-transition="fade" data-icon="arrow-r"></a>
          </li>
          
          <li>
            <a class="deliver_confirm" href="#page_sell_deliver">
            <img src="../image/sell_deliver.png" style="height:100%; margin-left: 10px;margin-top: 10px;">
            <h2>Meet + Deliver</h2>
            <p style="white-space:normal; ">Besides meet-up, I can also deliver food and receive $2 for my endeavor.</p>
            </a>
            <a class="deliver_confirm" href="#page_sell_deliver" data-transition="fade" data-icon="arrow-r"></a>
          </li>

        </ul>
      </div>



    <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
      <div data-role="navbar">
        <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div>
  </div>

  <div data-role="page" id="page_sell_deliver">
    <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

      <div data-role="main" class="ui-content">

        <div class="ui-grid-a">
          <div class="ui-block-a" style="width:20%">
            <a href="#" data-rel="back" class="back"><img src="../image/back.png"></a>
          </div>
          <div class="ui-block-b" style="width:80%">
             <div id="progress_bar_deliver_sell"></div>
          </div>
        </div>

        <ul id="campus_section_list" data-role="listview" data-inset="true">
           <li data-role="divider" style="white-space:normal;background-color: #9d8189;color: white;text-shadow: none;">Choose part(s) of campus map you can deliver food to.</li>
        </ul>
        
        <ul id="campus_section_list" data-role="listview" data-inset="true" style="margin-top: 30px">
          <li data-role="divider" style="white-space:normal;font-size: 0.9em">North Campus (Pembroke, Bio-Med, Granoff) <span style="color: #9d8189;font-size: 1em">&#8595</span></li>
          <li style="padding:0">
            <img class="click" id="north_click" src="../image/click.png">
            <div id="north_shadow" class="map_shadow"></div>

          </li>
        </ul>

        <ul id="campus_section_list" data-role="listview" data-inset="true">

          <li data-role="divider" style="white-space:normal;font-size: 0.9em">Central Campus (Main Green, Sci-Li, B&H) <span style="color: #9d8189;font-size: 1em">&#8595</span></li>
          <li style="padding:0">
            <img class="click" id="center_click" src="../image/click.png">
            <div id="center_shadow" class="map_shadow"></div>
          </li>
        </ul>

        <ul id="campus_section_list" data-role="listview" data-inset="true">

          <li data-role="divider" style="white-space:normal;font-size: 0.9em">South Campus (Wriston Quad, New Dorm, Perkins) <span style="color: #9d8189;font-size: 1em">&#8595</span></li>
          <li style="padding:0">
            <img class="click" id="south_click" src="../image/click.png">
            <div id="south_shadow" class="map_shadow"></div>
          </li>

        </ul>

        <a href="#page_sell_2" class="ui-btn" id="address_confirm_sell" style="background-color:#FFCAD4;color:#9D8189;display: block;margin-top: 20px;">Continue</a>

      </div>



    <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
      <div data-role="navbar">
        <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div>
  </div>

  <div data-role="page" id="page_sell_2">

    <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

      <div data-role="main" class="ui-content">

        <div class="ui-grid-a">
          <div class="ui-block-a" style="width:20%">
            <a href="#" data-rel="back" class="back"><img src="../image/back.png"></a>
          </div>
          <div class="ui-block-b" style="width:80%">
             <div id="progress_bar_2_sell"></div>

          </div>
        </div>
          
         

        <ul data-role="listview" data-inset="true" id="menu_sell">
          <li data-role="divider">Pick eatery(s) where you can go.</li>

          <li style="background-color: #ffe5d9">
                <img src="../image/andrews.png" style="width: 80px;">
                <label><input type="checkbox" value="unchecked" name="Andrews Commons" id=""/> Andrews Commons </label>
          </li>

          <li style="background-color: #E8E3E1">
                <img src="../image/blueroom.png" style="width: 80px;">
                <label><input type="checkbox" value="unchecked" name="Blueroom" id=""/> Blueroom </label>
          </li>

          <li style="background-color: #ffe5d9">
                <img src="../image/johs.png" style="width: 80px;">
                <label><input type="checkbox" value="unchecked" name="Josiah's" id=""/> Josiah's </label>
          </li>

          <li style="background-color: #E8E3E1">
                <img src="../image/ivyroom.png" style="width: 60px;margin-top: 10px; margin-left: 10px">
                <label><input type="checkbox" value="unchecked" name="Ivy Room" id=""/> Ivy Room </label>
          </li>

          <li style="background-color: #ffe5d9">
                <img src="../image/ratty.png" style="width: 80px;">
                <label><input type="checkbox" value="unchecked" name="Ratty" id=""/> Ratty </label>
          </li>

          <li style="background-color: #E8E3E1">
                <img src="../image/vdub.png" style="width: 60px;margin-top: 10px; margin-left: 10px">
                <label><input type="checkbox" value="unchecked" name="V-Dub" id=""/> V-Dub </label>
          </li>

        </ul>

        <a href="#page_sell_3" class="ui-btn" id="menu_confirm_sell" style="background-color:#FFCAD4;color:#9D8189;display: none;margin-top: 30px">Continue</a>
      </div>



    <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
      <div data-role="navbar">
        <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div>


  </div>

  <div data-role="page" id="page_sell_3">
    
    <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

      <div data-role="main" class="ui-content">

        <div class="ui-grid-a">
          <div class="ui-block-a" style="width:20%">
            <a href="#" data-rel="back" class="back"><img src="../image/back.png"></a>
          </div>
          <div class="ui-block-b" style="width:80%">
             <div id="progress_bar_3_sell"></div>

          </div>
        </div>

        <div class="full_width">

          <label style="margin-bottom: 20px;margin-top: 20px;" for="bound">How much would you like to sell a credit?</label>
          <p style="color:#9d8189;" id="payment_suggestion_sell">tap to view suggestion</p>
          <p style="display: none" id="payment_suggestion_detail_sell">Meal credits are usually traded at 10-15% off. Pick an ideal price to prompt your deal to finish.</p>
         <input type="range" name="bound_sell" id="bound_sell" value="6.0" min="0" max="7.3" step="0.1">
        
        </div>
        
        <div style="margin-bottom: 30px" id="credit_number">

          <label for="tel-1" style="margin-top: 20px;margin-bottom:20px;">How many credits are you willing to sell, at most?</label>
          <label>
              <input type="radio" data-mini="true" name="creditNum" id="credit_Num" value=1>One
          </label>

          <label for="radio-choice-0b">Two
              <input type="radio" data-mini="true" name="creditNum" id="credit_Num" class="custom" value=2 checked>
          </label>

        </div>

        <div style="margin-bottom: 30px" class="full_width">

          <label for="tel-1" style="margin-top: 20px;margin-bottom:20px;font-size: 1.2em;">Your phone number <span style="color:#9d8189">&#x2193</span></label>
          <input style="height: 40px;" type="tel" data-clear-btn="true" name="tel-1" id="tel-1_sell" value="" placeholder="buyer needs to contact you" >

          <p style="display: none; color:red" id="wrong_number_sell">Phone number incorrect. Please input 10-digit number.</p>

        </div>
        

        <div>
          <label style="margin-bottom: 20px;margin-top: 20px;" for="duration_sell">How long can you wait for your offer to be matched? (minutes)</label>
         <input type="range" name="duration_sell" id="duration_sell" value="30" min="0" max="120" step="10">
        </div>

        <a class="ui-btn" id="final_confirm_sell" style="margin-top: 40px;margin-bottom:40px;background-color: #ffe5d9">Yeah, let's do it!</a>
      </div>

    <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
      <div data-role="navbar">
        <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div>



  </div>

  <div data-role="page" id="page_confirm_true">
    
    <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

     <div data-role="main" class="ui-content">
     There is a match and please check your mail for detailed information.
     </div>


    <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
      <div data-role="navbar">
        <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div>


  </div>

  <div data-role="page" id="page_confirm_false">
    
    <div class="logo" data-role="header" data-id="persistent" data-position="fixed" style="background-color: #FFCAD4">
      <h1 style="font-size: 1em;">Credit Me @ Brown</h1>
    </div>

    <div data-role="main" class="ui-content">
    
    <div>
      <p>There is no match at this moment. Please patiently wait. We will mail you as long as there is someone who can match your requirement.</p>
    </div>
    
    <div style="text-align: center">
      <img src="../image/clock.png">
    </div>

    
    </ul>

    </div>





    <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" data-id="persistent">
      <div data-role="navbar">
        <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#page_sell_1" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div>

    
  </div>


</#assign>
<#include "main.ftl">

