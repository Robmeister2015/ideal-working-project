var RootUrl = "http://localhost:8080/RobsMovies/rest/movies";
var dataSet = "";

var findAll = function() {
	alert(RootUrl);
	$.ajax({
		type : 'GET',
		url : RootUrl,
		dataType : 'json',
		success : loadTable
	});
};