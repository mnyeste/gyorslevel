<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/pages/taglib.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="<fmt:message key="template.meta.description" />" />
        <meta name="keywords" content="<fmt:message key="template.meta.keywords" />" />
        <title><fmt:message key="template.title" /></title>
        <link href="http://fonts.googleapis.com/css?family=Abel" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="../../actionbutton.css" />
        <link rel="stylesheet" type="text/css" href="../../tabledesign.css" />
        <link rel="stylesheet" type="text/css" href="../../prolificstyle.css" />
        <link rel="stylesheet" type="text/css" href="../../iframe.css" />
        <script src="../../clipboard.js"></script>

        <jsp:include page="analytics/${sessionScope.profile.domain}analytics.jsp" />
        
    </head>
    <body>

        <div id="outer">
            <div id="wrapper">
                <div id="menu">
                    <ul>
                        <li class="first"><a href="/index"><fmt:message key="template.menu.mails" />!</a></li>
                    </ul>
                    <br class="clearfix" />
                </div>
                <div id="header">
                    <div id="logo">
                        <h1>
                            <a href="/index">
                                <c:out value="<img src='../../images/logo_beta_${sessionScope.profile.lang}.png' />" escapeXml="false" />
                            </a>
                        </h1>
                    </div>
                    <div id="banner">
                        <h1><a href="mailto:gyorslevel.info@gmail.com?subject=<fmt:message key="template.ad.contact.subject" />" target="_blank"><fmt:message key="template.ad.contact.line1" /></a></h1>
                        <h3><a href="mailto:gyorslevel.info@gmail.com?subject=<fmt:message key="template.ad.contact.subject" />" target="_blank"><fmt:message key="template.ad.contact.line2" /></a></h3>
                    </div>

                    <jsp:include page="facebook/${sessionScope.profile.domain}fb.jsp" />
                    
                </div>
                <div id="page">
                    <div id="sidebar">
                        <div class="box">
                            <h3><fmt:message key="template.box.faq.title" /></h3>
                            <p>
                                <ul class="list">
                                    <li><fmt:message key="template.box.faq.line1" /></li>
                                    <li><b><fmt:message key="template.box.faq.line2" /></b></li>
                                    <li><fmt:message key="template.box.faq.line3" /></li>
                                    <li><fmt:message key="template.box.faq.line4" /></li>
                                </ul>
                            </p>
                        </div>

                        <div class="box">
                            <h3><fmt:message key="template.box.help.title" /></h3>                            
                            <table>
                                <tr>
                                    <td>
                                        <img src="images/support.png" alt="" height="75" width="75"/>
                                    </td>
                                    <td>
                                        <p>
                                            <b><fmt:message key="template.box.help.line1" /></b><br />
                                            <fmt:message key="template.box.help.line2" />:<br />
                                            <a href="https://docs.google.com/spreadsheet/viewform?formkey=dGE1bkQ4OEN0M2czRldkOFVIR1ZBUEE6MQ#gid=0" class="link"  target="_blank">
                                                <fmt:message key="template.box.help.line3" />
                                            </a>
                                        </p>                                
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <div class="box">
                            <h3><fmt:message key="template.box.howto.title" /></h3>

                            <p>
                                <ul class="list">
                                    <li><fmt:message key="template.box.howto.line1" /></li>                                        
                                    <li><fmt:message key="template.box.howto.line2" /></li>                                        
                                    <li><fmt:message key="template.box.howto.line3" /></li>                                        
                                    <li><fmt:message key="template.box.howto.line4" /></li>                                        
                                </ul>
                            </p>                             

                        </div>

                    </div>
                    <div id="content">

                        <c:if test="${not empty expiration.userEmail}">

                            <div class="box">
                                <h3><fmt:message key="template.mailaddress.title" />:&nbsp;</h3>
                                <p>${expiration.userEmail}</p>
                                <a class="actionButton" onclick="javascript:copy_to_clipboard('')"><fmt:message key="template.mailaddress.copy" /></a>
                            </div>

                        </c:if>

                        <div class="box">

                            <jsp:include page="${bodyPage}.jsp" />

                        </div>
                            
                        <jsp:include page="ads/${sessionScope.profile.domain}ads.jsp" />

                        <br class="clearfix" />

                    </div>
                    <br class="clearfix" />
                </div>
            </div>
            <div id="footer">
                &copy; 2012 gyorslevel | <fmt:message key="template.footer.allrightsreserved" /> | Design by <a href="http://www.freecsstemplates.org/">FCT</a> | Images by <a href="http://fotogrph.com/">Fotogrph</a> | Version: <c:out value="${sessionScope.applicationVersion}"/>
            </div>
        </div>
    </body>
</html>