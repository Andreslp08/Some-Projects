package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;
import logica.Audio;

public class GuiDialogGanador extends JDialog {

	private JLabel contenedor;
	private Color colorG;
	private GuiBotones[] opciones;
	private String[] textoOpciones = {"Salir"};
	private int posXopcion, posYopcion;
	private ImageIcon icono;
	private Image img;
	private final int W_IMG = 1920, H_IMG = 500;
	private Audio audio;
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

	public class Contenedor extends JLabel {

		public Contenedor() {
			this.setLayout(null);
			// audio
			audio = new Audio();
			//imagen
			icono = new ImageIcon(getClass().getResource("/img/fondoGanador2.png"));
			img = icono.getImage().getScaledInstance(intW(W_IMG), intH(H_IMG), Image.SCALE_SMOOTH);
			icono = new ImageIcon(img);
			//label texto
			setFont(new Font("Impact", 1, intW(100)));
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
			setHorizontalTextPosition(CENTER);
			setVerticalTextPosition(CENTER);
			setForeground(Color.white);

			// botones
			posXopcion = (1920 - 200) / 2;
			posYopcion = 700;
			opciones = new GuiBotones[textoOpciones.length];
			for (int i = 0; i < textoOpciones.length; i++) {
				opciones[i] = new GuiBotones(this);
				opciones[i].setBounds(intW(posXopcion), intH(posYopcion), intW(300), intH(70));
				opciones[i].setText(textoOpciones[i]);
				opciones[i].setForeground(Color.white);
				opciones[i].boundsGboton(0, 0, 300, 70, 50, 50);
				opciones[i].setFont(new Font("Impact", 0, intW(30)));
				opciones[i].setHorizontalAlignment(CENTER);
				opciones[i].setVerticalAlignment(CENTER);
				this.add(opciones[i]);
				posXopcion = posXopcion + 155;
				opciones[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						aceptar(label);

					}

				});
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(img, intW(0), intH((1080 - H_IMG) / 2), rootPane);
			super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
		}

	}

	public GuiDialogGanador() {
		this.setUndecorated(true);
		this.setBounds(intW(0), intH(0), intW(1920), intH(1080));
		this.setVisible(false);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(0, 0, 0, 210));
		this.setAlwaysOnTop(true);
		contenedor = new Contenedor();
		add(contenedor);

	}

	boolean aceptar = false;

	public boolean aceptar(JLabel label) {
		switch (label.getText()) {
			case "Salir":
				setVisible(false);
				break;
		}
		return aceptar;
	}

	public void configDialog(String texto, Color colorG, String fuente, int tipo, int tamaño) {
		this.colorG = colorG;
		contenedor.setText(texto);
		contenedor.setBackground(colorG);
		contenedor.setFont(new Font(fuente, tipo, tamaño));
		audio.reproducirSonido("/sonidos/ganador.wav", false);
	}

	public GuiBotones[] getBotones() {
		return opciones;
	}

	public boolean opcionSeleccionada() {
		return true;
	}

}
