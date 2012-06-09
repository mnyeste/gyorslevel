<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="60;url=main">
        <title>${title}</title>
    </head>
    <body>    
        <img src="../../images/logo_small.png" />
        <h2>${email}</h2>

        <p>
    <c:choose>
        <c:when test="${empty messages}">
            <c:out value="Nincs olvasatlan levél" />
        </c:when>
        <c:otherwise>

            <table border="1">
                <thead>
                    <tr>
                        <th>Tárgy</th>
                        <th>Feladó</th>
                        <th>Dátum</th>
                    </tr>
                </thead>
                <tbody>

                <c:forEach var="message" items="${messages}">
                    <tr>
                        <td><a href="/mail?id=${message.id}">${message.subject}</a></td>
                        <td>${message.from}</td>
                        <td>${message.sentDate}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </c:otherwise>
    </c:choose>
</p>

<a href="/logout">Kilépés</a>

</body>    
</html>
