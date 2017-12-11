<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var = "language" value = "${not empty param.language ? param.language : not empty language ? language : 'es'}" scope="session"/>
<fmt:setLocale value = "${language}"/>
<fmt:setBundle basename="resources/language"/>



<%@ include file="header.html" %>
<%@ page import="beans.Cliente" %>
<%
Cliente c = null;
String user = null;
String dni = null;
String sessionID = null;

if(session.getAttribute("clientSession") == null) {
	response.sendRedirect("jones.jsp");
	
}
%>


<%@ include file="bienvenido.html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bienvenidos</title>

</head>
<body>

</body>
</html>