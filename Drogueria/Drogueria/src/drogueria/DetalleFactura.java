package drogueria;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DetalleFactura {

	private long idFactura;
	private String referencia, medicamento, cantidad, subtotal;
	private DetalleFactura nuevoDetalle;
	private ArrayList detalles;
	private StringBuilder infoCompleta;
	private String pregunta;
	private boolean permiso = false;
	private String laID = "";

	public DetalleFactura() {
		infoCompleta = new StringBuilder();
		detalles = new ArrayList<DetalleFactura>();
	}

	public DetalleFactura(long idFactura, String referencia, String medicamento, String cantidad, String subtotal) {
		this.idFactura = idFactura;
		this.referencia = referencia;
		this.medicamento = medicamento;
		this.subtotal = subtotal;
		this.cantidad = cantidad;
	}

	public void crearDetalle(long idFactura, String referencia, String medicamento, String cantidad, String subtotal) {
		this.idFactura = idFactura;
		this.referencia = referencia;
		this.medicamento = medicamento;
		this.subtotal = subtotal;
		this.cantidad = cantidad;
		nuevoDetalle = new DetalleFactura(idFactura, referencia, medicamento, cantidad, subtotal);
		detalles.add(nuevoDetalle);
	}

    public String getCantidad() {
        return cantidad;
    }

	public String toString() {
		return "ID:  " + idFactura + " Referencia: " + referencia + " Medicamento: " + medicamento
			+ " Cantidad: " + cantidad + " Subtotal: $" + subtotal;
	}

	public String getInfo(){
		return "<p>Productos adquiridos" + "</p>"
			+"<p>Referencia:" + referencia + "</p>"
			+"<p>Producto:" + medicamento + "</p>"
			+"<p>Cantidad:" + cantidad + "</p>"
			+"<p>Subtotal:" + cantidad + "</p>";
	}
	public long getIdFactura() {
		return idFactura;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public StringBuilder getInfoCompleta() {
		return infoCompleta;
	}

	public String getLaID() {
		return laID;
	}

    public String getSubtotal() {
        return subtotal;
    }

    public String getReferencia() {
        return referencia;
    }

	public void mostrarDetalles(ArrayList facturas, JTextField facturaID,JTextField cliente, JTextField fecha, JTextField iva, JTextField total, DefaultTableModel modeloTabla, String[]datosTabla) {
		try {
			do {
				Factura verificarFactura;
				infoCompleta = new StringBuilder();
				pregunta = JOptionPane.showInputDialog(null, "Ingrese el id de la factura: ");
				if (validarID( modeloTabla,datosTabla) == true) {
					for (int i = 0; i < facturas.size(); i++) {
						verificarFactura = (Factura) facturas.get(i);
						if (Long.parseLong(pregunta) == verificarFactura.getIdFactura()) {
							//JOptionPane.showMessageDialog(null, "<html> " + verificarFactura.toString() + "</p>" + getInfoCompleta() + "</html>");
							facturaID.setText(""+verificarFactura.getIdFactura());
                                                        cliente.setText(""+verificarFactura.getCedulaCliente());
                                                        fecha.setText(""+verificarFactura.getFecha());
                                                        iva.setText(""+verificarFactura.getIva());
                                                        total.setText(""+verificarFactura.getTotal());
                                                     
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "La ID ingresada no existe.");
				
				}
                                
			} while (Long.parseLong(pregunta) != 0);
		} catch (NumberFormatException exc) {
			JOptionPane.showMessageDialog(null, "Debe ingresar digitos enteros.");
		}

	}

	public ArrayList getDetalles() {
		return detalles;
	}

	public boolean validarID( DefaultTableModel modeloTabla, String[] datosTabla ) {
		DetalleFactura verificarDetalle;
		for (int i = 0; i < detalles.size(); i++) {
			verificarDetalle = (DetalleFactura) detalles.get(i);
			if (Long.parseLong(pregunta) == verificarDetalle.getIdFactura()) {
				laID = pregunta;
				datosTabla[0] = verificarDetalle.getReferencia();
                                datosTabla[1] = verificarDetalle.getMedicamento();
                                datosTabla[2] = verificarDetalle.getCantidad();
                                datosTabla[3] = verificarDetalle.getSubtotal();
                                modeloTabla.addRow( datosTabla );
				permiso = true;
			}

		}
		if (permiso == true) {
			return true;
		}
		return false;
	}
	
	

}
