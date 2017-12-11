package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Account;
import dao.AccountDAO;

/**
 * Servlet implementation class AgregaCuentaServlet
 */
@WebServlet("/AgregaCuentaServlet")
public class AgregaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregaCuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String iban = request.getParameter("iban");
		String saldoString = request.getParameter("saldo");
		double saldo = Double.parseDouble(saldoString);
		String cliente = request.getParameter("cliente");
		System.out.printf("Al cliente: %s se le agregara la cuenta %s con el saldo de %f \n", cliente, iban, saldo);
		
		Account account = new Account();
		account.setCliente(cliente);
		account.setIban(iban);
		account.setSaldo(saldo);
		
		boolean added = AccountDAO.insertAccount(account);
		
		if (added) {
			request.setAttribute("mensaje", "Cuenta añadida Satisfactoriamente.");
			request.getRequestDispatcher("listaCuentas.jsp").include(request, response);
		} else {
			request.setAttribute("mensaje", "Cuenta No añadida.");
			request.getRequestDispatcher("listaCuentas.jsp").include(request, response);			
		}
		
		/*
		 * <error-page>
		 * 		<error-code>404</error-code>
		 * 		<location>/404.html</location>
		 * </error-page>
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
