package searchplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class VistaPrevia  {

	private JLabel info;
	private Timer mov1;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int mW;
	private int mH;
	private int uW;
	private int uH;

	public VistaPrevia() {
		info = new JLabel();
		uW = (int) screenSize.getWidth();
		uH = (int) screenSize.getHeight();
		mW = 1920;
		mH = 1080;
	}

	public void NombreAño(String nombre, String año, int x, int y, int w, int h, JComponent componente) {
		info.setBounds((x * uW) / mW, ( y * uH) / mH, (w * uW) / mW, (h * uH) / mH);
		info.setText(nombre + " ( " + año + " )");
		info.setBackground(new Color(0, 0, 0, 255));
		info.setOpaque(true);
		info.setForeground(Color.white);
		info.setVerticalAlignment(CENTER);
		info.setHorizontalAlignment(CENTER);
		info.setFont(new Font("Franklin Gothic", 3, (15 * uW) / mW));
		info.setVisible(false);
		componente.add(info);
	}

	public void visibilidadNyA(boolean vof) {
		info.setVisible(vof);
	}
	
	public JLabel obtenerInfo(){
		return info;
	}
}
