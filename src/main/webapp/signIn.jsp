<html>

<%@ include file="templates/head.jsp" %>

<body>
    <%@ include file="templates/header.jsp" %>

    <div id="top">
        <h2>Sign In</h2>
    </div>

    <div id="main">
        <form action="j_security_check" method="POST">
            <label for="userName">User Name: </label>
            <input type="text" name="j_username" id="userName">
            <br />
            <label for="password">Password: </label>
            <input type="password" name="j_password" id="password">
            <br />
            <input type="submit" value="Log In" id="submit">
        </form>
    </div>

</body>
</html>
