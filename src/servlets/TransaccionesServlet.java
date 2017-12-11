package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;
import dao.Transaccion;
import dao.TransaccionesDAO;

/**
 * Servlet implementation class TransaccionesServlet
 */
@WebServlet("/TransaccionesServlet")
public class TransaccionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransaccionesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Ha entrado en TransaccionesServlet");
		Cliente cliente = (Cliente) request.getSession().getAttribute("clientSession");
		List<Transaccion> lTrans = TransaccionesDAO.listaTransacciones(cliente.getDni()); 
		HttpSession session = request.getSession();
		session.setAttribute("lista", lTrans);
		request.getRequestDispatcher("transacciones.jsp?page=1").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
