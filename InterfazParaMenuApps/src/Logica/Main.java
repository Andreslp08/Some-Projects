
package Logica;

import Gui.InterfazPrincipal;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				InterfazPrincipal interfazPrincipal = new InterfazPrincipal();
			}
		});
		
	}
	
}
