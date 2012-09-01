<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<h4><b>Tárgy: </b>${openedMessage.subject}</h4>
<h4><b>Feladó: </b>${openedMessage.from}</h4>

<div>
    <iframe src="mailmessage?id=${openedMessage.id}" class="mailframe"></iframe>
</div>

<a class="actionButton" href="/main">Vissza</a>