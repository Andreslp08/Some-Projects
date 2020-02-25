package Gui;

import Logica.Hilos;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class Notificacion extends JFrame {

	private JLabel labelNotificacion;
	private Hilos hilos = new Hilos();

	public Notificacion( ) {
		labelNotificacion = new JLabel("Notificación");
		labelNotificacion.setFont(new Font("Arial Narrow", 0, 20));
		labelNotificacion.setVerticalAlignment(CENTER);
		labelNotificacion.setHorizontalAlignment(CENTER);
	}

	public void notificar(String texto, JPanel contenedor, Color color, int x, int y, int w, int h, int posInicial, int posFinal) {
		labelNotificacion.setBounds(x, y, w, h);
		labelNotificacion.setText(texto);
		labelNotificacion.setForeground(color);
		System.err.println("clase noti: " + posInicial);
		hilos.notificacion(labelNotificacion, contenedor, posInicial, posFinal);
	}
	
	public JLabel getLabelNotificacion(){
		return labelNotificacion;
	}
}
