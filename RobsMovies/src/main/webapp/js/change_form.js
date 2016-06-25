$(document).ready(function(){
	$('#updateButton').on('click', function(){
		changeForm();
	})
})
var changeForm = function(){
	$('#moreinfotitle').replaceWith('<input type="text" class="form-control" id="moreinfotitle"></input>');
	$('#moreinfodescription').replaceWith('<textarea rows="4" cols="50" class="form-control" id="moreinfodescription></textarea>');
	$('#moreinfodirector').replaceWith('<input type="text" class="form-control" id="moreinfodirector"></input>');
	$('#moreinfocountry').replaceWith('<input type="text" class="form-control" id="moreinfocountry"></input>');
	$('#moreinfoyearMade').replaceWith('<input type="text" class="form-control" id="moreinfoyearMade"></input>');
	$('#moreinfobudget').replaceWith('<input type="text" class="form-control" id="moreinfobudget"></input>');
	$('#moreinforentalPrice').replaceWith('<input type="text" class="form-control" id="moreinforentalPrice"></input>');
	$('#moreinfoonLoan').replaceWith('<input type=text" class="form-control" id="moreinfoonLoan"></input>');
	$('.modal-body').append('<label for="fileinput">Image Upload</label><div class="form-group"><input type="file" class="form-control" id="fileinput" /></div>');
	
}