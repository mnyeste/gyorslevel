<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/pages/taglib.jsp" %>

<h4><b><fmt:message key="mailpage.message.subject" />&nbsp;</b>${openedMessage.subject}</h4>
<h4><b><fmt:message key="mailpage.message.sender" />&nbsp;</b>${openedMessage.from}</h4>

<div>
    <iframe src="mailmessage?id=${openedMessage.id}" class="mailframe"></iframe>
</div>

<h3><fmt:message key="mailpage.message.attachments" /></h3>
<p>
    <c:if test="${not empty openedMessage.attachedFiles}">

        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th><fmt:message key="mailpage.table.files.header.filename" /></th>
                        <th><fmt:message key="mailpage.table.files.header.link" /></th>
                    </tr>
                </thead>
                <tbody>

                <c:forEach var="attachedFile" items="${openedMessage.attachedFiles}" varStatus="lineInfo">
                    <tr class="<c:out value="${(lineInfo.index % 2)==0?'alt':''}"/>">
                        <td>${attachedFile.key}</td>
                        <td><a href="${attachedFile.value}" target="_blank"><fmt:message key="mailpage.table.files.message.open" /></a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </c:if>
</p>

<a class="actionButton" href="/main"><fmt:message key="mailpage.button.back" /></a>