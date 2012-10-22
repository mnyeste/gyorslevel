<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="com.gyorslevel.dict" />

<%@page import="java.io.File" %>
<script type="text/javascript">
    window.onload = setupRefresh;

    function setupRefresh() {
        setTimeout("refreshPage();", 60000); // milliseconds
    }
    function refreshPage() {
        window.location = location.href;
    }
</script>

<h3 class="pageName"><fmt:message key="mainpage.pageName" /></h3>

<p>
    <c:choose>
        <c:when test="${empty messages}">
            <fmt:message key="mainpage.message.nounread" />
        </c:when>
        <c:otherwise>

        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th><fmt:message key="mainpage.table.mails.header.subject" /></th>
                        <th><fmt:message key="mainpage.table.mails.header.sender" /></th>
                        <th><fmt:message key="mainpage.table.mails.header.date" /></th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="message" items="${messages}" varStatus="lineInfo">
                        
                        <tr class="<c:out value="${(lineInfo.index % 2)==0?'alt':''}"/>">
                            <td><a href="/mail?id=${message.id}">${message.subject}</a></td>
                            <td>${message.from}</td>
                            <td>${message.sentDate}</td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

    </c:otherwise>
</c:choose>
</p>

<a class="actionButton" href="/logout"><fmt:message key="mainpage.button.logout" /></a>
