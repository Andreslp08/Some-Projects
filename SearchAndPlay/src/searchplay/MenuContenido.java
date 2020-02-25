package searchplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class MenuContenido extends JFrame {

	private JPanel panelPrincipalMenu;
	private JLabel titulo;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	;
	private int mW;
	private int mH;
	private int uW;
	private int uH;
	private JLabel labelOpciones[];
	private String opciones[];
	private int posY;
	private boolean seleccionado;

	public MenuContenido(String[] opciones) {
		uW = (int) screenSize.getWidth();
		uH = (int) screenSize.getHeight();
		mW = 1920;
		mH = 1080;
		panelPrincipalMenu = new JPanel();
		titulo = new JLabel();
		posY = (90 * uH) / mH;
		this.opciones = opciones;
		labelOpciones = new JLabel[opciones.length];
		for (int i = 0; i < labelOpciones.length; i++) {
			labelOpciones[i] = new JLabel(opciones[i]);
			labelOpciones[i].setBounds((0 * uW) / mW, posY, (400 * uW) / mW, (50 * uH / mH));
			labelOpciones[i].setVerticalAlignment(CENTER);
			labelOpciones[i].setHorizontalAlignment(CENTER);
			labelOpciones[i].setFont(new Font("Agency FB", 2, (30 * uW) / mW));
			labelOpciones[i].setForeground(Color.white);
			labelOpciones[i].setOpaque(true);
			labelOpciones[i].setBackground(new Color(9, 9, 9, 255));
			panelPrincipalMenu.add(labelOpciones[i]);
			posY = posY + (50*uH)/mH;
			labelOpciones[i].addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {

				}

				public void mousePressed(MouseEvent e) {
					e.getComponent().setForeground(new Color(1, 58, 88, 255));
				}

				public void mouseReleased(MouseEvent e) {
					e.getComponent().setForeground(new Color(1, 48, 78, 255));
					for (int i = 0; i < labelOpciones.length; i++) {
						if (labelOpciones[i].getBackground().getRed() == 13) {
							labelOpciones[i].setBackground(new Color(9, 9, 9, 255));
						}
					}
					e.getComponent().setBackground(new Color(13, 13, 13, 255));
					e.getComponent().setForeground(new Color(1, 48, 78, 255));

				}

				public void mouseEntered(MouseEvent e) {
					e.getComponent().setForeground(new Color(1, 48, 78, 255));
				}

				public void mouseExited(MouseEvent e) {
					e.getComponent().setForeground(Color.white);
				}
			});
		}
	}

	public void guiMenuContenido(String textoTitulo, JComponent componente) {
		panelPrincipalMenu.setBounds((0 * uW) / mW, (0 * uH) / mH, (400 * uW) / mW, (800 * uH) / mH);
		panelPrincipalMenu.setBackground(new Color(9, 9, 9, 255));
		panelPrincipalMenu.setLayout(null);
		panelPrincipalMenu.setVisible(true);
		componente.add(panelPrincipalMenu);
		titulo.setBounds((0 * uW) / mW, (0 * uH) / mH, (400 * uW) / mW, (70 * uH) / mH);
		titulo.setBackground(new Color(13, 13, 13, 255));
		titulo.setOpaque(true);
		titulo.setText(textoTitulo);
		titulo.setHorizontalAlignment(CENTER);
		titulo.setVerticalAlignment(CENTER);
		titulo.setForeground(Color.darkGray);
		titulo.setFont(new Font("Arial Narrow", 2, (30 * uW) / mW));
		panelPrincipalMenu.add(titulo);

	}

	public void visibilidadMenu(boolean ToF) {
		panelPrincipalMenu.setVisible(ToF);
	}

	public void retornarMenu() {
		for (int i = 0; i < labelOpciones.length; i++) {
			if (labelOpciones[i].getBackground().getRed() == 13) {
				labelOpciones[i].setBackground(new Color(9, 9, 9, 255));
			}
		}
	}

	public JLabel obtenerSeleccion(int i) {
		return labelOpciones[i];
	}

}
