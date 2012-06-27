<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2 class="pageName">Admin</h2>

<table border="1" id="useremailstable">
    <thead>
        <tr>
            <th>User email</th>
        </tr>
    </thead>
    <tbody>

        <c:forEach var="userEmail" items="${userEmails}">
            <tr>
                <td>${userEmail}</td>
            </tr>
        </c:forEach>

    </tbody>
</table>
