var rootUrl = "http://localhost:8180/RobsMovies/rest/movies";

$(document).on("click", '#save', function() {
var formData = new FormData($('#uploadForm')[0]);
alert(rootUrl);
$.ajax({
    url: rootUrl + "/upload",
    type: "POST",
    dataType: 'text',
    contentType: false,
    processData: false,
    cache: false,
    data: formData,
    success: function(response) {
        alert("success");
    },
    error: function() {
        alert("unable to create the record");
    }
})
});