package Gui;

import logica.Audio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.*;

public class GuiBotonesPrincipales extends JFrame {

	private JLabel[] botones;
	private String[] textoBotones = {"X", "-"};
	private int posX, posY;
	private Audio audio;
	private GuiDialog dialog;
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
	public GuiBotonesPrincipales(JComponent componente, GuiPrincipal guiPrincipal) {
		// audio
		audio = new Audio();
		//
		botones = new JLabel[textoBotones.length];
		// jdialog
		dialog = new GuiDialog();
		for (int i = 0; i < dialog.getBotones().length; i++) {
			dialog.getBotones()[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					if (dialog.aceptar(label) == true) {
						System.exit(0);
					}
				}

			});
			//botonesPrincipales
			posX = intW(MW - 50);
			posY = intH(0);
			System.err.println(componente.getWidth());
			for (int j = 0; j < textoBotones.length; j++) {
				JLabel boton = botones[j];
				boton = new JLabel(textoBotones[j]);
				boton.setBounds(posX, posY, intW(50), intH(50));
				boton.setOpaque(true);
				boton.setBackground(new Color(25, 25, 25, 255));
				boton.setFont(new Font("Arial", 0, intW(20)));
				boton.setForeground(Color.white);
				boton.setHorizontalAlignment(SwingConstants.CENTER);
				boton.setVerticalAlignment(SwingConstants.CENTER);
				componente.add(boton);
				posX = posX - intW(50);
				boton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						e.getComponent().setBackground(new Color(45, 45, 45, 255));
						label.setFont(new Font("Arial", 1, intW(30)));
						audio.reproducirSonido("/sonidos/mouseAdentro.wav", false);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						e.getComponent().setBackground(new Color(25, 25, 25, 255));
						label.setFont(new Font("Arial", 0, intW(20)));
					}

					@Override
					public void mousePressed(MouseEvent e) {
						JLabel label = (JLabel) e.getComponent();
						e.getComponent().setBackground(new Color(0, 100, 250, 255));
						label.setFont(new Font("Arial", 1, intW(40)));
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						audio.reproducirSonido("/sonidos/click.wav", false);
						JLabel label = (JLabel) e.getComponent();
						e.getComponent().setBackground(new Color(45, 45, 45, 255));
						label.setFont(new Font("Arial", 1, intW(30)));
						switch (label.getText()) {
							case "X":
								dialog.setVisible(true);
								dialog.setVisible(true);
								dialog.configDialog("¿Desea salir por completo?", new Color(20, 20, 20, 230), "Arial", 1, 40);
								break;
							case "-":

								guiPrincipal.setState(1);
								break;
						}
					}

				});

			}
		}
	}

	public void cambiarPortador(JComponent componente) {
		componente.add(this);
	}
}
