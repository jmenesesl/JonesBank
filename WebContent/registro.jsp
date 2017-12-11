<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ page import="beans.Cliente" %>
<%
Cliente c = null;
String user = null;
String dni = null;
String sessionID = null;

if(session.getAttribute("clientSession") != null) {
	response.sendRedirect("loginok.jsp");
	
}
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var = "language" value = "${not empty param.language ? param.language : not empty language ? language : 'es'}" scope="session"/>
<fmt:setLocale value = "${language}"/>
<fmt:setBundle basename="resources/language"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>registro</title>
</head>
<body>
<%@ include file="header.html" %>
<%@ include file="registro.html" %>

<form class="form-horizontal" action='RegistroServlet' method="POST">
  <fieldset>
    <div id="legend">
      <legend class=""><fmt:message key="register"/></legend>
    </div>
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username"><fmt:message key="id"/></label>
      <div class="controls">
        <input type="text" id="dni" name="dni" placeholder="" class="input-xlarge">
      </div>
    </div>
    
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username"><fmt:message key="name"/></label>
      <div class="controls">
        <input type="text" id="nombre" name="nombre" placeholder="" class="input-xlarge">
      </div>
    </div>
 
 <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username"><fmt:message key="surname"/></label>
      <div class="controls">
        <input type="text" id="apellidos" name="apellidos" placeholder="" class="input-xlarge">
      </div>
    </div>
    
    
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username"><fmt:message key="dateOfBirth"/></label>
      <div class="controls">
        <input type="text" id="fecha_de_nacimiento" name="fecha_de_nacimiento" placeholder="dd/mm/yyyy" class="input-xlarge">
      </div>
    </div>
    
    
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username"><fmt:message key="sex"/></label>
      <div class="controls">
        <input type="text" id="sexo" name="sexo" placeholder="h/m" class="input-xlarge">
      </div>
    </div>
    
    
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username"><fmt:message key="address"/></label>
      <div class="controls">
        <input type="text" id="direccion" name="direccion" placeholder="" class="input-xlarge">
      </div>
    </div>
    
        <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username"><fmt:message key="phone"/></label>
      <div class="controls">
        <input type="text" id="telefono" name="telefono" placeholder="" class="input-xlarge">
      </div>
    </div>
    
    
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="password"><fmt:message key="password"/></label>
      <div class="controls">
        <input type="password" id="password" name="contrasena" placeholder="" class="input-xlarge" required/>
      </div>
    </div> 
    <div class="control-group">
    <br>
    <br>
      <!-- Button -->
      <div class="controls">
        <button class="btn btn-success">¡<fmt:message key="register"/> <fmt:message key="now"/>!</button>
      </div>
    </div>
  </fieldset>
</form>

</body>
</html>