<!DOCTYPE html>
<html class="no-js" lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>CreditMe@Brown</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="../css/app.css">
    <link href='https://fonts.googleapis.com/css?family=Lobster+Two:400italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script> 
    
    <!-- script src="http://code.jquery.com/jquery-1.11.3.min.js"></script-->
    <script src="../js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="../js/home.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <link href="../css/jquery-ui.min.css" rel="stylesheet">
    <script src="../js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="css/normalize.css">
    <script>
      $(document).bind('mobileinit',function(){
          $.mobile.changePage.defaults.changeHash = false;
          $.mobile.hashListeningEnabled = false;
          $.mobile.pushStateEnabled = false;
          $.mobile.ignoreContentEnabled = true;
      });
    </script>
  </head>
  <body>
  	<div class="main_page">${content}</div>
  </body>
</html>