$(document).ready(function() {
    $('#memoryTable').dataTable();

    $("#memoryTable tbody").on("click", "tr", function() {
        location.href = $(this).attr("id");
    })

    $('#rating').barrating({
        theme: 'fontawesome-stars'
    });
})