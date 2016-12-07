$(document).ready(function() {

    var dataTable = $('#memoryTable');
    dataTable.dataTable({
        retrieve: true,
        stateSave: true
    });

    dataTable.on("click", "tbody tr", function() {
        location.href = $(this).attr("id");
    })

    $('#rating').barrating({
        theme: 'fontawesome-stars'
    });

})