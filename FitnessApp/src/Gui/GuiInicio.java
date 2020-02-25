package Gui;

import Interfaces.Temas;
import Logica.Hilos;
import com.sun.awt.AWTUtilities;
import com.sun.javafx.tk.RenderJob;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class GuiInicio extends JFrame implements Temas {

	private JLabel fondo;
	private ImageIcon icono, icono2;
	private Image imagen, imagen2;
	private JLabel barra;
	private Shape forma;
	private int posXf, posYf, wf, hf;
	private Hilos hilos = new Hilos();
	private GuiInfoFisica guiInfoFisica;
	private static GuiInformacion guiInformacion;
	private GuiEscoger guiEscoger = new GuiEscoger();
	private float opacity;
	private String[] textoBotones = {"×", "•", "≡"};
	private JLabel[] botones;
	private JLabel titulo;
	private Notificacion notificacion;
	private String[] textoBotonesAcceso = {"Registrar información", "Ver información", "Rutina"};
	private JLabel[] botonesAcceso;
	private int posYba;

	public GuiInicio() {
		posXf = 0;
		posYf = 0;
		posYba = 310;
		wf = 320;
		hf = 600;
		this.setUndecorated(true);
		this.setOpacity(hilos.getOpacity());
		this.setVisible(true);
		this.setSize(wf, hf);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		botonesAcceso = new JLabel[textoBotonesAcceso.length];
		botones = new JLabel[textoBotones.length];
		forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 50, 50);
		AWTUtilities.setWindowShape(this, forma);
		fondo = new JLabel();
		fondo.setBounds(0, 0, this.getWidth(), this.getHeight());
		fondo.setOpaque(true);
		this.add(fondo);
		icono = new ImageIcon(this.getClass().getResource("/img/fondo.jpg"));
		imagen = icono.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH);
		icono = new ImageIcon(imagen);
		fondo.setIcon(icono);
		barra = new JLabel();
		barra.setBounds(fondo.getWidth(), 0, this.getWidth() + 280, 30);
		barra.setBackground(Color.gray);
		barra.setOpaque(true);
		barra.setLayout(null);
		this.add(barra);
		accion();
		guiInfoFisica = new GuiInfoFisica(this);
		guiInfoFisica.gui(fondo.getWidth(), 60, 600, 540);
		this.add(guiEscoger.getVentanaPrincipal());
		cambiarTema();
		aplicarTema("Claro", fondosClaro, fuentesClaro, accesoClaro);
		titulo = new JLabel();
		titulo.setBounds(0, 0, this.getWidth(), 150);
		fondo.add(titulo);
		icono2 = new ImageIcon(this.getClass().getResource("/img/titulo.png"));
		imagen2 = icono2.getImage().getScaledInstance(titulo.getWidth(), titulo.getHeight(), Image.SCALE_SMOOTH);
		icono2 = new ImageIcon(imagen2);
		titulo.setIcon(icono2);
		hilos.opacidadVentana(getGuiInicio());
	}

	public void accion() {
		for (int i = 0; i < botonesAcceso.length; i++) {
			botonesAcceso[i] = new JLabel(textoBotonesAcceso[i]);
			botonesAcceso[i].setBounds(20, posYba, fondo.getWidth() - 40, 50);
			botonesAcceso[i].setOpaque(true);
			botonesAcceso[i].setBackground(new Color(0, 0, 0, 100));
			botonesAcceso[i].setFont(new Font("Candara", 1, 25));
			botonesAcceso[i].setForeground(Color.white);
			botonesAcceso[i].setHorizontalAlignment(CENTER);
			botonesAcceso[i].setVerticalAlignment(CENTER);
			fondo.add(botonesAcceso[i]);
			posYba = posYba + 65;
			botonesAcceso[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(0, 0, 0, 200));
					componente.repaint();
					fondo.repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					hilos.animVentana(getGuiInicio());
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(0, 0, 0, 150));
					componente.repaint();
					fondo.repaint();
					if (componente.getText().equals(textoBotonesAcceso[0])) {
						guiInfoFisica.getPanel().setVisible(true);
						if (guiInformacion != null) {
							guiInformacion.setVisible(false);
						}
					} else if (componente.getText().equals(textoBotonesAcceso[1])) {
						guiInformacion = new GuiInformacion(getGuiInicio());
						getFrame().add(guiInformacion);
						guiInformacion.setVisible(true);
						guiInfoFisica.getPanel().setVisible(false);
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(0, 0, 0, 150));
					componente.repaint();
					fondo.repaint();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(0, 0, 0, 100));
					componente.repaint();
					fondo.repaint();
				}
			});

		}
		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JLabel(textoBotones[i]);
			botones[i].setOpaque(true);
			botones[i].setHorizontalAlignment(CENTER);
			botones[i].setVerticalAlignment(CENTER);
			switch (botones[i].getText()) {
				case "×":
					botones[i].setBounds(barra.getWidth() - 60, 0, 30, 30);
					botones[i].setFont(new Font("Arial Narrow", 1, 30));
					barra.add(botones[i]);
					break;
				case "•":
					botones[i].setBounds(barra.getWidth() - 90, 0, 30, 30);
					botones[i].setFont(new Font("Arial Narrow", 1, 30));
					barra.add(botones[i]);
					break;
				case "≡":
					botones[i].setBounds(barra.getWidth() - 120, 0, 30, 30);
					botones[i].setFont(new Font("Arial Narrow", 1, 30));
					barra.add(botones[i]);
					break;
			}
			botones[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					cambiarColorAccionBoton(1, componente);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					cambiarColorAccionBoton(2, componente);
					switch (componente.getText()) {
						case "×":
							System.exit(0);
							break;
						case "•":
							setExtendedState(1);
							break;
						case "≡":
							guiEscoger.gui(fondo.getWidth(), 30, getFrame().getWidth() - fondo.getWidth(), 30);
							if (!guiEscoger.getVentanaPrincipal().isVisible()) {
								guiEscoger.getVentanaPrincipal().setVisible(true);
							} else {
								guiEscoger.getVentanaPrincipal().setVisible(false);
							}
							break;
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
	}

	public JLabel getBarra() {
		return barra;
	}

	public JFrame getFrame() {
		return this;
	}

	public GuiInicio getGuiInicio() {
		return this;
	}

	@Override
	public Color obtenerColor(Color[] color, int num) {
		return color[num];
	}

	@Override
	public void aplicarTema(String tema, Color[] colorFondo, Color[] colorFuente, Color[] colorAcceso) {
		barra.setBackground(colorFondo[1]);
		getFrame().setBackground(colorFondo[0]);
		getFrame().getContentPane().setBackground(colorFondo[0]);
		cambiarColorBotones(0, null, colorAcceso[0], colorFuente[0]);
	}

	@Override
	public void cambiarColorBordes(int opcion, JComponent componente, Color color) {

	}

	@Override
	public void cambiarColorCampos(int opcion, JComponent componente, Color fondo, Color letra) {

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
			if (guiEscoger.getSeleccion().equals(themes[i])) {
				switch (opcion) {
					case 0:
						cambiarColorBotones(1, componente, obtenerColor(acceso[i], 1), obtenerColor(fuentes[i], 0));
						break;
					case 1:
						cambiarColorBotones(1, componente, obtenerColor(acceso[i], 2), obtenerColor(fuentes[i], 0));
						break;
					case 2:
						cambiarColorBotones(1, componente, obtenerColor(acceso[i], 1), obtenerColor(fuentes[i], 0));
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

	}

	@Override
	public void cambiarTema() {
		for (int i = 0; i < guiEscoger.getBotonesOpciones().length; i++) {
			guiEscoger.getBotonesOpciones()[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					System.err.println(componente.getText());
					for (int j = 0; j < themes.length; j++) {
						if (guiEscoger.getSeleccion().equals(themes[j])) {
							System.err.println("xd");
							aplicarTema(themes[j], fondos[j], fuentes[j], acceso[j]);
							guiInfoFisica.aplicarTema(themes[j], fondos[j], fuentes[j], acceso[j]);
							guiInformacion.aplicarTema(themes[j], fondos[j], fuentes[j], acceso[j]);
						}

					}
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
	}

	public JLabel getFondo() {
		return fondo;
	}

	public String getSeleccion() {
		return guiEscoger.getSeleccion();
	}

	public String[] getArrayDatosInfoFisica() {
		return guiInfoFisica.getTituloCampos();
	}
}
