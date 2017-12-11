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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import bd.ConnectionManager;
import beans.Account;

/**
 * @author iaw26068632
 *
 */
public class AccountDAO {
	static Connection con = null;

	public static List<Account> getAccounts(String dni) {
		List<Account> accountsByClie = new ArrayList<Account>();
		Account accountClie = new Account();
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;

		try {
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cuenta.getAccounts"));

			stmt.setString(1, dni);
			stmt.addBatch();

			// Ya habiendo realizado la consulta

			rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				accountClie.setIban(rs.getString("iban"));
				accountClie.setCliente(rs.getString("cliente")); // Mismo dni
				accountClie.setSaldo(Double.parseDouble(rs.getString("saldo"))); // accountClie.setSaldo(rs.getDouble("saldo"));
				accountsByClie.add(accountClie);
				accountClie = new Account(); // Borramos los datos
			}
		} catch (SQLException e) {
			System.out.println("Ha fallado la consulta");
		} catch (IOException e) {
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
		return accountsByClie;
	}
	
	
	public static boolean insertAccount(Account account) {
		boolean insert = false;
		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Properties prop = new Properties();
		InputStream input = AccountDAO.class.getClassLoader().getResourceAsStream("sql.properties");
		
		
		try {
			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cuenta.insertAccount"));
			stmt.setString(1, account.getIban());
			stmt.setDouble(2, account.getSaldo());
			stmt.setString(3, account.getCliente());
			stmt.addBatch();
			stmt.executeUpdate();
			insert = true;
			
		} catch(SQLException e) {
			System.out.println("Ha fallado la consulta");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ha fallado el input");
			e.printStackTrace();
		} finally {
			
		}
		return insert;
	}
	
	public static boolean deleteAccount(Account account) {
		boolean delete = false;
		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Properties prop = new Properties();
		InputStream input = AccountDAO.class.getClassLoader().getResourceAsStream("sql.properties");
		
		try {
			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cuenta.eliminar"));
			stmt.setString(1, account.getIban());
//			stmt.setString(2, account.getCliente());
			stmt.addBatch();
			stmt.executeUpdate();
			delete = true;
		} catch(SQLException e) {
			System.out.println("Ha fallado la consulta");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ha fallado el input");
			e.printStackTrace();
		} finally {
			
		}
		return delete;
	}
	
public static boolean existAccount(Account account) {
		
		boolean exist = false;
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cuenta.existIban"));
			stmt.setString(1, account.getIban());
			stmt.addBatch();
			// System.out.println(prop.getProperty("cliente.login"));
			rs = (ResultSet) stmt.executeQuery();

			if (rs.next()) {
				exist = true;
			} else {
				// Cerrar conexion
				exist = false;
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
		return exist;
	}
}
