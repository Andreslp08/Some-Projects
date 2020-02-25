package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;
import logica.Audio;
import logica.Jugador;
import logica.TicTacToe;

public class GuiJuego extends JPanel {

	private JPanel panelCuadricula;
	private final int W_PANEL = 800;
	private final int H_PANEL = 800;
	private int posXPanelCuadricula, posYPanelCuadricula;
	private int columnas, filas;
	private JLabel[][] cuadros;
	private ImageIcon circuloIcono, equisIcono;
	private Image circuloImg, equisImg;
	private int posXcuadro, posYcuadro;
	private Color colorCuadricula = new Color(25, 25, 25, 255);
	private String noSeleccionado = "No seleccionado";
	private String seleccionado = "Seleccionado";
	private String estado = noSeleccionado;
	private String[] mouseSonidos = {"/sonidos/mouse1.wav"};
	private Audio audio, cancion;
	private GuiBotonesPrincipales guiBotonesPrincipales;
	private TicTacToe ticTacToe;
	private int cantidad = 0;
	private GuiJugador guiJugador1, guiJugador2;
	private JLabel turnoDe;
	private GuiRondas guiRondas;
	private GuiPrincipal guiPrincipal;
	private GuiBotones[] botonesInferiores;
	private int posXbotonesInferiores;
	private String[] textoBotonesInferiores = {"Reiniciar ronda", "Abandonar partida"};

	public enum TipoCuadricula {
		TRESxTRES, SEISxSEIS, NUEVExNUEVE, DOCExDOCE, QUINCExQUINCE, DIECIOCHOxDIECIOCHO;
	};

	private TipoCuadricula tipoCuadricula;
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

