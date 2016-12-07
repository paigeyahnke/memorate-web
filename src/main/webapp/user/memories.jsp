<html>

<%@ include file="../templates/tagSetup.jsp" %>

<%@ include file="../templates/head.jsp" %>


<head>
    <title>MemoRate</title>

    <link rel="stylesheet" type="text/css" href="${root}/styles/style.css">
    <link href="https://fonts.googleapis.com/css?family=Hind|Istok+Web|Roboto+Condensed|Rubik" rel="stylesheet">

    <!-- For DataTables -->
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script type="application/javascript" src="${root}/js/jquery.js"></script>

    <script type="application/javascript" src="${root}/js/script.js"></script>
</head>

<body>
    <%@ include file="../templates/header.jsp" %>

    <%@ include file="../templates/navigation.jsp" %>

    <div id="top">
        <%--<input class="search" type="search" name="search" placeholder="Search" />--%>
    </div>

    <div id="main">
        <table class="display datatable" id="memoryTable">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Rating</th>
                <th>Memo</th>
                <th>Tags</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="memory" items="${memories}">
                    <tr id="${root}/viewMemory?id=${memory.memoryId}">
                        <td>${memory.memoryId}</td>
                        <td>${memory.name}</td>
                        <td>${memory.rating}</td>
                        <td>${memory.memo}</td>
                        <td>${memory.getTagList()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>       

    <footer>
    </footer>

</body>

</html>