package Frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.Timer;

public class GuiFotos extends JFrame {
	
	private JPanel PanelPrincipal;
	private JPanel PanelContenedor;
	private int Cantidad = 10;
	private JLabel[] LabelFotos = new JLabel[Cantidad];
	private String Imagenes[] = {"/FotosPerfil/0.png", "/FotosPerfil/1.png", "/FotosPerfil/2.png", "/FotosPerfil/3.png", "/FotosPerfil/4.png",
		"/FotosPerfil/5.png", "/FotosPerfil/6.png", "/FotosPerfil/7.png", "/FotosPerfil/8.png", "/FotosPerfil/9.png"};
	private ImageIcon[] Icono = new ImageIcon[Cantidad];
	private Image[] EscalarIcono = new Image[Cantidad];
	private int PosXFotos = 0;
	private int PosXContenedor;
	private JComponent FotoComponente;
	private Timer Timer;
	private Timer Timer2;
	private int PosXVentanaP = 1000;
	private JLabel Foto;
	private ImageIcon ImgFoto;
	private Image EscalarImgFoto;
	private JLabel LabelAddFoto;
	private ImageIcon ImgAddFoto;
	private Image EscalarImgAddFoto;
	private String UrlFoto = "/Img/FotoDePerfil.png";
	private boolean VerificarFoto = false;

	//490
	public GuiFotos() {
	}
	
	public void IconoFoto() {
		//Label Foto
		Foto = new JLabel();
		Foto.setBounds(355, 100, 150, 120);
		Foto.setHorizontalAlignment(CENTER);
		ImgFoto = new ImageIcon(this.getClass().getResource(UrlFoto));
		EscalarImgFoto = ImgFoto.getImage().getScaledInstance(118, 118, Image.SCALE_SMOOTH);
		ImgFoto = new ImageIcon(EscalarImgFoto);
		Foto.setIcon(ImgFoto);
		Foto.setVisible(true);
		//Label add Foto
		ImgAddFoto = new ImageIcon(this.getClass().getResource("/Img/AgregarFoto.png"));
		EscalarImgAddFoto = ImgAddFoto.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImgAddFoto = new ImageIcon(EscalarImgAddFoto);
		LabelAddFoto = new JLabel();
		LabelAddFoto.setBounds(115, 90, 30, 30);
		LabelAddFoto.setIcon(ImgAddFoto);
		Foto.add(LabelAddFoto);
		LabelAddFoto.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				VerificarFoto = true;
				Animacion();
				
			}
			
			public void mousePressed(MouseEvent e) {
				PresionadoZoomAddFoto();
			}
			
			public void mouseReleased(MouseEvent e) {
				AumentarZoomAddFoto();
			}
			
			public void mouseEntered(MouseEvent e) {
				AumentarZoomAddFoto();
			}
			
