package searchplay;

import DB.DB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import static java.awt.image.ImageObserver.ABORT;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class Categorias extends JFrame {

	private JPanel panelPrincipal;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int mW;
	private int mH;
	private int uW;
	private int uH;
	private MenuContenido menuContenido;
	private JLabel titulo2;
	private JLabel juegosEncontrados;
	private JPanel panelPrincipalJuegos, panelContenedorJuegos;
	private DB db = new DB();
	private ArrayList<JLabel> juegosCategoria;
	private int posXVJ;
	private Timer mov1;
	private JLabel nuevoJuego;
	private VistaPrevia vistaPrevia = new VistaPrevia();
	private ListaJuegos listaJuegos = new ListaJuegos(true);
	private Juego juego = new Juego();
	private int posXPCJ;

	public Categorias(String[] opciones) {
		uW = (int) screenSize.getWidth();
		uH = (int) screenSize.getHeight();
		mW = 1920;
		mH = 1080;
		panelPrincipal = new JPanel();
		titulo2 = new JLabel();
		juegosEncontrados = new JLabel();
		panelPrincipalJuegos = new JPanel();
		panelContenedorJuegos = new JPanel();
		juegosCategoria = new ArrayList();
		posXVJ = 2000;
		posXPCJ = 0;
		menuContenido = null;
		menuContenido = new MenuContenido(opciones);

	}

	public void gestionarCategoria(String categoria, String[] array, String queryCount, String query) {
		menuContenido.guiMenuContenido("Disponibles por " + categoria, panelPrincipal);
		for (int i = 0; i < array.length; i++) {
			menuContenido.obtenerSeleccion(i).addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseReleased(MouseEvent e) {
					juegosCategoria.clear();
					panelContenedorJuegos.removeAll();
					JLabel label = (JLabel) e.getComponent();
					panelPrincipalJuegos.setVisible(true);
					animPanelVJ();
					for (int i = 0; i < array.length; i++) {
						if (label.getText().equals(array[i])) {
							titulo2.setText(label.getText());
							titulo2.setVisible(true);
							//"SELECT COUNT(*) FROM juego INNER JOIN genero ON juego.id_genero = genero.id  WHERE genero.generoNombre = '"
							//"SELECT * FROM juego INNER JOIN genero ON juego.id_genero = genero.id WHERE genero.generoNombre = '"
							juegosEncontrados.setText("Juegos encontrados: " + db.obtenerCantidadJuegos(queryCount + label.getText() + "' "));
							juegosEncontrados.setVisible(true);
							db.consultarVideojuegoDB(query + label.getText() + "' ");
							panelContenedorJuegos.setLayout(new GridLayout(1, db.obtenerCantidadJuegos(queryCount + label.getText() + "' "), 3, 3));
							panelContenedorJuegos.setSize( ( (db.obtenerCantidadJuegos(queryCount + label.getText() + "' ") * 370 ) *uW)/mW, (450*uH)/mH);
							for (int j = 0; j < db.obtenerCantidadJuegos(queryCount + label.getText() + "' "); j++) {
								nuevoJuego = new JLabel(db.obtenerListaDatos(j).obtenerIdJuego());
								nuevoJuego.setFont(new Font("Arial", 1, 0));
								nuevoJuego.setLayout(null);
								nuevoJuego.setHorizontalAlignment(CENTER);
								nuevoJuego.setVerticalAlignment(CENTER);
								juegosCategoria.add(nuevoJuego);
								listaJuegos.ListaJuegos(db.obtenerListaDatos(j).obtenerPortada(), queryCount + label.getText() + "' ", 320, 390, nuevoJuego);
								panelContenedorJuegos.add(juegosCategoria.get(j));
								juegosCategoria.get(j).addMouseListener(new MouseListener() {

									public void mouseClicked(MouseEvent e) {

									}

									@Override
									public void mousePressed(MouseEvent e) {
										JLabel componente = (JLabel) e.getComponent();
										componente.setBackground(new Color(15, 15, 15, 255));
										componente.setOpaque(true);
									}

									@Override
									public void mouseReleased(MouseEvent e) {
										JLabel componente = (JLabel) e.getComponent();
										componente.setBackground(new Color(12, 12, 12, 255));
										componente.setOpaque(true);
										visibilidadCategorias(true);
										menuContenido.visibilidadMenu(false);
										db.consultarVideojuegoDB("SELECT * FROM juego WHERE id ='" + componente.getText() + "' ", true);
										juego.obtenerPanelC().setVisible(true);
										juego.guiJuego(db.obtenerTituloJuego(), db.obtenerAñoJuego(), db.obtenerPortada(), db.obtenerGenero(), db.obtenerPlataforma(), db.obtenerPuntuacion(), db.obtenerDescripcion(), db.obtenerRm(), db.obtenerRr(), obtenerPanelPrincipal());
										juego.animVentana();
									}

									@Override
									public void mouseEntered(MouseEvent e) {
										JLabel componente = (JLabel) e.getComponent();
										componente.setBackground(new Color(12, 12, 12, 255));
										componente.setOpaque(true);
										db.consultarVideojuegoDB("SELECT * FROM juego WHERE id ='" + componente.getText() + "' ", true);
										vistaPrevia.NombreAño(db.obtenerTituloJuego(), db.obtenerAñoJuego(), 0, 390, 410, 60, componente);
										vistaPrevia.visibilidadNyA(true);
									}

									public void mouseExited(MouseEvent e) {
										JLabel componente = (JLabel) e.getComponent();
										componente.setBackground(new Color(9, 9, 9, 255));
										componente.setOpaque(true);
										vistaPrevia.visibilidadNyA(false);
									}
								});
							}
						}
					}
					panelContenedorJuegos.addMouseWheelListener(new MouseWheelListener() {

						public void mouseWheelMoved(MouseWheelEvent e) {
							//Subir
							if (e.getWheelRotation() < 0) {
								if (panelContenedorJuegos.getX() < 0) {
									posXPCJ = posXPCJ + 50;
									panelContenedorJuegos.setLocation(posXPCJ, panelContenedorJuegos.getY());
								}
							}
							//bajar
							if (e.getWheelRotation() > 0) {
								if (panelContenedorJuegos.getX() + panelContenedorJuegos.getWidth() >= panelPrincipalJuegos.getWidth()) {
									posXPCJ = posXPCJ - 50;
									panelContenedorJuegos.setLocation(posXPCJ, panelContenedorJuegos.getY());
								} else {
									if (panelContenedorJuegos.getWidth() >= panelPrincipal.getWidth()) {
										posXPCJ = panelPrincipalJuegos.getWidth() - panelContenedorJuegos.getWidth();
										panelContenedorJuegos.setLocation(posXPCJ, panelContenedorJuegos.getY());
									} else {
										posXPCJ = 0;
										panelContenedorJuegos.setLocation(posXPCJ, panelContenedorJuegos.getY());
									}
								}
							}
						}
					});
				}

				public void mouseEntered(MouseEvent e) {

				}

				public void mouseExited(MouseEvent e) {
				}
			});

		}
	}

	public void guiCategorias(JComponent panel) {
		posXPCJ = 0;
		panel.add(panelPrincipal);
		panelPrincipal.setBounds((0 * uW) / mW, (0 * uH) / mH, (mW * uW) / mW, (800 * uH) / mH);
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(new Color(11, 11, 11, 255));
		panelPrincipal.setVisible(false);
		titulo2.setBounds((400 * uW) / mW, (80 * uH) / mH, (1520 * uW) / mW, (40 * uH) / mH);
		titulo2.setVisible(false);
		titulo2.setVerticalAlignment(CENTER);
		titulo2.setHorizontalAlignment(CENTER);
		panelPrincipal.add(titulo2);
		titulo2.setForeground(Color.darkGray);
		titulo2.setFont(new Font("Corbel", 2, (40 * uW) / mW));
		juegosEncontrados.setBounds((400 * uW) / mW, (120 * uH) / mH, (1520 * uW) / mW, (30 * uH) / mH);
		juegosEncontrados.setText("Juegos encontrados: ");
		juegosEncontrados.setVisible(false);
		juegosEncontrados.setVerticalAlignment(CENTER);
		juegosEncontrados.setHorizontalAlignment(CENTER);
		panelPrincipal.add(juegosEncontrados);
		juegosEncontrados.setForeground(Color.darkGray);
		juegosEncontrados.setFont(new Font("Arial Narrow", 2, (25 * uW) / mW));
		//420
		panelPrincipalJuegos.setBounds((posXVJ*uW)/mW, (190*uH)/mH, (1480*uW)/mW, (450*uH)/mH);
		panelPrincipalJuegos.setBackground(new Color(9, 9, 9, 255));
		panelPrincipalJuegos.setLayout(null);
		panelPrincipalJuegos.setVisible(false);
		panelPrincipal.add(panelPrincipalJuegos);
		panelContenedorJuegos.setBounds((posXPCJ*uW)/mW, (0*uH)/mH, (1480*uW)/mW, (450*uH)/mH);
		panelContenedorJuegos.setBackground(new Color(9, 9, 9, 255));
		panelContenedorJuegos.setVisible(true);
		panelPrincipalJuegos.add(panelContenedorJuegos);

	}

	public JPanel obtenerPanelPrincipal() {
		return panelPrincipal;
	}

	public void visibilidadCategorias(boolean ToF) {
		try {
			menuContenido.retornarMenu();
			menuContenido.visibilidadMenu(true);
		} catch (NullPointerException ecx) {

		}
		panelPrincipal.setVisible(ToF);
		titulo2.setVisible(false);
		juegosEncontrados.setVisible(false);
		panelPrincipalJuegos.setVisible(false);
		juego.obtenerPanelC().setVisible(false);
	}

	public void animPanelVJ() {
		posXPCJ = 0;
		posXVJ = (2000*uW)/mW;
		mov1 = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posXVJ = posXVJ - 10;
				panelPrincipalJuegos.setLocation(posXVJ, panelPrincipalJuegos.getY());
				if (posXVJ <= (420*uW)/mW) {
					posXVJ = (420*uW)/mW;
					panelPrincipalJuegos.setLocation(posXVJ, panelPrincipalJuegos.getY());
					mov1.stop();
				}
			}
		});

		mov1.start();
	}

	public MenuContenido obtenerMenuContenido() {
		return menuContenido;
	}
}
