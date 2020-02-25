package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import logica.Jugador;

public class GuiJugador extends JLabel {

	private final int W = 400, H = 400;
	private JLabel labelIcono;
	private JLabel labelInfo;
	private ImageIcon icono;
	private Image img;
	private Jugador jugador;
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

	public GuiJugador(GuiJuego guiJuego, String url) {
		setSize(intW(W), intH(H));
		guiJuego.add(this);
		setVerticalAlignment(CENTER);
		setHorizontalAlignment(CENTER);
		setFont(new Font("Impact", 0, intW(50)));
		setForeground(new Color(0, 100, 250, 255));
		setLayout(null);
		//label icono
		labelIcono = new JLabel();
		labelIcono.setBounds(intW(0), intH(0), intW(400), intH(200));
		labelIcono.setVerticalAlignment(CENTER);
		labelIcono.setHorizontalAlignment(CENTER);
		add(labelIcono);
		icono = new ImageIcon(getClass().getResource(url));
		img = icono.getImage().getScaledInstance(intW(100), intH(100), Image.SCALE_SMOOTH);
		icono = new ImageIcon(img);
		labelIcono.setIcon(icono);
//label icono
		labelInfo = new JLabel("<html><p>Rondas ganadas: 0</p></html>");
		labelInfo.setBounds(intW(0), intH(200), intW(400), intH(200));
		labelInfo.setVerticalAlignment(CENTER);
		labelInfo.setHorizontalAlignment(CENTER);
		labelInfo.setFont(new Font("Chiller", 1, intW(40)));
		labelInfo.setForeground(Color.lightGray);
		add(labelInfo);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(255, 255, 255, 10));
		g.fillOval(intW(0), intH(0), intW(W), intH(H));
		super.paintComponent(g);
	}

	public void crearJugador(GuiJuego guiJuego, String nombre, int rondasTotales, int rondasParaGanar) {
		jugador = new Jugador(guiJuego, nombre, rondasTotales, rondasParaGanar);
	}

	public Jugador getJugador() {
		return jugador;
	}

	public JLabel getLabelInfo() {
		return labelInfo;
	}

}
