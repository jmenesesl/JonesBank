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
 * Servlet implementation class ModificaServlet
 */
@WebServlet("/ModificaServlet")
public class ModificaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente c = null;
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String dni = request.getParameter("dni");
		String dniAnterior = request.getParameter("dni_anterior");
		String sexo = request.getParameter("sexo");
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		String contrasena = request.getParameter("contrasena");
		c = ClienteDAO.modificar(dni, nombre, apellidos, fecha_nacimiento, sexo, direccion, telefono, contrasena, dniAnterior);

		if (c.isIsvalid()) {
			
			request.setAttribute("saludo", "Se han modificado tus datos");
			request.getRequestDispatcher("loginok.jsp").include(request, response);
		} else {
			request.setAttribute("mensaje", "no se han actualizado los datos \n Contacte con webmaster");
			request.getRequestDispatcher("loginko.jsp").include(request, response);

		}
	}

}
