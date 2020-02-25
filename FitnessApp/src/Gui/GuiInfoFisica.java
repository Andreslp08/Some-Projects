package Gui;

import Interfaces.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;
import Logica.*;
import Db.*;
import static Interfaces.Temas.acceso;
import static Interfaces.Temas.fondos;
import static Interfaces.Temas.fuentes;
import static Interfaces.Temas.themes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.Date;

public class GuiInfoFisica extends JFrame implements Temas {

	private JPanel panel;
	private JLabel barraPrincipal;
	private JLabel[] botonesPrincipales;
	private DateFormat hora = new SimpleDateFormat("hh: mm a");
	private DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();
	private Db db = new Db();
	private InformacionFisica infoFisica = new InformacionFisica(db.obtenerConexion());
	private JTextField[] campos;
	private JLabel[] botones;
	private String[] textoBotones = {"Guardar", "Volver"};
	private String preguntarColor, seleccion = "";
	private JLabel barraNotificacion;
	private Timer timer1;
	private StringBuilder all_themes;
	private int posXbotonesP, posYBotonesP;
	private int contador;
	private String[] tituloCampos = {"Altura(cm)", "Peso(kg)", "I.M.C", "Grasa(%)", "Masa(kg)", "Cuello",
		"Hombros", "Pecho", "Brazo Izq.", "Brazo Der.", "AnteBrazo Izq.", "Antebrazo Der.",
		"Cintura", "Gluteos", "Pierna Izq.", "Pierna Der.", "Pantorrilla Izq.", "Pantorrilla Der."
	};
	private int posYcampos, posXcampos, posXbotones, posYBotones;
	private Hilos hilos = new Hilos();
	private Notificacion notificacion = new Notificacion();

	public GuiInfoFisica(JFrame frame) {
		campos = new JTextField[18];
		botones = new JLabel[textoBotones.length];
		posYcampos = 50;
		posXcampos = 40;
		posXbotones = 150;
		posYBotones = 465;
		all_themes = new StringBuilder();
		for (String t : themes) {
			all_themes.append("\n" + t);
		}
		panel = new JPanel();
		barraPrincipal = new JLabel("Nuevo registro");
		barraPrincipal.setLayout(null);
		barraNotificacion = new JLabel("Notificacion");
		posYBotonesP = 0;
		crearCampos();
		crearBotones();
		for (int i = 0; i < themes.length; i++) {
			if ("Claro".equals(themes[i])) {
				seleccion = themes[i];
				aplicarTema(themes[i], fondos[i], fuentes[i], acceso[i]);
			}

		}

		frame.add(panel);
	}

	public void gui(int x, int y, int w, int h) {
		panel.setBounds(x, y, w, h);
		panel.setLayout(null);
		barraPrincipal.setBounds(0, 0, panel.getWidth(), 50);
		barraPrincipal.setHorizontalAlignment(CENTER);
		barraPrincipal.setVerticalAlignment(CENTER);
		barraPrincipal.setFont(new Font("Comic Sans MS", 1, 25));
		barraPrincipal.setLayout(null);
		posXbotonesP = (barraPrincipal.getWidth() - 30);
		posYBotonesP = 0;
		panel.add(barraPrincipal);
		barraNotificacion.setBounds(0, panel.getHeight(), 600, 40);
		barraNotificacion.setFont(new Font("Arial Narrow", 0, 20));
		barraNotificacion.setVerticalAlignment(CENTER);
		barraNotificacion.setHorizontalAlignment(CENTER);
		barraNotificacion.setForeground(Color.green);
		panel.add(notificacion.getLabelNotificacion());
		panel.setVisible(false);
	}

	public void crearCampos() {
		for (int i = 0; i < campos.length; i++) {
			campos[i] = new JTextField(tituloCampos[i]);
			campos[i].setBounds(posXcampos, posYcampos, 155, 60);
			campos[i].setFont(new Font("Arial Narrow", 1, 25));
			campos[i].setHorizontalAlignment(CENTER);
			posYcampos = posYcampos + 65;
			campos[i].setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, panel.getBackground()));
			panel.add(campos[i]);
			if (i == 5 || i == 11) {
				posYcampos = 50;
				posXcampos = posXcampos + 180;
			}
			campos[i].addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {

				}

				public void mousePressed(MouseEvent e) {
					JTextField componente = (JTextField) e.getComponent();
					cambiarColorAccionCampo(1, componente);

				}

				public void mouseReleased(MouseEvent e) {
					JTextField componente = (JTextField) e.getComponent();
					cambiarTextoCampos(2, componente);
					cambiarColorAccionCampo(2, componente);

				}

				public void mouseEntered(MouseEvent e) {
					JTextField componente = (JTextField) e.getComponent();
					cambiarTextoCampos(0, componente);
					cambiarColorAccionCampo(0, componente);

				}

