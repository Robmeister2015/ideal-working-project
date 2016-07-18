var table;

$(document).ready(function() {
	findAll();
});

var loadTable = function(data){

    table = $('#moviesTable').DataTable( {	
    		
        data: data,
        
        "fnDrawCallback": function() {
        	 $('button.moreInfoClicks').click(function () {
        	        findById(this.id);
        	    });
		    },

        dom:' <"search"f><"top"l>rt<"bottom"ip><"clear">',
        
        "columnDefs": [
                       { "width": "5%", "targets": 0 },
                       { "width": "5%", "targets": 1 },
                       { "width": "5%", "targets": 2 },
                       { "width": "5%", "targets": 3 },
                       { "width": "5%", "targets": 4 },
                       { "width": "5%", "targets": 5 },
                     ],
        columns: [

            { data: "id" },

            { data: "title" },

            { data: "director" },

            { data: "yearMade" },
            
            { data: "picture",
            render: function(data, type, row) {
                return '<img id="tableImage" src="../RobsMovies/resources/images/'+data+'" />';
            }
            },
            { data: "id",
            render: function(data){
            
            	return '<button type="button" id="'+data+'" class="btn btn-primary btn-sm moreInfoClicks">More Information     <span class="glyphicons glyphicon glyphicon glyphicon-eye-open"></span></button>';
            }}
        ]

    } );
    
    
    $('button.moreInfoClicks').click(function () {
        findById(this.id);
    });
     
};

