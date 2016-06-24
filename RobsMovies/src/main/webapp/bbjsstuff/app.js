
//An instance of MainView is made whenever the app is run.

var mainView;
var movieList;
//The rendered main view is put on the page.
$(document).ready(function(){
	//console.log("here");
	movieList = new MovieList();
	movieList.fetch({
		success: function(data){
			mainView = new MainView({collection: movieList});
			mainView.$el.appendTo(document.body);
		}
	});
});

