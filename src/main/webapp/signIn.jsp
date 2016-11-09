<html>

<%@ include file="templates/head.jsp" %>

<body>
    <%@ include file="templates/header.jsp" %>

    <div id="top">
    </div>

    <div id="main">
        <div id="forms">
            <form action="j_security_check" method="POST">
                <h2>Sign In</h2>
                <br />

                <label for="userName">User Name: </label>
                <input type="text" name="j_username" id="userName">
                <br />

                <label for="password">Password: </label>
                <input type="password" name="j_password" id="password">
                <br />

                <button class="submit">Log In</button>
            </form>

            <form id="signUp" action="/signUp" method="post">
                <h2>Sign Up</h2>
                <br />

                <label for="newUserName">Username</label>
                <input type="text" name="username" id="newUserName" />
                <br />

                <label for="newPassword">Password</label>
                <input type="password" name="password" id="newPassword" />
                <br />

                <label for="validatePassword">Password</label>
                <input type="password" id="validatePassword" onkeyup="validate();" />
                <br />

                <button id="submitSignUp" class="submit" disabled="disabled">Sign Up</button>
            </form>
        </div>
    </div>

</body>
</html>
