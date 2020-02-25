package pruebas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AnimPelota extends JFrame {

	private Panel panel;
	private Pelota pelota;

	public AnimPelota() {
		this.setUndecorated(true);
		this.setSize(800, 800);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pelota = new Pelota();
		panel = new Panel();
		this.add(panel);
	}

//---------------------------------------------------------------------------------------
	public class Panel extends JPanel  {
		private Pelota[] pelotas;
		private static final int NUM_PELOTAS = 1000;
		public Panel() {
			this.setBackground(new Color( 25, 25, 25, 255));
			this.setBounds(0, 0, 800, 800 );
			pelotas = new Pelota[NUM_PELOTAS];
			for( int i = 0; i < NUM_PELOTAS; i ++ ){
				pelotas[i] = new Pelota();
			}

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
			for( int i = 0; i < NUM_PELOTAS; i ++ ){
				pelotas[i].dibujarPelota(g);
			}

		}

	}
//---------------------------------------------------------------------------------------

	public  class Pelota implements Runnable{

		private  int x, y, dx, dy,  velocidad;
		private static final int W =  2;
		private static final int H = 2;
		private  int r, g, b;
		private  Color colorPelota;

		public Pelota( ) {
			x = (int)Math.floor(Math.random()*((800-W)-1)+1);
			y = (int)Math.floor(Math.random()*((800-H)-1)+1);
			dx = (int)Math.floor(Math.random()*(3-1)+1);;
			dy = (int)Math.floor(Math.random()*(3-1)+1);;
			velocidad = (int)Math.floor(Math.random()*(10-3)+3);
			r =  (int) Math.floor(Math.random()*255);
			g =  (int) Math.floor(Math.random()*255);
			b =  (int) Math.floor(Math.random()*255);
			colorPelota = new Color( r, g, b, 255 );
			new Thread(this).start();
		}

		public  void dibujarPelota(Graphics g) {
			g.setColor(colorPelota);
			g.fillOval(x, y, W, H);
		}

		public  void animPelota() {
			x = x + dx;
			y = y + dy;
			if (x  > 800-W ) {
				dx = dx- 1;
				
			}
			if (x  < 0 ) {
				dx = dx+ 1;
				
			}
			if (y   > 800-H ) {
				dy = dy - 1;
				
			}
			if (y   < 0) {
				dy = dy+ 1;
				
			}
		
		}

		@Override
		public void run() {
			for(;;){
				animPelota();
				repaint();
				try {
					Thread.sleep(velocidad);
				} catch (InterruptedException ex) {
					System.err.println("Error al intentar descansar el hilo: " + ex);
				}
			}
		}
	}
//---------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AnimPelota();
			}
		});
	}
}
