<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
    <link rel="stylesheet" type="text/css" href="/ui/fts.css">
    <script type="text/javascript" src="/ui/jquery.min.js"></script>
    <script type="text/javascript" src="/ui/fts.js"></script>
</head>
<body>
<table width="100%">
    <tr>
        <td>文件名</td>
        <td>剩余时间</td>
        <td>访问权限</td>
        <td>操作</td>
    </tr>
    <c:if test="${viewList != null}">
        <c:forEach var="item" items="${viewList}" >
            <tr>
                <td>${item.name}</td>
                <td>${item.remaining}</td>
                <td>${item.access}</td>
                <td>${item.action}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
