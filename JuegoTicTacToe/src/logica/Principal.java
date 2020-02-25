
package logica;

import Gui.*;
import javax.swing.SwingUtilities;

public class Principal {
	public static void main( String args[] ){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GuiCarga guiCarga = new GuiCarga();
			}
		});
	}
}
