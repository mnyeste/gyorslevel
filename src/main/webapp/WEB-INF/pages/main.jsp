<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>    
        <h1>Gyors levél</h1>
        <h2>${email}</h2>

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
                    <td>${message}</td>
                    <td>${message}</td>
                    <td>${message}</td>
                </tr>
            </c:forEach>

            
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/logout">Kilépés</a>

</body>    
</html>
