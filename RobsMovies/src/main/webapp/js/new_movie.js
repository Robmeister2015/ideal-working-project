var mRootUrl = "http://localhost:8180/RobsMovies/rest/movies";

var convertToJSON = function() {
	return JSON.stringify({
		"id" : $('#id').val(),
		"title" : $('#title').val(),
		"description" : $('#description').val(),
		"director" : $('#director').val(),
		"country" : $('#country').val(),
		"yearMade" : $('#yearMade').val(),
		"budget" : $('#budget').val(),
		"rentalPrice" : $('#rentalPrice').val(),
		"onLoan" : $('#onLoan').val(),
		"picture" : $('#id').val() + '.jpg'
	});
};

var addFilm = function(){
	alert('addFilm');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: mRootUrl,
		dataType: 'json',
		data: convertToJSON(),
		success: function(data, textStatus, jqXHR){
			location.reload();
			alert('Success creating film');
			findAll();	
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('adding error: ' + textStatus);
		}
	})
};