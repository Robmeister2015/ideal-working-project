

$(document).ready(function() {
	findAll();
});

var loadTable = function(data){
	
    var table = $('#moviesTable').DataTable( {
        data: data,

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
            
            { data: "id",
            render: function(data, type, row) {
                return '<img id="tableImage" src="../RobsMovies/resources/images/'+data+'.jpg" />';
            }
            },
            { data: "id",
            render: function(data){
            	return '<a id="'+data+'" class="moreInfoClicks" href="#"> More Information</a>';
            }}
        ]

    } );
    
    $('a.moreInfoClicks').click(function () {
        findById(this.id);
    });
};