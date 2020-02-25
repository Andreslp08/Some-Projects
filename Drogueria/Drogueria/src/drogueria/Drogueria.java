
package drogueria;

import Gui.*;
import javax.swing.SwingUtilities;

public class Drogueria {

    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                //Gui gui = new Gui();
				Dashboard dashboard = new Dashboard();
				dashboard.abrirGui();
				dashboard.getVentana().setVisible(false);
				
            }
        });
    }
    
}
