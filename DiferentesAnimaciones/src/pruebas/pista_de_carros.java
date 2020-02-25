package pruebas;

import com.sun.webkit.ThemeClient;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class pista_de_carros extends JFrame {

	private PanelContenedor panel;
	private Pista pista;
	private Carro carro;

	public pista_de_carros() {
		this.setUndecorated(true);
		this.setBounds(0, 0, 800, 800);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//Panel
		panel = new PanelContenedor();
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
//---------------------------------------------------------------------------------------------------------------------------

	public class PanelContenedor extends JLabel {

		private Color colorFondo = new Color(0, 80, 0, 255);

		public PanelContenedor() {
			this.setBackground(colorFondo);
			this.setOpaque(true);
			pista = new Pista();
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
//					pista.incrementarVelocidad();
				}

			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
			pista.dibujarPista(g, rootPane);

		}

	}
//---------------------------------------------------------------------------------------------------------------------------

	public class Pista extends Thread implements Runnable {

		private Color color = new Color(25, 25, 25, 255);
		private int wPista = 0;
		private int hPista = 0;
		private int xPista = 0;
		private int yPista = 0;
		private int xMarcas = 0;
		private int[] yMarcas = {20, 228, 436, 644};
		private int wMarcas = 0;
		private int hMarcas = 0;
		private int velocidad = 5;

		public Pista() {
			this.start();
			carro = new Carro();
		}

		public void dibujarPista(Graphics g, JComponent component) {
			wPista = 500;
			hPista = component.getHeight();
			xPista = (component.getWidth() - wPista) / 2;
			yPista = (component.getHeight() - hPista) / 2;
			g.setColor(color);
			g.fillRect(xPista, yPista, wPista, hPista);
			dibujarMarcas(g, component);
			carro.dibujarCarro(g);
		}

		public void dibujarMarcas(Graphics g, JComponent component) {
			wMarcas = 40;
			hMarcas = (component.getHeight()) / 6;
			xMarcas = (component.getWidth() - wMarcas) / 2;
			int y = 20;
			for (int i = 0; i < 4; i++) {
				g.setColor(Color.white);
				g.fillRect(xMarcas, yMarcas[i], wMarcas, hMarcas);
			}
		}

		public int getyMarcas(int i) {
			return yMarcas[i];
		}

		public void incrementarVelocidad() {
			this.velocidad++;
		}

		public void animCarretera() {
			boolean arriba = false;
			boolean abajo = true;
			while (true) {
				while (abajo == true) {
					for (int i = 0; i < 4; i++) {
						yMarcas[i] = yMarcas[i] + velocidad;
						if (yMarcas[i] >= rootPane.getHeight()) {
							arriba = true;
							abajo = false;
						}
						rootPane.repaint();
						try {
							Thread.sleep(1);
						} catch (InterruptedException ex) {
							Logger.getLogger(pista_de_carros.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
				while (arriba == true) {
					for (int i = 0; i < 4; i++) {
						if (yMarcas[i] >= rootPane.getHeight()) {
							yMarcas[i] = -75;
							abajo = true;
							arriba = false;
							rootPane.repaint();
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException ex) {
							Logger.getLogger(pista_de_carros.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			}

		}

		@Override
		public void run() {
			animCarretera();
		}

	}
//---------------------------------------------------------------------------------------------------------------------------

	public class Carro extends Thread {

		private ImageIcon iconCarro;
		private Image imgCarro;
		private int x = 0, y = 0, w = 200, h = 200;

		public Carro() {
			iconCarro = new ImageIcon(getClass().getResource("/img/carro.png"));
			imgCarro = iconCarro.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
			iconCarro = new ImageIcon(imgCarro);
			this.start();
		}

		public void dibujarCarro(Graphics g) {
			x = (rootPane.getWidth() - w) / 2;
			y = rootPane.getHeight() - h;
			g.drawImage(imgCarro, x, y, rootPane);
		}

		@Override
		public void run() {

				


		}

	}
//---------------------------------------------------------------------------------------------------------------------------
//main

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new pista_de_carros();
			}
		});

	}

}
