<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="E-mail regisztráció nélkül" />
        <meta name="keywords" content="e-mail, mail, instant, instant mail, levél, gyors, gyorslevél, gyorslevel" />
        <title>${title}</title>
        <link href="http://fonts.googleapis.com/css?family=Abel" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="../../actionbutton.css" />
        <link rel="stylesheet" type="text/css" href="../../tabledesign.css" />
        <link rel="stylesheet" type="text/css" href="../../prolificstyle.css" />
        <link rel="stylesheet" type="text/css" href="../../iframe.css" />
        <script src="../../clipboard.js"></script>
    </head>
    <body>
        
        <div id="fb-root"></div>
        <script>(function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
            js = d.createElement(s); js.id = id;
            js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=198050480711";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));</script>
            
        <div id="outer">
            <div id="wrapper">
                <div id="menu">
                    <ul>
                        <li class="first"><a href="/index">Levelek!</a></li>
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
                    <div id="facebook">
                        <form action="" method="post">
                            <div>
                                <div class="fb-like" data-href="http://gyorslevel.info/" data-send="true" data-width="225" data-show-faces="true"></div>
                            </div>
                        </form>
                    </div>
                </div>
                <div id="page">
                    <div id="sidebar">
                        <div class="box">
                            <h3>Mire való a gyorslevél?</h3>
                            <p>
                                <ul class="list">
                                    <li>Ideiglenes e-mail, regisztráció nélkül!</li>
                                    <li>Fogadj leveleket majd felejtsd el!</li>
                                    <li>Nincs spam!</li>
                                </ul>
                            </p>
                        </div>

                        <div class="box">
                            <h3>Segíts nekünk!</h3>
                            <img class="alignleft" src="images/support.png" alt="" height="96" width="96"/>
                            <p>
                                <b>Hibát találtál?</b> Kérünk írd meg nekünk ide:
                                <a href="https://docs.google.com/spreadsheet/viewform?formkey=dGE1bkQ4OEN0M2czRldkOFVIR1ZBUEE6MQ#gid=0" class="link">
                                    Gyorslevél hibabejelentés
                                </a>
                            </p>                                
                        </div>

                        <!--
                        <div class="box">
                            <h3>Mire való a gyorslevél?</h3>
                            <ul class="list">
                                <li class="first"><a href="#">Aliquam sapien aliquam</a></li>
                                <li><a href="#">Ipsum ipsum ac venenatis</a></li>
                                <li><a href="#">Dolor ut non etiam</a></li>
                                <li><a href="#">Tempus ante integer vivamus</a></li>
                                <li><a href="#">Montes interdum sed parturient</a></li>
                                <li><a href="#">Velit malesuada accumsan</a></li>
                                <li><a href="#">Amet suscipit ornare tempor</a></li>
                                <li class="last"><a href="#">Ante mauris sodales</a></li>
                            </ul>
                        </div>
                        -->
                    </div>
                    <div id="content">

                        <c:if test="${not empty expiration.userEmail}">

                            <div class="box">
                                <h3>Az Ön gyorslevél címe:&nbsp;</h3>
                                <p>${expiration.userEmail}</p>
                                <a class="actionButton" onclick="javascript:copy_to_clipboard('Hello')">Vágólapra másolás (csak IE!)</a>
                            </div>

                        </c:if>

                        <div class="box">

                            <jsp:include page="${bodyPage}.jsp" />

                        </div>

                        <div class="box">
                            <h3>Így használd a szolgáltatásunk</h3>
                            <img class="alignleft" src="images/howto.png" alt="" height="96" width="96"/>                                
                            <p>
                                <ol>
                                    <li class="first">Kattints az "Új fiók" gombra</li>                                        
                                    <li>Jegyezd meg (másold ki) a generált gyorslevél címed</li>
                                    <li>Használd ezt például egy regisztrációhoz</li>
                                    <li class="last">Várj amíg a leveled megérkezik</li>
                                </ol>
                            </p>
                        </div>
                            
                        <br class="clearfix" />
                        
                    </div>
                    <br class="clearfix" />
                </div>
            </div>
            <div id="footer">
                &copy; 2012 gyorslevel | Minden jog fenntartva! | Design by <a href="http://www.freecsstemplates.org/">FCT</a> | Images by <a href="http://fotogrph.com/">Fotogrph</a> | Version: <c:out value="${sessionScope.applicationVersion}"/>
            </div>
        </div>
    </body>
</html>