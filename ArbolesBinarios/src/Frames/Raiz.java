package Frames;

import java.awt.Image;
import javax.swing.*;

public class Raiz extends JLabel {

	private ImageIcon icono;
	private Image imagen;

	public Raiz(int tipo, Hoja hoja) {
		switch (tipo) {
			case 0:
				icono = new ImageIcon(getClass().getResource("/img/RaizIzq.png"));
				imagen = icono.getImage().getScaledInstance(hoja.getWidth(), hoja.getHeight(), Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagen);
				this.setIcon(icono);
				break;
			case 1:
				icono = new ImageIcon(getClass().getResource("/img/RaizDer.png"));
				imagen = icono.getImage().getScaledInstance(hoja.getWidth(), hoja.getHeight(), Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagen);
				this.setIcon(icono);
				break;
		}
	}
}
