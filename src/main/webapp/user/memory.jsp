<html>

<%@ include file="../templates/head.jsp" %>

<body>
<%@ include file="../templates/header.jsp" %>
<%@ include file="../templates/navigation.jsp" %>

<div id="top"></div>

<div id="main">
    <div class="memoryDetail">
        <h1>${memory.name}</h1>
        <p id="memo">${memory.memo}</p>
        <img src="${imagePath}/${empty memory.imagePath ? defaultImage : memory.imagePath}" />
        ${memory.getRatingHtml()}
        <div id="tagList">${memory.getTagList()}</div>
    </div>

    <a href="${root}/delete?memoryId=${memory.memoryId}" id="deleteMemoryButton">Delete</a>

</div>

</body>
</html>
