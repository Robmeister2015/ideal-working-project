var counter = 0;

$(document).on("click", '#saveFilm', function(){    
	uploadFile();
	return false;
});


   var uploadFile = function(){ 
	   alert("here");
    input = document.getElementById('fileinput');
      file = input.files[0];
      fr = new FileReader();
      fr.readAsDataURL(file);
      fr.onload = receivedText;
  };


  function receivedText() {
	  var root = "http://localhost:8080/RobsMovies/rest/movies/upload";
    $.ajax({
        type: "POST",
        url: root,
        data: fr.result + "," + $('input[type=file]').val().replace(/C:\\fakepath\\/i, ''),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: console.log("image uploaded"),
        error: console.log("error")
  });
  };
    
  
  var uploadFile2 = function(){ 
   input = document.getElementById('fileinputsave');
     file = input.files[0];
     fr = new FileReader();
     fr.readAsDataURL(file);
     fr.onload = receivedText2;
 };


 function receivedText2() {
	  var root = "http://localhost:8080/RobsMovies/rest/movies/upload";
   $.ajax({
       type: "POST",
       url: root,
       data: fr.result + "," + $('#fileinputsave').val().replace(/C:\\fakepath\\/i, ''),
       contentType: "application/json; charset=utf-8",
       dataType: "json",
       success:  console.log("success"),
       error: console.log("error")
 });
 }
 
 