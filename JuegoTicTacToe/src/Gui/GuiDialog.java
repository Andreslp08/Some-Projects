package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class GuiDialog extends JDialog {

	private JLabel labelTexto;
	private Color colorG;
	private GuiBotones[] opciones;
	private String[] textoOpciones = {"Aceptar", "Cerrar"};
	private int posXopcion, posYopcion;
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

	public GuiDialog() {
		this.setUndecorated(true);
		this.setBounds(intW(0), intH(0), intW(600), intH(400));
		this.setBackground(new Color(20, 20, 20, 20));
		this.setVisible(false);
		this.setLocationRelativeTo(null);
		//label texto
		labelTexto = new JLabel();
		labelTexto.setFont(new Font("Arial", 1, intW(20)));
		labelTexto.setHorizontalAlignment(CENTER);
		labelTexto.setVerticalAlignment(CENTER);
		labelTexto.setHorizontalTextPosition(CENTER);
		labelTexto.setVerticalTextPosition(CENTER);
		labelTexto.setForeground(Color.white);
		labelTexto.setLayout(null);
		this.add(labelTexto);
		// botones
		posXopcion = (600 - 300) / 2;
		System.err.println("pos X " + posXopcion);
		System.err.println("pos:" + this.getWidth());
		posYopcion = 400 - 100;
		opciones = new GuiBotones[textoOpciones.length];
		for (int i = 0; i < textoOpciones.length; i++) {
			opciones[i] = new GuiBotones(labelTexto);
			opciones[i].setBounds(intW(posXopcion), intH(posYopcion), intW(150), intH(70));
			System.err.println("pos X " + posXopcion);
			opciones[i].setText(textoOpciones[i]);
			opciones[i].setForeground(Color.white);
			opciones[i].boundsGboton(0, 0, 150, 70, 50, 50);
			opciones[i].setFont(new Font("Impact", 0, intW(25)));
			opciones[i].setHorizontalAlignment(CENTER);
			opciones[i].setVerticalAlignment(CENTER);

			labelTexto.add(opciones[i]);
			posXopcion = posXopcion + 155;
			opciones[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel label = (JLabel) e.getComponent();
					aceptar(label);

				}

			});
		}

	}

	boolean aceptar = false;

	public boolean aceptar(JLabel label) {
		switch (label.getText()) {
			case "Aceptar":
				aceptar = true;
				setVisible(false);
				break;
			case "Cerrar":
				setVisible(false);
				aceptar = false;
				break;

		}
		return aceptar;
	}

	public void configDialog(String texto, Color colorG, String fuente, int tipo, int tamaño) {
		this.colorG = colorG;
		labelTexto.setText(texto);
		labelTexto.setBackground(colorG);
		labelTexto.setFont(new Font(fuente, tipo, intW(tamaño)));
	}

	public GuiBotones[] getBotones() {
		return opciones;
	}

	public boolean opcionSeleccionada() {
		return true;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g = labelTexto.getGraphics();
		g.setColor(colorG);
		g.fillRoundRect(intW(5), intW(5), this.getWidth() - intW(10), this.getHeight() - intH(10), intW(50), intH(50));
		labelTexto.update(g);
	}

}