			public void mouseExited(MouseEvent e) {
				DisminuirZoomAddFoto();
			}
		});
	}
	
	public void PanelFotos() {
		PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(null);
		PanelPrincipal.setBounds(PosXVentanaP, 100, 300, 120);
		PanelPrincipal.setBackground(new Color(15, 15, 15, 255));
		PanelPrincipal.setVisible(true);
		PanelContenedor = new JPanel();
		PanelContenedor.setLayout(null);
		PanelContenedor.setBounds(PosXContenedor, 0, Cantidad * 150, PanelPrincipal.getHeight());
		PanelContenedor.setBackground(new Color(15, 15, 15, 255));
		PanelContenedor.setOpaque(true);
		PanelPrincipal.add(PanelContenedor);
		for (int i = 0; i < LabelFotos.length; i++) {
			LabelFotos[i] = new JLabel();
			LabelFotos[i].setBounds(PosXFotos, 0, 150, 120);
			LabelFotos[i].setBackground(new Color(15, 15, 15, 255));
			LabelFotos[i].setOpaque(true);
			Icono[i] = new ImageIcon(this.getClass().getResource(Imagenes[i]));
			EscalarIcono[i] = Icono[i].getImage().getScaledInstance(118, 118, Image.SCALE_SMOOTH);
			Icono[i] = new ImageIcon(EscalarIcono[i]);
			LabelFotos[i].setIcon(Icono[i]);
			LabelFotos[i].setHorizontalAlignment(CENTER);
			LabelFotos[i].setVerticalAlignment(CENTER);
			LabelFotos[i].setText(Imagenes[i]);
			LabelFotos[i].setFont(new Font("Arial", 0, 1));
			PanelContenedor.add(LabelFotos[i]);
			PosXFotos = PosXFotos + 150;
			LabelFotos[i].addMouseListener(new MouseListener() {
				
				public void mouseClicked(MouseEvent e) {
					JLabel Componente = (JLabel) e.getComponent();
					CambiarFotoMuestra(Componente.getText());
					Animacion2();
					
				}
				
				public void mousePressed(MouseEvent e) {
					FotoComponente = (JComponent) e.getComponent();
					FotoComponente.setBackground(new Color(25, 25, 25, 255));
				}
				
				public void mouseReleased(MouseEvent e) {
					FotoComponente = (JComponent) e.getComponent();
					FotoComponente.setBackground(new Color(20, 20, 20, 255));
				}
				
				public void mouseEntered(MouseEvent e) {
					FotoComponente = (JComponent) e.getComponent();
					FotoComponente.setBackground(new Color(20, 20, 20, 255));
				}
				
				public void mouseExited(MouseEvent e) {
					FotoComponente = (JComponent) e.getComponent();
					FotoComponente.setBackground(new Color(15, 15, 15, 255));
					
				}
			});
		}
		PanelPrincipal.addMouseWheelListener(new MouseWheelListener() {
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() <= 0) {
					if (PanelContenedor.getX() + PanelContenedor.getWidth() >= PanelPrincipal.getHeight() + 300) {
						PosXContenedor = PosXContenedor - 150;
						PanelContenedor.setLocation(PosXContenedor, PanelContenedor.getY());
						System.out.println(PanelContenedor.getX());
					} else {
						PosXContenedor = PanelPrincipal.getWidth() - PanelContenedor.getWidth();
						PanelContenedor.setLocation(PosXContenedor, 0);
					}
					
				}
				if (e.getWheelRotation() >= 0) {
					if (PanelContenedor.getX() < 0) {
						PosXContenedor = PosXContenedor + 150;
						PanelContenedor.setLocation(PosXContenedor, PanelContenedor.getY());
						System.out.println(PanelContenedor.getX());
					}
					
				}
				
			}
		});
	}
	
	public JPanel ObtenerPanelPrincipalFotos() {
		return PanelPrincipal;
	}
	
	public void Animacion() {
		PanelPrincipal.setVisible(true);
		PosXVentanaP = 1000;
		PanelPrincipal.setLocation(PosXVentanaP, PanelPrincipal.getY());
		Timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PosXVentanaP = PosXVentanaP - 10;
				PanelPrincipal.setLocation(PosXVentanaP, PanelPrincipal.getY());
				if (PosXVentanaP <= 490) {
					PosXVentanaP = 490;
					PanelPrincipal.setLocation(PosXVentanaP, PanelPrincipal.getY());
					Timer.stop();
					;
				}
			}
		});
		Timer.start();
	}
	
	public void Animacion2() {
		PanelPrincipal.setVisible(true);
		PosXVentanaP = 490;
		PanelPrincipal.setLocation(PosXVentanaP, PanelPrincipal.getY());
		Timer2 = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PosXVentanaP = PosXVentanaP + 10;
				PanelPrincipal.setLocation(PosXVentanaP, PanelPrincipal.getY());
				if (PosXVentanaP >= 1000) {
					PosXVentanaP = 1000;
					PanelPrincipal.setLocation(PosXVentanaP, PanelPrincipal.getY());
					Timer2.stop();
					;
				}
			}
		});
		Timer2.start();
	}
	
	public void AumentarZoomAddFoto() {
		ImgAddFoto = new ImageIcon(this.getClass().getResource("/Img/AgregarFoto.png"));
		EscalarImgAddFoto = ImgAddFoto.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImgAddFoto = new ImageIcon(EscalarImgAddFoto);
		LabelAddFoto.setIcon(ImgAddFoto);
	}
	
	public void DisminuirZoomAddFoto() {
		ImgAddFoto = new ImageIcon(this.getClass().getResource("/Img/AgregarFoto.png"));
		EscalarImgAddFoto = ImgAddFoto.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImgAddFoto = new ImageIcon(EscalarImgAddFoto);
		LabelAddFoto.setIcon(ImgAddFoto);
	}
	
	public void PresionadoZoomAddFoto() {
		ImgAddFoto = new ImageIcon(this.getClass().getResource("/Img/AgregarFoto.png"));
		EscalarImgAddFoto = ImgAddFoto.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImgAddFoto = new ImageIcon(EscalarImgAddFoto);
		LabelAddFoto.setIcon(ImgAddFoto);
	}
	
	public JLabel ObtenerFotoPerfil() {
		return Foto;
	}
	
	public boolean ObtenerEstadoFoto() {
		return VerificarFoto;
	}
	
	public void CambiarFotoMuestra(String UrlFoto) {
		this.UrlFoto = UrlFoto;
		ImgFoto = new ImageIcon(this.getClass().getResource(UrlFoto));
		EscalarImgFoto = ImgFoto.getImage().getScaledInstance(118, 118, Image.SCALE_SMOOTH);
		ImgFoto = new ImageIcon(EscalarImgFoto);
		Foto.setIcon(ImgFoto);
		Foto.setFont( new Font( "Arial", 1, 1) );
		Foto.setHorizontalAlignment(CENTER);
		Foto.setVerticalAlignment(CENTER);
		Foto.setText(UrlFoto);
	}
}
