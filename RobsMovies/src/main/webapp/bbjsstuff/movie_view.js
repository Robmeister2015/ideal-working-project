var viewRootUrl = "http://localhost:8080/RobsMovies/rest/movies";

$(document).ready(function(){
$('#updateButton').on('click', function(){
//	var movie = new Movie({$('#moreinfoid').text()});
	renderIt();
})
})
window.Movie = Backbone.Model.extend({
urlRoot:viewRootUrl
});

//MovieView = Backbone.View.extend({
//	render:function(){
//		var template = _.template($('#movie_details').html(), this.model.toJSON());
//		$(this.el).html(template);
//		return this;
//	}
//});

MovieView = Backbone.View.extend({
	render:function(){
		var template = _.template($('#movie_details').html(), {});
		$(this.el).html(template);
		return this;
	}
});

var renderIt = function(){
	var movieView = new MovieView();
	$('#moreInfoForm').html(movieView.render().el)
}