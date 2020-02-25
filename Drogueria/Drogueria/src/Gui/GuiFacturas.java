package Gui;

import drogueria.DetalleFactura;
import drogueria.Factura;
import drogueria.Medicamento;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GuiFacturas extends GuiMedicamentos {

	private Object rowSeleccionado;
	private JLabel[] labels;
	private String[] textoLabels = {"Factura N° ", "C.C Cliente: ", "Fecha: ", "IVA: ", "Total: "};
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private int posY;
	private JScrollPane scrollpane;
	private String[] datosTabla = {"", "", "", ""};
	private JLabel nombreUsuario;

	public GuiFacturas(Factura factura, DetalleFactura detalle, String[] datos ) {
		labels = new JLabel[textoLabels.length];
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		scrollpane = new JScrollPane(tabla);
		scrollpane.getViewport().setBackground(new Color(60, 60, 60, 255));
		getPanel().add(scrollpane);
		modeloTabla.addColumn("Referencia");
		modeloTabla.addColumn("Medicamento");
		modeloTabla.addColumn("Cantidad");
		modeloTabla.addColumn("Subtotal");
		posY = 60;
		getLista().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				modeloTabla.setRowCount(0);
				StringBuilder info = new StringBuilder();
				Factura auxFactura = null;
				rowSeleccionado = getLista().getSelectedValue();
				for (int i = 0; i < factura.getfacturas().size(); i++) {
					auxFactura = (Factura) factura.getfacturas().get(i);
					if (auxFactura.getInfoFacturas().equals(rowSeleccionado)) {
						labels[0].setText(textoLabels[0] + auxFactura.getIdFactura());
						labels[1].setText(textoLabels[1] + auxFactura.getCedulaCliente());
						labels[2].setText(textoLabels[2] + auxFactura.getFecha());
						labels[3].setText(textoLabels[3] + auxFactura.getIva());
						labels[4].setText(textoLabels[4] + auxFactura.getTotal());
						nombreUsuario.setText( auxFactura.getUsuario() );
						for (int j = 0; j < detalle.getDetalles().size(); j++) {
							DetalleFactura auxDetalleFactura = (DetalleFactura) detalle.getDetalles().get(j);
							if (auxDetalleFactura.getIdFactura() == auxFactura.getIdFactura()) {
								modeloTabla.addRow(datos);
							}
						}
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(textoLabels[i]);
			labels[i].setBounds(340, posY, 115, 30);
			labels[i].setForeground(Color.white);
			getPanel().add(labels[i]);
			posY = posY + 35;
		}
		nombreUsuario = new JLabel("Usuario: ");
		nombreUsuario.setBounds(340, labels[4].getY()+35, 215, 30);
		nombreUsuario.setForeground(Color.white);
		getPanel().add(nombreUsuario);
	}

	public void configGuiFacturas() {
		abrirGui();
		this.setSize(800, this.getHeight());
		getTitulo().setSize(800, getTitulo().getHeight());
		getTitulo().setText("Facturas realizadas");
		ocultarTodo();
		getScroll().setVisible(true);
		getScroll().setBounds(20, 60, 310, this.getHeight() - 90);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) getLista().getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		for (JLabel i : getBotones()) {
			switch (i.getText()) {
				case "x":
					i.setBounds(getTitulo().getWidth() - 30, 0, 30, 30);
					break;
				case "-":
					i.setBounds(getTitulo().getWidth() - 60, 0, 30, 30);
			}
		}
		scrollpane.setBounds(340, 275, 440, 225);

		tabla.setBackground(new Color(60, 60, 60, 255));
		tabla.setBackground(new Color(55, 55, 55, 255));
		tabla.setFocusable(false);
		tabla.setForeground(Color.lightGray);
		tabla.setSelectionBackground(Color.gray);
		tabla.setShowGrid(false);
		tabla.setSelectionForeground(Color.white);
		tabla.setFont(new Font("Arial Narrow", 1, 15));
		tabla.getTableHeader().setBackground(new Color(55, 55, 55, 255));
		tabla.getTableHeader().setForeground(Color.lightGray);
		tabla.getTableHeader().setFont(new Font("Arial Narrow", 1, 15));
		tabla.getTableHeader().setFocusable(false);
		tabla.setOpaque(true);
	}

	public void agregarFacturas(Factura factura) {
		getModeloLista().removeAllElements();
		Factura auxFactura;
		for (int i = 0; i < factura.getfacturas().size(); i++) {
			auxFactura = (Factura) factura.getfacturas().get(i);
			getModeloLista().addElement(auxFactura.getInfoFacturas());
		}
	}

	public String[] getDatosTabla() {
		return datosTabla;
	}
	
	public void cambiarUsuario( String usuario ){
		nombreUsuario.setText(usuario );
	} 

}
