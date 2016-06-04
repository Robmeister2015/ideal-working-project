var rootUrl = "http://localhost:8080/RobsMovies/rest/movies";

$(function(){
    $('#destruction').on('click', function(){ 
       var fd = new FormData($("#uploadForm")[0]);
    	alert('hi');
    	alert(fd);
        alert('here');
        var formData = JSON.stringify(fd);
        alert('here now here now');
        $.ajax({
            url: rootUrl + '/upload',  
            type: 'POST',
            contentType: 'application/json', 
            data: formData,
            dataType: 'json',
            success:function(data){
                alert('success');
            },
        });
    });
});