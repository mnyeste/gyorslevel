<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<h2><b>Tárgy: </b>${openedMessage.subject}</h2>
<h3><b>Feladó: </b>${openedMessage.from}</h3>

<p>
    ${openedMessage.message}
</p>

<a class="actionButton" href="/main">Vissza</a>