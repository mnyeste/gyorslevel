<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

                <img src="../../images/logo_small_transp.gif" />
                
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

                Thanks for template to: http://www.code-sucks.com		

            </div>
            <!-- End Footer -->

        </div>
        <!-- End Wrapper -->
        
    </body>
</html>
