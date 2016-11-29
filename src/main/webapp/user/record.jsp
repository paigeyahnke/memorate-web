<!DOCTYPE html>
<html>

<%@ include file="../templates/head.jsp" %>

<body>
    <%@ include file="../templates/header.jsp" %>

    <%@ include file="../templates/navigation.jsp" %>

    <div id="top">
        <h2>Record</h2>
    </div>

    <div id="main">
        <form id="record" action="${pageContext.request.contextPath}/record" method="post">
            <h2>Record Memory:</h2>
            <br />

            <label for="title">Title</label>
            <input type="text" name="name" id="title" />
            <br />

            <label for="rating">Rating</label>
            <input type="number" name="rating" id="rating" min="1" max="5"/>
            <br />

            <label for="image">Image</label>
            <input type="file" id="image" name="image" accept="image/*" />
            <br />

            <label for="memo">Memo</label>
            <input type="text" name="memo" id="memo" />

            <label for="tags"></label>
            <input type="text" name="tags" id="tags" />

            <button id="submitSignUp" class="submit">Remember</button>
        </form>
    </div>

    <footer>
    </footer>
</body>

</html>