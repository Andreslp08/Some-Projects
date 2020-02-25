package Gui;

import drogueria.Clientes;
import drogueria.Medicamento;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GuiClientes extends GuiMedicamentos {

	private String[] textosLabel = {"Nombres", "Apellidos", "Cedula", "Dirección", "Telefono"};
	private String nombres, apellidos, direccion;
	private long telefono, cedula;
	private Clientes clientes = new Clientes();
	private Object rowSeleccionado;

	public GuiClientes() {
		configGuiClientes();

	}

	public void configGuiClientes() {
		getTitulo().setText("Registrar clientes");
		for (int i = 0; i < getLabelCampos().length; i++) {
			getLabelCampos()[i].setText(textosLabel[i]);
		}
		for (int i = 0; i < getBotones().length; i++) {
			botones[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					try {
						switch (componente.getText()) {
							case "Guardar":
								validarCedula();
								if (validarCedula() == true) {
									clientes.agregarCliente(getCampos()[0].getText(), getCampos()[1].getText(), Long.parseLong(getCampos()[2].getText()), getCampos()[3].getText(), Long.parseLong(getCampos()[4].getText()));
									getModeloLista().addElement("<html><font color = #747271 >Nombres:</font> " + getCampos()[0].getText() + " <font color = #747271 >Apellidos:</font> " + getCampos()[1].getText() + " <font color = #747271 >Cedula:</font> " + getCampos()[2].getText() + " <font color = #747271 >Dirección:</font> $" + getCampos()[3].getText() + " <font color = #747271 )>Telefono:</font> " + getCampos()[4].getText() + "</html>");
									JOptionPane.showMessageDialog(null, "El cliente se guardo correctamente.");
									limpiarCampos();
								} else {
									JOptionPane.showMessageDialog(null, "La cedula ya existe.");
								}
								break;
							case "Actualizar":
								Clientes auxClientes = null;
								rowSeleccionado = getLista().getSelectedValue();
								for (int i = 0; i < clientes.getClientes().size(); i++) {
									auxClientes = (Clientes) clientes.getClientes().get(i);
									if (auxClientes.getInfo().equals(rowSeleccionado)) {
										auxClientes.actualizarCliente(getCampos(0), getCampos(1), Long.parseLong(getCampos(2)), getCampos(3), Long.parseLong(getCampos(4)));
										getModeloLista().set(i, auxClientes.getInfo());
										JOptionPane.showMessageDialog(null, "Se actualizaron los datos correctamente.");
										limpiarCampos();
									}

								}
								break;
							case "Eliminar":
								auxClientes = null;
								if (clientes.getClientes().size() > 0) {
									rowSeleccionado = getLista().getSelectedValue();
									for (int i = 0; i < getModeloLista().size(); i++) {
										auxClientes = (Clientes) clientes.getClientes().get(i);
										if (auxClientes.getInfo().equals(rowSeleccionado)) {
											clientes.eliminarCliente((Clientes) clientes.getClientes().get(i));
											System.err.println(clientes.getClientes());
											getModeloLista().remove(i);
											JOptionPane.showMessageDialog(null, "Se elimino el cliente correctamente.");
											limpiarCampos();
										}

									}
								} else {
									JOptionPane.showMessageDialog(null, "No se encuentra registrado clientes.");
								}
								break;
							case "Limpiar":
								getCampos()[2].setEditable(true);
								getLabelCampos()[2].setText(textosLabel[2]);
								limpiarCampos();
								break;
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios, y cedula y telefono  solo pueden contener digitos.");
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

		}
		getLista().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Clientes auxClientes = null;
				getCampos()[2].setEditable(false);
				getLabelCampos()[2].setText(textosLabel[2] + "( no editable )");
				rowSeleccionado = getLista().getSelectedValue();
				for (int i = 0; i < getModeloLista().size(); i++) {
					auxClientes = (Clientes) clientes.getClientes().get(i);
					if (auxClientes.getInfo().equals(rowSeleccionado)) {
						getCampos()[0].setText(auxClientes.getNombres());
						getCampos()[1].setText(auxClientes.getApellidos());
						getCampos()[2].setText("" + auxClientes.getCedula());
						getCampos()[3].setText(auxClientes.getDireccion());
						getCampos()[4].setText("" + auxClientes.getTelefono());
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
	}

	public boolean validarCedula() {
		Clientes auxClientes = null;
		for (int i = 0; i < clientes.getClientes().size(); i++) {
			auxClientes = (Clientes) clientes.getClientes().get(i);
			if (auxClientes.getCedula() == Long.parseLong(getCampos(2))) {
				return false;
			}
		}
		return true;
	}

	public Clientes getThisClientes() {
		return clientes;
	}
}
