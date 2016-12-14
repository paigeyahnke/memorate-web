<html>

<%@ include file="../templates/head.jsp" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="703511659985-08bkth1fdhc355s5uqn44p8pvv84quf2.apps.googleusercontent.com">


<body>
    <%@ include file="../templates/header.jsp" %>
    <%@ include file="../templates/navigation.jsp" %>

    <div id="top">

        <table id="userInformation">
            <tr>
                <th colspan="2">Profile</th>
            </tr>
            <tr>
                <td>Username</td>
                <td class="editable">${user.userName}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td class="editable">${user.email != null ? user.email : "NA"}</td>
            </tr>
            <tr>
                <td>First Name</td>
                <td class="editable">${user.firstName != null ? user.firstName : "NA"}</td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td class="editable">${user.lastName != null ? user.lastName : "NA"}</td>
            </tr>
        </table>

        <div class="g-signin2" data-onsuccess="onSignIn" id="googleButton"></div>
        <button type="button" id="updateInformation">Update Information</button>
        <button type="button" id="googleSignOut">Sign out</button>

    </div>

    <div id="main">
    </div>

</body>
</html>
