<html>

<%@ include file="../templates/head.jsp" %>

<body>
<%@ include file="../templates/header.jsp" %>
<%@ include file="../templates/navigation.jsp" %>

<div id="top">
</div>

<div id="main">
    <div class="memory">
        <h2>${memory.name}</h2>
        <img src="${imagePath}/${empty memory.imagePath ? defaultImage : memory.imagePath}" />
        ${memory.getRatingHtml()}
    </div>

</div>

</body>
</html>