	public GuiJuego(GuiPrincipal guiPrincipal) {
		this.guiPrincipal = guiPrincipal;
		//cancion
		cancion = new Audio();
		//
		//turno de
		turnoDe = new JLabel();
		turnoDe.setBounds((guiPrincipal.getWidth() - intW(800)) / 2, intH(40), intW(800), intH(50));
		turnoDe.setForeground(Color.lightGray);
		turnoDe.setVerticalAlignment(CENTER);
		turnoDe.setHorizontalAlignment(CENTER);
		turnoDe.setFont(new Font("Impact", 0, intW(50)));
		add(turnoDe);
		//gui jugador 1
		guiJugador1 = new GuiJugador(this, "/img/equis.png");
		guiJugador1.setText("Jugador 1");
		guiJugador1.setLocation(intW(70), intH(130));
		//gui jugador 1
		guiJugador2 = new GuiJugador(this, "/img/circulo.png");
		guiJugador2.setText("Jugador 2");
		guiJugador2.setLocation(intW(70), intH(550));
		//tic tac toe
		ticTacToe = new TicTacToe(this);
		//botones principales
		guiBotonesPrincipales = new GuiBotonesPrincipales(this, guiPrincipal);
		guiPrincipal.add(this);
		//Audio
		audio = new Audio();
		// ventana
		this.setBackground(new Color(20, 20, 20, 255));
		this.setLayout(null);
		this.setVisible(true);
		//IMagenes
		circuloIcono = new ImageIcon(getClass().getResource("/img/circulo.png"));
		equisIcono = new ImageIcon(getClass().getResource("/img/equis.png"));
		// panel cuadricula
		panelCuadricula = new JPanel();
		posXPanelCuadricula = (guiPrincipal.getWidth() - intW(W_PANEL)) / 2;
		posYPanelCuadricula = (guiPrincipal.getHeight() - intH(H_PANEL)) / 2;
		panelCuadricula.setBounds(posXPanelCuadricula, posYPanelCuadricula, intW(W_PANEL), intH(H_PANEL));
		panelCuadricula.setBackground(new Color(25, 25, 25, 255));
		panelCuadricula.setLayout(null);
		this.add(panelCuadricula);
		//labelRonda
		guiRondas = new GuiRondas(this);
		guiRondas.setLocation(intW(1440), intH(140));
		//botones inferiores
		botonesInferiores = new GuiBotones[textoBotonesInferiores.length];
		posXbotonesInferiores = 550;
		for (int i = 0; i < botonesInferiores.length; i++) {
			botonesInferiores[i] = new GuiBotones(this);
			botonesInferiores[i].setBounds(intW(posXbotonesInferiores), guiPrincipal.getHeight() - intH(100), intW(400), intH(60));
			botonesInferiores[i].boundsGboton(0, 0, 400, 60, 50, 50);
			botonesInferiores[i].setText(textoBotonesInferiores[i]);
			botonesInferiores[i].setHorizontalAlignment(CENTER);
			botonesInferiores[i].setVerticalAlignment(CENTER);
			botonesInferiores[i].setForeground(Color.white);
			botonesInferiores[i].setFont(new Font("Impact", 1, intW(30)));
			botonesInferiores[i].setVisible(false);
			this.add(botonesInferiores[i]);
			posXbotonesInferiores = posXbotonesInferiores + 425;
			botonesInferiores[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					GuiBotones boton = (GuiBotones) e.getComponent();
					switch (boton.getText()) {
						case "Reiniciar ronda":
							int col = 0;
							int fila = 0;
							for (fila = 0; fila < getCantidad(); fila++) {
								for (col = 0; col < getCantidad(); col++) {
									getCuadros()[col][fila].setText("");
									getCuadros()[col][fila].setIcon(null);
								}
							}
							break;
						case "Abandonar partida":
							getGuiPrincipal().nuevoJuego();
							getGuiPrincipal().getGuiJuego().setVisible(false);
							getGuiPrincipal().getGuiMenu().setVisible(true);
							getGuiPrincipal().getGuiMenu().getGuiPartida().setVisible(false);
							getGuiPrincipal().getGuiMenu().getGuiPartida().restaurarTodo();
							getGuiPrincipal().getGuiMenu().getHilo().stop();
							getGuiPrincipal().getGuiMenu().reiniciarHilo();
							break;
					}
				}
			});
		}
	}

	public void configCuadricula(TipoCuadricula tipoCuadricula) {
		switch (tipoCuadricula) {
			case TRESxTRES:
				crearCuadricula(3);
				configIconos(intW(W_PANEL) / 3, intH(H_PANEL) / 3);
				break;
			case SEISxSEIS:
				crearCuadricula(6);
				configIconos(intW(W_PANEL) / 6, intH(H_PANEL)  / 6);
				break;
			case NUEVExNUEVE:
				crearCuadricula(9);
				configIconos(intW(W_PANEL) / 9, intH(H_PANEL)  / 9);
				break;
			case DOCExDOCE:
				crearCuadricula(12);
				configIconos(intW(W_PANEL) / 12, intH(H_PANEL)  / 12);
				break;
			case QUINCExQUINCE:
				crearCuadricula(15);
				configIconos(intW(W_PANEL) / 15, intH(H_PANEL)  / 15);
				break;
			case DIECIOCHOxDIECIOCHO:
				crearCuadricula(18);
				configIconos(intW(W_PANEL) / 18, intH(H_PANEL)  / 18);
				break;
		}
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	private void crearCuadricula(int cantidad) {
		this.cantidad = cantidad;
		this.filas = cantidad;
		this.columnas = cantidad;
		posXcuadro = 0;
		posYcuadro = 0;
		cuadros = new JLabel[columnas][filas];
		for (int col = 0; col < cantidad; col++) {
			for (int fila = 0; fila < cantidad; fila++) {
				cuadros[col][fila] = new JLabel();
				cuadros[col][fila].setBorder(BorderFactory.createMatteBorder( intW(5), intW(5), intW(5), intW(5), new Color(0, 100, 250, 255)));
				cuadros[col][fila].setBounds(intW(posXcuadro), intH(posYcuadro), intW(W_PANEL / filas)+1, intH(H_PANEL / columnas)+1);
				cuadros[col][fila].setHorizontalAlignment(CENTER);
				cuadros[col][fila].setVerticalAlignment(CENTER);
				cuadros[col][fila].setOpaque(true);
				cuadros[col][fila].setFont(new Font("Arial", 0, 0));
				cuadros[col][fila].setForeground(Color.white);
				cuadros[col][fila].setBackground(new Color(255, 255, 255, 0));
				panelCuadricula.add(cuadros[col][fila]);
				posXcuadro = posXcuadro + W_PANEL / filas;
				cuadros[col][fila].addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						label.setBackground(new Color(255, 255, 255, 20));
						panelCuadricula.repaint();
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						label.setBackground(new Color(255, 255, 255, 10));
						if (ticTacToe.comprobarTurno(label) == true) {
							audio.reproducirSonido("/sonidos/casillaNoSeleccionada.wav", false);
						} else {
							audio.reproducirSonido("/sonidos/casillaSeleccionada.wav", false);
						}
						ticTacToe.comprobarGanador();
						panelCuadricula.repaint();
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						label.setBackground(new Color(255, 255, 255, 10));
						panelCuadricula.repaint();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						label.setBackground(new Color(255, 255, 255, 0));
						panelCuadricula.repaint();
					}
				});
			}
			posXcuadro = 0;
			posYcuadro = posYcuadro + H_PANEL / columnas;
		}
	}

	public ImageIcon getCirculoIcono() {
		return circuloIcono;
	}

	public void setCirculoIcono(ImageIcon circuloIcono) {
		this.circuloIcono = circuloIcono;
	}

	public ImageIcon getEquisIcono() {
		return equisIcono;
	}

	public void setEquisIcono(ImageIcon equisIcono) {
		this.equisIcono = equisIcono;
	}

	public void configIconos(int w, int h) {
		//crear circulo a medida de la cuadricula
		circuloImg = circuloIcono.getImage().getScaledInstance(w - 30, h - 30, Image.SCALE_SMOOTH);
		circuloIcono = new ImageIcon(circuloImg);
		//crear equis a medida de la cuadricula
		equisImg = equisIcono.getImage().getScaledInstance(w - 30, h - 30, Image.SCALE_SMOOTH);
		equisIcono = new ImageIcon(equisImg);
	}

	public JLabel[][] getCuadros() {
		return cuadros;
	}

	public int getCantidad() {
		return cantidad;
	}

	public GuiJugador getGuiJugador1() {
		return guiJugador1;
	}

	public GuiJugador getGuiJugador2() {
		return guiJugador2;
	}

	public JLabel getTurnoDe() {
		return turnoDe;
	}

	public void setNombreJugador1(String nombre) {
		guiJugador1.setText(nombre);
	}

	public void setNombreJugador2(String nombre) {
		guiJugador2.setText(nombre);
	}

	public TicTacToe getTicTacToe() {
		return ticTacToe;
	}

	public GuiRondas getGuiRondas() {
		return guiRondas;
	}

	public GuiPrincipal getGuiPrincipal() {
		return guiPrincipal;
	}

	public void reproducirCancion() {
		cancion.reproducirSonido("/sonidos/juego.wav", true);
	}

	public Audio getCancion() {
		return cancion;
	}

}
