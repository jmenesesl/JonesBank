<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var = "language" value = "${not empty param.language ? param.language : not empty language ? language : 'es'}" scope="session"/>
<fmt:setLocale value = "${language}"/>
<fmt:setBundle basename="resources/language"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
.cuenta {
  font-size: 50px;
  color: #f6da74;
}
</style>

<script>
(function redireccionar() {
	setTimeout("location.href='registro.jsp'", 4000);})();
	

var seconds = 4;
function makeTimer() {
document.getElementById("cuenta").innerHTML = seconds;
seconds--;
	
}

setInterval(function() { makeTimer(); }, 1000);

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login KO</title>
</head>
<body>
<%@ include file="header.html" %>
<h1>Error</h1>

<h1>${mensaje}</h1>
<p1>Registrate</p1>
<p1>Serás redirigido en:<p1>
<div class="cuenta" id="cuenta"></div>
</body>
</html>