
$(document).ready(function(){

  var request = JSON.parse(localStorage.request||'{}');

  console.log(request)

  $("#progress_bar_2").progressbar({
     value: 50
  });

  $("#progress_bar").progressbar({
     value: 25
  });

  $("#progress_bar_deliver").progressbar({
     value: 37.5
  });

  $(".meet_up_confirm").tap(function(){
    request = JSON.parse(localStorage.request);
    request.method="meet";
    localStorage.request = JSON.stringify(request);
  })

  $(".deliver_confirm").tap(function(){
    request = JSON.parse(localStorage.request);
    request.method="deliver";
    localStorage.request = JSON.stringify(request);
  })
    
  


  locations=["Young Orchard Ave 010", "Marcy House: Wriston Quad", "Vartan Gregorian Quad A (New Dorm A)", "Richmond St 233", "Graduate Ctr A", "Davol Sq 003", "Young Orchard Ave 002", "Barbour Hall", "Sharpe House", "George St 180", "Peter Green House", "Hegeman Hall", "Brown St 068.5", "Caswell Hall", "Geo-Chem Bldg", "Manning Hall", "Ship St 070", "New Pembroke No. 4", "Bio-Med Grimshaw-Gudewicz", "Brown Stadium", "", "Perkins Hall", "Jameson-Mead: Keeney Quad", "Littlefield Hall", "University Hall", "List (Albert & Vera) Art Building", "Maddock Alumni Center", "Harkness House: Wriston Quad", "Sears House: Wriston Quad", "Watson Center For Information Tech", "Morrison-Gerard Studio", "Slater Hall", "George St 163", "Blistein House", "Waterman St 133", "Norwood House", "Goddard House: Wriston Quad", "Hope College", "Machado (Antonio) House", "Fones Alley 008", "Charlesfield St 059", "Miller Hall", "Metcalf Hall", "Bio-Med ACF", "Chapin House: Wriston Quad", "Dyer St 200", "Benevolent St 026", "Barus & Holley", "Manning St 029", "Faculty Club", "Giddings House", "Stimson Ave 002", "Emery: Pembroke Quad", "Rhode Island Hall", "Horace Mann House", "Robinson Hall", "Steinert Center", "Brook St 341", "Sciences Library", "Waterman St 094", "Bowen St 219", "Benevolent St 005", "OMAC Athletic Center", "Graduate Ctr D", "Prospect House", "Nelson Fitness Center", "Manning St 037", "Lincoln Field Building", "Lyman Hall", "Barus Building", "Metcalf Research Building", "Kassar (Edward W.) House", "Archibald-Bronson: Keeney Quad", "Granoff Ctr For The Creative Arts", "Walter Hall", "South Main St 121", "Sidney E. Frank Hall Life Sciences", "Meehan Auditorium", "Hoppin (Thomas P.) House", "New Pembroke No. 2", "Haffenreffer Barn", "Wilson Hall", "Diman House: Wriston Quad", "Meiklejohn House", "Graduate Ctr E", "Minden Hall", "Brown St 111", "Benevolent St 020", "West House", "Angell St 195", "Verney-Woolley Hall (VDub)", "Marston Hall", "Broadway 233", "Olney House: Wriston Quad", "George St 067", "Bio-Med Ctr", "MacMillan Hall", "Angell St 169", "Marston Boathouse", "Rochambeau House", "Waterman St 137", "MacFarlane House", "Brown Office Bldg", "Grant Fulton", "John Hay Library", "Morriss Hall: Pembroke Quad", "Arnold Lab", "Salomon Ctr For Teaching", "Medical Research Lab", "Faunce House", "Rockefeller Library", "Brown St 070", "Dyer House", "Urban Environmental Lab", "Gerard House, Samuel N.", "Thayer St 315", "Pizzitola", "Benoni Cooke House", "Orwig Music Hall", "Ladd Observatory", "5th Ave 500", "Andrews Hall", "Brook St 333", "Smith-Buonanno Hall", "King House", "Montgomery St 601", "Young Orchard Ave 004", "Graduate Ctr C", "Alumnae Hall", "Maxcy Hall", "George St 025", "Buxton House: Wriston Quad", "New Pembroke No. 3", "Waterman St 085", "Lippitt House", "Thayer St 135", "Nicholson House", "Graduate Ctr B", "Haffenreffer Outing Reservation Fac", "John St 050", "Pembroke Hall", "J. Walter Wilson Building", "Champlin: Pembroke Quad", "Vartan Gregorian Quad B (New Dorm B)", "Hemisphere Bldg", "T.F. Green Hall","Annmary Brown Memorial Library", "Everett-Poland: Keeney Quad", "Hope St 190", "Elm St 110", "Waterman St 131", "George St 182", "Waterman St 070", "Woolley Hall: Pembroke Quad", "Wayland House: Wriston Quad", "Partridge Hall & Annex", "Shirley Miller House", "Haffenreffer Museum Collections Res", "Churchill House", "John Carter Brown Library", "Sharpe Refectory (Ratty)", "Corliss-Brackett", "Feinstein", "Sayles Hall", "Prince Engineering Lab", "Richmond St 222 (Med Ed)", "Watson Institute", "George St 155", "New Pembroke No. 1", "Mencoff Hall", "Wilbour Hall"];

  $("#autocorrect").autocomplete({
     minLength:3,
     source: locations,
     select: function(value, data){
      request = JSON.parse(localStorage.request);
      request.address = data.item.value;  
      console.log("after completion, we have");
      console.log(request);
      localStorage.request = JSON.stringify(request);
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
  
  $('.ui-autocomplete').css({'fontSize': '1em', 'width': '304px','height':"auto",'background-color':'none'});


})