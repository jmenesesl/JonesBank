/**
 * 
 */
package beans;

import java.util.List;

import dao.AccountDAO;
import dao.Transaccion;
import dao.TransaccionesDAO;

/**
 * @author iaw26068632
 *
 */
public class Account {

	//Iban de la cuenta bancaria del cliente
	private String iban;
	
	//Saldo actual del cliente
	private double saldo;
	
	// dni de identificac ion del cliente
	private String cliente;
	

	

	//********************** Constructor ***********************
	
//	public Account(String iban, double saldo, String cliente) {
//		this.iban = iban;
//		this.saldo = saldo;
//		this.cliente = cliente;
//	}	

	//********************** Getter and Setter ***********************

	/**
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	//********************** HashCode and Equals ***********************


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iban == null) ? 0 : iban.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (iban == null) {
			if (other.iban != null)
				return false;
		} else if (!iban.equals(other.iban))
			return false;
		return true;
	}
	
	//********************** ToString ***********************


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Account [iban=%s, saldo=%s, cliente=%s]", iban, saldo, cliente);
	}

	public static void main(String[] args) {
		/*List<Account> list = AccountDAO.getAccounts("00000000Z");
		
		System.out.println(list);
		
		Account cuenta = new Account();
		cuenta.setCliente("26068632H");
		cuenta.setIban("ES97XXXXXXXXXXXXXXXXXXXX");
		cuenta.setSaldo(1000.00);
		// Con esto comprobamos que inserta y elimina
		AccountDAO.insertAccount(cuenta);
		AccountDAO.deleteAccount(cuenta);
*/
		Account cuenta1 = new Account();
		cuenta1.setCliente("26068632H");
		cuenta1.setSaldo(760.52);
		cuenta1.setIban("COLXXXXXXXXXXXXXXXXX9900");
		Account cuenta2 = new Account();
		cuenta2.setCliente("00000000Z");
		cuenta2.setSaldo(190);
		cuenta2.setIban("ESXXXXXXXXXXXXXXXXXX4572");
		// COMPROBAR QUE FUNCIONA LA TRANSACCION
//		System.out.println(TransaccionesDAO.existAccount(cuenta1));
//		System.out.println(TransaccionesDAO.existAccount(cuenta2));
//		System.out.println(TransaccionesDAO.AccountHaveCredit(cuenta1));
//		System.out.println(TransaccionesDAO.withdrawCredit(cuenta1, 20));
//		System.out.println(TransaccionesDAO.addCredit(cuenta2, 20));
		TransaccionesDAO.setCredit(80.02);
		System.out.println(TransaccionesDAO.transaction(cuenta1, cuenta2));
		for(Transaccion tr: TransaccionesDAO.listaTransacciones("26068632H"))
		System.out.println(tr.toString());
		
	}

}
