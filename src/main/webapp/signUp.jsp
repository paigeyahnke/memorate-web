<html>

<%@ include file="templates/head.jsp" %>

<body>
    <%@ include file="templates/header.jsp" %>

    <div id="top">
        <h2>Sign Up</h2>
    </div>

    <div id="main">
        <form id="signUp" action="/signUp" method="post">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" />
            <br />

            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            <br />

            <button>Submit</button>
        </form>
    </div>

</body>
</html>
