package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginUsuario
 */
@WebServlet("/LoginUsuario")
public class LoginUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id_cliente");
		String contrasena = request.getParameter("contrasena");
		boolean login = false;
		try {
		
		
		// CONEXION A BASE DE DATOS
				Connection connection = null;
				
				// conexion con base de datos postgres
				
				
				/*
				 *  IMPORTANTE!!! añadir el archivo postgresql-42.1.4.jar en la carpeta WebContent/WEB-INF/lib/
				 *  sin este archivo, no conecta con la base de datos Postgresql
				 */
				
				try {
					Class.forName("org.postgresql.Driver");		
					connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test1","jhonny", "123456");
				// connection.close();
				} catch (Exception e) {
					System.out.println("falló la conexión");
					e.printStackTrace();
					//		} catch (SQLException e) {
					//			System.out.println("Error en la consulta");
				}
				
				if (connection != null) {
					System.out.println("OK!");
				} else {
					System.out.println("fallo la conexion");
				}
				
				// LOGIN DE USUARIO
				
				if (loginUser(connection, id, contrasena)) {
					request.setAttribute("saludo", "Bienvenido " + id);
					request.getRequestDispatcher("/jones.jsp").forward(request, response);
				} else {
					request.setAttribute("saludo", "Usuario no encontrado");
					request.getRequestDispatcher("/jones.jsp").forward(request, response);
				}
				
				
				}catch (Exception e) {
				System.out.println("Ha fallado!!!");
				request.getRequestDispatcher("/muestraExcepcion.jsp").forward(request, response);
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected boolean loginUser(Connection connection, String id, String contrasena) {
		boolean login = false;
		try {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cliente where nombre = '" + id + "' and contraseña = md5('" + contrasena +"')");
		ResultSet rs = (ResultSet) stmt.executeQuery();
//		while (rs.next()) {
//			System.out.println("nombre: " + rs.getString("nombre"));
//			System.out.println("pass: " + rs.getString("pass"));
//
//		}
		if (rs.next()) {
			login = true;
//			request.setAttribute("saludo", "Bienvenido " + id);
//			request.getRequestDispatcher("/jones.jsp").forward(request, response);
		} else {
			login = false;
//			request.setAttribute("saludo", "Usuario no encontrado");
//			request.getRequestDispatcher("/jones.jsp").forward(request, response);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}

}
