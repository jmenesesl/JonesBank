<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var = "language" value = "${not empty param.language ? param.language : not empty language ? language : 'es'}" scope="session"/>
<fmt:setLocale value = "${language}"/>
<fmt:setBundle basename="resources/language"/>

    
   <%@ page import="beans.Cliente" %>

<%
Cliente c = null;
String user = null;
String dni = null;
String sessionID = null;

if(session.getAttribute("clientSession") == null) {
	response.sendRedirect("jones.jsp");
	
} else {
	c = (Cliente) session.getAttribute("clientSession");
	user = c.getNombre();
	dni = c.getDni();
}

// Obtenemos las cookies y si esta nuestra cookie obtenemos el valor
Cookie[] cookies = request.getCookies();
if (cookies != null ) {
	
	for (Cookie cookie: cookies) {
		if(cookie.getName().equals("JSESSIONID")) {
			sessionID = cookie.getValue();
		}
	}
} else {
	sessionID="NO hay galletas";
}

%>
<%@ include file="header.html" %>


<style>
.wrap {
  position: absolute;
  top: 25%;
  left: 50%;
  margin-top: -86px;
  margin-left: -89px;
  text-align: center;
}

a {
  -webkit-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
  -moz-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
  -ms-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
  -o-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
  transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
  display: inline;
  margin: 20px auto;
  max-width: 180px;
  text-decoration: none;
  border-radius: 4px;
  padding: 20px 30px;
}

a.button {
  color: rgba(30, 22, 54, 0.6);
  box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;
}

a.button:hover {
  color: rgba(255, 255, 255, 0.85);
  box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;
}

a.button2 {
  color: rgba(30, 22, 54, 0.6);
  box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;
}

a.button2:hover {
  color: rgba(255, 255, 255, 0.85);
  box-shadow: rgba(30, 22, 54, 0.7) 0 80px 0px 2px inset;
}
</style>



<h1>${saludo}</h1>

<h3>hola <%=user %> con dni <%=dni %> con id de session <%=sessionID %></h3>
 <button onClick="window.location='listaCuentas.jsp';" value="listacuentas" ><fmt:message key="seeAccounts"/></button>
<button onClick="window.location='listaTransacciones.jsp';" value="listatransacciones" ><fmt:message key="seeTransactions"/></button>


<!-- <div class="wrap">
  <a onClick="window.location='listaCuentas.jsp" class="button"><fmt:message key="seeAccounts"/></a>
  <a onClick="window.location='listaTransacciones.jsp" class="button2"><fmt:message key="seeTransactions"/></a>
</div>
 -->
<!-- 
<form action="<%=response.encodeURL("PerfilServlet") %>" method="post">
<input type="submit" value="perfil">
</form>


<a href="<%=response.encodeURL("LogoutServlet") %>" >Logout</a>
 -->
<form action="LogoutServlet" method="POST">
<button Type="Submit">Logout</button>
</form>


<br>
<br>
<br>
<br>

<h1><fmt:message key="modifyData"/></h1>
<button onclick="modifica_datos()"><fmt:message key="clickHere"/></button>

<script type="text/javascript">

function modifica_datos() {
	alert("modificaremos tus datos");
	var webContent = document.body.innerHTML;
	
	var formulario_datos = "";
	formulario_datos += "<br><br><h1>_______________________________________</h1><h1>A continuación introduce tus datos correctamente:</h1><br>";

	formulario_datos += "<form action='ModificaServlet' method='post'><table>";
	
	formulario_datos += '<tr><td><fmt:message key="name"/>: </td><td><input type="text" id="nombre" name="nombre" value="<%=user %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="surname"/>: </td><td><input type="text" id="apellidos" name="apellidos" value="<%=c.getApellidos() %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="id"/>: </td><td><input type="text" id="dni" name="dni" value="<%=c.getDni() %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="previousId"/>: </td><td><input type="text" id="dni_anterior" name="dni_anterior" value="<%=c.getDni() %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="sex"/>: </td><td><input type="text" id="sexo" name="sexo" value="<%=c.getSexo() %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="dateOfBirth"/>: </td><td><input type="text" id="fecha_nacimiento" name="fecha_nacimiento" value="<%=c.getFechaNacimiento() %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="address"/>: </td><td><input type="text" id="direccion" name="direccion" value="<%=c.getDireccion() %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="phone"/>: </td><td><input type="text" id="telefono" name="telefono" value="<%=c.getTelefono() %>"></input></td></tr>';
	formulario_datos += '<tr><td><fmt:message key="password"/>: </td><td><input type="password" id="contrasena" name="contrasena" value="" required></input></td></tr>';
	formulario_datos += '</table>';
	formulario_datos += '<button type="submit"><fmt:message key="updateData"/></button><br><br>';



	
	formulario_datos += "</form>";
	
	webContent += formulario_datos;
	
	document.body.innerHTML = webContent;
}



</script>