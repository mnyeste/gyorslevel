<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h4><b>Tárgy: </b>${openedMessage.subject}</h4>
<h4><b>Feladó: </b>${openedMessage.from}</h4>

<div>
    <iframe src="mailmessage?id=${openedMessage.id}" class="mailframe"></iframe>
</div>

<h3>Csatolmányok</h3>
<p>
    <c:if test="${not empty openedMessage.attachedFiles}">

        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th>Fájlnév</th>
                        <th>Link</th>
                    </tr>
                </thead>
                <tbody>

                <c:forEach var="attachedFile" items="${openedMessage.attachedFiles}" varStatus="lineInfo">
                    <tr class="<c:out value="${(lineInfo.index % 2)==0?'alt':''}"/>">
                        <td>${attachedFile.key}</td>
                        <td><a href="${attachedFile.value}" target="_blank">Megnyitás / Letöltés</a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </c:if>
</p>

<a class="actionButton" href="/main">Vissza</a>