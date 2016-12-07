<html>

<%@ include file="../templates/head.jsp" %>

<body>
<%@ include file="../templates/header.jsp" %>
<%@ include file="../templates/navigation.jsp" %>

<div id="top">
    <div id="memoryNavigator">
        <ul>
            <li>
                <a href="#" class="memoryNavigator"><img src="${root}/images/left-arrow.png" alt="See previous memory"></a>
            </li> |
            <li>
                <a href="${root}/memories">Back to Memories</a>
            </li> |
            <li>
                <a href="${root}/delete?memoryId=${memory.memoryId}">Delete this Memory</a>
            </li> |
            <li>
                <a href="#"><img src="${root}/images/right-arrow.png" alt="See next memory"></a>
            </li>
        </ul>
    </div>
</div>

<div id="main">
    <div class="memoryDetail">
        <h1>${memory.name}</h1>
        <p id="memo">${memory.memo}</p>
        <img src="${imagePath}/${empty memory.imagePath ? defaultImage : memory.imagePath}" />
        ${memory.getRatingHtml()}
        <div id="tagList">${memory.getTagList()}</div>
    </div>
</div>

</body>
</html>
