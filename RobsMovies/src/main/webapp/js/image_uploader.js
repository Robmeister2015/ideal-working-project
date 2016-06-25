$(document).on("click", '#saveFilm', function(){    
	uploadFile();
	return false;
});


   var uploadFile = function(){ 
	   alert('uploadFile');
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
        success: function(data){alert(fr.result);},
        failure: function(errMsg) {
            alert(errMsg);
        }
  });
  };
    