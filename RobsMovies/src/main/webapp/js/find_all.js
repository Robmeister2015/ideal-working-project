var mRootUrl = "http://localhost:8180/RobsMovies/rest/movies";
var RootUrl = "http://localhost:8180/RobsMovies/rest/movies";
var evenRootierUrl = "http://localhost:8080/RobsMovies/rest/movies";
var dataSet = "";

var findAll = function() {
	
	window.Movie = Backbone.Model.extend({
		urlRoot:evenRootierUrl
	});
	
	window.Movies = Backbone.Collection.extend({
		model:Movie, 
		"url":function(){
			return evenRootierUrl
		}
		});
	var movies = new Movies();
	
	movies.fetch({
		success: function(movies){
			loadTable(movies.toJSON());
		}
	})
	}
