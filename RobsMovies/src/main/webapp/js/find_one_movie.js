var findById = function(id) {
	
	$.ajax({
		type : 'GET',
		url : evenRootierUrl + "/" + id,
		dataType : "json",
		success : loadMoreInfo
	});
	$('#moreInfoModal').modal('show');
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
}
