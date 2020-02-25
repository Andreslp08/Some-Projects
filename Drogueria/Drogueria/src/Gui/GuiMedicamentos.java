package Gui;

import drogueria.Medicamento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class GuiMedicamentos extends JFrame {

	private ArrayList medicamentos = new ArrayList<Medicamento>();
	private JTextField[] campos;
	private JLabel[] labelCampos;
	private String[] tituloCampo = {"Referencia", "Nombre", "Laboratorio", "Precio", "Presentacion"};
	private String[] tituloBotones = {"Guardar", "Actualizar", "Eliminar", "Limpiar", "-", "x"};
	JPanel panel;
	private JLabel titulo;
	private JLabel tituloLista;
	private int posY, posY2, posY3;
	JLabel botones[];
	private DefaultListModel modeloLista;
	private JList lista;
	private Medicamento medicamento = new Medicamento();
	private JScrollPane scroll;
	private int sizeW = 280;
	private int sizeH = 400;
	private int xMouse, yMouse;

	private Object rowSeleccionado;
	private boolean validarEsNumero, validarReferencia;

	public GuiMedicamentos() {
		this.setUndecorated(true);
		this.setSize(1000, 530);
		panel = new JPanel();
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		titulo = new JLabel("Registro de medicamentos");
		titulo.setBounds(panel.getX(), panel.getY(), panel.getWidth(), 30);
		labelCampos = new JLabel[tituloCampo.length];
		campos = new JTextField[tituloCampo.length];
		botones = new JLabel[tituloBotones.length];
		lista = new JList();
		modeloLista = new DefaultListModel();
		scroll = new JScrollPane();
		lista.setModel(modeloLista);
		posY = 60;
		posY2 = 90;
		posY3 = 400;
		acciones();
	}

	public void abrirGui() {
		this.setVisible(true);
		panel.setBackground(Color.darkGray);
		panel.setLayout(null);
		titulo.setOpaque(true);
		titulo.setBackground(new Color(55, 55, 55, 255));
		titulo.setForeground(Color.white);
		titulo.setVerticalAlignment(CENTER);
		titulo.setHorizontalAlignment(CENTER);
		panel.add(titulo);
		scroll.setBounds(200, 80, 780, 400);
		panel.add(scroll);
		scroll.setFocusable(false);
		scroll.setBorder(null);
		scroll.setBackground(new Color(40, 40, 40, 255));
		scroll.setForeground(Color.red);
		scroll.setViewportView(lista);
		scroll.setOpaque(true);
		this.add(panel);
		lista.setBounds(200, 80, 280, 400);
		lista.setBackground(new Color(60, 60, 60, 255));
		lista.setFont(new Font("Arial Narrow", 1, 20));
		lista.setForeground(Color.white);
		lista.setAutoscrolls(true);
		lista.setBorder(null);
		lista.setSelectionBackground(new Color(70, 70, 70, 255));
		lista.setSelectionForeground(Color.white);
		lista.setFocusable(false);
		this.setLocationRelativeTo(null);
	}

	public void acciones() {
		for (int i = 0; i < tituloCampo.length; i++) {
			labelCampos[i] = new JLabel(tituloCampo[i]);
			labelCampos[i].setBounds(20, posY, 150, 30);
			labelCampos[i].setForeground(Color.lightGray);
			panel.add(labelCampos[i]);
			posY = posY + 65;
			campos[i] = new JTextField("");
			campos[i].setBounds(20, posY2, 150, 30);
			campos[i].setForeground(Color.white);
			campos[i].setBackground(Color.darkGray);
			campos[i].setFont(new Font("Arial Narrow", 1, 15));
			//255, 87, 51, 255
			campos[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(55, 55, 55, 255)));
			posY2 = posY2 + 65;
			panel.add(campos[i]);
			campos[i].addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {

				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseReleased(MouseEvent e) {

				}

				public void mouseEntered(MouseEvent e) {
					JTextField componente = (JTextField) e.getComponent();
					componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 87, 51, 255)));

				}

				public void mouseExited(MouseEvent e) {
					JTextField componente = (JTextField) e.getComponent();
					componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(55, 55, 55, 255)));

				}
			});

		}
		for (int j = 0; j < tituloBotones.length; j++) {
			botones[j] = new JLabel(tituloBotones[j]);
			botones[j].setBounds(20, posY3, 150, 25);
			botones[j].setOpaque(true);
			botones[j].setBackground(new Color(55, 55, 55, 255));
			botones[j].setForeground(Color.white);
			botones[j].setHorizontalAlignment(CENTER);
			botones[j].setVerticalAlignment(CENTER);
			if (botones[j].getText().equals("x")) {
				botones[j].setBounds(titulo.getWidth() - 30, 0, 30, 30);
				System.out.println(titulo.getWidth());
				titulo.add(botones[j]);

			} else if (botones[j].getText().equals("-")) {
				botones[j].setBounds(titulo.getWidth() - 60, 0, 30, 30);
				System.out.println(botones);
				titulo.add(botones[j]);

			} else {
				panel.add(botones[j]);
				posY3 = posY3 + 30;
			}
			botones[j].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					e.getComponent().setBackground(new Color(200, 60, 40, 255));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					e.getComponent().setBackground(new Color(255, 87, 51, 255));
					if (getTitulo().getText().equals("Registro de medicamentos")) {
						switch (componente.getText()) {
							case "Guardar":
								validarEsNum();
								if (!getCampos(0).equals("") && !getCampos(1).equals("") && !getCampos(2).equals("") && !getCampos(3).equals("") && !getCampos(4).equals("")) {
									validarReferencia();
									if (validarReferencia == true) {
										if (validarEsNumero == true) {
											medicamento.agregarMedicamento(getCampos(0), getCampos(1), getCampos(2), Integer.parseInt(getCampos(3)), getCampos(4));
											modeloLista.addElement("<html><font color = #747271 >Referencia:</font> " + getCampos(0) + " <font color = #747271 >Nombre:</font> " + getCampos(1) + " <font color = #747271 >Laboratorio:</font> " + getCampos(2) + " <font color = #747271 >Precio:</font> $" + Integer.parseInt(getCampos(3)) + " <font color = #747271 >Presentacion:</font> " + getCampos(4) + "</html>");
											limpiarCampos();
											if (modeloLista.size() > 15) {
												sizeH = sizeH + 26;
												System.out.println(sizeH);
												lista.setPreferredSize(new Dimension(2000, sizeH));
											}
										} else {
											JOptionPane.showMessageDialog(null, "El precio solo puede contener numeros.");
										}
									} else {
										JOptionPane.showMessageDialog(null, "La referencia ya existe.");
									}
								} else {
									JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
								}

								break;

							case "Actualizar":
								try {
									if (modeloLista.getSize() <= 0) {
										JOptionPane.showMessageDialog(null, "No se pueden actualizar los datos ( El registro esta vacio )");
									} else {
										if (!getCampos(0).equals("") && !getCampos(1).equals("") && !getCampos(2).equals("") && !getCampos(3).equals("") && !getCampos(4).equals("")) {
											validarEsNum();

											if (validarEsNumero == true) {
												actualizarMedicamento();
												limpiarCampos();

											} else {
												JOptionPane.showMessageDialog(null, "El precio solo puede contener numeros.");
											}

										} else {
											JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
										}
									}
								} catch (NullPointerException exc) {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento para poder gestionarlo.");
								}
								break;
							case "Eliminar":
								try {
									if (modeloLista.getSize() <= 0) {
										JOptionPane.showMessageDialog(null, "No se pueden eliminar los datos ( El registro esta vacio )");
									} else {
										int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar permanentemente este medicamento del registro ?");
										if (confirmar == 0) {
											eliminarMedicamento();
											limpiarCampos();
											if (modeloLista.size() > 0) {
												sizeH = sizeH - 26;
												System.out.println(sizeH);
												lista.setPreferredSize(new Dimension(2000, sizeH));
											} else {
												sizeH = 400;
												System.out.println(sizeH);
												lista.setPreferredSize(new Dimension(2000, sizeH));
											}
										}
									}
								} catch (NullPointerException exc) {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento para poder eliminarlo.");
								}
								break;
						}
					}
					switch (componente.getText()) {
						case "Limpiar":
							limpiarCampos();
							break;
						case "-":
							setExtendedState(1);
							break;
						case "x":
							getVentana().setVisible(false);
							break;
					}
				}

				@Override
				public void mouseEntered(MouseEvent e
				) {
					e.getComponent().setBackground(new Color(255, 87, 51, 255));
				}

				@Override
				public void mouseExited(MouseEvent e
				) {
					e.getComponent().setBackground(new Color(55, 55, 55, 255));
				}
			}
			);

			lista.addMouseListener(
				new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e
				) {

				}

				@Override
				public void mousePressed(MouseEvent e
				) {

				}

				@Override
				public void mouseReleased(MouseEvent e
				) {
					if (titulo.getText().equals("Registro de medicamentos")) {
						try {
							rowSeleccionado = lista.getSelectedValue();

							for (int j = 0; j < modeloLista.size(); j++) {
								if (rowSeleccionado.equals(((Medicamento) medicamento.obtenerMedicamentos(j)).obtenerInfo())) {
									campos[0].setText(medicamento.obtenerMedicamentos(j).getReferencia());
									campos[1].setText(medicamento.obtenerMedicamentos(j).getNombre());
									campos[2].setText(medicamento.obtenerMedicamentos(j).getLaboratorio());
									campos[3].setText("" + medicamento.obtenerMedicamentos(j).getPrecio());
									campos[4].setText(medicamento.obtenerMedicamentos(j).getPresentacion());
								}
							}
						} catch (NullPointerException exc) {
							System.out.println("Debe seleccionar un elemento para poder gestionarlo.");
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e
				) {

				}

				@Override
				public void mouseExited(MouseEvent e
				) {

				}
			});

			titulo.addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
					VentanaPresionada(e);
					titulo.setBackground(new Color(50, 50, 50, 255));
				}

				public void mouseReleased(MouseEvent e) {
					titulo.setBackground(new Color(55, 55, 55, 255));
				}

				public void mouseEntered(MouseEvent e) {

				}

				public void mouseExited(MouseEvent e) {

				}
			});

			titulo.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					MoverVentana(e);
				}
			});

		}
	}

	public void validarEsNum() {
		validarReferencia = true;
		for (int k = 0; k < getCampos(3).length(); k++) {
			char caracter = getCampos(3).charAt(k);
			if (Character.isDigit(caracter)) {
				validarEsNumero = true;
			} else {
				validarEsNumero = false;
			}
		}

	}

	public boolean validarReferencia() {

		for (int i = 0; i < medicamento.getArray().size(); i++) {
			if (((Medicamento) medicamento.getArray().get(i)).getReferencia().equals(getCampos(0))) {
				return validarReferencia = false;
			}
		}
		return validarReferencia = true;
	}

	public String getCampos(int i) {
		return campos[i].getText();
	}

	public void VentanaPresionada(MouseEvent evt) {
		xMouse = evt.getX(); // tomar coordenadas de mouse
		yMouse = evt.getY();
	}

	public void MoverVentana(MouseEvent evt) {
		int x = evt.getXOnScreen(); // tomar coordenadas de pantalla
		int y = evt.getYOnScreen();
		setLocation(x - xMouse, y - yMouse);
	}

	public void actualizarMedicamento() {
		for (int j = 0; j < modeloLista.size(); j++) {
			if (rowSeleccionado.equals(((Medicamento) medicamento.obtenerMedicamentos(j)).obtenerInfo())) {
				((Medicamento) medicamento.obtenerMedicamentos(j)).actualizarMedicamento(((Medicamento) medicamento.obtenerMedicamentos(j)).getReferencia(), getCampos(1), getCampos(2), Integer.parseInt(getCampos(3)), getCampos(4));
				if (!((Medicamento) medicamento.obtenerMedicamentos(j)).getReferencia().equals(getCampos(0))) {
					JOptionPane.showMessageDialog(null, "La referencia no se puede modificar.");
				} else {
					modeloLista.set(j, ((Medicamento) medicamento.obtenerMedicamentos(j)).obtenerInfo());
					JOptionPane.showMessageDialog(null, "Se actualizaron los datos correctamente.");
				}
				break;
			}
		}
	}

	public void eliminarMedicamento() {
		for (int j = 0; j < modeloLista.size(); j++) {
			if (rowSeleccionado.equals(((Medicamento) medicamento.obtenerMedicamentos(j)).obtenerInfo())) {
				medicamento.eliminarMedicamento((Medicamento) medicamento.obtenerMedicamentos(j));
				modeloLista.remove(j);
				break;
			}

		}
	}

	public void limpiarCampos() {
		for (JTextField i : campos) {
			if (!i.getText().equals("")) {
				i.setText("");
				i.setEditable(true);
			}
		}
	}

	public JFrame getVentana() {
		return this;
	}

	public void visibilidadGuiMedicamento(boolean tof) {
		for (JLabel boton : botones) {
			if (!boton.getText().equals("x") && !boton.getText().equals("-") && !boton.getText().equals("Agregar producto") && !boton.getText().equals("Crear factura") && !boton.getText().equals("Buscar factura") && !boton.getText().equals("Limpiar campos")) {
				boton.setVisible(tof);
			}
		}
		scroll.setVisible(tof);
		lista.setValueIsAdjusting(tof);
	}

	public void ocultarTodo() {
		for (JLabel boton : botones) {
			if (!boton.getText().equals("x") && !boton.getText().equals("-") && !boton.getText().equals("Registrar medicamentos") && !boton.getText().equals("Registrar facturas") && !boton.getText().equals("Ver facturas") && !boton.getText().equals("Registrar clientes")) {
				boton.setVisible(false);
			}
		}
		scroll.setVisible(false);
		lista.setValueIsAdjusting(false);
		for (JTextField j : campos) {
			j.setVisible(false);
		}
		for (JLabel j : labelCampos) {
			j.setVisible(false);
		}
	}

	public JTextField[] getCampos() {
		return campos;
	}

	public JLabel[] getLabelCampos() {
		return labelCampos;
	}

	public String[] getTituloCampo() {
		return tituloCampo;
	}

	public String[] getTituloBotones() {
		return tituloBotones;
	}

	public JLabel[] getBotones() {
		return botones;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public JList getLista() {
		return lista;
	}

	public JPanel getPanel() {
		return panel;
	}

	public String xd() {
		return "";
	}

	public ArrayList getArrayMedicamentos() {
		return medicamento.getArray();
	}

	public DefaultListModel getModeloLista() {
		return modeloLista;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public Object getRowSeleccionado() {
		return rowSeleccionado;
	}

}
