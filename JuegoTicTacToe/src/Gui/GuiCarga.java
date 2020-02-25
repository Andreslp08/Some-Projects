package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class GuiCarga extends JFrame {

	private ImageIcon IconoFondo, iconoTitulo, iconoBarra;
	private Image imgFondo, imgTitulo, imgBarra;
	private JLabel labelFondo, labelTitulo;
	private GuiPrincipal guiPrincipal;
	private BarraCarga barraCarga;
	// resoluciones
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int UW = (int) screenSize.getWidth(), UH = (int) screenSize.getHeight();
	private final int MW = 1920, MH = 1080;

	public GuiCarga() {
		this.setUndecorated(true);
		this.setSize( intW(500), intH(600));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//Imagen fondo barra menu
		iconoBarra = new ImageIcon(getClass().getResource("/img/icono.png"));
		imgBarra = iconoBarra.getImage().getScaledInstance(intW(550), intH(600), Image.SCALE_SMOOTH);
		iconoBarra = new ImageIcon(imgBarra);
		this.setIconImage(iconoBarra.getImage());
		this.setLayout(null);
		//Imagen titulo
		iconoTitulo = new ImageIcon(getClass().getResource("/img/titulo.png"));
		imgTitulo = iconoTitulo.getImage().getScaledInstance(intW(400), intH(400), Image.SCALE_SMOOTH);
		iconoTitulo = new ImageIcon(imgTitulo);
		//fondo carga
		IconoFondo = new ImageIcon(getClass().getResource("/img/fondoCarga.jpg"));
		imgFondo = IconoFondo.getImage().getScaledInstance(intW(550), intH(600), Image.SCALE_SMOOTH);
		IconoFondo = new ImageIcon(imgFondo);
		labelFondo = new JLabel();
		labelFondo.setBounds(intW(0), intH(0), intW(550), intH(600));
		labelFondo.setIcon(IconoFondo);
		labelFondo.setLayout(null);
		this.add(labelFondo);
		// titulo
		labelTitulo = new JLabel();
		labelTitulo.setBounds(intW((500 - 400) / 2), intH((600 - 400) / 2), intW(400), intH(400));
		labelTitulo.setIcon(iconoTitulo);
		labelFondo.add(labelTitulo);
		//barra carga
		barraCarga = new BarraCarga(this);
		labelFondo.add(barraCarga);
	}

	public class BarraCarga extends JLabel {

		private Timer timer;
		private int wBarra, hBarra, progreso;
		private GuiPrincipal guiPrincipal;
		private int contador;

		public BarraCarga(JFrame frame) {
			//Barra de carga
			wBarra = intW(0);
			hBarra = intH(30);
			this.setBounds(intW(0),labelFondo.getHeight() -  intH(35), frame.getWidth(), intH(30));
			this.setFont(new Font("Impact", 1, intW(20)));
			this.setHorizontalAlignment(CENTER);
			this.setVerticalAlignment(CENTER);
			this.setForeground(Color.white);
			timer = new Timer(1, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					wBarra = wBarra + 4;
					progreso = progreso + 1;
					contador = contador + 1;
					setText("" + progreso + "%");
					repaint();
					if (wBarra >= intW(400)) {
						wBarra = intW(400);
						progreso = 100;
						setText("" + progreso + "%");
						repaint();
						if (contador == 150) {
							timer.stop();
							guiPrincipal = new GuiPrincipal();
							frame.setVisible(false);
						}
					}
				}
			});
			timer.start();
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(new Color(0, 100, 255, 100));
			g.fillRoundRect(intW(50), intH(0), wBarra, hBarra, intW(30), intH(30));
			super.paintComponent(g);
			repaint();
		}

	}
		public int intW(int num) {
			return ( num*UW )/MW;
		}
		public int intH(int num) {
			return ( num*UH )/MH;
		}		
		
}
