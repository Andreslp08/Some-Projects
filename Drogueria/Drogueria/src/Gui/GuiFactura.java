package Gui;

import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import drogueria.Factura;
import drogueria.Medicamento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TableView;
import javax.swing.text.TableView.TableRow;
import jdk.nashorn.internal.runtime.ListAdapter;
import drogueria.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiFactura extends GuiMedicamentos {

	private String[] tituloCampos = {"N° Factura", "C.C Cliente", "Fecha", "IVA", "Total"};
	Object[] datos = {1, 2, 3, 4};
	private JComboBox combobox;
	private DefaultComboBoxModel modeloComboBox;
	private JLabel[] labelTitulos = new JLabel[2];
	private String[] titulos = {"Medicamentos", "cantidad"};
	private int posXtitulo, posXColumna;
	private JSpinner spinnerCantidad;
	private JScrollPane pane;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private String[] columnas = {"Codigo", "Nombre", "Cantidad", "Subtotal"};
	private Factura factura = new Factura();
	private String[] datosTabla = {"", "", "", ""};
	private ArrayList arrayMedicamentos;
	private int posXBotones;
	private DetalleFactura detalle = new DetalleFactura();
	private JLabel nombreCliente;
	private JLabel nombreUsuario;

	private Clientes cliente;

	public GuiFactura(Clientes cliente, JLabel nombreUsuario ) {
		this.cliente = cliente;
		this.nombreUsuario = nombreUsuario;
		getTitulo().setText("Nueva factura");
		getBotones()[0].setText("Agregar producto");
		getBotones()[1].setText("Crear factura");
		getBotones()[2].setText("Buscar factura");
		getBotones()[3].setText("Limpiar campos");
		visibilidadGuiMedicamento(false);
		combobox = new JComboBox();
		modeloComboBox = new DefaultComboBoxModel();
		combobox.setModel(modeloComboBox);
		posXtitulo = getLabelCampos()[0].getX() + 200;
		spinnerCantidad = new JSpinner();
		accionesFactura();
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		pane = new JScrollPane(tabla);
		pane.setBounds(labelTitulos[0].getX(), getCampos()[1].getY(), 400, 222);
		panel.add(pane);
		posXColumna = getLabelCampos()[1].getX() + 200;
		crearColumnas();
		getCampos()[0].setText("" + factura.getIdFactura());
		getCampos()[0].setEditable(false);
		getCampos()[3].setText("19%");
		getCampos()[3].setEditable(false);
		getCampos()[4].setText("$");
		getCampos()[4].setEditable(false);
	}

	public void agregarMedicamentosCB(ArrayList<Medicamento> arrayMedicamentos) {
		modeloComboBox.removeAllElements();
		for (Medicamento i : arrayMedicamentos) {
			modeloComboBox.addElement(i.getInfo());
		}
	}

	public void abrirGuiFactura() {
		abrirGui();
		getVentana().setSize(645, 440);
		combobox.setBounds(getCampos()[0].getX() + 200, getCampos()[0].getY(), 280, 30);
		combobox.setFocusable(false);
		combobox.setBorder(null);
		combobox.setRequestFocusEnabled(false);
		getPanel().add(combobox);
		spinnerCantidad.setBounds(labelTitulos[1].getX(), combobox.getY(), 100, 30);
		spinnerCantidad.setBorder(null);
		spinnerCantidad.setFocusable(false);
		spinnerCantidad.setRequestFocusEnabled(false);
		getPanel().add(spinnerCantidad);
		getVentana().setLocationRelativeTo(null);
		getTitulo().setSize(654, 30);
		for (int i = 0; i < getBotones().length; i++) {
			if (getBotones()[i].getText().equals("x")) {
				getBotones()[i].setBounds(getTitulo().getWidth() - 40, 0, 30, 30);
			} else if (getBotones()[i].getText().equals("-")) {
				getBotones()[i].setBounds(getTitulo().getWidth() - 70, 0, 30, 30);
			}
		}

		tabla.setBounds(0, 0, pane.getWidth(), pane.getHeight());
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
		pane.getViewport().setBackground(new Color(55, 55, 55, 255));
		nombreCliente = new JLabel("");
		nombreCliente.setBounds(0, 35,654, 30);
		nombreCliente.setForeground(Color.lightGray );
		nombreCliente.setFont(new Font( "Arial", 1, 20 ));
		nombreCliente.setVerticalAlignment(CENTER);
		nombreCliente.setHorizontalAlignment(CENTER);
		getPanel().add(nombreCliente);
	}

	public void accionesFactura() {
		posXBotones = 15;
		for (int i = 0; i < tituloCampos.length; i++) {
			getLabelCampos()[i].setText(tituloCampos[i]);
		}
		for (int i = 0; i < labelTitulos.length; i++) {
			labelTitulos[i] = new JLabel(titulos[i]);
			labelTitulos[i].setBounds(posXtitulo, getLabelCampos()[0].getY(), 250, 30);
			labelTitulos[i].setForeground(Color.lightGray);
			getPanel().add(labelTitulos[i]);
			posXtitulo = posXtitulo + 300;
		}
		for (int i = 0; i < getBotones().length; i++) {
			getBotones()[i].setLocation(posXBotones, getBotones()[0].getY());
			posXBotones = posXBotones + 155;
			getBotones()[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					try {
						if (componente.getText().equals("Agregar producto")) {
							if (!getCampos()[1].getText().equals("") && !getCampos()[2].getText().equals("")) {
								if (Integer.parseInt(spinnerCantidad.getValue().toString()) > 0) {
									Medicamento obtenerMedicamento = null;
									for (int i = 0; i < getArrayMedicamentos().size(); i++) {
										obtenerMedicamento = (Medicamento) getArrayMedicamentos().get(i);
										if (obtenerMedicamento.getInfo().equals(combobox.getSelectedItem().toString())) {
											datosTabla[0] = obtenerMedicamento.getReferencia();
											datosTabla[1] = obtenerMedicamento.getNombre();
											datosTabla[2] = spinnerCantidad.getValue().toString();
											datosTabla[3] = "" + (obtenerMedicamento.getPrecio() * Integer.parseInt(datosTabla[2]));
											modeloTabla.addRow(datosTabla);
										}
										factura.setTotal((factura.getTotal() + (Double.parseDouble(datosTabla[3])) * 1.19));
										getCampos()[4].setText("" + factura.getTotal());
										factura.setIva((factura.getIva() + (Double.parseDouble(datosTabla[3])) * 0.19));
										getCampos()[3].setText("" + factura.getIva());

									}
									try {
										detalle.crearDetalle(factura.getIdFactura(), datosTabla[0], datosTabla[1], datosTabla[2], datosTabla[3] );
										System.out.println(detalle.getDetalles());

									} catch (NumberFormatException exc) {
										JOptionPane.showMessageDialog(null, "Algunos campos solo pueden contener valores numericos enteros.");
									}

								} else {
									JOptionPane.showMessageDialog(null, "La cantidad de medicamentos debe ser mayor a 0.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
							}
						} else if (componente.getText().equals("Crear factura")) {
							try {
								if (modeloTabla.getRowCount() > 0) {
									factura.crearFactura(factura.getIdFactura(), Long.parseLong(getCampos()[1].getText()), getCampos()[2].getText(), Double.parseDouble(getCampos()[3].getText()), Double.parseDouble(getCampos()[4].getText()), combobox.getSelectedItem().toString(), datosTabla[0], datosTabla[1], datosTabla[2], Long.parseLong(datosTabla[3]), getNombreUsuario().getText());
									System.out.println(factura.getfacturas());
									JOptionPane.showMessageDialog(null, "Factura N°: " + factura.getIdFactura() + " creada." + factura.getUsuario());
									factura.incrementarIdFactura();
									getCampos()[0].setText("" + factura.getIdFactura());
									modeloTabla.setRowCount(0);
									factura.setTotal(0);
									getCampos()[4].setText("" + factura.getTotal());
									factura.setIva(0);
									getCampos()[3].setText("" + factura.getIva());
									spinnerCantidad.setValue(0);
								} else {
									JOptionPane.showMessageDialog(null, "Debe tener agregado productos para crear la factura..");
								}
							} catch (NumberFormatException exc) {
								JOptionPane.showMessageDialog(null, "Error a la hora de crear la factura: " + exc);

							}
						} else if (componente.getText().equals("Buscar factura")) {
							try {
								detalle.mostrarDetalles(factura.getfacturas(), getCampos()[0], getCampos()[1], getCampos()[2], getCampos()[3], getCampos()[4], modeloTabla, datosTabla);
								spinnerCantidad.setValue(0);
							} catch (NullPointerException ex) {

							}

						} else if (componente.getText().equals("Limpiar campos")) {
							getCampos()[0].setText("" + factura.getIdFactura());
							getCampos()[1].setText("");
							getCampos()[2].setText("");
							getCampos()[3].setText("19%");
							getCampos()[4].setText("$");
							modeloTabla.setRowCount(0);

						}
					} catch (NullPointerException exc) {
						JOptionPane.showMessageDialog(null, "Error al realizar la accion: " + exc);
					}
				}

				@Override

				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}
			}
			);
		}
		getCampos()[1].addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Timer(1, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Clientes auxCliente = null;
						for (int i = 0; i < getCliente().getClientes().size(); i++) {
							auxCliente = (Clientes) getCliente().getClientes().get(i);
							if (getCampos()[1].getText().equals("" + auxCliente.getCedula())) {
								nombreCliente.setText("Cliente: " + auxCliente.getNombres() + " " + auxCliente.getApellidos() );
								break;
							} else {
								nombreCliente.setText("" );
						
							}
						}
					}
				}).start();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}

	public void crearColumnas() {
		modeloTabla.addColumn("Referencia");
		modeloTabla.addColumn("Medicamento");
		modeloTabla.addColumn("Cantidad");
		modeloTabla.addColumn("Subtotal");

	}

	public ArrayList getArrayMedicamentos() {
		return arrayMedicamentos;
	}

	public ArrayList getArrayMedicamentos(ArrayList<Medicamento> arrayMedicamentos) {
		this.arrayMedicamentos = arrayMedicamentos;
		return arrayMedicamentos;
	}

	public Factura getThisFactura() {
		return factura;
	}

	public DetalleFactura getThisDetalleFactura() {
		return detalle;
	}

	public String[] getDatos() {
		return datosTabla;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public JLabel getNombreCliente() {
		return nombreCliente;
	}

	public JLabel getNombreUsuario() {
		return nombreUsuario;
	}
	
	
}
