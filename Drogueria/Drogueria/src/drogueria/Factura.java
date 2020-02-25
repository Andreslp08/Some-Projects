package drogueria;

import java.util.ArrayList;

public class Factura {

	private  long idFactura = 1;
	private long subtotal;
	private long cedulaCliente;
	private String fecha;
	private double total = 0;
	private String referencia, nombre, cantidad;
	private double iva= 0;
	private ArrayList facturas;
	private Factura nuevaFactura;
	private String medicamentoSeleccionado;
	private String usuario;

	public Factura() {
		facturas = new ArrayList<Factura>();
	}

	public Factura(long id, long cedulaCliente, String fecha, double iva, double total, String medicamentoSeleccionado, String codigo, String nombre, String cantidad, long subtotal, String usuario) {
		this.idFactura = id;
		this.cedulaCliente = cedulaCliente;
		this.fecha = fecha;
		this.total = total;
		this.referencia = codigo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.iva = iva;
		this.usuario = usuario;
		this.medicamentoSeleccionado = medicamentoSeleccionado;
	}

	public void crearFactura(long id, long cedulaCliente, String fecha, double iva, double total, String medicamentoSeleccionado, String codigo, String nombre, String cantidad, long subtotal, String usuario ) {
		this.idFactura = id;
		this.cedulaCliente = cedulaCliente;
		this.fecha = fecha;
		this.total = total;
		this.referencia = codigo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.iva = iva;
		this.usuario = usuario;
		nuevaFactura = new Factura(id, cedulaCliente, fecha, iva, total, medicamentoSeleccionado, codigo, nombre, cantidad, subtotal, usuario );
		facturas.add(nuevaFactura);
	}

	public long getId() {
		return this.idFactura;
	}

	public String toString() {
		return "<p>Factura N°:  " + getIdFactura()
			+ "</p><p>C.C Cliente: " + cedulaCliente
			+ "</p><p>Fecha: " + fecha
			+"</p><p>IVA: " + iva
			+ "%</p><p>Total + IVA:$ " + total;
	}
	
	public long getCedulaCliente() {
		return cedulaCliente;
	}

	public String getFecha() {
		return fecha;
	}

	public double getTotal() {
		return total;
	}

	public long getIdFactura() {
		return idFactura;
	}

	public double getIva() {
		return iva;
	}
	

	public void setTotal(double total) {
		this.total = total;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public void incrementarIdFactura() {
		idFactura = idFactura + 1;
	}
	
	public ArrayList getfacturas(){
		return facturas;
	}
	
	public String getInfoFacturas(){
		return "Factura N° " + getIdFactura() + " Fecha: " + getFecha();
	}

	public String getUsuario() {
		return usuario;
	}

}
