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

    $('#googleSignOut').on("click", function() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    });

    $('#updateInformation').on("click", function() {
        var auth2 = gapi.auth2.init();
        if (auth2.isSignedIn.get()) {
            var profile = auth2.currentUser.get().getBasicProfile();

            var xhr = new XMLHttpRequest();
            xhr.open("post", "updateUser");

            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            var params = "email=" + profile.getEmail() + "&firstName="
                + profile.getGivenName() + "&lastName=" + profile.getFamilyName();

            xhr.onreadystatechange = function() {
                if (xhr.readyState == XMLHttpRequest.DONE) {
                    if (xhr.status == 200) {
                        location.reload();
                        console.log('Response: ' + xhr.responseText );
                    } else{
                        console.log('Error: ' + xhr.statusText )
                    }
                }
            }

            xhr.send(params);
        }

    });

})