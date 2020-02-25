package drogueria;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class Clientes {

	private String nombres, apellidos, direccion;
	private long telefono, cedula;
	private Clientes nuevoCliente;
	private ArrayList clientes;

	public Clientes() {
		clientes = new ArrayList<Clientes>();
	}

	public Clientes(String nombres, String apellidos, long cedula, String direccion, long telefono) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cedula = cedula;
	}

	public void agregarCliente(String nombres, String apellidos, long cedula, String direccion, long telefono) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cedula = cedula;
		nuevoCliente = new Clientes(nombres, apellidos, cedula, direccion, telefono);
		clientes.add(nuevoCliente);
	}

	public void actualizarCliente(String nombres, String apellidos, long cedula, String direccion, long telefono) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cedula = cedula;
	}
    public void eliminarCliente( Clientes cliente ) {
                clientes.remove( cliente );
    }
	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public long getTelefono() {
		return telefono;
	}

	public long getCedula() {
		return cedula;
	}

	public Clientes getNuevoCliente() {
		return nuevoCliente;
	}

	public ArrayList getClientes() {
		return clientes;
	}
	
	public String getInfo(){
		return "<html><font color = #747271 >Nombres:</font> " + getNombres() + " <font color = #747271 >Apellidos:</font> " + getApellidos() + " <font color = #747271 >Cedula:</font> " + getCedula() + " <font color = #747271 >Dirección:</font> $" + getDireccion() + " <font color = #747271 )>Telefono:</font> " + getTelefono()+ "</html>";
	}

}
