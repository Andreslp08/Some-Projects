package Gui;

import com.sun.media.jfxmedia.AudioClip;
import logica.Audio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GuiMenu extends JLabel implements Runnable {

	private ImageIcon fondoIcono, tituloIcono;
	private Image fondoImg, tituloImg;
	private String[] textoOpciones = {"Iniciar partida", "Configuración", "Salir"};
	private JLabel[] opciones = new JLabel[textoOpciones.length];
	private int posX, posY, posXg, posYg;
	private Color[] colorGopciones;
	private Color colorGpanelContenedor;
	private int wPanelContenedorG, hPanelContenedorH;
	private JLabel titulo;
	private GuiBotonesPrincipales guiBotonesPrincipales;
	private GuiDialog jDialog;
	private Audio audioMouse, audioIntro;
	private GuiPartida guiPartida;
	private JLabel panelContenedor;
	private Thread hilo;
	private GuiPrincipal ventanaPrincipal;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// resoluciones
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int UW = (int) screenSize.getWidth(), UH = (int) screenSize.getHeight();
	private final int MW = 1920, MH = 1080;

	//getters resolucion
	public int intW(int num) {
		return (num * UW) / MW;
	}

	public int intH(int num) {
		return (num * UH) / MH;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public GuiMenu(GuiPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.setBounds(intW(0), intH(0), intW(MW), intH(MH));
		this.setLayout(null);
		//hilo
		hilo = new Thread(this);
		hilo.start();
		//audio mouse
		audioMouse = new Audio();
		//
		audioIntro = new Audio();
		audioIntro.reproducirSonido("/sonidos/Intro.wav", true);
		audioIntro.cambiarVolumen(-40);
		//fondo
		fondoIcono = new ImageIcon(getClass().getResource("/img/fondoMenu.jpg"));
		fondoImg = fondoIcono.getImage().getScaledInstance(intW(MW), intH(MH), Image.SCALE_SMOOTH);
		fondoIcono = new ImageIcon(fondoImg);
		ventanaPrincipal.add(this);
		this.setIcon(fondoIcono);
		// panel contenedor
		colorGpanelContenedor = new Color(20, 20, 20, 100);
		wPanelContenedorG = intW(0);
		hPanelContenedorH = intH(0);
		panelContenedor = new JLabel();
		panelContenedor.setBounds(intW(540), intH(290), wPanelContenedorG, hPanelContenedorH);
		panelContenedor.setLayout(null);
		this.add(panelContenedor);
		//gui partida
		guiPartida = new GuiPartida(this);
		panelContenedor.add(guiPartida);
		// botones principales
		guiBotonesPrincipales = new GuiBotonesPrincipales(this, ventanaPrincipal);
		// dialog
		jDialog = new GuiDialog();
		for (int i = 0; i < jDialog.getBotones().length; i++) {
			jDialog.getBotones()[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					if (jDialog.aceptar(label) == true) {
						System.exit(0);
					}
				}

			});
		}
		// opciones
		posX = intW(20);
		posY = intH((MH - 450) / 2);
		colorGopciones = new Color[textoOpciones.length];
		for (int i = 0; i < textoOpciones.length; i++) {
			colorGopciones[i] = new Color(20, 20, 20, 100);
			JLabel opcion = opciones[i];
			opcion = new JLabel(textoOpciones[i]);
			opcion.setBounds(posX, posY, intW(500), intH(100));
			opcion.setForeground(Color.white);
			opcion.setHorizontalAlignment(CENTER);
			opcion.setVerticalAlignment(CENTER);
			opcion.setFont(new Font("Impact", 0, intW(50)));
			this.add(opcion);
			posY = posY + intH(150);
			opcion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					cambiarColorOpciones(new Color(0, 100, 255, 100), label);
					label.setFont(new Font("Impact", 0, intW(70)));
					audioMouse.reproducirSonido("/sonidos/mouseAdentro.wav", false);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					cambiarColorOpciones(new Color(0, 100, 255, 200), label);
					label.setFont(new Font("Impact", 0, intW(80)));
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					cambiarColorOpciones(new Color(0, 100, 255, 100), label);
					label.setFont(new Font("Impact", 0, intW(70)));
					repaint();
					audioMouse.reproducirSonido("/sonidos/click.wav", false);
					switch (label.getText()) {
						case "Iniciar partida":
							guiPartida.setVisible(true);
							guiPartida.restaurarTodo();
							modificarTitulo(intW((MW + (520 - 600)) / 2), intH(20), intW(600), intH(250));
							animPanelContenedor();
							break;
						case "Configuración":
							guiPartida.setVisible(false);
							modificarTitulo(intW((MW + (520 - 600)) / 2), intH(20), intW(600), intH(250));
							animPanelContenedor();
							break;
						case "Salir":
							wPanelContenedorG = intW(0);
							hPanelContenedorH = intH(0);
							jDialog.setVisible(true);
							jDialog.configDialog("¿Desea salir por completo?", new Color(20, 20, 20, 230), "Arial", 1, intW(40));
							break;
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					cambiarColorOpciones(new Color(20, 20, 20, 100), label);
					label.setFont(new Font("Impact", 0, intW(50)));
					repaint();
				}

			});
		}
		//titulo
		tituloIcono = new ImageIcon(getClass().getResource("/img/titulo.png"));
		tituloImg = tituloIcono.getImage().getScaledInstance(intW(800), intH(800), Image.SCALE_SMOOTH);
		tituloIcono = new ImageIcon(tituloImg);
		titulo = new JLabel();
		modificarTitulo(intW((MW + (520 - 800)) / 2), intH((MH - 800) / 2), intW(800), intH(800));
		titulo.setIcon(tituloIcono);
		this.add(titulo);
	}

	public void modificarTitulo(int x, int y, int w, int h) {
		tituloIcono = new ImageIcon(getClass().getResource("/img/titulo.png"));
		tituloImg = tituloIcono.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		tituloIcono = new ImageIcon(tituloImg);
		titulo.setBounds(x, y, w, h);
		titulo.setIcon(tituloIcono);
	}

	public void cambiarColorOpciones(Color color, JLabel label) {
		for (int i = 0; i < textoOpciones.length; i++) {
			if (label.getText().equals(textoOpciones[i])) {
				colorGopciones[i] = color;
			}
		}
	}

	public JLabel getThis() {
		return this;
	}

	private Timer timer;
	private boolean animCompleta = false;

	public boolean animPanelContenedor() {
//		wPanelContenedorG = this.getWidth()-560;
//		hPanelContenedorH = 770;
		wPanelContenedorG = intW(0);
		hPanelContenedorH = intH(0);
		timer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wPanelContenedorG = wPanelContenedorG + intW(70);
				hPanelContenedorH = hPanelContenedorH + intH(40);
				if (wPanelContenedorG >= getThis().getWidth() - intW(560)) {
					wPanelContenedorG = getThis().getWidth() - intW(560);
					hPanelContenedorH = intH(770);
					timer.stop();
					animCompleta = true;
				}
				repaint();
			}
		});
		timer.start();
		return animCompleta;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
		g.setColor(new Color(20, 20, 20, 100));
		g.fillRoundRect(intW(20), intH(20), intW(500), this.getHeight() - intH(40), intW(50), intH(50));
		posXg = intW(40);
		posYg = intH((MH - 450) / 2);
		for (int i = 0; i < textoOpciones.length; i++) {
			g.setColor(colorGopciones[i]);
			g.fillRoundRect(posXg, posYg, intW(455), intH(100), intW(50), intH(50));
			posYg = posYg + intH(150);
		}
		g.setColor(colorGpanelContenedor);
		g.fillRoundRect(intW(540), intH(290), wPanelContenedorG, hPanelContenedorH, intW(50), intH(50));
		panelContenedor.setSize(wPanelContenedorG, hPanelContenedorH);
	}

	public GuiPrincipal getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public GuiJuego getGuiJuego() {
		return getVentanaPrincipal().getGuiJuego();
	}

	public GuiPartida getGuiPartida() {
		return guiPartida;
	}

	@Override
	public void run() {
		cambiarAPartida();
	}

	private void cambiarAPartida() {
		int contador = 4;
		while (true) {
			if (guiPartida != null) {
				System.out.println("");
				if (guiPartida.getBotonJugar().getForeground().equals(Color.cyan)) {
					for (int i = 0; i < 6; i++) {
						if (i >= 2) {
							contador--;
							guiPartida.getBotonJugar().setText("" + contador);
							if (contador == 0) {
								this.setVisible(false);
								audioIntro.detenerSonido();
								getGuiJuego().setVisible(true);
								getGuiJuego().reproducirCancion();
								getGuiJuego().getCancion().cambiarVolumen(-30);
								// crear jugador
								getGuiJuego().getGuiJugador1().crearJugador(getGuiJuego(), guiPartida.getCampoNombre()[0].getText(), Integer.parseInt(guiPartida.getCamposRondas()[1].getText()), Integer.parseInt(guiPartida.getCamposRondas()[0].getText()));
								getGuiJuego().getGuiJugador2().crearJugador(getGuiJuego(), guiPartida.getCampoNombre()[1].getText(), Integer.parseInt(guiPartida.getCamposRondas()[1].getText()), Integer.parseInt(guiPartida.getCamposRondas()[0].getText()));
								//poner info del jugador
								getGuiJuego().getGuiJugador1().getLabelInfo().setText("<html><p>Rondas ganadas:" + getGuiJuego().getGuiJugador1().getJugador().getRondasGanadas() + "</p></html>");
								getGuiJuego().getGuiJugador2().getLabelInfo().setText("<html><p>Rondas ganadas:" + getGuiJuego().getGuiJugador2().getJugador().getRondasGanadas() + "</p></html>");
								getGuiJuego().setNombreJugador1(getGuiJuego().getGuiJugador1().getJugador().getNombre());
								getGuiJuego().setNombreJugador2(getGuiJuego().getGuiJugador2().getJugador().getNombre());
								getGuiJuego().getGuiRondas().getLabelInfo()[0].setText("<html>Rondas totales<p align=\"center\">" + guiPartida.getCamposRondas()[1].getText() + "</p></html>");
								getGuiJuego().getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + 1 + "</p></html>");
								getGuiJuego().getGuiRondas().getLabelInfo()[2].setText("<html>Rondas para ganar<p align=\"center\">" + guiPartida.getCamposRondas()[0].getText() + "</p></html>");
							}
						}
						try {
							Thread.sleep(500);
						} catch (InterruptedException ex) {
							Logger.getLogger(GuiMenu.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			} else {
				System.err.println("");
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Logger.getLogger(GuiMenu.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public Thread getHilo() {
		return hilo;
	}

	public void reiniciarHilo() {
		audioIntro.reproducirSonido("/sonidos/Intro.wav", true);
		audioIntro.cambiarVolumen(-40);
		modificarTitulo(intW((MW + (520 - 800)) / 2), intH((MH - 800) / 2), intW(800), intH(800));
		wPanelContenedorG = intW(0);
		hPanelContenedorH = intH(0);
		hilo = new Thread(this);
		hilo.start();
	}

}
