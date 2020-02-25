package searchplay;

import DB.DB;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListaJuegos {

	DB db = new DB();

	private  ImageIcon iconoJuegosTodos[];
	private  Image imgJuegosTodos[];
	private int cantidadTotal;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int uW;
	private int uH;
	private int mW;
	private int mH;

	public ListaJuegos() {
		db.obtenerConexion();
		cantidadTotal = db.obtenerCantidadJuegos("SELECT COUNT(*) FROM juego");
		uW = (int) screenSize.getWidth();
		uH = (int) screenSize.getHeight();
		mW = 1920;
		mH = 1080;
		iconoJuegosTodos = new ImageIcon[obtenerNumJuegosTodos()];
		imgJuegosTodos = new Image[cantidadTotal];

		for (int i = 0; i < iconoJuegosTodos.length; i++) {
			db.consultarVideojuegoDB("SELECT * FROM juego WHERE  id  = '" + (i + 1) + "' ");
			iconoJuegosTodos[i] = new ImageIcon(getClass().getResource(db.obtenerPortada()), db.obtenerPortada());
			imgJuegosTodos[i] = iconoJuegosTodos[i].getImage().getScaledInstance((350 * uW) / mW, (230 * uH) / mH, Image.SCALE_SMOOTH);
			iconoJuegosTodos[i] = new ImageIcon(imgJuegosTodos[i]);
		}

	}

	public ListaJuegos(boolean xd) {
		uW = (int) screenSize.getWidth();
		uH = (int) screenSize.getHeight();
		mW = 1920;
		mH = 1080;
		iconoJuegosTodos = null;

	}

	public  void ListaJuegos(String portada, String query, int w, int h, JLabel componente) {
		db.obtenerConexion();
		cantidadTotal = db.obtenerCantidadJuegos(query);
		if (iconoJuegosTodos == null) {
			iconoJuegosTodos = new ImageIcon[obtenerNumJuegosTodos()];
			imgJuegosTodos = new Image[cantidadTotal];
		}
		for (int i = 0; i < iconoJuegosTodos.length; i++) {
			iconoJuegosTodos[i] = new ImageIcon(getClass().getResource(portada));
			imgJuegosTodos[i] = iconoJuegosTodos[i].getImage().getScaledInstance((w * uW) / mW, (h * uH) / mH, Image.SCALE_SMOOTH);
			iconoJuegosTodos[i] = new ImageIcon(imgJuegosTodos[i]);
			componente.setIcon(iconoJuegosTodos[i]);
		}

	}

	public int obtenerNumJuegosTodos() {
		return cantidadTotal;
	}

	public  ImageIcon obtenerIconoJuegos(int icono) {
		return iconoJuegosTodos[icono];
	}

}
