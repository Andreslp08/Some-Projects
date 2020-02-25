package searchplay;

import DB.DB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class Juego {

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int mW;
	private int mH;
	private int uW;
	private int uH;
	private JPanel panelContenedor;
	private JLabel labelTitulo, imgVJ, botonDescarga, labelGenero, labelPlataforma, labelPuntuacionT, labelDescripcion, labelRM, labelRR;
	private ImageIcon iconoCaratula;
	private Image imgCaratula;
	private int posY;
	private Timer mov1;
	private DB db = new DB();

	public Juego() {
		uW = (int) screenSize.getWidth();
		uH = (int) screenSize.getHeight();
		mW = 1920;
		mH = 1080;
		panelContenedor = new JPanel();
		labelTitulo = new JLabel();
		imgVJ = new JLabel();
		botonDescarga = new JLabel();
		labelGenero = new JLabel();
		labelPlataforma = new JLabel();
		labelPuntuacionT = new JLabel();
		labelDescripcion = new JLabel();
		labelRM = new JLabel();
		labelRR = new JLabel();
		posY = (-1500 * uW) / mW;
	}

	public void guiJuego(String titulo, String año, String img, String genero, String plataforma, String puntuacion, String descripcion, String rm, String rr, JComponent componente) {
		//panel contenedor
		panelContenedor.setBounds((0 * uW) / mW, posY, (1920 * uW) / mW, (800 * uH) / mH);
		panelContenedor.setOpaque(true);
		panelContenedor.setLayout(null);
		componente.add(panelContenedor);
		panelContenedor.setBackground(new Color(11, 11, 11, 255));
		// Titulo
		labelTitulo.setBounds((20 * uW) / mW, (0 * uH) / mH, (600 * uW) / mW, (100 * uH) / mH);
		labelTitulo.setText(titulo + " ( " + año + " )");
		labelTitulo.setFont(new Font("Arial Rounded MT", 1, (30 * uW) / mW));
		labelTitulo.setForeground(Color.white);
		labelTitulo.setHorizontalAlignment(CENTER);
		labelTitulo.setVerticalAlignment(CENTER);
		panelContenedor.add(labelTitulo);
		//Imagen
		imgVJ.setBounds((20 * uW) / mW, (100 * uH) / mH, (600 * uW) / mW, (600 * uH) / mH);
		imgVJ.setBackground(new Color(15, 15, 15, 255));
		imgVJ.setOpaque(true);
		imgVJ.setHorizontalAlignment(CENTER);
		imgVJ.setVerticalAlignment(CENTER);
		panelContenedor.add(imgVJ);
		iconoCaratula = new ImageIcon(getClass().getResource(img));
		imgCaratula = iconoCaratula.getImage().getScaledInstance((600 * uW) / mW, (600 * uH) / mH, Image.SCALE_SMOOTH);
		iconoCaratula = new ImageIcon(imgCaratula);
		imgVJ.setIcon(iconoCaratula);
		//BotonDescarga
		botonDescarga.setBounds((170 * uW) / mW, (720 * uH) / mH, (300 * uW) / mW, (60 * uH) / mH);
		botonDescarga.setText("Descargar");
		botonDescarga.setBackground(new Color(11, 11, 11, 255));
		botonDescarga.setFont(new Font("Impact", 1, (40 * uW) / mW));
		botonDescarga.setForeground(Color.white);
		botonDescarga.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.white));
		botonDescarga.setHorizontalAlignment(CENTER);
		botonDescarga.setHorizontalAlignment(CENTER);
		botonDescarga.setOpaque(true);
		panelContenedor.add(botonDescarga);
		botonDescarga.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				botonDescarga.setBackground(new Color(25, 25, 25, 255));
			}

			public void mouseReleased(MouseEvent e) {
				botonDescarga.setBackground(new Color(20, 20, 20, 255));
			}

			public void mouseEntered(MouseEvent e) {
				botonDescarga.setBackground(new Color(20, 20, 20, 255));
				botonDescarga.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, new Color(1, 48, 78, 255)));
			}

			public void mouseExited(MouseEvent e) {
				botonDescarga.setBackground(new Color(11, 11, 11, 255));
				botonDescarga.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.white));
			}
		});
		//Genero
		labelGenero.setBounds((640 * uW) / mW, (100 * uH) / mH, (600 * uW) / mW, (80 * uH) / mH);
		labelGenero.setText("<html> <p><font color='#01304E'>Genero:</font></p> <font color='#FFFFFF'>" + genero + "</font></html>");
		labelGenero.setFont(new Font("Arial Rounded MT", 1, (30 * uW) / mW));
		labelGenero.setForeground(Color.white);
		panelContenedor.add(labelGenero);
		//Plataforma
		labelPlataforma.setBounds((640 * uW) / mW, (180 * uH) / mH, (600 * uW) / mW, (80 * uH) / mH);
		labelPlataforma.setText("<html><p><font color='#01304E'>Plataforma:</p> <font color ='#FFFFFF'>" + plataforma + "</font> </html>");
		labelPlataforma.setFont(new Font("Arial Rounded MT", 1, (30 * uW) / mW));
		labelPlataforma.setForeground(Color.white);
		panelContenedor.add(labelPlataforma);
		//Puntuacion
		labelPuntuacionT.setBounds((640 * uW) / mW, (260 * uH) / mH, (600 * uW) / mW, (110 * uH) / mH);
		labelPuntuacionT.setText("<html><p><font color='#01304E'>Puntuacion general:</p> <font color ='#FFFFFF'>" + puntuacion + "</html>");
		labelPuntuacionT.setFont(new Font("Arial Rounded MT", 1, (30 * uW) / mW));
		labelPuntuacionT.setForeground(Color.white);
		panelContenedor.add(labelPuntuacionT);
		//Descripcion
		labelDescripcion.setBounds((640 * uW) / mW, (380 * uH) / mH, (600 * uW) / mW, (320 * uH) / mH);
		labelDescripcion.setText("<html><p><font color='#01304E'>Descripción:</p>" + descripcion + "</html>");
		labelDescripcion.setFont(new Font("Arial Rounded MT", 1, (27 * uW) / mW));
		labelDescripcion.setForeground(Color.white);
		panelContenedor.add(labelDescripcion);
		//Requisitos minimos
		labelRM.setBounds((1270 * uW) / mW, (100 * uH) / mH, (600 * uW) / mW, (300 * uH) / mH);
		labelRM.setText("<html><p><font color='#01304E'>Requisitos minimos( Solo PC ):</p> <font color ='#FFFFFF'>" + rm + "</font> </html>");
		labelRM.setFont(new Font("Arial Rounded MT", 1, (25 * uW) / mW));
		labelRM.setForeground(Color.white);
		panelContenedor.add(labelRM);
		//Requisitos recomendados
		labelRR.setBounds((1270 * uW) / mW, (400 * uH) / mH, (600 * uW) / mW, (300 * uH) / mH);
		labelRR.setText("<html><p><font color='#01304E'>Requisitos recomendados( Solo PC ):</p> <font color ='#FFFFFF'>" + rr + "</font> </html>");
		labelRR.setFont(new Font("Arial Rounded MT", 1, (25 * uW) / mW));
		labelRR.setForeground(Color.white);
		panelContenedor.add(labelRR);
		animVentana();
	}

	public JPanel obtenerPanelC() {
		return panelContenedor;
	}

	public void animVentana() {
		posY = (-1500 * uW) / mW;
		mov1 = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posY = posY + 50;
				panelContenedor.setLocation(panelContenedor.getX(), posY);
				if (posY >= (0 * uH) / mH) {
					posY = (0 * uH) / mH;
					panelContenedor.setLocation(panelContenedor.getX(), posY);
					mov1.stop();
				}
			}
		});
		mov1.start();
	}

}
