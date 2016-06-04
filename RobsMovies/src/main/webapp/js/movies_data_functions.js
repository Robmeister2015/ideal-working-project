// JavaScript Document
var rootUrl = "http://localhost:8080/RobsMovies/rest/movies";
var findAll = function() {
	$.ajax({
		type : 'GET',
		url : rootUrl,
		dataType : "json",
		success : renderList
	});
};

$(document).on("click", '#newFilm', function() {
	newFilm();
});

$(document).on("click", '#searchButton', function() {
	searchByName($('#search').val());
});

$(document).on("click", '#save', function() {
	if($('#id').val() == '')
	addFilm();
	else
		updateFilm();
	return false;
});

var findById = function(id) {
	$.ajax({
		type : 'GET',
		url : rootUrl + "/" + id,
		dataType : "json",
		success : renderDetails
	});
};

var renderList = function(data) {
	$.each(data, function(index, movie) {
		$('#movieList').append(
				'<li><a href="#" id="' + movie.id + '">' + movie.title
						+ '</a></li>');
	});
}

var searchByName = function(search){
	$.ajax({
		type : 'GET',
		url : rootUrl + "/search?title=" + search,
		dataType : "json",
		success : renderDetails
	});
};



var renderDetails = function(movie) {
	$('#title').val(movie.title).prop('disabled', false);
	$('#id').val(movie.id).prop('disabled', true);
	$('#description').val(movie.description).prop('disabled', false);
	$('#director').val(movie.director).prop('disabled', false); 
	$('#country').val(movie.country).prop('disabled', false); 
	$('#yearMade').val(movie.yearMade).prop('disabled', false);
	$('#budget').val(movie.budget).prop('disabled', false); 
	$('#rentalPrice').val(movie.rentalPrice).prop('disabled', false); 
	$('#onLoan').val(movie.onLoan).prop('disabled', false); 
	// $('#pics').attr('src', 'pics/' + movie.picture).prop('disabled', true);
	$('#save').show();
	$('#image').hide();
}

var newFilm = function() {
	$('#title').val("").prop('disabled', false);
	$('#id').val('');
	$('#description').val('').prop('disabled', false);
	$('#director').val('').prop('disabled', false);
	$('#country').val('').prop('disabled', false);
	$('#yearMade').val('').prop('disabled', false);
	$('#budget').val('').prop('disabled', false);
	$('#rentalPrice').val('').prop('disabled', false);
	$('#onLoan').val('').prop('disabled', false);
	// $('#pics').attr('').prop('disabled', false);
	$('#save').show();
	$('#image').show();
	$('#delete').show();
}

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
		"picture" : 'test'
	});
};

var saveFilm = function() {

}

$(document).ready(function() {
	findAll();
	$(document).on("click", "#movieList a", function() {
		findById(this.id);
	});
	$(document).on("click", "#delete", function() {
		deleteFilm();
	});
	$("#save").hide();
	$("#image").hide();
	$('#delete').hide();
});

var addFilm = function(){
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootUrl,
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

var uploadImage = function(){
	
}

var deleteFilm = function(){
	$.ajax({
		type: 'DELETE',
		url: rootUrl + '/' + $('#id').val(),
		success: function(data, textStatus, jqXHR){
			location.reload();
			alert('movie gone!');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('failed deletey time!');
		}
	});
};

var updateFilm = function(){
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootUrl + '/' + $('#id').val(),
		dataType: 'json',
		data: convertToJSON(),
		success: function(data, textStatus, jqXHR){
			location.reload();
			alert('Updaterised!');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('failed to updaterise!');
		}
	
	})
}