package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;
import dao.ClienteDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("dni");
		String pass = request.getParameter("pass");

		Cliente c = ClienteDAO.loginValid(dni, pass);

		if (c.isIsvalid()) { //if (c.isIsvalid()) {
			
			HttpSession session = request.getSession(); // Se inicia la seccion getSession(true)
			session.setAttribute("clientSession", c); // Se associa por si sola la seccion con las cokies
			
			request.setAttribute("saludo", "Bienvenido " + c.getNombre());
//			request.getRequestDispatcher("loginok.jsp").include(request, response);
			
			String encodeURL = response.encodeRedirectURL("loginok.jsp");
			response.sendRedirect(encodeURL);
		} else {
			request.setAttribute("mensaje", "Nombre de usuario o contraseña incorrectos.");
			request.getRequestDispatcher("loginko.jsp").include(request, response);

		}
		
		
	}

}
