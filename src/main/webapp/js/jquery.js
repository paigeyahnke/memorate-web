$(document).ready(function() {
    $("table").DataTable( {
        "columns": [
            { "width": "10%" },
            { "width" : "15%" },
            { "width": "5%" },
            { "width" : "30%" },
            { "width": "30%" }
        ]
    } );

    $("tbody tr").on("click", function() {
        location.href = $(this).attr("id");
    })

    $('#rating').barrating({
        theme: 'fontawesome-stars'
    });

    // $('#deleteMemory').on("click", function() {
    //     var memoryId = $('.memory').attr("id");
    //     location.href = "/delete?memoryId=" + memoryId;
    // })
})