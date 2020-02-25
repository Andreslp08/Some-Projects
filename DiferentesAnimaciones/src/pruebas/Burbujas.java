package pruebas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Burbujas extends JFrame {

	private static int posY = 0;
	private static int posX = 0;
	private Panel panel;
	private final int CANTIDAD = 50;
	private static Thread hilo;

	public Burbujas() {
		this.setBounds(0, 0, 800, 800);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new Panel(this);
	}

	//-------------------------------------------------------------------------------------	
	public class Panel extends JPanel {

		private Burbuja[] burbuja = new Burbuja[CANTIDAD];
		
		public Panel(JFrame frame) {
			this.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			this.setBackground(Color.darkGray);
			frame.add(this);
			for (int i = 0; i < CANTIDAD; i++) {
					burbuja[i] = new Burbuja(this);
				}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			crearGraficas(g);
		}

		public boolean crearGraficas(Graphics g) {
			posY = 0;
			posX = 0;
				for (int i = 0; i < CANTIDAD; i++) {
					burbuja[i].dibujar(g, posY);
					posY = posY + 15;
				}
				return true;
		}

	}
	//-------------------------------------------------------------------------------------

	public final class Burbuja implements Runnable {

		private int x, y;
		private int r, g, b;
		private Color color;
		private JComponent component;

		public Burbuja(JComponent componente) {
			this.component = componente;
			r = (int) Math.floor(Math.random() * 255);
			g = (int) Math.floor(Math.random() * 255);
			b = (int) Math.floor(Math.random() * 255);
			color = new Color(r, g, b, 50);
			hilo = new Thread(this);
			hilo.start();
		}

		public void dibujar(Graphics g, int y ) {
			this.y = y;
			g.setColor(color);
			g.fillOval(x, y, 200, 200);
		}

		public Thread getHilo() {
			return hilo;
		}

		@Override
		public void run() {
			x = 0;
			boolean derecha = true;
			boolean izquierda = false;
			while (true) {
				int random1 = (int) Math.floor(Math.random() * 10 + 5);
				int random2 = (int) Math.floor(Math.random() * 5 + 1);
				while (derecha == true) {
					x = x + random2;
					repaint();
					if (x >= component.getWidth() - 200) {
						izquierda = true;
						derecha = false;
					}
					try {
						Thread.sleep(random1);
					} catch (InterruptedException ex) {
						Logger.getLogger(Burbujas.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				while (izquierda == true) {
					x = x - random2;
					repaint();
					if (x <= 0) {
						izquierda = false;
						derecha = true;
					}
					try {
						Thread.sleep(random1);
					} catch (InterruptedException ex) {
						Logger.getLogger(Burbujas.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		new Burbujas();
	}

}
