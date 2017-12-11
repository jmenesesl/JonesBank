/**
 * 
 */
package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import bd.ConnectionManager;
import beans.Cliente;

/**
 * @author iaw26068632
 *
 */
public class ClienteDAO {

	static Connection con = null;

	public static Cliente loginValid(String user, String pass) {
		Cliente c = new Cliente();
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);

//			System.out.println("SELECT * FROM cliente where contraseña = md5('" + pass + "')" + " and dni = '" + user + "'");

			stmt = con.prepareStatement(prop.getProperty("cliente.login"));
			stmt.setString(1, pass);
			stmt.setString(2, user);
			stmt.addBatch();
			// System.out.println(prop.getProperty("cliente.login"));
			rs = (ResultSet) stmt.executeQuery();

			if (rs.next()) {
				c.setNombre(rs.getString("nombre"));
				c.setDni(rs.getString("dni"));
				c.setApellidos(rs.getString("apellidos"));
				c.setFechaNacimiento(rs.getString("fecha_de_nacimiento"));
				c.setSexo(rs.getString("sexo"));
				c.setDireccion(rs.getString("direccion"));
				c.setTelefono(rs.getString("telefono"));
				c.setIsvalid(true);
			} else {
				// Cerrar conexion
				c.setIsvalid(false);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Ha fallado la consulta");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("ha fallado el IO");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return c;
	}

	public static Cliente registro(String dni, String nombre, String apellidos, String fechaNacimiento, String sexo,
			String direccion, String telefono, String pass) {
		Cliente c = new Cliente();
		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Properties prop = new Properties();
		InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

		try {
			
			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cliente.registro"));
			stmt.setString(1, dni);
			stmt.setString(2, nombre);
			stmt.setString(3, apellidos);
			stmt.setString(4, fechaNacimiento);
			stmt.setString(5, sexo);
			stmt.setString(6, direccion);
			stmt.setString(7, telefono);
			stmt.setString(8, pass);
			stmt.addBatch();
			stmt.executeUpdate();
			c = ClienteDAO.existUser(dni);
			
		} catch (IOException e) {
			System.out.println("ha fallado el input");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Ha fallado la consulta");
			e.printStackTrace();
	} finally {
		c.setDni(dni);
		c.setNombre(nombre);
		c.setApellidos(apellidos);
		c.setFechaNacimiento(fechaNacimiento);
		c.setSexo(sexo);
		c.setDireccion(direccion);
		c.setTelefono(telefono);
	}
		System.out.println(c.toString());
		return c;
	}
	
	public static Cliente modificar(String dni, String nombre, String apellidos, String fechaNacimiento, String sexo,
			String direccion, String telefono, String pass, String dniAnterior) {
		Cliente c = new Cliente();
		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Properties prop = new Properties();
		InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

		try {
			
			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cliente.update"));
			stmt.setString(1, dni);
			stmt.setString(2, nombre);
			stmt.setString(3, apellidos);
			stmt.setString(4, fechaNacimiento);
			stmt.setString(5, sexo);
			stmt.setString(6, direccion);
			stmt.setString(7, telefono);
			stmt.setString(8, pass);
			stmt.setString(9, dniAnterior);
			stmt.addBatch();			
			stmt.executeUpdate();
			c = ClienteDAO.existUser(dni);
			
		} catch (IOException e) {
			System.out.println("ha fallado el input");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Ha fallado la consulta");
			e.printStackTrace();
	} finally {
		c.setDni(dni);
		c.setNombre(nombre);
		c.setApellidos(apellidos);
		c.setFechaNacimiento(fechaNacimiento);
		c.setSexo(sexo);
		c.setDireccion(direccion);
		c.setTelefono(telefono);
	}
		System.out.println(c.toString());
		return c;
	}
	
	
	public static Cliente existUser(String user) {
		Cliente c = new Cliente();
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cliente.exist"));
			stmt.setString(1, user);
			stmt.addBatch();
			// System.out.println(prop.getProperty("cliente.login"));
			rs = (ResultSet) stmt.executeQuery();

			if (rs.next()) {
				c.setIsvalid(true);
			} else {
				// Cerrar conexion
				c.setIsvalid(false);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Ha fallado la consulta");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("ha fallado el IO");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return c;
	}
}
