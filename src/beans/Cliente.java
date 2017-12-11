/**
 * 
 */
package beans;

import dao.ClienteDAO;

/**
 * @author iaw26068632
 *
 */
public class Cliente {

	private String dni;
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String sexo;
	private String direccion;
	private String telefono;
	private boolean isvalid; // Atributo para saber si es valido o no
	
	public boolean isIsvalid() {
		return isvalid;
	}
	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	@Override
	public String toString() {
		return String.format(
				"Cliente [dni=%s, nombre=%s, apellidos=%s, fechaNacimiento=%s, sexo=%s, direccion=%s, telefono=%s, isvalid=%s]",
				dni, nombre, apellidos, fechaNacimiento, sexo, direccion, telefono, isvalid);
	}
	
	public static void main(String[] args) {
		// Con connection pool esto falla
		Cliente c; 
		c = ClienteDAO.loginValid("00000000Z", "1234");
		System.out.println(c.toString());
		
		
	}
}
