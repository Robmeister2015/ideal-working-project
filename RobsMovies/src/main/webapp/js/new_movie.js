$(document).ready(function(){
	$('#newFilmTab').on('click', function(){
		$('#newFilmModal').modal('show');
	});
	
	$('#saveFilm').on('click', function(){
		saveFilm();
	});
	
	$('#deleteFilm').on('click', function(){
		deleteFilm();
	});
})

//var mRootUrl = "http://localhost:8080/RobsMovies/rest/movies";
//
//var convertToJSON = function() {
//	return JSON.stringify({
//		"id" : $('#id').val(),
//		"title" : $('#title').val(),
//		"description" : $('#description').val(),
//		"director" : $('#director').val(),
//		"country" : $('#country').val(),
//		"yearMade" : $('#yearMade').val(),
//		"budget" : $('#budget').val(),
//		"rentalPrice" : $('#rentalPrice').val(),
//		"onLoan" : $('#onLoan').val(),
//		"picture" : $('#id').val() + '.jpg'
//	});
//};
//
//var addFilm = function(){
//	alert('addFilm');
//	$.ajax({
//		type: 'PUT',
//		contentType: 'application/json',
//		url: mRootUrl,
//		dataType: 'json',
//		data: convertToJSON(),
//		success: function(data, textStatus, jqXHR){
//			location.reload();
//			alert('Success creating film');
//			findAll();	
//		},
//		error: function(jqXHR, textStatus, errorThrown){
//			alert('adding error: ' + textStatus);
//		}
//	})
//};

var mRootUrl = "http://localhost:8080/RobsMovies/rest/movies";

window.Movie = Backbone.Model.extend({
	urlRoot:mRootUrl
});

var saveFilm = function(){
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
			alert(movie.toJSON());
		}
	});
};

var updateWine = function(){
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
			alert(movie.toJSON());
		}
	});
	
};
	var deleteFilm = function(){
		alert("here");
		alert($('#moreinfoid').text());
		var movie = new Movie({id:$('#moreinfoid').text()});
		movie.destroy({
			success: function(movie){
				alert("it's gone!");
			}
		})
	}
	
