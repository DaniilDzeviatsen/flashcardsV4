<%--
  Created by IntelliJ IDEA.
  User: devia
  Date: 16.06.2023
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="chapters" scope="request" type="java.util.List<com.example.flashcardsv4.models.Chapter>" />
<!DOCTYPE html>
<html>
<head>
    <title>Chapters</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>

<main>
    <section>
        <h2>Chapters</h2>
        <c:if test="${chapters.isEmpty()}">
            <p>There are no any chapters</p>
        </c:if>
        <c:if test="${!chapters.isEmpty()}">
            <ul>
                <c:forEach var="chapter" items="${chapters}">
                    <li>
                        <a href="<c:url value="/editor?chapterId=${chapter.chapterId}"/> ">
                            <c:out value="${chapter.name}"/>
                        </a>
                            ${chapter.numOfLearnedCards}/${chapter.totalCardsNum}
                        <form action="<c:url value="/deleteChapter"/> "
                              method="post"
                              enctype="application/x-www-form-urlencoded">
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </section>
    <section>
        <h2>Create chapter</h2>
        <form action="<c:url value="/create-chapter"/> "
              method="post"
              enctype="application/x-www-form-urlencoded">
            <div>
                <label for="create-chapter-name">Name</label>
                <input type="text" id="create-chapter-name" name="name" required>
            </div>
            <button type="submit">Create</button>
        </form>
    </section>
    <section>
        <h2>Delete chapter</h2>
        <form action="<c:url value="/deleteChapter"/> "
              method="post"
              enctype="application/x-www-form-urlencoded">
            <div>
                <label for="delete-chapter-name">Id</label>
                <input type="text" id="delete-chapter-name" name="chapterId" required>
            </div>
            <button type="submit">Delete</button>
        </form>
    </section>
</main>
<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
