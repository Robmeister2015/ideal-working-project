var mRootUrl = "http://localhost:8180/RobsMovies/rest/movies";
var dataSet = "";

var getDataSet = function(){
	alert(dataSet + 'in getdataset');
	return dataSet;
};

var findAll = function() {
	$.ajax({
		type : 'GET',
		url : mRootUrl,
		dataType : 'json',
		success : loadTable
	});
};