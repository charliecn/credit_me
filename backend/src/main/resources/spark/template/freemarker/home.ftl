<#assign content>
<div data-role="page" id="home-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
      </div>
      <div data-role="main" class="ui-content">
        <a href="#login-page" data-role="button" class="ui-btn">Buy Credit</a>
        <a href="#login-page" data-role="button" id="sell-button" class="ui-btn">Sell Credit</a>
      </div>
    </div>

    <div data-role="page" id="login-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
      </div>
      <div data-role="main" class="ui-content centered">
        <form id="login-form">
          <div class="ui-field-contain">
            <label for="login-user">Email:</label>
            <input type="text" name="login-user" id="login-user"class="half-input" value="@brown.edu">
<!--             <span id="brownedu" style="position: relative">@brown.edu</span> -->
            <label style="margin-top: 10px"for="login-pwd">Password:</label>
            <input type="password" name="login-pwd" id="login-pwd">
            <div style="margin-top: 10px" id="login-error">
            </div>
            <a href="#" id="login-submit" value="Log In" data-role="none" class="ui-btn" style="margin-top: 20px">Log In</a>
          </div>
<!--           <input type="submit" data-role="none" value="Log In" class="ui-btn" style="margin-top: 20px"> -->
        </form>
        <a href="#signup-page" data-role="none" class="ui-btn" style="margin-top: 0px" id="signup-btn">Sign Up</a>
        <a href="#forgetpwd-page" data-role="none" class="ui-btn" style="margin-top: 14px">Forget Password?</a>
      </div>
    </div>

    <div data-role="page" id="signup-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
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
<!--             <p>Please check your email to confirm your login.</p> -->
            <a href="#" data-role="none" id="signup-submit" value="Sign Up" class="ui-btn" style="margin-top: 20px">Sign Up</a>
            <a href="#login-page" data-role="none" id="signup-submit" value="Login In" class="ui-btn" style="margin-top: 20px">Back To Login In</a>
          </div>
        </form>
      </div>
    </div>

    <div data-role="page" id="forgetpwd-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
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
      </div>
    </div>

    <div data-role="page" id="delivery-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
      </div>
      <div data-role="main" class="ui-content centered">
<!--         <form id="delivery-form"> -->
        <h2>Delivery or Not?</h2>
        <fieldset data-role="controlgroup">
          <legend>Would you like to have your food delivered to a place of your choice, or meet up at the dinning hall?</legend>
          <label for="to-deliver">Delivery to a place of my choice</label>
          <input type="radio" name="delivery" id="to-deliver" value="to-deliver">
          <label for="not-deliver">Meetup at the dinning hall</label>
          <input type="radio" name="delivery" id="not-deliver" value="not-deliver">
        </fieldset>
        <input type="submit" data-role="none" value="Next" class="ui-btn" style="margin-top: 20px">
      </div>
      <div data-role="footer" data-tap-toggle="false" data-id="foo1" data-position="fixed" position="fixed" data-id="persistent">
      <div data-role="navbar">
        <ul id="bottom_ul">
          <li><a href="#page_buy_1" style="padding:0"><img src="../image/buy.png" width="30%"><p>Buy</p></a></li>
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
      </div>
    </div>

    <div data-role="page" id="profile-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
      </div>
    </div>

    <div data-role="page" id="changepwd-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
      </div>
    </div>

    <div data-role="page" id="orders-page">
      <div data-role="header" class="row">
        <div class="small-6 small-centered columns logo">
          <h1>CreditMe@Brown</h1>
        </div>
      </div>
      <div data-role="main" class="ui-content centered">
        <h2>Orders</h2>
        <h3>Current</h3>
        <ul style="text-align: left; margin-top: 10px" data-role="listview" style="padding-top: 30px">
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
          <li><a href="#" style="padding:0"><img src="../image/sell.png" width="30%"><p>Sell</p></a></li>
          <li><a href="#orders-page" style="padding:0"><img src="../image/orders.png" width="30%"><p>Orders</p></a></li>
          <li><a href="#profile-page" style="padding:0"><img src="../image/me.png" width="30%"><p>Me</p></a></li>

        </ul>
      </div>
    </div>


</#assign>
<#include "main.ftl">