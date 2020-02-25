package Logica;

import Db.*;
import Gui.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		Db db = new Db();
		//db.obtenerConexion();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				GuiInicio guiInicio = new GuiInicio();

			}
		});
	}

}
