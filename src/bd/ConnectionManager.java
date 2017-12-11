/**
 * 
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author iaw26068632
 *
 */
public class ConnectionManager {

	/**
	 * Esta clase hará la conexion a la base de datos y la devolverá para
	 * utilizar sus datos
	 */

	static Connection connection;
	static Connection con;

	public static Connection getConnection() { // Static para que no cree
		
		// static Connection con;

		
		
		InitialContext ctx;

		try {
			ctx = new javax.naming.InitialContext();	
			DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/banco");		
			con = ds.getConnection();
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		
		
//		// instancias de la clase
//		// CONEXION A BASE DE DATOS
//		Connection connection = null;
//
//		try {
//			Class.forName("org.postgresql.Driver");
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test1", "jhonny", "123456");
//
//		} catch (Exception e) {
//			System.out.println("falló la conexión");
//			e.printStackTrace();
//		}
//		return connection;

		return con;
		
		
		
	}
}
