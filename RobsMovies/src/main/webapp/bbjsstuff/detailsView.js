var DetailsView = Backbone.View.extend({
	model: Movie,
	id: "addContainer",
	initialize: function(){
		alert("here in detailsView");
	      console.log("init");
	},
	events:{
		  "click #newFilmTab": "revealModal",
		  "click #btnSave": "saveMovie",
		  "click #btnDelete": "deleteMovie",
		  "click #btnAdd": "add",
	  },
	  saveMovie: function(e){
		  alert("model"+this.model.get('name'));
		  var movieDetails= {title:$('#title').val(), 
				  			description:$('#description').val(),
				  			director:$('#director').val(),
				  			country:$('#country').val(),
				  			yearMade:$('#yearMade').val(),
				  			budget:$('#budget').val(),
				  			rentalPrice:$('#rentalPrice').val(),
				  			onLoan:$('#onLoan').val(),
				  			picture:$('#picture').val()};
		//  if (this.model.isNew()) {
		  if (($('#movieId').val()) == ''){
			  alert("isnew");
			  this.model= new Movie(movieDetails);
			  alert("test"+this.model.get('name'));
			  movieList.add(this.model);
			  alert("added");
			  this.model.save({
				  title:$('#title').val(), 
		  			description:$('#description').val(),
		  			director:$('#director').val(),
		  			country:$('#country').val(),
		  			yearMade:$('#yearMade').val(),
		  			budget:$('#budget').val(),
		  			rentalPrice:$('#rentalPrice').val(),
		  			onLoan:$('#onLoan').val(),
		  			picture:$('#picture').val()
					  },{
						  success:function(movie){
							  alert("sucess - added");
							  $('#movieId').val(movie.id);
						  }
					  });
			  }
		  else{
		  this.model.save({
			  id:parseInt($('#movieId').val()),
			  title:$('#title').val(), 
	  			description:$('#description').val(),
	  			director:$('#director').val(),
	  			country:$('#country').val(),
	  			yearMade:$('#yearMade').val(),
	  			budget:$('#budget').val(),
	  			rentalPrice:$('#rentalPrice').val(),
	  			onLoan:$('#onLoan').val(),
	  			picture:$('#picture').val()
			  },{
				  success:function(movie){
					  alert("sucess - updated");
					  mainView.renderList();
				  }
			  });
		  }
		  return false;
	  },
	  deleteWine: function(e){
		  var movieId= parseInt($('#movieId').val());
		  this.model.set({id:movieId});
		  this.model.destroy(
				  {success:function(data){
					  alert("wine deleted");
					  mainView.renderList();
				  }
		  });
		  return false;
	  },
	  
	  add: function(e){
		  $('#btnDelete').hide();
		  alert('Add');
		  $('#movieId').val(null);
		  $('#title').val("");  
		  $('#description').val("");
		  $('#director').val("");
		  $('#country').val(""); 
		  $('#yearMade').val("");     
		  $('#budget').val("") ;
		  $('#rentalPrice').val("");
		  $('#onLoan').val("");
		  $('#picture').val("");
		  return false;
	  },
	
  render: function(){
	  console.log("render"+this.model.get('name'));
	 var template= _.template($('#movie-details').html(), this.model.toJSON());
	 return this.$el.html(template);
  },
	  
	  revealModal: function(){
		  console.log("revealing modal...");
		 $("#newFilmModal").modal('show');
	  }
});






