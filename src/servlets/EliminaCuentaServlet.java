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
 * Servlet implementation class EliminaCuentaServlet
 */
@WebServlet("/EliminaCuentaServlet")
public class EliminaCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaCuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String iban = request.getParameter("ibanEliminar");
		System.out.println("El iban es:" + request.getParameter("ibanEliminar"));
		Account account = new Account();
		account.setIban(iban);
		boolean eliminado = false;
		eliminado = AccountDAO.deleteAccount(account);
		response.setCharacterEncoding("ISO-8859-1");
		if (eliminado) {
			request.setAttribute("mensaje", "Cuenta eliminada satisfactoriamente.");
			request.getRequestDispatcher("listaCuentas.jsp").include(request, response);
			
		} else {
			request.setAttribute("mensaje", "Cuenta NO eliminada.");
			request.getRequestDispatcher("listaCuentas.jsp").include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
