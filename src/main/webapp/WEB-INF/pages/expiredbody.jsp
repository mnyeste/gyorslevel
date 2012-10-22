<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="com.gyorslevel.dict" />

<h2 class="pageName"><fmt:message key="expirepage.pageName" /></h2>
<h3><fmt:message key="expirepage.message.line1" /></h3>
<p><b><fmt:message key="expirepage.message.line2" />&nbsp;</b></p>
<a class="actionButton" href="/index">
    <fmt:message key="expirepage.button.back" />
</a>