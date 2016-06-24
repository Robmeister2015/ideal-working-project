
var rootURL = "http://localhost:8180/RobsMovies/rest/movies";

var Movie = Backbone.Model.extend({
	urlRoot:rootURL,
	defaults:{       
		"id":null, 
		"title":"",     
		"description":"",  
		"director":"", 
		"country":"",  
		"yearMade":"",      
		"budget":"",  
		"rentalPrice":"",
		"onLoan":"",
		"picture":""},
  initialize: function(){
    console.log("movie init");
    this.on('change', function(){
    	console.log('Values for a model have changed');
    });
  }
});

var MovieList = Backbone.Collection.extend({
	model: Movie,
	url:rootURL});
