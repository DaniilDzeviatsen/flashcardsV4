<%--
  Created by IntelliJ IDEA.
  User: devia
  Date: 18.06.2023
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/update-memorizing"/>"
      method="post"
      enctype="application/x-www-form-urlencoded">
  <input type="hidden" name="memorized" value="${!card.is_remembered}">
  <button type="submit" name="id" value="${card.id}">
    ${card.is_remembered?'true':'false'}
  </button>
  <c:out value="${card.}"
                        </form>
</body>
</html>
