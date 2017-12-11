<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : 'es'}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources/language" />

<%@ page import="beans.Cliente"%>


<%
	Cliente c = null;
	String user = null;
	String dni = null;
	String sessionID = null;

	if (session.getAttribute("clientSession") == null) {
		response.sendRedirect("jones.jsp");

	} else {
		c = (Cliente) session.getAttribute("clientSession");
		user = c.getNombre();
		dni = c.getDni();
	}
%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.Account"%>
<%@ page import="dao.AccountDAO"%>
<%@ page import="dao.Transaccion"%>
<%@ page import="dao.TransaccionesDAO"%>

<%
	ArrayList<Transaccion> transacciones = (ArrayList) TransaccionesDAO.listaTransacciones(c.getDni());
%>

<%
	ArrayList<Account> cuentasCliente = (ArrayList) AccountDAO.getAccounts(c.getDni());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<script type="text/javascript">
	function addTransaction() {
		alert("añadir");
		document.getElementById("newTransaction").style.display = "block";

	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista transacciones</title>
</head>
<body>
	<%@ include file="header.html"%>

	<h1>
		<fmt:message key="WelcomeTransactions" />
	</h1>

	<h1>${mensaje}</h1>


	<div class="wrap">
		<a onClick="addTransaction()" class="button"><fmt:message
				key="newTransaction" /></a>
	</div>

	<br>
	<br>
	<!-- function addTransacction() ---- >var codigo = '<form action="NuevaTransaccionServlet" id="agregarTransaccion" method="get"><select name="ibanOrigen"><%for (int i = 0; i < cuentasCliente.size(); i++) {%><option><%=((Account) cuentasCliente.get(i)).getIban()%></option><%}%></select><input type="text" value="ibanDestino" name="ibanDestino"/><input type="text" value="Cantidad" name="cantidad"/><button type="submit">Realizar</button></form>';
	document.getElementById("contenido").innerHTML = codigo; -->

	<div class="container">
		<div class="row col-md-6 col-md-offset-2 custyle">
			<table class="table table-striped custab"
				style="background-color: white">

				<thead>
					<tr>
						<th><fmt:message key="from" /></th>
						<th><fmt:message key="destino" /></th>
						<th><fmt:message key="howMuch" /></th>
						<!--  <th class="text-center">Opción</th>  -->
					</tr>
				</thead>
				<%
					for (int i = 0; i < transacciones.size(); i++) {
				%>
				<form action="EliminaCuentaServlet">
					<tr>
						<td><%=((Transaccion) transacciones.get(i)).getOrigen()%></td>
						<td><%=((Transaccion) transacciones.get(i)).getDestino()%></td>
						<td><%=((Transaccion) transacciones.get(i)).getCantidad()%>
							&nbsp€</td>
					</tr>
				</form>
				<%
					}
				%>
			</table>
		</div>
	</div>

	<div class="newTransaction" id="newTransaction"
		style="display: none; position: relative; left: 30%;">
		<div class="row">
			<div class="yy" id="contenido">
				<div class="span3 well" style="width: 15%; height: 40%;">
					<legend><fmt:message key="newTransaction"/></legend>
					<form accept-charset="UTF-8" action="NuevaTransaccionServlet"
						method="post">
						<select name="ibanOrigen">
							<%
								for (int i = 0; i < cuentasCliente.size(); i++) {
							%><option><%=((Account) cuentasCliente.get(i)).getIban()%></option>
							<%
								}
							%>
						</select><br>
						<br> <input class="span3" name="ibanDestino"
							placeholder="CC Destino" type="text"><br>
						<br> <input class="span3" name="cantidad"
							placeholder="cantidad" type="text"><br>
						<br>
						<button class="btn btn-success" type="submit"><fmt:message key="makeTransaction"/></button>
					</form>
				</div>

			</div>
		</div>
	</div>


<div style="position: relative; left: 45%;">
<form action="TransaccionesServlet" method="get">
<input type="submit" value="Listar"/></form>
</div>

<div style="position: relative; left: 20%;">
<form action="DescargaTransaccionesServlet" method="post">
<input type="submit" value="Descargar"/></form>
</div>

</body>
</html>