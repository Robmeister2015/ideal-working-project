$(document).ready(function() {
	findAllImages();
});

var html = "";

var loadImages = function(data){
	
	html += "<div class='item active'>"
	      +"<img class='imagesize' src='../RobsMovies/resources/images/1.jpg' alt='1'>"
	    +  "</div>";
		$.each(data, function(i, e) {
			html += "<div class='item'>"
		      +"<img class='imagesize' src='../RobsMovies/resources/images/"+e.picture +"' alt="+i+">"
		    +  "</div>";
			});	
		
		$('#imageCarousel').html(html);

}


var RootUrl = "http://localhost:8080/RobsMovies/rest/movies";
var dataSet = "";

var findAllImages = function() {
	$.ajax({
		type : 'GET',
		url : RootUrl,
		dataType : 'json',
		success : loadImages
	});
};