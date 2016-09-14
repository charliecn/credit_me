

$(document).ready(function(){

  var offset = 0

  // $('body').click(function(){
  //   offset += $(window).height();
  //   $('#process').css("top",-offset);
  // });

  $('.choice').click(function(){

    $('.choice').css('background-color','white');
    $(this).css('background-color','pink');
    $(this).parents('.container').addClass("animated pulse");
    var classes = $(this).parents('.container').attr('class');
    
    setTimeout(function(){

      $(this).parents('.container').css('top','30%');
      

      //var classes = $(this).parents('.container').attr('class');

      console.log(classes);
      
      var num = parseInt(classes.split(' ')[1].split('_')[1]);

      console.log(num);

      var to_move_up = $(".container_"+(num+1));
      
      console.log(to_move_up);

      to_move_up.css('top',"20%");
      $(this).parents('.container').removeClass("animated bounceOutUp");

    },1000);
    
    


  })



});