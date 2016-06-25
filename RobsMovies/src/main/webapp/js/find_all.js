var mRootUrl = "http://localhost:8180/RobsMovies/rest/movies";
var RootUrl = "http://localhost:8180/RobsMovies/rest/movies";
var evenRootierUrl = "http://localhost:8080/RobsMovies/rest/movies";
var dataSet = "";

var findAll = function() {
	$.ajax({
		type : 'GET',
		url : evenRootierUrl,
		dataType : 'json',
		success : loadTable
	});
};