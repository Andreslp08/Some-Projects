package Gui;

import Interfaces.Temas;
import Logica.Hilos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;
import Db.*;
import javax.swing.border.MatteBorder;

public class GuiInformacion extends JPanel implements Temas {

	private static String seleccion;
	private static JPanel panelLista, panelPrincipalLista;
	private static JPanel panelInfo, panelPrincipalInfo;
	private static JLabel tituloLista;
	private static JLabel[] registro;
	private static int posYregistro, posYLista, posXPanelPinfo, posYPanelInfo, posYdatosInfo;
	private static int cantidad = 100;
	private static JLabel titulo;
	private static Hilos hilos = new Hilos();
	private static JLabel[] datosInfo;
	private static String datos[][] = {
		{"Altura: ", "cm"}, {"Peso: ", "kg"}, {"I.M.C: ", "kg/m²"}, {"Grasa corporal: ", "%"}, {"Masa muscular: ", "kg"}, {"Cuello: ", "cm"},
		{"Hombros: ", "cm"}, {"Pecho: ", "cm"}, {"Brazo Izq: ", "cm"}, {"Brazo Der: ", "cm"}, {"Antebrazo Izq: ", "cm"}, {"Antebrazo Der: ", "cm"},
		{"Cintura: ", "cm"}, {"Gluteos: ", "cm"}, {"Pierna Izq: ", "cm"}, {"Pierna Der: ", "cm"}, {"Pantorrilla Izq: ", "cm"}, {"Pantorrilla Der:", "cm"},};

	private Db db = new Db();

