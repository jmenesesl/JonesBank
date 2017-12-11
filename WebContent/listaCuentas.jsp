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
%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.Account"%>
<%@ page import="dao.AccountDAO"%>

<%
ArrayList<Account> accounts = (ArrayList)AccountDAO.getAccounts(c.getDni());
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1"/>
<script>

function escribeCuentas() {
	var cuentas = "";
	
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ListaCuentas</title>

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

.custab{
    border: 1px solid #ccc;
    padding: 5px;
    margin: 5% 0;
    box-shadow: 3px 3px 2px #ccc;
    transition: 0.5s;
    }
.custab:hover{
    box-shadow: 3px 3px 0px transparent;
    transition: 0.5s;
    }
    
.yy {
  margin: 50px auto;
  width: 250px;
  height: 250px;
  border-left: 1px solid #3ACFD5;
  border-right: 1px solid #3a4ed5;
  background-position: 0 0, 0 100% ;
  background-repeat: no-repeat;
  -webkit-background-size: 100% 1px;
  -moz-background-size: 100% 1px;
  background-size: 100% 1px;
  background-image: -webkit-linear-gradient(left, #3acfd5 0%, #3a4ed5 100%), -webkit-linear-gradient(left, #3acfd5 0%, #3a4ed5 100%);
  background-image: -moz-linear-gradient(left, #3acfd5 0%, #3a4ed5 100%), -moz-linear-gradient(left, #3acfd5 0%, #3a4ed5 100%);
  background-image: -o-linear-gradient(left, #3acfd5 0%, #3a4ed5 100%), -o-linear-gradient(left, #3acfd5 0%, #3a4ed5 100%);
  background-image: linear-gradient(to right, #3acfd5 0%, #3a4ed5 100%), linear-gradient(to right, #3acfd5 0%, #3a4ed5 100%);
}
</style>

<script type="text/javascript">
function dropAccount() {
	alert("eliminar");
	document.getElementById("insertOrDelete").style.display = "block";
	var codigo = '<form action="EliminaCuentaServlet" id="eliminarAccount" method="get"><select name="ibanEliminar"><% for(int i=0; i<accounts.size();i++){%><option value="<%= ((Account)accounts.get(i)).getIban() %>" ><%= ((Account)accounts.get(i)).getIban() %></option> <%}%>	</select><button type="submit">Eliminar</button></form>';
	document.getElementById("contenido").innerHTML = codigo;
	}

function addAccount() {
	alert("añadir");
	document.getElementById("insertOrDelete").style.display = "block";
	var codigo = '<form action="AgregaCuentaServlet" id="agregarAccount" method="get"><input type="hidden" name="cliente" value="<%=c.getDni()%>" /><input type="text" value="iban" name="iban"/><input type="text" value="00" name="saldo"/><button type="submit">Agregar</button></form>';
	document.getElementById("contenido").innerHTML = codigo;
}
</script>




</head>
<%@ include file="header.html" %>
<body>

<h1><fmt:message key="listAccount"/></h1>
<br>


<div class="wrap">
  <a onClick="addAccount()" class="button"><fmt:message key="addAccount"/></a>
  <a onClick="dropAccount()" class="button2"><fmt:message key="dropAccount"/></a>
</div>

<h1>${mensaje}</h1>

<!-- <table  border="1">
    <%
    for(int i=0; i<accounts.size();i++){%>
        <tr>
              <td><%= ((Account)accounts.get(i)).getIban() %></td>
              <td><%= ((Account)accounts.get(i)).getCliente() %></td>
              <td><%= ((Account)accounts.get(i)).getSaldo() %>&nbsp€</td>
        </tr>
      <%}%>
</table>
 -->
 
<div class="container">
    <div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab" style="background-color:white">
    
    <thead>
        <tr>
            <th>Cliente</th>
            <th>Iban</th>
            <th>Saldo</th>
            <!--  <th class="text-center">Opción</th>  -->
        </tr>
    </thead>
    <% for(int i=0; i<accounts.size();i++){%>
    		<form action="EliminaCuentaServlet">
            <tr>
                <td><%= ((Account)accounts.get(i)).getCliente() %></td>
                <td><%= ((Account)accounts.get(i)).getIban() %><input type="hidden" name="iban" style="visibility:hidden" value="<%= ((Account)accounts.get(i)).getIban() %>"></td>
                <td><%= ((Account)accounts.get(i)).getSaldo() %>&nbsp€</td>
                <!--  <td class="text-center" ><a class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span><button type="submit" style=" outline: 0px; background-color: transparent;">Elimina</button></a></td> -->
            </tr>
            </form>
         <%}%>
    </table>
    </div>
</div>

<div class="insertOrDelete" id="insertOrDelete" style="display:none;">
	<div class="row">
		<div class="yy" id="contenido">  </div>
	</div>
</div>



</body>
</html>