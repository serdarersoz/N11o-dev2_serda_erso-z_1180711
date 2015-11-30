<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
    <link href="resources/styles.css" rel="stylesheet">
</head>
<body>
<a href="<c:url value="/" />" class="likeabutton">Add Book</a>
<br><br>
<c:if test="${not empty list}">
<table>
    <tr>
        <td>Book Name</td>
        <td>Author</td>
        <td>ISBN</td>
        <td>Price</td>
    </tr>
    <c:forEach var="book" items="${list}">
        <tr>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.isbn}</td>
            <td>${book.price}</td>
        </tr>
    </c:forEach>
</table>
</c:if>




</body>
</html>
