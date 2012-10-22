<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="com.gyorslevel.dict" />

<h3 class="pageName">
    <fmt:message key="indexpage.pageName" />
</h3>

<a class="actionButton" href="/main" name="login">
    <fmt:message key="indexpage.button.login" />
</a>