$(document).ready(function() {
    $("table").DataTable();

    $("tbody tr").on("click", function() {
        location.href = $(this).attr("id");
    })
})