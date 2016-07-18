var picSave = '';

$(document).ready(function(){
	var movie2 = new Movie();
})

var updateMovie = function(){
	movie2 = new Movie({id:$('#moreinfoid').text()});
	var movie = new Movie();
	if($('#fileinputsave').val().replace(/C:\\fakepath\\/i, '') == ""){
		picSave = picture;
	}else{
		uploadFile2();
		picSave = $('#fileinputsave').val().replace(/C:\\fakepath\\/i, '');
	}
			movie.save({
				id:$('#moreinfoidsave').text(),
				title:$('#moreinfotitlesave').val(),
				description:$('#moreinfodescriptionsave').val(),
				director:$('#moreinfodirectorsave').val(),
				country:$('#moreinfocountrysave').val(),
				yearMade:$('#moreinfoyearMadesave').val(),
				budget:$('#moreinfpbudgetsave').val(),
				rentalPrice:$('#moreinforentalPricesave').val(),
				onLoan:$('#moreinfoonLoansave').val(),
				picture:picSave
			})
			movie.save();
			
			 setTimeout(function(){
				 table.destroy();
				 findAll();
			    }, 5000);
			 
				
}
