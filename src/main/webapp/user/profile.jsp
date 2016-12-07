<html>

<%@ include file="../templates/head.jsp" %>

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

    </div>

    <div id="main">
    </div>

</body>
</html>
