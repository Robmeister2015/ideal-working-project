var findById = function(movieId) {
	window.Movie = Backbone.Model.extend({
		urlRoot:evenRootierUrl
	});

	var movie = new Movie({id:movieId});
	movie.fetch({
		success: function(movie){
			loadMoreInfo(movie.toJSON());
		}
	})
};

var loadMoreInfo = function(data){
	$('#moreinfoid').text(data.id);
	$('#moreinfotitle').text(data.title);
	$('#moreinfodescription').text(data.description);
	$('#moreinfodirector').text(data.director); 
	$('#moreinfocountry').text(data.country);
	$('#moreinfoyearMade').text(data.yearMade);
	$('#moreinfobudget').text(data.budget);
	$('#moreinforentalPrice').text(data.rentalPrice);
	$('#moreinfoonLoan').text(data.onLoan);
	$('#moreInfoImage').attr('src', '../RobsMovies/resources/images/' + data.picture);
	$('#moreInfoModal').modal('show');
}

