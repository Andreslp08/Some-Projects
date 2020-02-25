package pruebacolorpicker;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class PruebaColorPicker extends JFrame {

	private PaletaRoja paletaRoja;
	private PaletaVerde paletaVerde;
	private PaletaAzul paletaAzul;
	private PaletaColores paletaColores;
	private static final int CANTIDAD = 255;
	private static final int W =  1, H = 50;
	private JLabel labelColor;

	public PruebaColorPicker() {
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.darkGray);
		labelColor = new JLabel();
		labelColor.setBounds( (this.getWidth()-500)/2, 100, 500, 100 );
		labelColor.setText("R: G: B:" );
		labelColor.setFont(new Font( "Impact", 0, 50));
		labelColor.setHorizontalAlignment(CENTER);
		labelColor.setVerticalAlignment(CENTER);
		labelColor.setForeground(Color.lightGray);
		this.add(labelColor);
		paletaColores = new PaletaColores();
		paletaColores.setBounds( (this.getWidth()-1530)/2, 10, W * PaletaColores.CANTIDAD, H);
		this.add(paletaColores);
//		paletaRoja = new PaletaRoja();
//		paletaRoja.setBounds(20, 20, W * CANTIDAD + 100, H);
//		this.add(paletaRoja);
//		paletaVerde = new PaletaVerde();
//		paletaVerde.setBounds(20, 20 + H, W * CANTIDAD, H);
//		this.add(paletaVerde);
//		paletaAzul = new PaletaAzul();
//		paletaAzul.setBounds(20, 20 + H * 2, W * CANTIDAD, H);
//		this.add(paletaAzul);
		
	}
public class PaletaColores extends JLabel {

		private int r, g, b;
		private Color color;
		private int posX, posY;
		private JLabel[] cuadroColor;
		private static final int CANTIDAD = 1530;
		
		public PaletaColores() {
			this.setLayout(null);
			this.setOpaque(true);
			this.setBackground(Color.darkGray);
			r = 255;
			g = 0;
			b = 0;
			color = new Color(r, g, b);
			cuadroColor = new JLabel[CANTIDAD];
			posX = 0;
			for (int i = 0; i < CANTIDAD; i++) {
				cuadroColor[i] = new JLabel("R: " + r + " G: " + g+" B: " + b);
				cuadroColor[i].setBounds(posX, 0, W, H);
				cuadroColor[i].setBackground(color);
				cuadroColor[i].setOpaque(true);
				cuadroColor[i].setFont(new Font("Arial", 0, -50));
				this.add(cuadroColor[i]);
				posX = posX + W;
//				if (i >= 255) {
//					r -= 1;
//					g = 0;
//					b = 0;
//					color = new Color(r, g, b);
//				} 
				if (i < 255) {
					g += 1;
					color = new Color(r, g, b);
				}
				 if( i > 255 && i< 255*2 ){
					r -=1;
					color = new Color(r, g, b);
				}
				 if( i > 255*2 && i < 255*3  ){
					 b +=1;
					 color = new Color(r, g, b);
				 }
				 if( i > 255*3 && i < 255*4  ){
					 g -= 1;
					  color = new Color(r, g, b);
				 }
				 if( i > 255*4 && i < 255*5){
					 r +=1;
					 color = new Color(r, g, b);
				 }
				 if( i > 255*5 && i < 255*6){
					 b -=1;
					 color = new Color(r, g, b);
				 }
				cuadroColor[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						labelColor.setText(label.getText());
						labelColor.setForeground(label.getBackground());
					}

				});
			}
		}

	}


	public class PaletaRoja extends JLabel {

		private int r, g, b;
		private Color color;
		private int posX, posY;
		private JLabel[] cuadroColor;

		public PaletaRoja() {
			this.setLayout(null);
			this.setOpaque(true);
			this.setBackground(Color.darkGray);
			r = 255;
			g = 255;
			b = 255;
			color = new Color(r, g, b);
			cuadroColor = new JLabel[CANTIDAD];
			posX = 0;
			for (int i = 0; i < CANTIDAD; i++) {
				cuadroColor[i] = new JLabel("Color: " + r + "," + g + "," + b);
				cuadroColor[i].setBounds(posX, 0, W, H);
				cuadroColor[i].setBackground(color);
				cuadroColor[i].setOpaque(true);
				cuadroColor[i].setFont(new Font("Arial", 0, -50));
				this.add(cuadroColor[i]);
				posX = posX + W;
//				if (i >= 255) {
//					r -= 1;
//					g = 0;
//					b = 0;
//					color = new Color(r, g, b);
//				} 
				if (i < 255) {
					g -= 1;
					b -= 1;
					color = new Color(r, g, b);
				}

				cuadroColor[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						System.err.println(label.getText());
					}

				});
			}
		}

	}

	public class PaletaVerde extends JLabel {

		private int r, g, b;
		private Color color;
		private int posX;
		private JLabel[] cuadroColor;

		public PaletaVerde() {
			this.setLayout(null);
			this.setOpaque(true);
			this.setBackground(Color.darkGray);
			r = 255;
			g = 255;
			b = 255;
			color = new Color(r, g, b);
			cuadroColor = new JLabel[CANTIDAD];
			posX = 0;
			for (int i = 0; i < CANTIDAD; i++) {
				cuadroColor[i] = new JLabel();
				cuadroColor[i].setBounds(posX, 0, W, H);
				cuadroColor[i].setBackground(color);
				cuadroColor[i].setOpaque(true);
				this.add(cuadroColor[i]);
				r -= 1;
				b -= 1;
				color = new Color(r, g, b);
				posX = posX + W;
			}
		}
	}

	public class PaletaAzul extends JLabel {

		private int r, g, b;
		private Color color;
		private int posX;
		private JLabel[] cuadroColor;

		public PaletaAzul() {
			this.setLayout(null);
			this.setOpaque(true);
			this.setBackground(Color.darkGray);
			r = 255;
			g = 255;
			b = 255;
			color = new Color(r, g, b);
			cuadroColor = new JLabel[CANTIDAD];
			posX = 0;
			for (int i = 0; i < CANTIDAD; i++) {
				cuadroColor[i] = new JLabel();
				cuadroColor[i].setBounds(posX, 0, W, H);
				cuadroColor[i].setBackground(color);
				cuadroColor[i].setOpaque(true);
				this.add(cuadroColor[i]);
				r -= 1;
				g -= 1;
				color = new Color(r, g, b);
				posX = posX + W;
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PruebaColorPicker();
			}
		});
	}
}