				public void mouseExited(MouseEvent e) {
					JTextField componente = (JTextField) e.getComponent();
					cambiarTextoCampos(1, null);
					cambiarColorAccionCampo(3, componente);

				}
			});
		}
	}

	public void crearBotones() {
		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JLabel(textoBotones[i]);
			botones[i].setBounds(posXbotones, posYBotones, 150, 35);
			botones[i].setBorder(null);
			botones[i].setHorizontalAlignment(CENTER);
			botones[i].setVerticalAlignment(CENTER);
			botones[i].setOpaque(true);
			panel.add(botones[i]);
			posXbotones = posXbotones + 155;
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
						case "Guardar":
							for (int i = 0; i < campos.length; i++) {
								if (!campos[i].getText().equals("") && !campos[i].getText().equals(tituloCampos[i])) {
									infoFisica.nuevaInfo(campos);
									db.nuevoRegistro("INSERT INTO infofisico( clase, altura, peso, imc, grasa, masa, cuello, hombros, pecho, brazoI, brazoD, antebrazoI, antebrazoD, cintura, gluteos, piernaI, piernaD, pantorrillaI, pantorrillaD, fecha, hora ) "
										+ "values('" + infoFisica.getClase() + "', " + "'" + infoFisica.getDatos(0) + "'," + "'" + infoFisica.getDatos(1) + "'," + "'" + infoFisica.getDatos(2) + "'," + "'" + infoFisica.getDatos(3) + "',"
										+ "'" + infoFisica.getDatos(4) + "'," + "'" + infoFisica.getDatos(5) + "'," + "'" + infoFisica.getDatos(6) + "'," + "'" + infoFisica.getDatos(7) + "'," + "'" + infoFisica.getDatos(8) + "',"
										+ "'" + infoFisica.getDatos(9) + "'," + "'" + infoFisica.getDatos(10) + "'," + "'" + infoFisica.getDatos(11) + "'," + "'" + infoFisica.getDatos(12) + "'," + "'" + infoFisica.getDatos(13) + "',"
										+ "'" + infoFisica.getDatos(14) + "'," + "'" + infoFisica.getDatos(15) + "'," + "'" + infoFisica.getDatos(16) + "'," + "'" + infoFisica.getDatos(17) + "','" + fecha.format(date) + "', '" + hora.format(date) + "' )");
									notificacion.notificar("Datos guardados correctamente... Fecha de creacion: ( " + fecha.format(date) + " )", getPanel(), Color.green, 0, panel.getHeight()-40, panel.getWidth(), 40, panel.getHeight(), panel.getHeight()-40);
									break;
								} else {
									System.err.println(panel.getHeight());
										notificacion.notificar("Los campos no pueden estar vacios.", getPanel(), Color.red, 0, panel.getHeight()-40, panel.getWidth(), 40, panel.getHeight(), panel.getHeight()-40);
		
									break;
								}
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

	public JTextField getCamposI(int i) {
		return campos[i];
	}

	public void cambiarTextoCampos(int opcion, JTextField campo) {
		switch (opcion) {
			case 0:
				for (int i = 0; i < campos.length; i++) {
					if (campo.getText().equals(tituloCampos[i])) {
						campo.setText("");
						for (int c = 0; c < themes.length; c++) {
							if (seleccion.equals(themes[c])) {
								campo.setForeground(obtenerColor(fuentes[c], 1));
							}
						}
					}

				}
				break;

			case 1:
				for (int i = 0; i < campos.length; i++) {
					if (campos[i].getText().equals("")) {
						campos[i].setText(tituloCampos[i]);
						for (int c = 0; c < themes.length; c++) {
							if (seleccion.equals(themes[c])) {
								campos[i].setForeground(obtenerColor(fuentes[c], 1));
							}
						}
					}
				}
				break;
			case 2:
				for (int i = 0; i < campos.length; i++) {
					for (int c = 0; c < themes.length; c++) {
						if (seleccion.equals(themes[c])) {
							campo.setForeground(obtenerColor(fuentes[c], 0));
						}
					}
				}
		}
	}

	@Override
	public Color obtenerColor(Color[] color, int num) {
		return color[num];
	}

	@Override
	public void cambiarColorBordes(int opcion, JComponent componente, Color color) {
		switch (opcion) {
			case 0:
				for (JTextField i : campos) {
					i.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, color));
				}
				break;

			case 1:
				componente.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, color));
				break;
		}
	}

	@Override
	public void cambiarColorCampos(int opcion, JComponent componente, Color fondo, Color letra) {
		switch (opcion) {
			case 0:
				for (JTextField i : campos) {
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
	public void cambiarTema() {

		for (int i = 0; i < themes.length; i++) {
			if (seleccion.equals(themes[i])) {
				aplicarTema(themes[i], fondos[i], fuentes[i], acceso[i]);
			}
		}

	}

	@Override
	public void aplicarTema(String tema, Color[] colorFondo, Color[] colorFuente, Color[] colorAcceso) {
		seleccion = tema;
		panel.setBackground(obtenerColor(colorFondo, 0));
		barraPrincipal.setForeground(obtenerColor(colorFuente, 0));
		barraPrincipal.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, obtenerColor(colorFondo, 1)));
		cambiarColorBotones(0, null, obtenerColor(colorAcceso, 0), obtenerColor(colorFuente, 0));
		cambiarColorBordes(0, null, obtenerColor(colorAcceso, 0));
		cambiarColorCampos(0, null, obtenerColor(colorFondo, 0), obtenerColor(colorFuente, 1));

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
		for (int i = 0; i < themes.length; i++) {
			if (seleccion.equals(themes[i])) {
				switch (opcion) {
					case 0:
						cambiarColorBordes(1, componente, obtenerColor(acceso[i], 1));
						break;
					case 1:
						cambiarColorBordes(1, componente, obtenerColor(acceso[i], 2));
						break;
					case 2:
						cambiarColorBordes(1, componente, obtenerColor(acceso[i], 0));
						break;
					case 3:
						cambiarColorBordes(1, componente, obtenerColor(acceso[i], 0));
						break;
				}

			}
		}
	}
	
	public JPanel getPanel(){
		return panel;
	}

	public String[] getTituloCampos() {
		return tituloCampos;
	}

}
