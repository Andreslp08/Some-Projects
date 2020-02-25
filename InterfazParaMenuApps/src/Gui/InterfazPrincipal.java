package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.sql.Time;
import java.text.AttributedCharacterIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class InterfazPrincipal extends JFrame {

	private JPanel panel, panelContenedorSeccion, panelContenedorApps;
	private JLabel boton;
	private Graphics circulo, circulo2;
	private boolean cajaAdentro, cajaAfuera, cajaPresionada, cajaSuelta;
	private int x, y, xg, yg;
	private int colInicial = 0;
	private int colFinal = 6;
	private int filasTotales = 5;
	private JLabel[] cajas;
//	new Color(255, 255, 255, 5);
	private Color[] colorG;
//	private Color colorG = new Color( 255, 255, 255, 5);
	private Color colorCampo = new Color(0, 0, 0, 25);
	private Color colorAgregar = new Color(0, 0, 0, 25);
	private Color colorBorrar = new Color(0, 0, 0, 25);
	private Color colorVolver = new Color( 0, 0, 0, 25 );
	private JTextField campo;
	private JLabel fechaHora;
	private SimpleDateFormat diaFormato = new SimpleDateFormat("EEE");
	private SimpleDateFormat mes = new SimpleDateFormat("MMMMM");
	private SimpleDateFormat numDia = new SimpleDateFormat("dd");
	private SimpleDateFormat anio = new SimpleDateFormat("yyyy");
	private SimpleDateFormat hora = new SimpleDateFormat("h:mm a");
	private String dia = "";
	private String infoGeneral = "";
	private String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
	private Date date = new Date();
	private JLabel agregarSeccion, borrarSeccion;
	private ImageIcon iconoBorrar, iconoVolver;
	private Image imgIconoBorrar, imgIconoVolver;
	private JLabel labelVolver;

	public InterfazPrincipal() {
		// crear colores
		colorG = new Color[30];
		for (int i = 0; i < 30; i++) {
			colorG[i] = new Color(255, 255, 255, 5);
		}
		switch (diaFormato.format(date).toString()) {
			case "lun":
				dia = dias[0];
				break;
			case "mar":
				dia = dias[1];
				break;
			case "mié":
				dia = dias[2];
				break;
			case "jue":
				dia = dias[3];
				break;
			case "vie":
				dia = dias[4];
				break;
			case "sáb":
				dia = dias[5];
				break;
			case "dom":
				dia = dias[6];
				break;

		}
		infoGeneral = "<html><p>" + dia + ",</p>" + "<p>" + mes.format(date) + " " + numDia.format(date) + " de " + anio.format(date) + "</p>" + "<p><b>" + hora.format(date) + "</b></p>" + "</html>";
		cajas = new JLabel[30];
		xg = 410;
		yg = 165;
		x = 0;
		y = 15;
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		//Panel
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 190));
		panel.setLayout(null);
		this.add(panel);
		//Panel contenedor de secciones
		panelContenedorSeccion = new JPanel();
		panelContenedorSeccion.setBounds(0, 0, this.getWidth() * 5, this.getHeight());
		panelContenedorSeccion.setBackground(panel.getBackground());
		panelContenedorSeccion.setLayout(null);
		panel.add(panelContenedorSeccion);
		//Seccion
		panelContenedorApps = new JPanel();
		panelContenedorApps.setBounds(400, 150, 1150, 800);
		panelContenedorApps.setBackground(new Color(0, 0, 0, 25));
		panelContenedorApps.setBorder(BorderFactory.createMatteBorder(10, 0, 10, 0, new Color(255, 255, 255, 10)));
		panelContenedorApps.setLayout(null);
		panelContenedorSeccion.add(panelContenedorApps);
		panelContenedorApps.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panelContenedorApps.setBackground(new Color(0, 0, 0, 50));
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				panelContenedorApps.setBackground(new Color(0, 0, 0, 25));
				repaint();
			}
		});

		for (int cantidad = 0; cantidad < cajas.length; cantidad++) {
			cajas[cantidad] = new JLabel("app " + cantidad);
			cajas[cantidad].setFont(new Font(Font.DIALOG, 0, 20));
			cajas[cantidad].setVerticalAlignment(SwingConstants.BOTTOM);
			cajas[cantidad].setHorizontalAlignment(SwingConstants.CENTER);
			cajas[cantidad].setForeground(Color.white);
			cajas[cantidad].setBackground(Color.red);
			panelContenedorApps.add(cajas[cantidad]);
			cajas[cantidad].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					label.setFont(new Font(Font.DIALOG, 1, 40));
					cambiarColor(new Color(255, 255, 255, 15), label);
					repaint();

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					label.setForeground(Color.lightGray);
					label.setFont(new Font(Font.DIALOG, 1, 30));
					cambiarColor(new Color(255, 255, 255, 10), label);
					repaint();

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					label.setForeground(Color.lightGray);
					label.setFont(new Font(Font.DIALOG, 1, 30));
//					colorG = new Color(255, 255, 255, 10);
					panelContenedorApps.setBackground(new Color(0, 0, 0, 50));
					cambiarColor(new Color(255, 255, 255, 10), label);
					repaint();

				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					label.setForeground(Color.white);
					label.setFont(new Font(Font.DIALOG, 0, 20));
					cambiarColor(new Color(255, 255, 255, 5), label);
					repaint();

				}
			});
		}
		//Algoritmo para organizar filas y columnas 
		for (int fila = 0; fila < filasTotales; fila++) {
			for (int columna = colInicial; columna < colFinal; columna++) {
				cajas[columna].setBounds(x, y, 150, 150);
				x = x + 165;
			}
			x = (int) (x - 165 * 5.76);
			y = y + 155;
			colInicial += 6;
			colFinal += 6;

		}

		//Campo
		campo = new JTextField("Mis aplicaciones");
		campo.setBounds(400, 100, 400, 50);
		campo.setOpaque(false);
		campo.setBorder(null);
		campo.setForeground(Color.white);
		campo.setFont(new Font(Font.SANS_SERIF, 1, 30));
		campo.setSelectionColor(Color.white);
		campo.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				colorCampo = new Color(0, 0, 0, 50);
				repaint();
			}

			public void mousePressed(MouseEvent e) {
				colorCampo = new Color(0, 0, 0, 70);
				repaint();
			}

			public void mouseReleased(MouseEvent e) {
				colorCampo = new Color(0, 0, 0, 50);
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				colorCampo = new Color(0, 0, 0, 25);
				repaint();
			}
		});
		campo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				repaint();
			}
		});
		panel.add(campo);
		//Fecha y hora
		fechaHora = new JLabel(infoGeneral);
		fechaHora.setBounds(1600, 10, 310, 310);
		fechaHora.setForeground(Color.white);
		fechaHora.setVerticalAlignment(CENTER);
		fechaHora.setHorizontalAlignment(CENTER);
		fechaHora.setFont(new Font("Arial", 0, 55));
		panel.add(fechaHora);
		//Agregar seccion
		agregarSeccion = new JLabel("+");
		agregarSeccion.setBounds(810, 100, 50, 50);
		agregarSeccion.setFont(new Font("Arial", 1, 50));
		agregarSeccion.setVerticalAlignment(CENTER);
		agregarSeccion.setHorizontalAlignment(CENTER);
		agregarSeccion.setForeground(Color.white);
		panel.add(agregarSeccion);
		agregarSeccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				colorAgregar = new Color(0, 0, 0, 45);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				colorAgregar = new Color(0, 0, 0, 65);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				colorAgregar = new Color(0, 0, 0, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				colorAgregar = new Color(0, 0, 0, 25);
			}

		});
		//Imagen borrarSeccion
		iconoBorrar = new ImageIcon(getClass().getResource("/img/iconoEliminar.png"));
		imgIconoBorrar = iconoBorrar.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH);
		iconoBorrar = new ImageIcon(imgIconoBorrar);
		//Borrar sección
		borrarSeccion = new JLabel();
		borrarSeccion.setBounds(865, 100, 50, 50);
		borrarSeccion.setVerticalAlignment(CENTER);
		borrarSeccion.setHorizontalAlignment(CENTER);
		borrarSeccion.setIcon(iconoBorrar);
		panel.add(borrarSeccion);
		borrarSeccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				colorBorrar = new Color(0, 0, 0, 45);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				colorBorrar = new Color(0, 0, 0, 65);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				colorBorrar = new Color(0, 0, 0, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				colorBorrar = new Color(0, 0, 0, 25);
			}
		});
		// Icono volver
		iconoVolver = new ImageIcon(getClass().getResource("/img/iconoVolver.png"));
		imgIconoVolver = iconoVolver.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		iconoVolver = new ImageIcon(imgIconoVolver);
		//Volver
		labelVolver = new JLabel();
		labelVolver.setBounds(50, 20, 100, 100 );
		labelVolver.setIcon(iconoVolver);
		panel.add(labelVolver);
		labelVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				colorVolver = new Color(0, 0, 0, 45);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				colorVolver = new Color(0, 0, 0, 65);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				colorVolver = new Color(0, 0, 0, 45);
				setState(1);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				colorVolver = new Color(0, 0, 0, 25);
			}
			
		});
	}

	public void cambiarColor(Color color, JLabel label) {
		for (int i = 0; i < cajas.length; i++) {
			if (label.getText().equals(cajas[i].getText())) {
				colorG[i] = color;
			}
		}
	}

	@Override
	public void paint(Graphics g
	) {
		hora = new SimpleDateFormat("h:mm a");
		date = new Date();
		xg = 410;
		yg = 165;
		int numColor = 0;
		super.paint(g);
		for (int columnas = 0; columnas < 5; columnas++) {
			for (int filas = 0; filas < 6; filas++) {
				g.setColor(colorG[numColor]);
				g.fillOval(xg, yg, 150, 150);
				xg = xg + 165;
				numColor++;
			}
			xg = (int) (xg - 165 * 5.76);
			yg = yg + 155;
		}
		g = campo.getGraphics();
		g.setColor(colorCampo);
		g.fillRoundRect(-50, 0, 450, 50, 50, 50);
		campo.update(g);
		g = fechaHora.getGraphics();
		g.setColor(colorCampo);
		fechaHora.update(g);
		g = agregarSeccion.getGraphics();
		g.setColor(colorAgregar);
		g.fillOval(0, 0, 50, 50);
		agregarSeccion.update(g);
		g = borrarSeccion.getGraphics();
		g.setColor(colorBorrar);
		g.fillOval(0, 0, 50, 50);
		borrarSeccion.update(g);
		g = labelVolver.getGraphics();
		g.setColor(colorVolver);
		g.fillOval(0, 0, 100, 100 );
		labelVolver.update(g);
		infoGeneral = "<html><p>" + dia + ",</p>" + "<p>" + mes.format(date) + " " + numDia.format(date) + " de " + anio.format(date) + "</p>" + "<p><b>" + hora.format(date) + "</b></p>" + "</html>";
		fechaHora.setText(infoGeneral);
		repaint();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				repaint();
			}
			
		});
	}

}
