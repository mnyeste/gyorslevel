<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="../../main.css" />
    </head>
    <body>

        <!-- Begin Wrapper -->
        <div id="wrapper">

            <!-- Begin Header -->
            <div id="header">

                <img src="../../images/plain_logo.png" />

            </div>
            <!-- End Header -->

            <!-- Begin Faux Columns -->
            <div id="faux">

                <!-- Begin Left Column -->
                <div id="leftcolumn">

                    <p>
                        <b>Hirdetmények</b></br>
                        Oldalunk tesztelés alatt!
                    </p>

                    <p>

                        Látogatottság:</br>

                        <!-- Start counter code -->

                        <a href="http://xyz.freelogs.com/stats/d/dave00ster/" target="_top"><img border="0" alt="web counter code" src="http://xyz.freelogs.com/counter/index.php?u=dave00ster&s=ainv" ALIGN="middle" HSPACE="4" VSPACE="2"></a><script src=http://xyz.freelogs.com/counter/script.php?u=dave00ster></script>
                        <br><a style="font-size:12" href="http://freelogs.com/" target="_top"><font style="font-size:12" color="#666666">web counter code</font></a>

                        <!-- End counter code -->

                    </p>

                </div>
                <!-- End Left Column -->

                <!-- Begin Content Column -->
                <div id="content">

                    <c:if test="${not empty expiration.userEmail}">

                        <div class="highlighted">
                            <h3><b>Az Ön gyorslevél címe:</b>&nbsp;</h3>
                            ${expiration.userEmail}
                        </div>

                    </c:if>

                    <jsp:include page="${bodyPage}.jsp" />

                </div>
                <!-- End Content Column -->



                <!-- Begin Right Column -->
                <div id="rightcolumn">

                    <img src="http://www.deviantart.com/download/72078315/Heineken_Advertisement_by_Stan88.jpg" width="170"/>

                </div>
                <!-- End Right Column -->

            </div>	   
            <!-- End Faux Columns --> 

            <!-- Begin Footer -->
            <div id="footer">

                Version: <c:out value="${sessionScope.applicationVersion}"/>
                
                | Thanks for template to: http://www.code-sucks.com |  http://quackit.com	

            </div>
            <!-- End Footer -->

        </div>
        <!-- End Wrapper -->

    </body>
</html>
