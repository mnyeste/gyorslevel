<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<h2>${expiration.userEmail}</h2>

<h3><b>Tárgy: </b></h3>${openedMessage.subject}

<p>
    ${openedMessage.message}
</p>

<a href="/main">Vissza</a>