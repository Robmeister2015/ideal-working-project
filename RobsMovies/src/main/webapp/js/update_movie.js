

var updateMovie = function(){
	alert("hihi");
	var movie = new Movie({id:$('#moreinfoid').text()});
	movie.fetch({
		success:function(movie){
			movie.save({
				id:$('#moreinfoid').text(),
				title:$('#moreinfotitle').text(),
				description:$('#moreinfodescription').text(),
				director:$('#moreinfodirector').text(),
				country:$('#moreinfocountry').text(),
				yearMade:$('#moreinfoyearMade').text(),
				budget:$('#moreinfpbudget').text(),
				rentalPrice:$('#moreinforentalPrice').text(),
				onLoan:$('#moreinfoonLoan').text()
			})
		}
	})
}