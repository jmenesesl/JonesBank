package registro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registrar
 */
@WebServlet("/Registrar")
public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String fechaNacimiento = request.getParameter("fecha_nacimiento");
		String dni = request.getParameter("dni");
		String sexo = request.getParameter("sexo");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		String contrasena = request.getParameter("contrasena");
		
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
	
		if (!loginUser(connection, nombre, contrasena)) {		
			if(registrar(connection, nombre, apellidos, fechaNacimiento, direccion, telefono, dni, contrasena)) {
				System.out.println("USUARIO Registrado!");
				request.getRequestDispatcher("/bienvenido.html").forward(request, response);
			}
		} else {
			System.out.println("USUARIO NO registrado porque ya existe!");
		}
		}catch (Exception e) {
		System.out.println("No hay conexión con PostgreSql!!!");
		request.getRequestDispatcher("/muestraExcepcion.jsp").forward(request, response);
		}
	}
//
//	public void connectDatabase() {
//        try {
//            // We register the PostgreSQL driver
//            // Registramos el driver de PostgresSQL
//            try { 
//                Class.forName("org.postgresql.Driver");
//            } catch (ClassNotFoundException ex) {
//                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
//            }
//            Connection connection = null;
//            // Database connect
//            // Conectamos con la base de datos
//            connection = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:5432/test1","jhonny", "123456");
// 
//            boolean valid = connection.isValid(50000);
//            System.out.println(valid ? "TEST OK" : "TEST FAIL");
//        } catch (java.sql.SQLException sqle) {
//            System.out.println("Error: " + sqle);
//        }
//    } 
	protected boolean registrar(Connection connection, String nombre, String apellidos, String fechaNacimiento, String direccion, String telefono, String dni, String contrasena) {
		Statement st = null;
		String sql = "INSERT INTO cliente VALUES ('" + dni + "', '" + nombre + "', '" + apellidos + "', '" + fechaNacimiento + "', '" + direccion + "', '" + telefono  + "', md5('" + contrasena + "'));";
		try {
		st = connection.createStatement();
		st.executeUpdate(sql);
		return true;
		} catch (Exception e) {
			System.out.println("ha fallado el insert");
			return false;
		}
	}
	protected boolean loginUser(Connection connection, String id, String contrasena) {
		boolean login = false;
		try {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users where nombre = '" + id + "' and pass = '" + contrasena +"'");
		ResultSet rs = (ResultSet) stmt.executeQuery();
		if (rs.next()) {
			login = true;
		} else {
			login = false;
		}
		} catch (Exception e) {
			
		}
		return login;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
