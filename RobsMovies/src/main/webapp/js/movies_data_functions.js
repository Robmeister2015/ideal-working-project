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

var renderDetails = function(movie) {
	$('#title').val(movie.title).prop('disabled', true);
	$('#id').val(movie.id);
	$('#description').val(movie.description).prop('disabled', true);
	$('#director').val(movie.director).prop('disabled', true);
	$('#country').val(movie.country).prop('disabled', true);
	$('#yearMade').val(movie.yearMade).prop('disabled', true);
	$('#budget').val(movie.budget).prop('disabled', true);
	$('#rentalPrice').val(movie.rentalPrice).prop('disabled', true);
	$('#onLoan').val(movie.onLoan).prop('disabled', true);
	// $('#pics').attr('src', 'pics/' + movie.picture).prop('disabled', true);
	$('#save').hide();
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
}

var convertToJSON = function() {
	return JSON.stringify({
		"title" : $('#title').val(),
		"description" : $('#director').val(),
		"director" : $('#director').val(),
		"country" : $('#title').val(),
		"yearMade" : $('#yearMade').val(),
		"budget" : $('#budget').val(),
		"rentalPrice" : $('#rentalPrice').val(),
		"onLoan" : $('#onLoan').val()
	});
};

var saveFilm = function() {

}

$(document).ready(function() {
	findAll();
	$(document).on("click", "#movieList a", function() {
		findById(this.id);
	});
	$("#save").hide();
	$("#image").hide();
});

var fileSelect = document.getElementById('file-select');