package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.SwingConstants.CENTER;

public class VentanaPrincipal extends JFrame {

	// Variables
	private int posY = -100;
	private int posY2 = 600;
	private Timer Mov1;
	private Timer Mov2;
	private JPanel Panel;
	private JLabel Fondo;
	private JLabel Titulo;
	private JPanel Linea;
	private JPanel BarraPrincipal;
	private JLabel Cerrar;
	private JLabel Info;
	private JLabel Minimizar;
	private JLabel IniciarSesion;
	private JPanel PanelInfo;
	private JLabel TextoInfo;
	private JLabel Notificacion;
	private int xMouse;
	private int yMouse;
	private int posOriginal;
	private ImageIcon ImgFondo;
	private Image EscalarImgFondo;
	private int W = 430;

	public VentanaPrincipal() {
	}

	public void GuiVentana() {
		//Frame
		this.setTitle("Pruebas");
		this.setSize(W, 510);
		this.setLocationRelativeTo(null);
		posOriginal = this.getY();
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setVisible(true);
		setLocation(getX(), posY);
		// Panel
		Panel = new JPanel();
		Panel.setBackground(new Color(0, 0, 0, 0));
		Panel.setBounds(0, 0, W, 510);
		this.add(Panel);
		Panel.setVisible(true);
		//Label Fondo
		ImgFondo = new ImageIcon(this.getClass().getResource("/Img/Fondo.png"));
		EscalarImgFondo = ImgFondo.getImage().getScaledInstance(W, 510, Image.SCALE_SMOOTH);
		ImgFondo = new ImageIcon(EscalarImgFondo);
		Fondo = new JLabel();
		Fondo.setBounds(0, 0, W, 510);
		Fondo.setIcon(ImgFondo);
		Fondo.setForeground(new Color(255, 255, 255, 255));
		Fondo.setOpaque(true);
		Fondo.setBackground(new Color(0, 0, 0, 255));
		Panel.add(Fondo);
		Fondo.setVisible(true);
		// Panel Barra Principal
		BarraPrincipal = new JPanel();
		BarraPrincipal.setBounds(0, 0, W, 30);
		BarraPrincipal.setBackground(new Color(0, 0, 0, 15));
		Fondo.add(BarraPrincipal);
		BarraPrincipal.setVisible(true);
		//Label Titulo
		Titulo = new JLabel();
		Titulo.setBounds(0, 0, W, 30);
		Titulo.setBackground(Color.white);
		Titulo.setForeground(Color.lightGray);
		Titulo.setFont(new Font("Georgia", 2, 14));
		Titulo.setText("    Login & Register");
		Fondo.add(Titulo);
		Titulo.setVisible(true);
		Titulo.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				LineaMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				LineaMouseAfuera(evt);
			}

