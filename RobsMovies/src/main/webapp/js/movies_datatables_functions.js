

$(document).ready(function() {
	findAll();
});

var loadTable = function(data){
	
    var table = $('#moviesTable').DataTable( {
        data: data,

        columns: [

            { data: "id" },

            { data: "title" },

            { data: "director" },

            { data: "yearMade" },
            
            { data: "id",
            render: function(data, type, row) {
                return '<img id="tableImage" src="../RobsMovies/resources/images/'+data+'.jpg" />';
            }
            }
        ]

    } );
    
    $('#moviesTable tbody').on('click', 'tr', function () {
        findById(table.row(this).data().id);
    });
};