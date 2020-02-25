package Gui;

import Gui.GuiJuego.TipoCuadricula;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import logica.Audio;

public class GuiPartida extends JLabel {

	private JLabel[] paneles;
	private int posXpaneles, posYpaneles;
	private JLabel[] labelJugador;
	private String[] urlIconos = {"/img/equis.png", "/img/circulo.png"};
	private String[] textoCampos = {"Nombre jugador 1", "Nombre jugador 2"};
	private final String[] textoCuadriculas = {"3x3", "6x6", "9x9", "12x12", "15x15", "18x18"};
	private int[] numCuadriculas = {3, 6, 9, 12, 15, 18};
	private GuiBotones[] guiBotonesCuadriculas;
	private int posXjugador, posYJugador, posXcampo, posYcampo, posXbotonCuadricula, posYbotonCuadricula;
	private ImageIcon[] iconoJugador;
	private Image[] imgJugador;
	private JLabel textoVersus;
	private JTextField[] campoNombre;
	private JLabel textoEscogerCuadricula;
	private JLabel textoEscogerRondas;
	private JTextField[] camposRondas;
	private GuiBotones botonJugar;
	private GuiDialog guiDialog;
	private Audio audio;
	private GuiBotones botonSeleccionado;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// resoluciones
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int UW = (int) screenSize.getWidth(), UH = (int) screenSize.getHeight();
	private final int MW = 1920, MH = 1080;

	//getters resolucion
	public int intW(int num) {
		return ((num) * UW) / MW;
	}

	public int intH(int num) {
		return ((num) * UH) / MH;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public GuiPartida(GuiMenu guiMenu) {
		this.setBounds(intW(20), intH(20), intW(1320), intH(770 - 40));
		this.setVisible(true);
		//Audio
		audio = new Audio();
		// dialog
		guiDialog = new GuiDialog();		
		// paneles opciones
		paneles = new JLabel[3];
		posXpaneles = 0;
		posYpaneles = 0;
		for (int i = 0; i < paneles.length; i++) {
			paneles[i] = new JLabel();
			paneles[i].setBounds( intW(posXpaneles), intH(posYpaneles), intW(1320), intH(256));
			paneles[i].setOpaque(true);
			paneles[i].setBackground(new Color(255, 255, 255, 0));
			paneles[i].setBorder(BorderFactory.createMatteBorder(0, 0, intW(5), 0, new Color(255, 255, 255, 255)));
			this.add(paneles[i]);
			posYpaneles = posYpaneles + 256;
			paneles[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					label.setBackground(new Color(0, 100, 255, 30));
					repaint();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					label.setBackground(new Color(0, 100, 255, 0));
					repaint();
				}

			});
		}
		//img jugador
		labelJugador = new JLabel[2];
		iconoJugador = new ImageIcon[2];
		imgJugador = new Image[2];
		campoNombre = new JTextField[2];
		posXjugador = 30;
		posYJugador = 30;
		posXcampo = 300;
		posYcampo = (256 - 50) / 2;
		for (int i = 0; i < labelJugador.length; i++) {
			//Fondo jugador
			labelJugador[i] = new JLabel();
			labelJugador[i].setBounds(intW(posXjugador), intH(posYJugador), intW(200), intH(200));
			labelJugador[i].setHorizontalAlignment(CENTER);
			labelJugador[i].setVerticalAlignment(CENTER);
			iconoJugador[i] = new ImageIcon(getClass().getResource(urlIconos[i]));
			imgJugador[i] = iconoJugador[i].getImage().getScaledInstance(intW(200), intH(200), Image.SCALE_SMOOTH);
			iconoJugador[i] = new ImageIcon(imgJugador[i]);
			labelJugador[i].setIcon(iconoJugador[i]);
			paneles[0].add(labelJugador[i]);
			posXjugador = posXjugador + 1060;
			//campos de nombre
			campoNombre[i] = new JTextField(textoCampos[i]);
			campoNombre[i].setBounds(intW(posXcampo), intH(posYcampo), intW(250), intH(50));
			campoNombre[i].setFont(new Font("Impact", 0, intW(30)));
			campoNombre[i].setBorder(BorderFactory.createMatteBorder(0, 0, intW(5), 0, new Color(0, 100, 255, 255)));
			campoNombre[i].setHorizontalAlignment(CENTER);
			paneles[0].add(campoNombre[i]);
			posXcampo = posXcampo + 250 * 2 - 10;
		}
		// texto Versus
		textoVersus = new JLabel("Vs.");
		textoVersus.setBounds(intW((1320 - 200) / 2), intH((256 - 200) / 2), intW(200), intH(200));
		textoVersus.setHorizontalAlignment(CENTER);
		textoVersus.setVerticalAlignment(CENTER);
		textoVersus.setForeground(new Color(0, 100, 255, 255));
		textoVersus.setFont(new Font("Impact", 0, intW(90)));
		paneles[0].add(textoVersus);
		//texto escoger cuadricula
		textoEscogerCuadricula = new JLabel("Escoger cuadricula");
		textoEscogerCuadricula.setBounds(intW((1320 - 500) / 2), intH(0), intW(500), intH(100));
		textoEscogerCuadricula.setFont(new Font("Impact", 0, intW(60)));
		textoEscogerCuadricula.setForeground(new Color(0, 100, 255, 255));
		textoEscogerCuadricula.setHorizontalAlignment(CENTER);
		textoEscogerCuadricula.setVerticalAlignment(CENTER);
		paneles[1].add(textoEscogerCuadricula);

