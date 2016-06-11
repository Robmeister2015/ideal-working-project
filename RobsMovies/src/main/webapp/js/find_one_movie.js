var findById = function(id) {
	$('#moreInfoModal').modal('show');
	$.ajax({
		type : 'GET',
		url : mRootUrl + "/" + id,
		dataType : "json",
		success : loadMoreInfo
	});
};

var loadMoreInfo = function(data){
	alert(data.id);
	$('#moreinfoid').val(data.id).prop('disabled', true);
	$('#moreinfotitle').val(data.title).prop('disabled', false);
	$('#moreinfodescription').val(data.description).prop('disabled', false);
	$('#moreinfodirector').val(data.director).prop('disabled', false); 
	$('#moreinfocountry').val(data.country).prop('disabled', false); 
	$('#moreinfoyearMade').val(data.yearMade).prop('disabled', false);
	$('#moreinfobudget').val(data.budget).prop('disabled', false); 
	$('#moreinforentalPrice').val(data.rentalPrice).prop('disabled', false); 
	$('#moreinfoonLoan').val(data.onLoan).prop('disabled', false); 
	// $('#pics').attr('src', 'pics/' + movie.picture).prop('disabled', true);
}
