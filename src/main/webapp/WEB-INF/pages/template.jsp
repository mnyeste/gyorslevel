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

                    Hirdetmények

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

                    Reklámok

                </div>
                <!-- End Right Column -->

            </div>	   
            <!-- End Faux Columns --> 

            <!-- Begin Footer -->
            <div id="footer">

                Thanks for template to: http://www.code-sucks.com |  http://quackit.com	

            </div>
            <!-- End Footer -->

        </div>
        <!-- End Wrapper -->

    </body>
</html>
