<!DOCTYPE html>
<html>

<%@ include file="../templates/head.jsp" %>

<body>
    <%@ include file="../templates/header.jsp" %>

    <%@ include file="../templates/navigation.jsp" %>

    <div id="top">
    </div>

    <div id="main">
        <div id="forms">
            <form id="record"
                  action="${pageContext.request.contextPath}/remember"
                  method="post">

                <h2>Remember</h2>
                <br />

                <label for="title">Title</label>
                <input type="text"
                       name="name"
                       id="title"
                       autofocus />
                <br />

                <label for="memo">Memo</label>
                <input type="text"
                       name="memo"
                       id="memo" />
                <br />

                <label for="tags">Tags</label>
                <input type="text"
                       name="tags"
                       id="tags"
                       placeholder=" tag1, tag2, tag3 " />
                <br />

                <input type="file"
                       id="imageSelector"
                       name="image"
                       accept="image/*" />

                <select id="rating" name="rating" style="display:none;">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>

                <button id="saveMemoryButton" class="submit">Remember</button>
            </form>
        </div>

    </div>

    <footer>
    </footer>
</body>

</html>