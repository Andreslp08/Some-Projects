package Gui;

import logica.Audio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GuiBotones extends JLabel {

	private Color colorG;
	private int x, y, w, h, bw, bh;
	private int tamañoLetraOriginal;
	private Audio audio;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// resoluciones
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int UW = (int) screenSize.getWidth(), UH = (int) screenSize.getHeight();
	private final int MW = 1920, MH = 1080;
	
	//getters resolucion
	public int intW(int num) {
		return (num * UW) / MW;
	}

	public int intH(int num) {
		return (num * UH) / MH;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public GuiBotones(JComponent componente) {
		// audio
		audio = new Audio();
		colorG = new Color(20, 20, 20, 150);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel label = (JLabel) e.getComponent();
				tamañoLetraOriginal = label.getFont().getSize();
				colorG = new Color(0, 100, 255, 100);
				label.setFont(new Font("Impact", 0, label.getFont().getSize() + intW(10)));
				componente.repaint();
					audio.reproducirSonido("/sonidos/mouseAdentro.wav", false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JLabel label = (JLabel) e.getComponent();
				colorG = new Color(0, 100, 255, 200);
				label.setFont(new Font("Impact", 0, tamañoLetraOriginal + intW(20)));
				componente.repaint();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JLabel label = (JLabel) e.getComponent();
				colorG = new Color(0, 100, 255, 100);
				label.setFont(new Font("Impact", 0, tamañoLetraOriginal + intW(10)));
				componente.repaint();
				audio.reproducirSonido("/sonidos/click.wav", false);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel label = (JLabel) e.getComponent();
				colorG = new Color(20, 20, 20, 100);
				label.setFont(new Font("Impact", 0, tamañoLetraOriginal));
				componente.repaint();
			}

		});

	}

	public void setColor( Color color ){
		colorG = color;
	}
	public void boundsGboton(int x, int y, int w, int h, int bw, int bh) {
		this.x = intW(x);
		this.y = intH(y);
		this.w = intW(w);
		this.h = intH(h);
		this.bw = intW(bw);
		this.bh = intH(bh);
		repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(colorG);
		g.fillRoundRect(x, y, w, h, bw, bh);
		super.paintComponent(g);
	}

}
