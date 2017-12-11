package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.ConnectionManager;
import beans.Cliente;
import dao.ClienteDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
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
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String fechaNacimiento = request.getParameter("fecha_de_nacimiento");
			String sexo = request.getParameter("sexo");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			String contrasena = request.getParameter("contrasena");
			c = ClienteDAO.registro(dni, nombre, apellidos, fechaNacimiento, sexo, direccion, telefono, contrasena);
			
			if (c.isIsvalid()) {
				
				HttpSession session = request.getSession(); // Se inicia la seccion getSession(true)
				session.setAttribute("clientSession", c); // Se associa por si sola la seccion con las cokies
				
				request.setAttribute("saludo", "Registro completo, Bienvenido " + c.getNombre());
				request.getRequestDispatcher("loginok.jsp").include(request, response);
			} else {
				request.setAttribute("mensaje", "El usuario con dni " + c.getDni() + " ya está registrado \n Contacte con webmaster");
				request.getRequestDispatcher("loginko.jsp").include(request, response);

			}
	}
}
