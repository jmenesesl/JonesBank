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
<meta charset="UTF-8">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jones JSP</title>

<style>
  .formulario {
    position: relative;
    width: 40%;
    height: 70%;
    padding-left: 2%;
  }
  
  .footer {
      position: fixed;
      left: 0;
      bottom: 0;
      width: 100%;
      text-align: center;
  }  
  
</style>

</head>
<body>
<%@ include file="header.html" %>
<%@ include file="jones.html" %>

<div class="formulario">
<form action="LoginServlet" method="post">
    <div class="container">
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
            <h4><fmt:message key="welcome"/></h4>
            <p1><fmt:message key="user"/></p1>
            <input type="text" id="userName" class="form-control input-sm chat-input" placeholder="username" name="dni"></input>
            </br>
            <p1><fmt:message key="password"/></p1>
            <input type="password" id="userPassword" class="form-control input-sm chat-input" placeholder="password" name="pass"></input>
            </br>
            <div class="wrapper">
            <span class="group-btn">     
                <a href="#" class="btn btn-primary btn-md"><button type="submit" style=" background-color: transparent; border-color: transparent; cursor: default;" ><fmt:message key="submit"/></button> <i class="fa fa-sign-in"></i></a>
            </span>
            </div>
            </div>
        
        </div>
    </div>
</div>
</form>






</div>
  <h1>${saludo}</h1>
</body>
</html>