<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>
        <h1>Gyors levél</h1>
        <h2>${email}</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Tárgy</th>
                    <th>Feladó</th>
                    <th>Dátum</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="/gyorslevel/mail">Példa 1</a></td>
                    <td>Géza</td>
                    <td>2012.05.19</td>
                </tr>
                <tr>
                    <td><a href="/gyorslevel/mail">Példa 1</a></td>
                    <td>Lajos</td>
                    <td>2012.05.21</td>
                </tr>
                <tr>
                    <td><a href="/gyorslevel/mail">Példa 1</a></td>
                    <td>Hugó</td>
                    <td>2012.06.30</td>
                </tr>
            </tbody>
        </table>
        
        <a href="/gyorslevel/logout">Kilépés</a>
        
    </body>    
</html>
