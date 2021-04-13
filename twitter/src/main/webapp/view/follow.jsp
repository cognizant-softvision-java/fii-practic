<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <title>${userID}</title>
</head>
<body>
    <div align="center" style="margin-top: 50px;">
        <h2>Welcome !</h2>
        <form:form method="POST" modelAttribute="followForm">
            <h2>Choose a user ID to follow</h2>

                <spring:bind path="followeeId">
                <div>
                    <form:input type="text" path="followeeId" placeholder="ID to follow" autofocus="true"></form:input>
                </div>
                </spring:bind>

            <button type="submit">Submit</button>
        </form:form>
    </div>
</body>
</html>