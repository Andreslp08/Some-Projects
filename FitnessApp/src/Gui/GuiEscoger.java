package Gui;

import Interfaces.Temas;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class GuiEscoger extends JFrame implements Temas {

	private JPanel panelPrincipal, panelContenedor;
	private JLabel[] botones;
	private int posY;
	private String seleccion;
	

	public GuiEscoger() {
		panelPrincipal = new JPanel();
		panelContenedor = new JPanel();
		botones = new JLabel[themes.length];
		panelPrincipal.add(panelContenedor);
		for (int i = 0; i < themes.length; i++) {
			botones[i] = new JLabel(themes[i]);
			botones[i].setForeground(Color.red);
			botones[i].setVerticalAlignment(CENTER);
			botones[i].setHorizontalAlignment(CENTER);
			botones[i].setOpaque(true);
			botones[i].setFont(new Font("Gabriola", 2, 25));
			panelContenedor.add(botones[i]);
			botones[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();

				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					cambiarColorAccionBoton(1, componente);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					seleccion = componente.getText();
					for (int i = 0; i < themes.length; i++) {
						if (seleccion.equals(themes[i])) {
							aplicarTema(seleccion, fondos[i], fuentes[i], acceso[i]);
							panelPrincipal.setVisible(false);
						}
					}

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					cambiarColorAccionBoton(0, componente);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					cambiarColorAccionBoton(3, componente);
				}
			});
		}
		panelPrincipal.setVisible(false);
		seleccion = "Claro";
		for (int i = 0; i < themes.length; i++) {
			if ("Claro".equals(themes[i])) {
				aplicarTema(themes[i], fondos[i], fuentes[i], acceso[i]);
			}

		}
	}

	public void gui( int x, int y, int w, int h) {
		panelPrincipal.setBounds(x, y, w, h);
		panelPrincipal.setBackground(Color.black);
		panelPrincipal.setLayout(null);
		panelContenedor.setBounds(0, 0, w, h);
		panelContenedor.setLayout(new GridLayout(1, botones.length));
	}

	public JPanel getVentanaPrincipal() {
		return panelPrincipal;
	}

	@Override
	public Color obtenerColor(Color[] color, int num) {
		return color[num];
	}

	@Override
	public void aplicarTema(String tema, Color[] colorFondo, Color[] colorFuente, Color[] colorAcceso) {
		for (JLabel i : botones) {
			cambiarColorBotones(0, null, obtenerColor(colorAcceso, 0), obtenerColor(colorFuente, 0));
		}
	}

	@Override
	public void cambiarColorBordes(int opcion, JComponent componente, Color color) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void cambiarColorCampos(int opcion, JComponent componente, Color fondo, Color letra) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void cambiarColorBotones(int opcion, JComponent componente, Color fondo, Color letra) {
		switch (opcion) {
			case 0:
				for (JLabel i : botones) {
					i.setBackground(fondo);
					i.setForeground(letra);
				}
				break;

			case 1:
				componente.setBackground(fondo);
				componente.setForeground(letra);
				break;
		}
	}

	@Override
	public void cambiarColorAccionBoton(int opcion, JLabel componente) {
		for (int i = 0; i < themes.length; i++) {
			if (seleccion.equals(themes[i])) {
				switch (opcion) {
					case 0:
						cambiarColorBotones(1, componente, obtenerColor(acceso[i], 1), obtenerColor(fuentes[i], 0));
						break;
					case 1:
						cambiarColorBotones(1, componente, obtenerColor(acceso[i], 2), obtenerColor(fuentes[i], 0));
						break;
					case 2:
						cambiarColorBotones(1, componente, obtenerColor(acceso[i], 0), obtenerColor(fuentes[i], 0));
						break;
					case 3:
						cambiarColorBotones(1, componente, obtenerColor(acceso[i], 0), obtenerColor(fuentes[i], 0));
						break;
				}

			}
		}
	}

	@Override
	public void cambiarColorAccionCampo(int opcion, JTextField componente) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void cambiarTema() {

	}

	public String getSeleccion() {
		return seleccion;
	}

	public JLabel[] getBotonesOpciones() {
		return botones;
	}
}
