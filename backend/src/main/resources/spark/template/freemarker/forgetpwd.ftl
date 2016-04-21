<#assign content>

<div data-role="page" id="resetpwd-page">
  <div data-role="header" class="row">
    <div class="small-6 small-centered columns logo">
      <h1>CreditMe@Brown</h1>
    </div>
  </div>

  <div data-role="main" class="ui-content">
  	<form id="resetpwd-form">
      <div class="ui-field-contain">
        <label for="resetpwd-user">New Password:</label>
        <input type="text" name="resetpwd-pwd" id="resetpwd-pwd">
        <a href="#" id="resetpwd-submit" ata-role="none" class="ui-btn" style="margin-top: 20px">Reset Password</a>
      </div>
    </form>
    <div id="reset-scd">
    	<p>You have successfully reset your password. Now go back to log in!</p>
    	<a href="#login-page" data-role="button" class="ui-btn">Go To Log In</a>
    </div>
    <div id="reset-email">${email}</div>
  </div>
</div>

</#assign>
<#include "main.ftl">