			public void mousePressed(MouseEvent evt) {
				VentanaPresionada(evt);
			}
		});
		Titulo.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				MoverVentana(evt);
			}
		});

		// Linea de titulo
		Linea = new JPanel();
		Linea.setBackground(Color.DARK_GRAY);
		Linea.setBounds(0, 30, 440, 1);
		Fondo.add(Linea);
		Linea.setVisible(false);
		// Boton cerrar
		ImageIcon ImgCerrar = new ImageIcon(this.getClass().getResource("/Img/BotonCerrar.png"));
		Image EscalarImgCerrar = ImgCerrar.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImgCerrar = new ImageIcon(EscalarImgCerrar);
		Cerrar = new JLabel();
		Cerrar.setBounds(390, 1, 39, 28);
		Cerrar.setHorizontalAlignment(CENTER);
		Cerrar.setIcon(ImgCerrar);
		Cerrar.setOpaque(true);
		Cerrar.setBackground(Color.BLACK);
		Titulo.add(Cerrar);
		Cerrar.setVisible(true);
		Cerrar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				CerrarMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				CerrarMouseAfuera(evt);
			}

			public void mousePressed(MouseEvent evt) {
				CerrarMousePresionado(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				CerrarMouseSuelto(evt);
			}
		});
		// Boton Info
		ImageIcon ImgInfo = new ImageIcon(this.getClass().getResource("/Img/BotonInfo.png"));
		Image EscalarImgInfo = ImgInfo.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImgInfo = new ImageIcon(EscalarImgInfo);
		Info = new JLabel();
		Info.setBounds(350, 1, 40, 28);
		Info.setHorizontalAlignment(CENTER);
		Info.setIcon(ImgInfo);
		Info.setOpaque(true);
		Info.setBackground(Color.BLACK);
		Titulo.add(Info);
		Info.setVisible(true);
		Info.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InfoMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				InfoMouseAfuera(evt);
			}

			public void mousePressed(MouseEvent evt) {
				InfoMousePresionado(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				InfoMouseSuelto(evt);
			}
		});
		// Panel informacion
		PanelInfo = new JPanel();
		PanelInfo.setBounds(310, 30, 120, 120);
		PanelInfo.setBackground(new Color(255, 255, 255, 10));
		Fondo.add(PanelInfo);
		PanelInfo.setVisible(false);
		// Informacion de diseño y versión
		TextoInfo = new JLabel();
		TextoInfo.setForeground(Color.white);
		TextoInfo.setText("<html> <p></p>  <p>Versión 1.0.1</p> <p>Diseñado por:</p> <p>Andrés López</p> <p>2018</p> </html>");
		PanelInfo.add(TextoInfo);
		TextoInfo.setVisible(true);
		// Boton Minimizar
		ImageIcon ImgMinimizar = new ImageIcon(this.getClass().getResource("/Img/BotonMinimizar.png"));
		Image EscalarImgMinimizar = ImgMinimizar.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImgMinimizar = new ImageIcon(EscalarImgMinimizar);
		Minimizar = new JLabel();
		Minimizar.setBounds(310, 1, 40, 28);
		Minimizar.setHorizontalAlignment(CENTER);
		Minimizar.setIcon(ImgMinimizar);
		Minimizar.setOpaque(true);
		Minimizar.setBackground(Color.BLACK);
		Titulo.add(Minimizar);
		Minimizar.setVisible(true);
		Minimizar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				MinimizarMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				MinimizarMouseAfuera(evt);
			}

			public void mousePressed(MouseEvent evt) {
				MinimizarMousePresionado(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				MinimizarMouseSuelto(evt);
			}
		});

		// Notificacion
		Notificacion = new JLabel();
		Notificacion.setBounds(20, posY2, 390, 30);
		Notificacion.setForeground(new Color(255, 0, 0, 100));
		Notificacion.setText("La contraseña debe tener almenos 6 digitos");
		Notificacion.setHorizontalAlignment(CENTER);
		Notificacion.setVerticalAlignment(CENTER);
		Fondo.add(Notificacion);
		Notificacion.setVisible(true);

		Mov1 = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				posY = posY + 15;
				setLocation(getX(), posY);
				if (posY >= posOriginal) {
					posY = posOriginal;
					Mov1.stop();
				}
			}
		});
		Mov1.start();
	}

	//  Setters de eventos
	public void LineaMouseAdentro(MouseEvent evt) {
		Linea.setVisible(true);
		BarraPrincipal.setBackground(new Color(255, 255, 255, 7));
	}

	public void LineaMouseAfuera(MouseEvent evt) {
		Linea.setVisible(false);
		BarraPrincipal.setBackground(new Color(0, 0, 0, 12));
	}

	public void VentanaPresionada(MouseEvent evt) {
		xMouse = evt.getX(); // tomar coordenadas de mouse
		yMouse = evt.getY();
	}

	public void MoverVentana(MouseEvent evt) {
		int x = evt.getXOnScreen(); // tomar coordenadas de pantalla
		int y = evt.getYOnScreen();
		setLocation(x - xMouse, y - yMouse);
	}

	public void CerrarMouseAdentro(MouseEvent evt) {
		Cerrar.setBackground(new Color(255, 0, 0, 100));
	}

	public void CerrarMouseAfuera(MouseEvent evt) {
		Cerrar.setBackground(Color.BLACK);
	}

	public void CerrarMousePresionado(MouseEvent evt) {
		Cerrar.setBackground(new Color(255, 0, 0, 140));
	}

	public void CerrarMouseSuelto(MouseEvent evt) {
		Cerrar.setBackground(new Color(255, 0, 0, 100));
		System.exit(0);
	}

	public void InfoMouseAdentro(MouseEvent evt) {
		Info.setBackground(new Color(51, 51, 51, 100));
	}

	public void InfoMouseAfuera(MouseEvent evt) {
		Info.setBackground(Color.BLACK);
		PanelInfo.setVisible(false);
	}

	public void InfoMousePresionado(MouseEvent evt) {
		Info.setBackground(new Color(51, 51, 51, 140));
		PanelInfo.setVisible(true);
	}

	public void InfoMouseSuelto(MouseEvent evt) {
		Info.setBackground(Color.black);
	}

	public void MinimizarMouseAdentro(MouseEvent evt) {
		Minimizar.setBackground(new Color(51, 51, 51, 100));
	}

	public void MinimizarMouseAfuera(MouseEvent evt) {
		Minimizar.setBackground(Color.BLACK);
	}

	public void MinimizarMousePresionado(MouseEvent evt) {
		Minimizar.setBackground(new Color(51, 51, 51, 140));
	}

	public void MinimizarMouseSuelto(MouseEvent evt) {
		Minimizar.setBackground(Color.black);
		setExtendedState(1);
	}

	//Metodos de notificación
	public void AnimacionNotificacion() {
		posY2 = 600;
		Mov2 = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				posY2 = posY2 - 2;
				Notificacion.setBounds(20, posY2, Notificacion.getWidth(), Notificacion.getHeight());
				if (posY2 <= 460) {
					Mov2.stop();
				}
			}
		});
		Mov2.start();
	}

	public void TextoNotificacion(String Texto) {
		Notificacion.setText(Texto);
	}

	// Metodo para adaptar tamaño de ventana segun sea Login o Register
	public void TamañoDeVentana(int W) {
		this.setBounds(this.getX(), this.getY(), W, this.getHeight());
		Panel.setBounds(this.getX(), this.getY(), W, this.getHeight());
		Fondo.setBounds(this.getX(), this.getY(), W, this.getHeight());
		EscalarImgFondo = ImgFondo.getImage().getScaledInstance(W, this.getHeight(), Image.SCALE_SMOOTH);
		ImgFondo = new ImageIcon(EscalarImgFondo);
		Fondo.setIcon(ImgFondo);
		BarraPrincipal.setSize(W, BarraPrincipal.getHeight());
		Titulo.setSize(W, Titulo.getHeight());
		Linea.setSize(W, Linea.getHeight());
		Cerrar.setBounds(821, Cerrar.getY(), Cerrar.getWidth(), Cerrar.getHeight());
		Minimizar.setBounds(741, Minimizar.getY(), Minimizar.getWidth(), Minimizar.getHeight());
		Info.setBounds(781, Info.getY(), Info.getWidth(), Info.getHeight());
		PanelInfo.setBounds(Minimizar.getX(), PanelInfo.getY(), PanelInfo.getWidth(), PanelInfo.getHeight());
		Notificacion.setSize(W - 40, Notificacion.getHeight());
		this.setLocationRelativeTo(null);
		Fondo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
	}

	//Metodos getters
	public String ObtenerTextoNotificacion() {
		return Notificacion.getText();
	}

	public JLabel ObtenerVentana() {
		return Fondo;
	}

	public int ObtenerPosY() {
		return posY;
	}

	public int ObtenerPosOriginal() {
		return posOriginal;
	}

	public int ObtenerW() {
		return W;
	}
}
