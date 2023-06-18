<%--
  Created by IntelliJ IDEA.
  User: devia
  Date: 18.06.2023
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="cards" scope="request" type="java.util.List<com.example.flashcardsv4.models.Card>" />
<jsp:useBean id="chapterId" scope="request" type="java.lang.Long"/>
<!DOCTYPE html>
<html>
<head>
    <title>Cards</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<main>
    <section>
       <h2>Cards</h2>
        <c:if test="${cards.isEmpty()}">
            <p>There are no any chapters</p>
        </c:if>
        <c:if test="${!cards.isEmpty()}">
            <ul>
                <c:forEach var="card" items="${cards}">
                    <h4> <c:out value="${card.question}"/></h4>
                   <h4><c:out value="${card.answer}"/></h4>
                    <c:choose>
                        <c:when test="${card.is_remembered}">
                            <h4>
                                <input class="form-check-input" type="checkbox" value="" aria-label="Remembered"checked>
                                Remembered
                            </h4>
                        </c:when>
                        <c:otherwise>
                            <h4>
                                <input class="form-check-input" type="checkbox" value="" aria-label="Remembered"checked>
                                Not remembered
                            </h4>
                        </c:otherwise>
                    </c:choose>
                    <form action="<c:url value="/delete-card"/> " method="post" enctype="application/x-www-form-urlencoded">
                        <button type="submit" name="cardId" value="${card.id}">Delete
                        </button>
                    </form>
                </c:forEach>
                <form action="<c:url value="/create-card"/> "method="post"enctype="application/x-www-form-urlencoded">
                    <h5>question</h5>
                    <input type="text" name="question" required>
                    <h5>answer</h5>
                    <input type="text" name="answer" required>
                    <input type="hidden" id="chapterId" name="chapterId" value="${chapterId}">
                    <button type="submit" name="chapterId" value="${chapterId}">add</button>
                </form>
            </ul>
        </c:if>
    </section>
</main>
<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
