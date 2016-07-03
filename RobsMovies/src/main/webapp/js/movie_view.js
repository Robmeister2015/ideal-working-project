var viewRootUrl = "http://localhost:8080/RobsMovies/rest/movies";
var picture = "";
$(document).ready(function(){
$('#updateButton').on('click', function(){
	var movie = new Movie({id:$('#moreinfoid').text()});
	movie.fetch({
		success:function(){
			picture = movie.get('picture');
				renderIt(movie);	
	}
});
});
window.Movie = Backbone.Model.extend({
urlRoot:viewRootUrl
});
})

MovieView = Backbone.View.extend({
	 defaults: {
		    "picture": picture
		  },
		  
	events: {
		"click #finalUpdateButton":function(){
			alert("picsy: " + picture);
			updateMovie();
		//	renderItBack();
		},
		
		"click #updateButton":function(){
			var movie = new Movie({id:$('#moreinfoid').text()});
			movie.fetch({
				success:function(){
						renderIt(movie);	
			}
		});
		},
		
		"click #deleteFilm":function(){
			alert('deleting...');
			deleteFilm();
		}
	},
	render:function(){
		var template = _.template($('#movie_details').html(), this.model.toJSON());
		$(this.el).html(template);
		return this;
	},
	render2:function(){
		var template = _.template($('#movie_details_returned').html(), {});
		$(this.el).html(template);
		return this;
	}
});

var renderIt = function(movie){
	var movieView = new MovieView({model:movie});
	$('#modalContent').html(movieView.render().el);
}

var renderItBack = function(){
	var movieView2 = new MovieView();
	$('#modalContent').html(movieView2.render2().el);
}