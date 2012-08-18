<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
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
                        <li class="first"><a href="/index">Home</a></li>
                        <li><a href="/index">About</a></li>
                        <li class="last"><a href="/index">Contact</a></li>
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
                                Blabla, blabla, blabla, blabla, blabla, blabla, blabla
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

                            <!--<img class="alignleft" src="images/pic01.jpg" alt="" />
                            <p>
                                This is <strong>Prolific</strong>, a free, fully standards-compliant CSS template by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>. The images used in this template are from <a href="http://fotogrph.com/">Fotogrph</a>. This free template is released under a <a href="http://creativecommons.org/licenses/by/3.0/">Creative Commons Attributions 3.0</a> license, so you are pretty much free to do whatever you want with it (even use it commercially) provided you keep the footer credits intact. Aside from that, have fun with it :)
                            </p>-->
                            
                        </div>
                                
                        <!--
                        <div id="col1" class="box">
                                <h3>Aliquet viverra semper</h3>
                                <p>
                                        Consequat condimentum praesent nullam eleifend morbi purus suspendisse. Accumsan luctus risus libero
                                        lorem ipsum dolor.
                                </p>
                                <ul class="list">
                                        <li class="first"><a href="#">Tristique libero magna porta</a></li>
                                        <li><a href="#">Donec sed dolor quis</a></li>
                                        <li><a href="#">Integer mattis placerat mattis</a></li>
                                        <li><a href="#">Integer congue sociis ultricies</a></li>
                                        <li class="last"><a href="#">Porta at suspendisse curabitur</a></li>
                                </ul>
                        </div>
                        <div id="col2" class="box">
                                <h3>Quam vestibulum</h3>
                                <p>
                                        Semper leo sem primis tempor. Ipsum morbi euismod non morbi phasellus tempus etiam nullam dolor
                                        consequat sed curae.
                                </p>
                                <ul class="list">
                                        <li class="first"><a href="#">Euismod vivamus aliquet sagittis</a></li>
                                        <li><a href="#">Montes interdum aenean parturient</a></li>
                                        <li><a href="#">Velit malesuada tristique accumsan</a></li>
                                        <li><a href="#">Amet suscipit ornare tempor</a></li>
                                        <li class="last"><a href="#">Ligula nibh curae pulvinar</a></li>
                                </ul>
                        </div>
                        -->
                        <br class="clearfix" />
                    </div>
                    <br class="clearfix" />
                </div>
            </div>
            <div id="footer">
                &copy; 2012 Untitled | Design by <a href="http://www.freecsstemplates.org/">FCT</a> | Images by <a href="http://fotogrph.com/">Fotogrph</a> | Version: <c:out value="${sessionScope.applicationVersion}"/>
            </div>
        </div>
    </body>
</html>