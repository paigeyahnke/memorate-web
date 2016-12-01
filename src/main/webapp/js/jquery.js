$(document).ready(function() {
    $("table").DataTable();

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