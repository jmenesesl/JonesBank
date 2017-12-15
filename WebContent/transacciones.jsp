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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transacciones</title>
</head>
<body>
	<%@ include file="header.html"%>
	<h1>
		<fmt:message key="WelcomeTransactions" />
	</h1>
	<%
		String spageid = request.getParameter("page") == null ? "1" : request.getParameter("page");
	
		int size = 3;
		List<Transaccion> lTrans = (List<Transaccion>) request.getSession().getAttribute("lista");
		if (lTrans.size() == 1) {
			size = 1;
		} else if (lTrans.size() == 2) {
			size = 2;
		} else {
			size = 3;
		}
	%>


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
					int pagina = Integer.parseInt(spageid);
					pagina = pagina > (lTrans.size() / size) ? 5 : pagina;
					int inici = pagina == 1 ? pagina - 1 : (pagina - 1) * size;
					int fi = pagina * size;
					List<Transaccion> sList = lTrans.subList(inici, fi);
					for (Transaccion t : sList) {
				%>
				<form action="EliminaCuentaServlet">
					<tr>
						<td><%=t.getOrigen()%></td>
						<td><%=t.getDestino()%></td>
						<td><%=t.getCantidad()%> &nbsp€</td>
					</tr>
				</form>
				<%
					}
				%>

			</table>
			<%
				int n = 1;

				//lTrans.size()/size

				for (n = 1; n <= lTrans.size() / size; n++) {
			%>
			<a href="transacciones.jsp?page=<%=n%>"><%=n%></a>
			<%
				}
			%>
		</div>
	</div>


</body>
</html>