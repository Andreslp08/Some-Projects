package searchplay;

import DB.DB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;
import sun.java2d.pipe.DrawImage;

public class GuiPrincipal extends JFrame {

	private JPanel panelPrincipal;
	private JLabel Cerrar;
	private Botones botonMinimizar = new Botones();
	private Botones botonCerrar = new Botones();
	private JLabel labelTitulo;
	private Timer timerTitulo;
	private int posYTitulo;
	private int posOriginal;
	private int posInicial;
	private int tiempoInicio;
	private boolean tituloVisible;
	private JTextField barraBuscar;
	private JPanel barraOpciones;
	private JLabel botonOpcion[];
	private String textoBotonOpcion[] = {"Lista completa", "Genero", "Año", "Plataforma", "Mas jugados", "Biblioteca"};
	private int numOpcion = textoBotonOpcion.length;
	private int posXbO;
	private JPanel panelVJ;
	private Todos todos = new Todos();
	private Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int uW;
	private int uH;
	private int mW;
	private int mH;
	private ImageIcon iconLupa;
	private Image imgLupa;
	private JLabel lupa;
	private Timer movPanelVJ;
	private int posXVJ;
	private String[] opcionesGenero = {"Battle Royale", "Shooter", "MOBA", "Accion-Aventura", "Carreras", "Survival horror"};
	private String opcionesAño[] = {"2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007"};
	private String[] opcionesPlataforma = {"PC", "Xbox One", "Play Station 4"};
	private Categorias genero = new Categorias(opcionesGenero);
	private Categorias año = new Categorias(opcionesAño);
	private Categorias plataforma = new Categorias(opcionesPlataforma);

	public GuiPrincipal() {
		uW = (int) ScreenSize.getWidth();
		uH = (int) ScreenSize.getHeight();
		mW = 1920;
		mH = 1080;
		this.setExtendedState(MAXIMIZED_BOTH);
		panelPrincipal = new JPanel();
		labelTitulo = new JLabel();
		posYTitulo = (-560 * uH) / mH;
		posOriginal = (440 * uH) / mH;
		posInicial = (-560 * uH) / mH;
		tiempoInicio = 0;
		tituloVisible = false;
		barraBuscar = new JTextField();
		barraOpciones = new JPanel();
		botonOpcion = new JLabel[numOpcion];
		posXbO = 0;
		panelVJ = new JPanel();
		iconLupa = new ImageIcon(getClass().getResource("/img/Lupa.png"));
		imgLupa = iconLupa.getImage().getScaledInstance((40 * uW) / mW, (40 * uH) / mH, Image.SCALE_SMOOTH);
		iconLupa = new ImageIcon(imgLupa);
		lupa = new JLabel();
		posXVJ = (-2000 * uW) / mW;

	}

