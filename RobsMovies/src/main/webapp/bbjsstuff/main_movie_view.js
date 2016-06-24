//MainView grabs all the rendered subviews (WineViews) and appends them.
var MainView = Backbone.View.extend({
  collection: MovieList,
  id: "container",
  initialize: function(){
	  alert("here");
   this.listenTo(this.collection, 'add', this.renderList);
   this.render();
  },

  render: function(){
    this.collection.each(function(movie){
    $('#movieList').append(new MovieView({model:movie}).render());
    }, this);
    var movie = new Movie(); 
    $('#mainArea').html(new DetailsView({model:movie}).render());
  },
  
  renderList:function(){
	  $('#movieList li').remove();
	  this.collection.each(function(movie){
		    $('#movieList').append(new MovieView({model:movie}).render());
		    }, this);
 
  }
});

