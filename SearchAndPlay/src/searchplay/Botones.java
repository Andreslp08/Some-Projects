package searchplay;

import com.sun.corba.se.impl.encoding.CodeSetConversion;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class Botones extends JFrame {

	private JLabel botonMyC;
	private ImageIcon iconoBotonMyC;
	private Image imgBotonMyC;	
	private String img[] = {"/img/BotonCerrar.png", "/img/BotonMinimizar.png"};
	private Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int uH;
	private int uW;
	private int mW ;
	private int mH;

	public Botones() {
		uW = (int) ScreenSize.getWidth();
		uH = (int) ScreenSize.getHeight();
		mW = 1920;
		mH = 1080;		
	}

	public void crearBotonCyM(String texto, int x, int y, int w, int h, Color color, int tipo, JFrame frame) {
		botonMyC = new JLabel();
		botonMyC.setBounds(( x*uW )/mW, ( y*uH )/mH, ( w*uW )/mW, ( h*uH )/mH);
		botonMyC.setBackground(color);
		botonMyC.setText("");
		botonMyC.setOpaque(true);
		iconoBotonMyC = new ImageIcon(this.getClass().getResource(img[tipo]));
		imgBotonMyC = iconoBotonMyC.getImage().getScaledInstance( ( ( w-10)*uW)/mW, ( ( h-10)*uH)/mH, Image.SCALE_SMOOTH);
		iconoBotonMyC = new ImageIcon(imgBotonMyC);
		botonMyC.setIcon(iconoBotonMyC);
		botonMyC.setHorizontalAlignment( CENTER );
		botonMyC.setVerticalAlignment(CENTER );
		botonMyC.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tipo == 1) {
					frame.setState(ICONIFIED);
				} else if (tipo == 0) {
					System.exit(0);
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				switch( tipo ){
					case 0:
						botonMyC.setBackground( Color.red );
						break;
					case 1:
						botonMyC.setBackground( new Color( 40, 40, 40, 255 ));
						break;
					default:
						botonMyC.setBackground( new Color( 10, 10, 10, 255 ));
						break;
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				botonMyC.setBackground(new Color(30, 30, 30, 255));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				botonMyC.setBackground(new Color(30, 30, 30, 255));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				botonMyC.setBackground(new Color(10, 10, 10, 255));
			}
		});
	}
	
	public JLabel obtenerBotonMyC() {
		return botonMyC;
	}
}
