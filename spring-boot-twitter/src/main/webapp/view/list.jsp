<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head></head>
<body>
    <h1>Hello there !</h1>
    <h2>${tableName}</h2>
    <div>
        <c:if test="${users != null}">
            <table>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Number</th>
                <th>Email</th>
                <th>Username</th>
                <th>Password</th>
                <th>Following</th>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.userName}</td>
                        <td>${user.pass}</td>
                        <td>${user.following}
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
    <div>
        <br/>
        <a href="registration">Click here to create an user</a>
    </div>
</body>
</html>