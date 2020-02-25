package Frames;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;

public class ComboBox extends JFrame {

	private JPanel PanelPrincipalCB;
	private JPanel PanelContenedor;
	private JComponent ComponenteCB;
	private JComponent ComponenteCasilla;
	private JLabel BotonCB;
	private JLabel Casilla[];
	private JLabel CasillaDefault;
	private int PosYCasilla;
	private int CasillasObtenidas = 1;
	private int PosYContenedor;
	private MouseWheelListener Scroll;
	private JLabel BotonRegistro;

	public ComboBox() {

	}

	public void NuevoComboBox( int X, int Y, int W, int H, String TEXTO ) {
			BotonCB = new JLabel();
			BotonCB.setForeground(Color.gray);
			BotonCB.setFont(new Font("Arial Narrow", 1, 20));
			BotonCB.setText(TEXTO );
			BotonCB.setHorizontalAlignment(CENTER);
			BotonCB.setVerticalAlignment(CENTER);
			BotonCB.setBackground(Color.red);
			BotonCB.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
			BotonCB.setBounds(X, Y, W, H);
			BotonCB.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {				
				}

				@Override
				public void mousePressed(MouseEvent e) {
					e.getComponent().setForeground( Color.darkGray );				
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					e.getComponent().setForeground( Color.gray );
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					ComponenteCB = (JComponent) e.getComponent();
					ComponenteCB.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red));
					e.getComponent().setForeground( Color.LIGHT_GRAY );

				}

				@Override
				public void mouseExited(MouseEvent e) {
					ComponenteCB = (JComponent) e.getComponent();
					ComponenteCB.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
					e.getComponent().setForeground( Color.gray  );

				}
			});
	}

	public void CasillasComboBox(   boolean Default,  int W, String NumCasillas[]) {
		if( Default == true ){
			W = BotonCB.getWidth();
		}
		// Frame 
		PanelPrincipalCB = new JPanel();
		PanelPrincipalCB.setLayout(null);
		PanelPrincipalCB.setBackground(new Color(12, 12, 12, 255));
		PanelPrincipalCB.setBounds(BotonCB.getX(), BotonCB.getY() + BotonCB.getHeight(), W, BotonCB.getHeight() * 5);
		PanelPrincipalCB.setVisible(false);
		//Casilla Default
		CasillaDefault = new JLabel();
		CasillaDefault.setBounds(0, 0, W, BotonCB.getHeight());
		CasillaDefault.setForeground(Color.gray);
		CasillaDefault.setText(BotonCB.getText());
		CasillaDefault.setBackground(new Color(12, 12, 12, 255));
		CasillaDefault.setFont(new Font("Arial Narrow", 1, 20));
		CasillaDefault.setHorizontalAlignment(CENTER);
		CasillaDefault.setVerticalAlignment(CENTER);
		CasillaDefault.setVisible(true);
		CasillaDefault.setOpaque(true);
		PanelPrincipalCB.add(CasillaDefault);
		CasillaDefault.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				ComponenteCasilla = (JComponent) e.getComponent();
				JLabel JCasilla = (JLabel) ComponenteCasilla;
				BotonCB.setText(JCasilla.getText());
				PanelPrincipalCB.setVisible(false);
				PanelContenedor.setVisible(false);
				PanelContenedor.setLocation(0, 30);
			}

			public void mousePressed(MouseEvent e) {
				ComponenteCasilla = (JComponent) e.getComponent();
				ComponenteCasilla.setBackground(new Color(22, 22, 22, 255));
			}

			public void mouseReleased(MouseEvent e) {
				ComponenteCasilla = (JComponent) e.getComponent();
				ComponenteCasilla.setBackground(new Color(18, 18, 18, 255));
			}

			public void mouseEntered(MouseEvent e) {
				ComponenteCasilla = (JComponent) e.getComponent();
				ComponenteCasilla.setBackground(new Color(18, 18, 18, 255));

			}

			public void mouseExited(MouseEvent e) {
				ComponenteCasilla = (JComponent) e.getComponent();
				ComponenteCasilla.setBackground(new Color(12, 12, 12, 255));
			}
		});
		BotonCB.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				if( PanelPrincipalCB.isVisible() == false ){
					PanelPrincipalCB.setVisible( true );
					PanelContenedor.setVisible(true);
				}
				else{
					PanelPrincipalCB.setVisible( false );
					PanelPrincipalCB.setVisible( false );
				}
						PosYContenedor = 30;
						PanelContenedor.setLocation(0, PosYContenedor);
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}
		});
		// Frame Contenedor
		PosYContenedor = CasillaDefault.getY() + CasillaDefault.getHeight();
		PanelContenedor = new JPanel();
		PanelContenedor.setLayout(null);
		PanelContenedor.setVisible(false);
		PanelContenedor.setBounds(0, PosYContenedor, W, BotonCB.getHeight() * NumCasillas.length);
		PanelContenedor.setBackground(Color.white);
		PanelPrincipalCB.add(PanelContenedor);
		// Casillas que se obtienen
		PosYCasilla = 0;
		Casilla = new JLabel[NumCasillas.length];
		for (int i = 0; i < Casilla.length; i++) {
			CasillasObtenidas = CasillasObtenidas + 1;
			Casilla[i] = new JLabel();
			Casilla[i].setBounds(0, PosYCasilla, W, BotonCB.getHeight());
			Casilla[i].setForeground(Color.gray);
			Casilla[i].setText(NumCasillas[i]);
			Casilla[i].setBackground(new Color(12, 12, 12, 255));
			Casilla[i].setFont(new Font("Arial Narrow", 1, 20));
			Casilla[i].setHorizontalAlignment(CENTER);
			Casilla[i].setVerticalAlignment(CENTER);
			Casilla[i].setVisible(true);
			Casilla[i].setOpaque(true);
			PanelContenedor.add(Casilla[i]);
			PosYCasilla = PosYCasilla + BotonCB.getHeight();
			Casilla[i].addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					ComponenteCasilla = (JComponent) e.getComponent();
					JLabel JCasilla = (JLabel) ComponenteCasilla;
					BotonCB.setText(JCasilla.getText());
					PanelPrincipalCB.setVisible(false);
					PanelContenedor.setVisible(false);
					PanelContenedor.setLocation(0, 30);
				}

				public void mousePressed(MouseEvent e) {
					ComponenteCasilla = (JComponent) e.getComponent();
					ComponenteCasilla.setBackground(new Color(22, 22, 22, 255));
				}

				public void mouseReleased(MouseEvent e) {
					ComponenteCasilla = (JComponent) e.getComponent();
					ComponenteCasilla.setBackground(new Color(18, 18, 18, 255));
				}

				public void mouseEntered(MouseEvent e) {
					ComponenteCasilla = (JComponent) e.getComponent();
					ComponenteCasilla.setBackground(new Color(18, 18, 18, 255));

				}

				public void mouseExited(MouseEvent e) {
					ComponenteCasilla = (JComponent) e.getComponent();
					ComponenteCasilla.setBackground(new Color(12, 12, 12, 255));
				}
			});

		}
		PanelPrincipalCB.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() <= 0) {
					if (PanelContenedor.getY() <= 0) {
						PosYContenedor = PosYContenedor + CasillaDefault.getHeight();
						PanelContenedor.setLocation(PanelContenedor.getX(), PosYContenedor );
					} else {
						PosYContenedor = 30;
						PanelContenedor.setLocation(0, PosYContenedor);
					}
				}

				if (e.getWheelRotation() >= 0) {
					if (PanelContenedor.getY() + PanelContenedor.getHeight() >= PanelPrincipalCB.getHeight() + 30) {
						PosYContenedor = PosYContenedor - CasillaDefault.getHeight();
						PanelContenedor.setLocation(PanelContenedor.getX(), PosYContenedor );
					} else {
						PosYContenedor = 150 - PanelContenedor.getHeight();
						PanelContenedor.setLocation(0, PosYContenedor );
					}
				}
				System.out.println("Panel P: " + PanelPrincipalCB.getHeight() + " Panel C: " + PanelContenedor.getHeight());
			}

		});
	}

	public JLabel ObtenerIndiceComboBox() {
		return BotonCB;
	}

	public JLabel ObtenerBotonCB() {
		return BotonCB;
	}

	public int ObtenerIndiceCasilla() {
		return Casilla.length;
	}

	public JLabel ObtenerCasilla(int i) {
		return Casilla[i];
	}

	public JLabel ObtenerCasillaDefault() {
		return CasillaDefault;
	}

	public JPanel ObtenerPanelCasillaCB() {
		return PanelPrincipalCB;
	}

	public JPanel ObtenerPanelContenedorCB() {
		return PanelContenedor;
	}

}
