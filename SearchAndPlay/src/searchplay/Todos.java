package searchplay;

import DB.DB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEADING;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class Todos{

	private JPanel panelPrincipalT, panelContenedor;
	private JLabel[] juegos;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int uW;
	private int uH;
	private int mW;
	private int mH;
	private int cantidadJ;
	private JComponent componente;
	private int posYLista;
	private VistaPrevia infoNyA = new VistaPrevia();
	private Juego juego = new Juego();
	private ListaJuegos listaJuegos = new ListaJuegos();
	private int año;
	private String infoNombre;
	private String infoAño;
	private String infoImagen;
	private String infoGenero;
	private String infoPlataforma;
	private String infoPuntuacion;
	private String infoDescripcion;
	private String infoRM;
	private String infoRR;

	private DB db = new DB();

	public Todos() {
		uW = (int) screenSize.getWidth();
		uH = (int) screenSize.getHeight();
		mW = 1920;
		mH = 1080;
		panelPrincipalT = new JPanel();
		panelContenedor = new JPanel();
		juegos = new JLabel[listaJuegos.obtenerNumJuegosTodos()];
		posYLista = 0;
		año = 2019;		
	}

	public void guiTodos() {
		panelPrincipalT.setBounds((0 * uW) / mW, (0 * uH) / mH, (mW * uW) / mW, (800 * uH) / mH);
		panelPrincipalT.setLayout(null);
		panelPrincipalT.setBackground(new Color(11, 11, 11, 255));
		panelContenedor.setBounds((0 * uW) / mW, (posYLista * uH) / mH, (mW * uW) / mW, ((53 * listaJuegos.obtenerNumJuegosTodos()) * uH) / mH);
		panelContenedor.setLayout(new GridLayout(0, 5));
		panelPrincipalT.add(panelContenedor);
		panelContenedor.setBackground(new Color(11, 11, 11, 255));
		for (int i = 0; i < juegos.length; i++) {
			db.consultarVideojuegoDB("SELECT * FROM juego WHERE  id  = '" + ( i + 1 ) + "' ");
			juegos[i] = new JLabel();
			juegos[i].setBackground(new Color(11, 11, 11, 255));
			juegos[i].setForeground(Color.darkGray);
			juegos[i].setHorizontalAlignment(CENTER);
			juegos[i].setVerticalAlignment(CENTER);
			juegos[i].setOpaque(true);
			juegos[i].setLayout(null);
			juegos[i].setIcon(listaJuegos.obtenerIconoJuegos(i));
			listaJuegos.obtenerIconoJuegos(i);
			juegos[i].setText(db.obtenerIdJuego());
			juegos[i].setFont(new Font("Arial", 0, 0));
			System.err.println(juegos[i].getX() + " " + juegos[i].getY() + " " + juegos[i].getWidth() + " " + juegos[i].getHeight());
			panelContenedor.add(juegos[i]);
			juegos[i].addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(40, 40, 40, 255));

				}

				public void mouseReleased(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(20, 20, 20, 255));
					visibilidad(false);
					db.consultarVideojuegoDB("SELECT * FROM juego WHERE  id  = '" + componente.getText() + "' ");
					juego.guiJuego(db.obtenerTituloJuego(), db.obtenerAñoJuego(), db.obtenerPortada(), db.obtenerGenero(), db.obtenerPlataforma(), db.obtenerPuntuacion(), db.obtenerDescripcion(), db.obtenerRm(), db.obtenerRr(), obtenerPanelPrincipalT());
					visibilidadJuego(true);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(20, 20, 20, 255));
					db.consultarVideojuegoDB("SELECT * FROM juego WHERE  id  = '" + componente.getText() + "' ");
					infoNyA.NombreAño(db.obtenerTituloJuego(), db.obtenerAñoJuego(), 0, 220, 384, 50, componente);
					infoNyA.visibilidadNyA(true);
				}

				public void mouseExited(MouseEvent e) {
					JLabel componente = (JLabel) e.getComponent();
					componente.setBackground(new Color(11, 11, 11, 255));
					infoNyA.obtenerInfo().setVisible(false);
				}
			});
			panelPrincipalT.addMouseWheelListener(new MouseWheelListener() {

				public void mouseWheelMoved(MouseWheelEvent e) {
					//Subir
					if (e.getWheelRotation() < 0) {
						if (panelContenedor.getY() < 0) {
							posYLista = posYLista + 1;
							panelContenedor.setLocation(panelContenedor.getX(), posYLista);
						}
					}
					//bajar
					if (e.getWheelRotation() > 0) {
						if (panelContenedor.getY() + panelContenedor.getHeight() >= panelPrincipalT.getHeight()) {
							posYLista = posYLista - 1;
							panelContenedor.setLocation(panelContenedor.getX(), posYLista);
						} else {
							posYLista = panelPrincipalT.getHeight() - panelContenedor.getHeight();
							panelContenedor.setLocation(panelContenedor.getX(), posYLista);
						}
					}
				}
			});
		}

	}

	public void visibilidad(boolean vof) {
		infoNyA.visibilidadNyA(false);
		panelContenedor.setVisible(vof);
	}

	public void visibilidadJuego(boolean vof) {
		juego.obtenerPanelC().setVisible(vof);
	}

	public JPanel obtenerPanelPrincipalT() {
		return panelPrincipalT;
	}
}
