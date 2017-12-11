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
public class TransaccionesDAO {

	private static double credit;

	static Connection con = null;

	// Check if account exist
	public static boolean existAccountFrom(Account account) {
		
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
			stmt = con.prepareStatement(prop.getProperty("cuenta.exist"));
			
			stmt.setString(1, account.getIban());
			stmt.setString(2, account.getCliente());
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
	
public static boolean existAccountTo(Account account) {
		
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
	
	public static boolean AccountHaveCredit(Account account) {
		boolean haveCredit = false;
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cuenta.haveCredit"));
			stmt.setString(1, account.getIban());
			stmt.addBatch();
			// System.out.println(prop.getProperty("cliente.login"));
			rs = (ResultSet) stmt.executeQuery();

			if (rs.next()) {
				haveCredit = true;
			} else {
				// Cerrar conexion
				haveCredit = false;
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
		return haveCredit;
	}
	
	public static boolean withdrawCredit(Account account) {
		boolean withdraw = false;
//		System.out.println(account.getSaldo() + "-" + credit);
		if (account.getSaldo() < credit) {
			return false;
		}
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");
			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cuenta.retirar"));
			stmt.setDouble(1, credit);
			stmt.setString(2, account.getIban());
			stmt.addBatch();
			// System.out.println(prop.getProperty("cliente.login"));
			stmt.executeUpdate();
			withdraw = true;
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Ha fallado la consulta");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("ha fallado el IO");
			e.printStackTrace();
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
		return withdraw;
		
	}
	
	public static boolean addCredit(Account account) {
		boolean insertCredit = false;
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("cuenta.agregar"));
			stmt.setDouble(1, credit);
			stmt.setString(2, account.getIban());
			stmt.addBatch();
			// System.out.println(prop.getProperty("cliente.login"));
			stmt.executeUpdate();
			insertCredit = true;
		}catch (SQLException e) {
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
		return insertCredit;
	}
	
	public static boolean transaction(Account accountFrom, Account accountTo) {
		boolean transaction = false;
		if(existAccountFrom(accountFrom) && existAccountTo(accountTo)) {
			if(AccountHaveCredit(accountFrom)) {
				try {
					if(withdrawCredit(accountFrom) && addCredit(accountTo) && insertTransaction(accountFrom, accountTo)) {
					transaction = true;
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return transaction;
	}
	
	public static boolean insertTransaction(Account accountFrom, Account accountTo) {
		boolean insertTransaction = false;
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("transacciones.insert"));
			stmt.setDouble(1, credit);
			stmt.setString(2, accountFrom.getIban());
			stmt.setString(3, accountTo.getIban());
			stmt.addBatch();
			// System.out.println(prop.getProperty("cliente.login"));
			stmt.executeUpdate();
			insertTransaction = true;
		}catch (SQLException e) {
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
		return insertTransaction;
	}
	
	
	
	
	public static List<Transaccion> listaTransacciones(String idCliente) { // Idcliente es su dni
	
		List<Transaccion> transacciones = new ArrayList<Transaccion>();
		con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		try {
			// Utilizaremos fichero sql.properties que tiene una consulta

			// Cargamos las propiedades
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			prop.load(input);
			stmt = con.prepareStatement(prop.getProperty("transacciones.FromOrToClient"));
			stmt.setString(1, idCliente);
			stmt.addBatch();
			rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				Transaccion tr = new Transaccion();
				tr.setId(rs.getInt("id"));
				tr.setFecha(rs.getString("fecha"));
				tr.setCantidad(Double.parseDouble(rs.getString("cantidad")));
				tr.setOrigen(rs.getString("origen")); 
				tr.setDestino(rs.getString("destino")); 
				transacciones.add(tr);
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
//		return haveCredit;
		
		return transacciones;
	}
	
	
	
	
	//***************** Getter & Setter *************************
	/**
	 * @return the credit
	 */
	public static double getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public static void setCredit(double credito) {
		credit = credito;
	}
}
