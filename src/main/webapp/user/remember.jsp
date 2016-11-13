<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="defaultImage" value="no_image_available.png" scope="request" />
<c:set var="imagePath" value="${pageContext.request.contextPath}/images/memories" scope="request" />
<c:set var="root" value="${pageContext.request.contextPath}" scope="request" />


<!DOCTYPE html>
<html>

<%@ include file="../templates/head.jsp" %>

<body>
    <%@ include file="../templates/header.jsp" %>

    <%@ include file="../templates/navigation.jsp" %>

    <div id="top">
        <input class="search" type="search" name="search" placeholder="Search" />
    </div>

    <div id="main">
        <ul>
            <c:forEach var="memory" items="${memories}">
                <a href="${root}/memory?id=${memory.memoryId}" class="memory">
                    <h3>${memory.name}</h3>
                    <img src="${imagePath}/${empty memory.imagePath ? defaultImage : memory.imagePath}" />
                    <span id="rating">
                        <c:forEach var="i" begin="1" end="${memory.rating}">&#x2605</c:forEach>
                    </span>
                </a>
            </c:forEach>
        </ul>

    </div>       

    <footer>     
    </footer>

</body>

</html>