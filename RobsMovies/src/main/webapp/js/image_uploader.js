$(document).on("click", '#submit', function(){    
	uploadFile();
	return false;
});


   var uploadFile = function(){ 
	   alert('uploadFile');
    input = document.getElementById('fileinput');
      file = input.files[0];
      fr = new FileReader();
      fr.onload = receivedText;
      fr.readAsDataURL(file);
      receivedText();
  };


  function receivedText() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/RobsMovies/rest/movies/upload",
        data: fr.result + ",2",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){alert(fr.result);},
        failure: function(errMsg) {
            alert(errMsg);
        }
  });
  };
    