		// botones cuadriculas
		guiBotonesCuadriculas = new GuiBotones[textoCuadriculas.length];
		posXbotonCuadricula = (1320 - 900) / 2;
		posYbotonCuadricula = (256 - 50) / 2;
		for (int i = 0; i < guiBotonesCuadriculas.length; i++) {
			guiBotonesCuadriculas[i] = new GuiBotones(paneles[1]);
			guiBotonesCuadriculas[i].setText(textoCuadriculas[i]);
			guiBotonesCuadriculas[i].setBounds(intW(posXbotonCuadricula), intH(posYbotonCuadricula), intW(150), intH(50));
			guiBotonesCuadriculas[i].boundsGboton(0, 0, 150, 50, 20, 20);
			guiBotonesCuadriculas[i].setHorizontalAlignment(CENTER);
			guiBotonesCuadriculas[i].setVerticalAlignment(CENTER);
			guiBotonesCuadriculas[i].setForeground(Color.white);
			guiBotonesCuadriculas[i].setFont(new Font("Impact", 1, intW(40)));
			paneles[1].add(guiBotonesCuadriculas[i]);
			posXbotonCuadricula = posXbotonCuadricula + 160;
			guiBotonesCuadriculas[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					botonSeleccionado = (GuiBotones) e.getComponent();
					for (int i = 0; i < textoCuadriculas.length; i++) {
						guiBotonesCuadriculas[i].setForeground(Color.white);
					}
					botonSeleccionado.setForeground(Color.cyan);
					repaint();
				}

			});
		}

		//Escoger rondas
		textoEscogerRondas = new JLabel("victorias de                 rondas para ganar                        ");
		textoEscogerRondas.setBounds((intW(1320) - paneles[2].getWidth()) / 2, intW((256 - 80) / 2), paneles[2].getWidth(), intH(50));
		textoEscogerRondas.setFont(new Font("Impact", 0, intW(50)));
		textoEscogerRondas.setForeground(new Color(0, 100, 255, 255));
		textoEscogerRondas.setHorizontalAlignment(CENTER);
		textoEscogerRondas.setVerticalAlignment(CENTER);
		paneles[2].add(textoEscogerRondas);
		// campos rondas
		camposRondas = new JTextField[2];
		posXcampo = 40;
		posYcampo = 0;
		for (int i = 0; i < camposRondas.length; i++) {
			camposRondas[i] = new JTextField("0");
			camposRondas[i].setBounds(intW(posXcampo), intH(posYcampo), intW(100), intH(50));
			camposRondas[i].setFont(new Font("Impact", 0, intW(30)));
			camposRondas[i].setBorder(BorderFactory.createMatteBorder(0, 0, intW(5), 0, new Color(0, 100, 255, 255)));
			camposRondas[i].setHorizontalAlignment(CENTER);
			textoEscogerRondas.add(camposRondas[i]);
			posXcampo = posXcampo + 390;
		}

		//boton jugar
		botonJugar = new GuiBotones(guiMenu);
		botonJugar.setText("JUGAR!");
		botonJugar.setBounds(paneles[2].getWidth() - intW(230), intH((256 - 80) / 2), intW(200), intH(60));
		botonJugar.boundsGboton(0, 0, 200, 60, 20, 20);
		botonJugar.setForeground(Color.white);
		botonJugar.setHorizontalAlignment(CENTER);
		botonJugar.setVerticalAlignment(CENTER);
		botonJugar.setFont(new Font("Impact", 0, intW(30)));
		paneles[2].add(botonJugar);
		botonJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int numRondas = 0;
				int numVictorias = 0;
				Color color = new Color(0, 100, 255, 255);
				if (campoNombre[0].getText().length() >= 5 && campoNombre[1].getText().length() >= 5) {
					if (!campoNombre[0].getText().equalsIgnoreCase(campoNombre[1].getText())) {
						if (!campoNombre[0].getText().equals(textoCampos[0]) && !campoNombre[1].getText().equals(textoCampos[1])) {
							if (cuadriculaSeleccionada(guiMenu.getGuiJuego()) == true) {
								try {
									numVictorias = Integer.parseInt(camposRondas[0].getText());
									numRondas = Integer.parseInt(camposRondas[1].getText());
									if (numVictorias > 0 && numRondas > 0) {
										if (numVictorias <= numRondas) {
											if (numRondas <= 50) {
												botonJugar.setText("Comenzando...");
												botonJugar.setForeground(Color.cyan);
												audio.reproducirSonido("/sonidos/empezandoPartida.wav", false);
					
											} else {
												guiDialog.setVisible(true);
												guiDialog.configDialog("<html><p><El maximo de rondas es de 50.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);

											}

										} else {
											guiDialog.setVisible(true);
											guiDialog.configDialog("<html><p>Las victorias deben ser menor</p><p> o igual a las rondas ingresadas.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);
										}
									} else {
										guiDialog.setVisible(true);
										guiDialog.configDialog("<html><p>las rondas y victorias,</p><p>deben ser mayores a 0.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);
									}
								} catch (Exception exception) {
									guiDialog.setVisible(true);
									guiDialog.configDialog("<html><p>los campos 'Rondas' y 'Victorias' </p><p>solo deben contener numeros</p><p> enteros mayores a 0.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);

								}

							}
						} else {
							guiDialog.setVisible(true);
							guiDialog.configDialog("<html><p>Ingrese los nombres de los jugadores.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);
						}

					} else {
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p>Los nombres no pueden ser iguales,</p><p>intenta cambiarlos. </p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);
					}
				} else {
					guiDialog.setVisible(true);
					guiDialog.configDialog("<html><p>Los nombres deben tener </p><p>al menos 5 caracteres.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);
				}
			}

		});

	}

	public boolean cuadriculaSeleccionada(GuiJuego guiJuego) {
		try {
			if (botonSeleccionado.getForeground().equals(Color.cyan)) {
				switch (botonSeleccionado.getText()) {
					case "3x3":
						guiJuego.configCuadricula(TipoCuadricula.TRESxTRES);
						break;
					case "6x6":
						guiJuego.configCuadricula(TipoCuadricula.SEISxSEIS);
						break;
					case "9x9":
						guiJuego.configCuadricula(TipoCuadricula.NUEVExNUEVE);
						break;
					case "12x12":
						guiJuego.configCuadricula(TipoCuadricula.DOCExDOCE);
						break;
					case "15x15":
						guiJuego.configCuadricula(TipoCuadricula.QUINCExQUINCE);
						break;
					case "18x18":
						guiJuego.configCuadricula(TipoCuadricula.DIECIOCHOxDIECIOCHO);
						break;
				}
				return true;
			} else {
				guiDialog.setVisible(true);
				guiDialog.configDialog("<html><p>Debes seleccionar un tipo de cuadricula.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);
				return false;
			}
		} catch (Exception ex) {
			guiDialog.setVisible(true);
			guiDialog.configDialog("<html><p>Debes seleccionar un tipo de cuadricula.</p></html>", new Color(20, 20, 20, 200), "Arial", 1, 30);
		}
		return false;
	}

	public void restaurarTodo() {
		for( int i = 0; i < guiBotonesCuadriculas.length; i ++ ){
			guiBotonesCuadriculas[i].setForeground(Color.white);
		}
		botonJugar.setForeground(Color.white);
		botonJugar.setText("JUGAR!");
		getCamposRondas()[0].setText("0");
		getCamposRondas()[1].setText("0");
	}

	public GuiBotones getBotonJugar() {
		return botonJugar;
	}

	public GuiPartida getGuiPartida(){
		return this;
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(20, 20, 20, 100));
		g.fillRoundRect(intW(0), intH(0), this.getWidth(), this.getHeight(), intW(20), intH(20));
		super.paintComponent(g);
		repaint();
	}

	public JTextField[] getCampoNombre() {
		return campoNombre;
	}

	public JTextField[] getCamposRondas() {
		return camposRondas;
	}

}