	public void AbrirGui() {
		this.setUndecorated(true);
		this.setVisible(true);
		this.setEnabled(true);
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		panelPrincipal.setBackground(new Color(10, 10, 10, 255));
		panelPrincipal.setBounds(0, 0, this.getWidth(), this.getHeight());
		panelPrincipal.setLayout(null);
		this.add(panelPrincipal);
		botonMinimizar.crearBotonCyM("Minimizar", 1920 - 60, 0, 30, 30, new Color(10, 10, 10, 255), 1, ObtenerFramePrincipal());
		panelPrincipal.add(botonMinimizar.obtenerBotonMyC());
		botonMinimizar.obtenerBotonMyC().setVisible(false);
		botonCerrar.crearBotonCyM("Cerrar", 1920 - 30, 0, 30, 30, new Color(10, 10, 10, 255), 0, ObtenerFramePrincipal());
		panelPrincipal.add(botonCerrar.obtenerBotonMyC());
		botonCerrar.obtenerBotonMyC().setVisible(false);
		labelTitulo.setText("<html>Search <font color='#FFFFFF'> & </font> <font color='#01304E'>Play</font></html>");
		labelTitulo.setLocation((560 * uW) / mW, posYTitulo);
		labelTitulo.setSize((800 * uW) / mW, (200 * uH) / mH);
		System.err.println(labelTitulo.getX() + " " + labelTitulo.getY());
		labelTitulo.setHorizontalAlignment(CENTER);
		labelTitulo.setVerticalAlignment(CENTER);
		labelTitulo.setFont(new Font("Dialog", 3, (85 * uW) / mW));
		panelPrincipal.add(labelTitulo);
		posYTitulo = (-560 * uH) / mH;
		posOriginal = (440 * uH) / mH;
		timerTitulo = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tituloVisible == false) {
					posYTitulo = posYTitulo + 30;
					tiempoInicio = tiempoInicio + 1;
					int tiempoEspera = tiempoInicio / 100;
					System.err.println(tiempoEspera);
					labelTitulo.setLocation(labelTitulo.getX(), posYTitulo);
					if (posYTitulo >= posOriginal) {
						posYTitulo = posOriginal;
						labelTitulo.setLocation(labelTitulo.getX(), posYTitulo);
						if (tiempoEspera >= 5) {
							tituloVisible = true;
							botonCerrar.obtenerBotonMyC().setVisible(true);
							botonMinimizar.obtenerBotonMyC().setVisible(true);
						}
					}
				} else if (tituloVisible == true) {
					posYTitulo = posYTitulo - 30;
					labelTitulo.setLocation(labelTitulo.getX(), posYTitulo);
					if (posYTitulo <= posInicial) {
						posYTitulo = posInicial;
						labelTitulo.setLocation(labelTitulo.getX(), posYTitulo);
						guiInicio();
						todos.guiTodos();
						panelVJ.add(todos.obtenerPanelPrincipalT());
						categorias();
						timerTitulo.stop();
					}
				}
			}
		});
		timerTitulo.start();
		genero.guiCategorias(panelVJ);
		año.guiCategorias(panelVJ);
		plataforma.guiCategorias(panelVJ);

	}

	public void guiInicio() {
		labelTitulo.setBounds(0, 0, (400 * uW) / mW, (100 * uH) / mH);
		labelTitulo.setFont(new Font("Dialog", 3, (40 * uW) / mW));
		barraBuscar.setBounds((810 * uW) / mW, (100 * uH) / mH, (300 * uW) / mW, (50 * uH) / mH);
		panelPrincipal.add(barraBuscar);
		barraBuscar.setBackground(new Color(15, 15, 15, 255));
		barraBuscar.setBorder(null);
		barraBuscar.setFont(new Font("Bradley Hand ITC", 1, (20 * uW) / mW));
		barraBuscar.setText("");
		barraBuscar.setHorizontalAlignment(CENTER);
		//Icono lupa
		lupa.setBounds((1115 * uW) / mW, (100 * uH) / mH, (50 * uW) / mW, (50 * uH) / mH);
		lupa.setIcon(iconLupa);
		panelPrincipal.add(lupa);
		lupa.setBackground(new Color(10, 10, 10, 255));
		lupa.setVerticalAlignment(CENTER);
		lupa.setHorizontalAlignment(CENTER);
		lupa.setOpaque(true);
		barraBuscar.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (barraBuscar.getText().equals("Buscar")) {
					barraBuscar.setText("");
					barraBuscar.setForeground(Color.white);
				}
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				if (barraBuscar.getText().equals("Buscar") || barraBuscar.getText().equals("")) {
					barraBuscar.setText("Buscar");
					barraBuscar.setForeground(Color.darkGray);
				}
				if (!barraBuscar.getText().equals("Buscar")) {
					barraBuscar.setForeground(Color.white);
				}
			}

			public void mouseExited(MouseEvent e) {
				if (barraBuscar.getText().equals("Buscar")) {
					barraBuscar.setText("");

				}
			}
		});

		lupa.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				e.getComponent().setBackground(new Color(20, 20, 20, 255));
			}

			public void mouseReleased(MouseEvent e) {
				e.getComponent().setBackground(new Color(15, 15, 15, 255));
			}

			public void mouseEntered(MouseEvent e) {
				JLabel Componente = (JLabel) e.getComponent();
				e.getComponent().setBackground(new Color(15, 15, 15, 255));
				Componente.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(1, 48, 78, 255)));
			}

			public void mouseExited(MouseEvent e) {
				JLabel Componente = (JLabel) e.getComponent();
				e.getComponent().setBackground(new Color(10, 10, 10, 255));
				Componente.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(1, 48, 78, 255)));
			}
		});
		barraOpciones.setBounds((360 * uW) / mW, (200 * uH) / mH, ((200 * numOpcion) * uW) / mW, (50 * uH) / mH);
		System.out.println("xd" + barraBuscar.getY());
		panelPrincipal.add(barraOpciones);
		barraOpciones.setBackground(new Color(10, 10, 10, 255));
		barraOpciones.setLayout(null);
		for (int i = 0; i < botonOpcion.length; i++) {
			botonOpcion[i] = new JLabel();
			botonOpcion[i].setBounds((posXbO * uW) / mW, (0 * uH) / mH, (200 * uW) / mW, (50 * uH) / mH);
			botonOpcion[i].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(25, 25, 25, 255)));
			botonOpcion[i].setText(textoBotonOpcion[i]);
			botonOpcion[i].setHorizontalAlignment(CENTER);
			botonOpcion[i].setVerticalAlignment(CENTER);
			botonOpcion[i].setFont(new Font("Ink Free", 1, (20 * uW) / mW));
			botonOpcion[i].setForeground(Color.white);
			botonOpcion[i].setOpaque(true);
			botonOpcion[i].setBackground(new Color(10, 10, 10, 255));
			barraOpciones.add(botonOpcion[i]);
			posXbO = posXbO + 202;
			botonOpcion[i].addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					categorias();
					switch (componente.getText()) {
						case "Lista completa":
							todos.visibilidad(true);
							todos.visibilidadJuego(false);
							genero.visibilidadCategorias(false);
							año.visibilidadCategorias(false);
							plataforma.visibilidadCategorias(false);
							break;
						case "Genero":
							todos.visibilidad(false);
							todos.visibilidadJuego(false);
							genero.visibilidadCategorias(true);
							año.visibilidadCategorias(false);
							plataforma.visibilidadCategorias(false);
							genero.gestionarCategoria("Genero", opcionesGenero, "SELECT COUNT(*) FROM juego INNER JOIN genero ON juego.id_genero = genero.id  WHERE genero.generoNombre = '", "SELECT * FROM juego INNER JOIN genero ON juego.id_genero = genero.id WHERE genero.generoNombre = '");		
							break;
						case "Año":
							todos.visibilidad(false);
							todos.visibilidadJuego(false);
							genero.visibilidadCategorias(false);
							año.visibilidadCategorias(true);
							plataforma.visibilidadCategorias(false);
							año.gestionarCategoria("Año", opcionesAño, "SELECT COUNT(*) FROM juego WHERE año = '", "SELECT * FROM juego WHERE año = '");
							break;
						case "Plataforma":
							todos.visibilidad(false);
							todos.visibilidadJuego(false);
							genero.visibilidadCategorias(false);
							año.visibilidadCategorias(false);
							plataforma.visibilidadCategorias(true);
							plataforma.gestionarCategoria("Plataforma", opcionesPlataforma, "SELECT COUNT(*) FROM juego INNER JOIN plataforma ON juego.id_plataforma = plataforma.id  WHERE plataforma.laPlataforma = '", "SELECT * FROM juego INNER JOIN plataforma ON juego.id_plataforma = plataforma.id  WHERE plataforma.laPlataforma = '");
							break;
						default:
							todos.visibilidad(false);
							todos.visibilidadJuego(false);
							genero.visibilidadCategorias(false);
							año.visibilidadCategorias(false);
							plataforma.visibilidadCategorias(false);
							break;
					}

				}

				public void mousePressed(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(20, 20, 20, 255));
				}

				public void mouseReleased(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(10, 10, 10, 255));
				}

				public void mouseEntered(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(1, 48, 78, 255)));
				}

				public void mouseExited(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(25, 25, 25, 255)));
				}
			});
		}
		panelVJ.setBounds(posXVJ, (280 * uH) / mH, (mW * uW) / mW, (800 * uH) / mH);
		panelVJ.setLayout(null);
		panelPrincipal.add(panelVJ);
		panelVJ.setBackground(new Color(11, 11, 11, 255));
	}

	public void categorias() {
		posXVJ = (-2000 * uW) / mW;
		movPanelVJ = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posXVJ = posXVJ + 50;
				panelVJ.setLocation(posXVJ, panelVJ.getY());
				if (posXVJ >= (0 * uW) / mW) {
					posXVJ = (0 * uW) / mW;
					panelVJ.setLocation(posXVJ, panelVJ.getY());
					movPanelVJ.stop();
				}
			}
		});
		movPanelVJ.start();
	}

	public JFrame ObtenerFramePrincipal() {
		return this;
	}

	public String[] getOpcionesGenero() {
		return opcionesGenero;
	}

	public String[] getOpcionesAño() {
		return opcionesAño;
	}
	
	public String[] getOpcionesPlataforma() {
		return opcionesPlataforma;
	}
	public static void main(String[] args) {

		GuiPrincipal guiPrincipal = new GuiPrincipal();
		guiPrincipal.AbrirGui();

		DB db = new DB();
		db.obtenerConexion();

	}

}
