package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Account;
import beans.Cliente;
import dao.AccountDAO;
import dao.TransaccionesDAO;

/**
 * Servlet implementation class NuevaTransaccionServlet
 */
@WebServlet("/NuevaTransaccionServlet")
public class NuevaTransaccionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NuevaTransaccionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String dniFrom = ((Cliente) request.getSession().getAttribute("clientSession")).getDni();
		String ibanFrom = request.getParameter("ibanOrigen");
		String ibanDestino = request.getParameter("ibanDestino");

		// System.out.println(((Cliente)request.getSession().getAttribute("clientSession")).toString());
		double cantidad = Double.parseDouble(request.getParameter("cantidad"));

		Account cuentaOrigen = new Account();
		cuentaOrigen.setCliente(dniFrom);// Continuar aqui
		cuentaOrigen.setIban(ibanFrom);

		Account cuentaDestino = new Account();
		cuentaDestino.setIban(ibanDestino);
		if (TransaccionesDAO.existAccountFrom(cuentaOrigen)) { // Entro en las
																// cuentas para
																// establecer el
																// saldo y asi
																// saber si la
																// cuenta posee
																// saldo
																// suficiente
																// para realizar
																// la
																// transaccion
			for (Account a : AccountDAO.getAccounts(dniFrom)) {
				if (a.getIban().equals(ibanFrom))
					cuentaOrigen.setSaldo(a.getSaldo());
			}
		}

		String cant = request.getParameter("cantidad");

		System.out.printf("Se transferirá %f, de la cuenta %s a la cuenta %s\n", Double.parseDouble(cant),
				cuentaOrigen.getIban(), cuentaDestino.getIban());
		TransaccionesDAO.setCredit(Double.parseDouble(cant));
		response.setCharacterEncoding("ISO-8859-1");

		if (TransaccionesDAO.transaction(cuentaOrigen, cuentaDestino)) {
			request.setAttribute("mensaje", "Transaccion Satisfactoria.\n" + "Se ha transferido " + cant + " €, de la cuenta "+ cuentaOrigen.getIban() + " a la cuenta " + cuentaDestino.getIban());
			request.getRequestDispatcher("listaTransacciones.jsp").include(request, response);
		} else {
			request.setAttribute("mensaje", "Transaccion No realizada.");
			request.getRequestDispatcher("listaTransacciones.jsp").include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
