$(document).ready(function(){
	$('#newFilmTab').on('click', function(){
	//	$('#newFilmModal').modal('show');
	});
	
	$('#saveFilm').on('click', function(){
		saveFilm();
	});
	
	$('#deleteFilm').on('click', function(){
		deleteFilm();
	});
})

var mRootUrl = "http://localhost:8080/RobsMovies/rest/movies";

window.Movie = Backbone.Model.extend({
	urlRoot:mRootUrl
});

var saveFilm = function(){
	uploadFile();
	var movieDetails = {
			"title" : $('#title').val(),
			"description" : $('#description').val(),
			"director" : $('#director').val(),
			"country" : $('#country').val(),
			"yearMade" : $('#yearMade').val(),
			"budget" : $('#budget').val(),
			"rentalPrice" : $('#rentalPrice').val(),
			"onLoan" : $('#onLoan').val(),
			"picture" : $('input[type=file]').val().replace(/C:\\fakepath\\/i, '')
	}
	
	var movie = new Movie();
	movie.save(movieDetails, {
		success: function(movie){
			table.destroy();
			findAll();
		}
	});
};
	
	var deleteFilm = function(){
		var movie = new Movie({id:$('#moreinfoid').text()});
		movie.destroy({
			success: function(movie){
				table.destroy();
				findAll();
			}
		})
	}
	
