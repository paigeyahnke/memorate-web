<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="defaultImage" value="no_image_available.png" scope="request" />
<c:set var="imagePath" value="${pageContext.request.contextPath}/images/memories" scope="request" />
<c:set var="root" value="${pageContext.request.contextPath}" scope="request" />

<html>

<%@ include file="../templates/head.jsp" %>

<body>
    <%@ include file="../templates/header.jsp" %>

    <%@ include file="../templates/navigation.jsp" %>

    <div id="top">
        <input class="search" type="search" name="search" placeholder="Search" />
    </div>

    <div id="main">
        <c:forEach var="memory" items="${memories}">
            <a href="${root}/memory?id=${memory.memoryId}" class="memory">
                <h3>${memory.name}</h3>
                <img src="${imagePath}/${empty memory.imagePath ? defaultImage : memory.imagePath}" />
                ${memory.getRatingHtml()}
            </a>
        </c:forEach>
    </div>       

    <footer>     
    </footer>

</body>

</html>