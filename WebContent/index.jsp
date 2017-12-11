<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var = "language" value = "${not empty param.language ? param.language : not empty language ? language : 'es'}" scope="session"/>
<fmt:setLocale value = "${language}"/>
<fmt:setBundle basename="resources/language"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
img {
  filter: gray; /* IE6-9 */
  -webkit-filter: grayscale(1); /* Google Chrome, Safari 6+ & Opera 15+ */
    -webkit-box-shadow: 0px 2px 6px 2px rgba(0,0,0,0.75);
    -moz-box-shadow: 0px 2px 6px 2px rgba(0,0,0,0.75);
    box-shadow: 0px 2px 6px 2px rgba(0,0,0,0.75);
    margin-bottom:20px;
     height:300px;
     width:200px;
}

img:hover {
  filter: none; /* IE6-9 */
  -webkit-filter: grayscale(0); /* Google Chrome, Safari 6+ & Opera 15+ */
 
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
<%@ include file="header.html" %>
<!--
<i><form action="MultiServlet"><button type="submit">Localizar Idioma en servlet</button></form></i>
-->

<h1><fmt:message key="welcome"/></h1>
<div style="width:35%; position: relative; left:1%; text-align:right">
<p1><fmt:message key="welcomeText"/></p1>
</div>


<form action="MultiServlet" method="get">
<!--
<i><button type="submit" value="dale">dale</button></i>
-->

</form>
<br>
<div class="container">
<h1><fmt:message key="weAreIn"/></h1>
	<div class="row">
		<div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive" src="images/barcelona.jpg" /></div>
        <div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive" src="images/madrid.jpg" /></div>
        <div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive" src="images/bogota.jpg" /></div>
        <div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive" src="images/ny.jpg" /></div>
    	<div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive"src="images/miami.jpg" /></div>
        <div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive" src="images/nimes.jpg" /></div>
	    <div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive"src="images/beijing.jpg" /></div>
        <div class="col-md-3 col-sm-4 col-xs-6"><img class="img-responsive" src="images/zurich.jpg" /></div>
    </div>
</div>






</body>
</html>