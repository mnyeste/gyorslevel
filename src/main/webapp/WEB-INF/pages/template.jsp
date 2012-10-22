<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="com.gyorslevel.dict" />

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
        <script type="text/javascript">
            
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-35017747-1']);
            _gaq.push(['_trackPageview']);
            
            (function() {
                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();
            
        </script>
    </head>
    <body>

        <div id="outer">
            <div id="wrapper">
                <div id="menu">
                    <ul>
                        <li class="first"><a href="/index"><fmt:message key="template.menu.mails" />!</a></li>
                        <!--
                        <li><a href="/index">About</a></li>
                        <li class="last"><a href="/index">Contact</a></li>
                        -->
                    </ul>
                    <br class="clearfix" />
                </div>
                <div id="header">
                    <div id="logo">
                        <h1><a href="/index"><img src="../../images/logo_beta.png" /></a></h1>
                    </div>
                    <div id="banner">
                        <h1><a href="mailto:gyorslevel.info@gmail.com?subject=<fmt:message key="template.ad.contact.subject" />" target="_blank"><fmt:message key="template.ad.contact.line1" /></a></h1>
                        <h3><a href="mailto:gyorslevel.info@gmail.com?subject=<fmt:message key="template.ad.contact.subject" />" target="_blank"><fmt:message key="template.ad.contact.line2" /></a></h3>
                    </div>
                    <div id="facebook">
                        <iframe src="//www.facebook.com/plugins/like.php?href=http%3A%2F%2Fwww.facebook.com%2Fgyorslevel&amp;send=false&amp;layout=standard&amp;width=300&amp;show_faces=false&amp;action=like&amp;colorscheme=light&amp;font&amp;height=35&amp;appId=198050480711" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:300px; height:35px;" allowTransparency="true"></iframe>
                    </div>
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

                        <div class="box">
                            <br />                            
                            <script src="http://adchange.hu/show.php?g=r:735,f:f,c:d,t:7" type="text/javascript"></script>
                        </div>

                        <div class="box">
                            <IFRAME SCROLLING=NO FRAMEBORDER=0 MARGINwidth=0 NORESIZE MARGINheight=0 VSPACE=0 HSPACE=0 width=468 height=60 SRC='http://www.gyakorikerdesek.hu/gykbanner-51ab3ebbe93fc629f6b2c5896016c371-1'></IFRAME>
                        </div>
                            
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