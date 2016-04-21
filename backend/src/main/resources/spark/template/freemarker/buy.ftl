 <#assign content>
 
   	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <link href="../css/jquery-ui.min.css" rel="stylesheet">
    <script src="../js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="../css/app.css">
  	

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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

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
        <p style="font-size: 1.2em">Current order price: <span id="price_span" style="color: #9d8189">$15.00</span></p>

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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div> 
  </div>

</#assign>
<#include "main.ftl">