	public GuiInformacion(GuiInicio guiInicio) {
		db.obtenerConexion();
		datosInfo = new JLabel[datos.length];
		this.setVisible(false);
		posYregistro = 0;
		posYLista = 0;
		registro = new JLabel[db.getCantidadRegistros()];
		System.err.println("registros encontrados: " +db.getCantidadRegistros());
		this.setLayout(null);
		this.setBounds(guiInicio.getFondo().getWidth(), 60, 600, guiInicio.getFrame().getHeight() - 90);
		panelPrincipalLista = new JPanel();
		panelPrincipalLista.setBounds(0, 30, 250, 30 * 15);
		panelPrincipalLista.setLayout(null);
		this.add(panelPrincipalLista);
		panelLista = new JPanel();
		panelLista.setBounds(0, posYLista, 247, 30 * db.getCantidadRegistros());
		panelLista.setLayout(null);
		panelPrincipalLista.add(panelLista);
		tituloLista = new JLabel("Registro");
		tituloLista.setBounds(0, 0, 250, 30);
		tituloLista.setHorizontalAlignment(CENTER);
		tituloLista.setVerticalAlignment(CENTER);
		tituloLista.setFont(new Font("Verdana", 0, 15));
		tituloLista.setOpaque(true);
		this.add(tituloLista);
		titulo = new JLabel("Información");
		titulo.setFont(new Font("Comic Sans MS", 1, 25));
		titulo.setBounds(panelLista.getWidth(), 0, 355, 50);
		titulo.setHorizontalAlignment(CENTER);
		titulo.setVerticalAlignment(CENTER);
		this.add(titulo);
		posXPanelPinfo = this.getWidth();
		panelPrincipalInfo = new JPanel();
		panelPrincipalInfo.setBounds(posXPanelPinfo, titulo.getHeight(), titulo.getWidth(), panelPrincipalLista.getHeight() - 20);
		panelPrincipalInfo.setLayout(null);
		this.add(panelPrincipalInfo);
		posYdatosInfo = 0;
		posYPanelInfo = 0;
		panelInfo = new JPanel();
		panelInfo.setBounds(0, posYPanelInfo, panelPrincipalInfo.getWidth(), 50 * datos.length);
		panelInfo.setLayout(null);
		panelPrincipalInfo.add(panelInfo);
		for (int i = 0; i < registro.length; i++) {

			registro[i] = new JLabel();
			registro[i].setBounds(0, posYregistro, panelLista.getWidth(), 30);
			registro[i].setFont(new Font("Arial Narrow", 1, 20));
			registro[i].setHorizontalAlignment(CENTER);
			registro[i].setVerticalAlignment(CENTER);
			panelLista.add(registro[i]);
			posYregistro = posYregistro + 30;
			registro[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel row = (JLabel) e.getComponent();
					for (int j = 0; j < themes.length; j++) {
						if (guiInicio.getSeleccion().equals(themes[j])) {
							row.setForeground(acceso[j][2]);
						}

					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel row = (JLabel) e.getComponent();
					posYPanelInfo = 0;
					panelInfo.setLocation(0, posYPanelInfo);
					hilos.animInfo(getThis());
					db.mostrarDatos("Select * from infoFisico WHERE Fecha = '" + row.getText() + "'", getThis() );
					for (int j = 0; j < themes.length; j++) {
						if (guiInicio.getSeleccion().equals(themes[j])) {
							row.setForeground(acceso[j][1]);
							for( int i = 0; i < registro.length; i ++ ){
								registro[i].setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, fondos[j][0]));
							}
							
							row.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, acceso[j][2]));
							
						}

					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel row = (JLabel) e.getComponent();
					for (int j = 0; j < themes.length; j++) {
						if (guiInicio.getSeleccion().equals(themes[j])) {
							row.setForeground(acceso[j][1]);
							if( !( ( MatteBorder )row.getBorder() ).getMatteColor().equals( acceso[j][2] )){
								row.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, acceso[j][1]));
							}
						}

					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel row = (JLabel) e.getComponent();
					for (int j = 0; j < themes.length; j++) {
						if (guiInicio.getSeleccion().equals(themes[j])) {
							row.setForeground(fuentes[j][0]);
							if( !( ( MatteBorder )row.getBorder() ).getMatteColor().equals( acceso[j][2] )){
								row.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, acceso[j][1]));
							}
							
						}

					}
				}
			});
		}
		db.consultarFecha("SELECT * from infoFisico", getThis());
		for (int j = 0; j < datosInfo.length; j++) {
			datosInfo[j] = new JLabel(datos[j][0] + "" + datos[j][1]);
			datosInfo[j].setBounds(20, posYdatosInfo, panelInfo.getWidth(), 50);
			datosInfo[j].setVerticalAlignment(CENTER);
			datosInfo[j].setFont(new Font("Franklin Gothic", 1, 25));
			panelInfo.add(datosInfo[j]);
			posYdatosInfo = posYdatosInfo + 50;
		}
		panelLista.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				//Bajar
				if (e.getWheelRotation() > 0) {
					if (panelLista.getY() + panelLista.getHeight() > panelPrincipalLista.getHeight()) {
						posYLista = posYLista - 30;
						panelLista.setLocation(0, posYLista);
					}
				}
				//Subir
				if (e.getWheelRotation() < 0) {
					if (posYLista < 0) {
						posYLista = posYLista + 30;
						panelLista.setLocation(0, posYLista);
					}
				}
			}
		});
		panelInfo.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				//Bajar
				if (e.getWheelRotation() > 0) {
					if (panelInfo.getY() + panelInfo.getHeight() > panelPrincipalInfo.getHeight()) {
						posYPanelInfo = posYPanelInfo - 50;
						panelInfo.setLocation(0, posYPanelInfo);
					}
				}
				//Subir
				if (e.getWheelRotation() < 0) {
					if (posYPanelInfo < 0) {
						posYPanelInfo = posYPanelInfo + 50;
						panelInfo.setLocation(0, posYPanelInfo);
					}
				}
			}
		});

		for (int i = 0; i < themes.length; i++) {
			aplicarTema("Claro", fondosClaro, fuentesClaro, accesoClaro);

		}
	}

	@Override
	public Color obtenerColor(Color[] color, int num) {
		return color[num];
	}

	@Override
	public void aplicarTema(String tema, Color[] colorFondo, Color[] colorFuente, Color[] colorAcceso) {
		this.setBackground(colorFondo[0]);
		panelLista.setBackground(colorFondo[0]);
		panelPrincipalLista.setBackground(colorFondo[0]);
		panelPrincipalLista.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, colorAcceso[0]));
		tituloLista.setBackground(colorAcceso[0]);
		tituloLista.setForeground(colorFuente[0]);
		titulo.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, colorFondo[1]));
		titulo.setForeground(colorFuente[0]);
		panelPrincipalInfo.setBackground(colorFondo[1].brighter());
		panelInfo.setBackground(colorFondo[1].brighter());
		for (int i = 0; i < registro.length; i++) {
			registro[i].setForeground(colorFuente[0]);
			registro[i].setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, fondos[1][0]));
		}
		for (int i = 0; i < datosInfo.length; i++) {
			datosInfo[i].setForeground(colorFuente[0]);
		}

	}

	@Override
	public void cambiarColorBordes(int opcion, JComponent componente, Color color) {
	}

	@Override
	public void cambiarColorCampos(int opcion, JComponent componente, Color fondo, Color letra) {
	}

	@Override
	public void cambiarColorBotones(int opcion, JComponent componente, Color fondo, Color letra) {
	}

	@Override
	public void cambiarColorAccionBoton(int opcion, JLabel componente) {
	}

	@Override
	public void cambiarColorAccionCampo(int opcion, JTextField componente) {
	}

	@Override
	public void cambiarTema() {
	}

	public JPanel getPanel() {
		return this;
	}

	public JPanel getPanelPrincipalInfo() {
		return panelPrincipalInfo;
	}

	public GuiInformacion getThis() {
		return this;
	}

	public int getPosXPanelPinfo() {
		return posXPanelPinfo;
	}

	public JPanel getPanelPrincipalLista() {
		return panelPrincipalLista;
	}

	public JLabel[] getRegistro() {
		return registro;
	}

	public  JLabel[] getDatosInfo() {
		return datosInfo;
	}

	public  String[][] getDatos() {
		return datos;
	}

}
