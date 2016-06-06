

$(document).ready(function() {
	findAll();
	    $('[data-toggle="popover"]').popover(); 

});

var loadTable = function(data){
	
    $('#moviesTable').DataTable( {
        data: data,

        columns: [

            { data: "id" },

            { data: "title" },

            { data: "description" },

            { data: "director" },

            { data: "yearMade" },

            { data: "budget" },
            
            { data: "rentalPrice" },
            
            { data: "onLoan"}

        ]

    } );
};