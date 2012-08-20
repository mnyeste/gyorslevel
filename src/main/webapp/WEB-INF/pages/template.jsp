<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="E-mail regisztráció nélkül" />
        <meta name="keywords" content="e-mail, mail, instant, instant mail" />
        <title>${title}</title>
        <link href="http://fonts.googleapis.com/css?family=Abel" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="../../actionbutton.css" />
        <link rel="stylesheet" type="text/css" href="../../tabledesign.css" />
        <link rel="stylesheet" type="text/css" href="../../prolificstyle.css" />
    </head>
    <body>
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
                        <h1><a href="/index">${title}</a></h1>
                    </div>
                    <div id="search">
                        <form action="" method="post">
                            <div>
                                <input class="form-text" name="search" size="32" maxlength="64" />	
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
                                <a href="mailto:gyorslevel.info@gmail.com?Subject=Gyorslevél hibabejelentés">
                                    gyorslevel.info@gmail.com
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