package Frames;

import DB.DBUsuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.SwingConstants.CENTER;

public class GuiLogin extends JFrame {

	// Variables
	private int Alpha = 0;
	private int Alpha2 = 0;
	private JLabel IniciarSesion;
	private JLabel Foto;
	private JTextField CampoNombre;
	private JPanel PanelNombre;
	private JPasswordField CampoContra;
	private JPanel PanelContra;
	private JLabel Recordar;
	private JLabel Ingresar;
	private JLabel Registro;
	private JPanel BotonIngresar;
	private JPanel BotonRegistro;
	private boolean CorreoCorrecto;
	private boolean ContraCorrecta;
	private Timer Mov1;
	private VentanaPrincipal Ventana = new VentanaPrincipal();
	private DB.DBUsuarios DBUsuarios = new DBUsuarios();
	private GuiFotos GuiFotos = new GuiFotos();
	private String Usuario = "";
	private String Contraseña = "";
	private String EstadoRecordar = "Recordar datos: Desactivado";
	private int PosXFoto = -200;
	private Timer TimerPosFoto;

	public GuiLogin() {
		Alpha = 0;
		Alpha2 = 0;
		InterfazL();
	}

	public void InterfazL() {
		DBUsuarios.ConsultarRecordarDB();
		Usuario = DBUsuarios.ObtenerNombreRecordar();
		Contraseña = DBUsuarios.ObtenerContraRecordar();
		EstadoRecordar = DBUsuarios.ObtenerEstadoRecordar();
		Ventana.GuiVentana();
		//Label Iniciar Sesion
		IniciarSesion = new JLabel();
		IniciarSesion.setBounds(80, 60, 270, 30);
		IniciarSesion.setForeground(new Color(153, 153, 153, Alpha));
		IniciarSesion.setHorizontalAlignment(CENTER);
		IniciarSesion.setFont(new Font("Dialog", 3, 24));
		IniciarSesion.setText("Iniciar Sesión");
		Ventana.ObtenerVentana().add(IniciarSesion);
		IniciarSesion.setVisible(true);
		// Label Foto de perfil
		ImageIcon ImgFoto = new ImageIcon(this.getClass().getResource("/Img/FotoDePerfil.png"));
		Image EscalarImgFoto = ImgFoto.getImage().getScaledInstance(118, 118, Image.SCALE_SMOOTH);
		ImgFoto = new ImageIcon(EscalarImgFoto);
		Foto = new JLabel();
		Foto.setBounds(PosXFoto, 100, 150, 120);
		Foto.setHorizontalAlignment(CENTER);
		Foto.setIcon(ImgFoto);
		Ventana.ObtenerVentana().add(Foto);
		Foto.setVisible(false);
		// Panel Nombre
		PanelNombre = new JPanel();
		PanelNombre.setBounds(80, 260, 270, 2);
		PanelNombre.setBackground(new Color(51, 51, 51, Alpha));
		PanelNombre.setBorder(null);
		Ventana.ObtenerVentana().add(PanelNombre);
		PanelNombre.setVisible(true);
		// Nombre
		CampoNombre = new JTextField();
		CampoNombre.setBounds(80, 230, 270, 30);
		CampoNombre.setBorder(null);
		CampoNombre.setOpaque(false);
		CampoNombre.setFont(new Font("Arial Narrow", 1, 20));
		CampoNombre.setForeground(Color.white);
		Ventana.ObtenerVentana().add(CampoNombre);
		CampoNombre.setVisible(true);
		CampoNombre.setText(Usuario);
		CampoNombre.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				CampoNombreMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				CampoNombreMouseAfuera(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				CampoNombreMouseSuelto(evt);
			}
		});
		// Panel Contraseña
		PanelContra = new JPanel();
		PanelContra.setBounds(80, 310, 270, 2);
		PanelContra.setBackground(new Color(51, 51, 51, Alpha));
		PanelContra.setBorder(null);
		Ventana.ObtenerVentana().add(PanelContra);
		PanelContra.setVisible(true);
		// Contraseña
		CampoContra = new JPasswordField();
		CampoContra.setBounds(80, 280, 270, 25);
		CampoContra.setBorder(null);
		CampoContra.setOpaque(false);
		CampoContra.setForeground(Color.white);
		CampoContra.setFont(new Font("Arial Narrow", 1, 30));
		Ventana.ObtenerVentana().add(CampoContra);
		CampoContra.setVisible(true);
		CampoContra.setText(Contraseña);
		CampoContra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				CampoContraMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				CampoContraMouseAfuera(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				CampoContraMouseSuelto(evt);
			}
		});
		//  Label Recordar datos
		Recordar = new JLabel();
		Recordar.setBounds(0, 320, 430, 30);
		Recordar.setText(EstadoRecordar);
		Recordar.setForeground(new Color(51, 51, 51, Alpha));
		Recordar.setFont(new Font("Times New Roman", 3, 14));
		Recordar.setHorizontalAlignment(CENTER);
		Ventana.ObtenerVentana().add(Recordar);
		Recordar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				RecordarMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				RecordarMouseAfuera(evt);
			}

			public void mouseClicked(MouseEvent evt) {
				RecordarMousePresionado(evt);
			}

		});
		// BotonIngresar
		BotonIngresar = new JPanel();
		BotonIngresar.setBounds(150, 395, 132, 2);
		BotonIngresar.setBackground(Color.red);
		Ventana.ObtenerVentana().add(BotonIngresar);
		BotonIngresar.setVisible(false);
		// Label ingresar
		Ingresar = new JLabel();
		Ingresar.setBounds(150, 360, 132, 40);
		Ingresar.setForeground(new Color(153, 153, 153, Alpha));
		Ingresar.setText("Ingresar");
		Ingresar.setFont(new Font("Bernard MT Condensed", 1, 18));
		Ingresar.setHorizontalAlignment(CENTER);
		Ventana.ObtenerVentana().add(Ingresar);
		Ingresar.setVisible(true);
		Ingresar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				IngresarMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				IngresarMouseAfuera(evt);
			}

			public void mousePressed(MouseEvent evt) {
				IngresarMousePresionado(evt);
				ConfirmarDatos(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				IngresarMouseSuelto(evt);
			}
		});
		// Boton Registro
		BotonRegistro = new JPanel();
		BotonRegistro.setBounds(150, 445, 132, 2);
		BotonRegistro.setBackground(Color.red);
		Ventana.ObtenerVentana().add(BotonRegistro);
		BotonRegistro.setVisible(false);
		// Label Registro
		Registro = new JLabel();
		Registro.setBounds(150, 410, 132, 40);
		Registro.setForeground(new Color(153, 153, 153, Alpha));
		Registro.setText("Registrarme");
		Registro.setFont(new Font("Bernard MT Condensed", 1, 18));
		Registro.setHorizontalAlignment(CENTER);
		Ventana.ObtenerVentana().add(Registro);
		Registro.setVisible(true);
		Registro.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				RegistroMouseAdentro(evt);
			}

			public void mouseExited(MouseEvent evt) {
				RegistroMouseAfuera(evt);
			}

			public void mousePressed(MouseEvent evt) {
				RegistroMousePresionado(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				RegistroMouseSuelto(evt);
			}
		});
		AnimAlphaOn();
	}

	//  Setters de eventos	
	public void CampoNombreMouseAdentro(MouseEvent evt) {
		PanelNombre.setBackground(Color.red);
		if (CampoNombre.getText().equals("") || CampoNombre.getText().equals("Usuario")) {
			CampoNombre.setForeground(Color.gray);
			CampoNombre.setText("Usuario");
		} else {
			return;
		}
	}

	public void CampoNombreMouseAfuera(MouseEvent evt) {
		PanelNombre.setBackground(Color.DARK_GRAY);
		if (CampoNombre.getText().equals("Usuario") || CampoNombre.getText().equals("")) {
			CampoNombre.setText("");
		} else {
			return;
		}
	}

	public void CampoNombreMouseSuelto(MouseEvent evt) {
		if (CampoNombre.getText().equals("Usuario")) {
			CampoNombre.setText("");
			CampoNombre.setForeground(Color.white);
		} else {
			return;
		}
	}

	public void CampoContraMouseAdentro(MouseEvent evt) {
		PanelContra.setBackground(Color.red);
		if (CampoContra.getText().equals("|||Zcod1234ABTERWEO!#>>>>") || CampoContra.getText().equals("")) {
			CampoContra.setText("|||Zcod1234ABTERWEO!#>>>>");
			CampoContra.setForeground(Color.gray);
		} else {
			return;
		}
	}

	public void CampoContraMouseAfuera(MouseEvent evt) {
		PanelContra.setBackground(Color.DARK_GRAY);
		if (CampoContra.getText().equals("|||Zcod1234ABTERWEO!#>>>>") || CampoContra.getText().equals("")) {
			CampoContra.setText("");
		} else {
			return;
		}
	}

	public void CampoContraMouseSuelto(MouseEvent evt) {
		if (CampoContra.getText().equals("|||Zcod1234ABTERWEO!#>>>>")) {
			CampoContra.setText("");
			CampoContra.setForeground(Color.white);
		} else {
			return;
		}
	}

	public void RecordarMouseAdentro(MouseEvent evt) {
		Recordar.setForeground(new Color(143, 143, 143, Alpha));
	}

	public void RecordarMouseAfuera(MouseEvent evt) {
		if (Recordar.getText().equals("Recordar datos: Activado")) {
			Recordar.setForeground(Color.white);
		} else if (Recordar.getText().equals("Recordar datos: Desactivado")) {
			Recordar.setForeground(Color.darkGray);
		}

	}

	public void RecordarMousePresionado(MouseEvent evt) {
		if (Recordar.getText().equals("Recordar datos: Activado")) {
			Recordar.setText("Recordar datos: Desactivado");
			Recordar.setForeground(Color.darkGray);
		} else if (Recordar.getText().equals("Recordar datos: Desactivado")) {
			Recordar.setText("Recordar datos: Activado");
			Recordar.setForeground(Color.white);
		}
	}

	public void IngresarMouseAdentro(MouseEvent evt) {
		Ingresar.setForeground(Color.lightGray);
		BotonIngresar.setVisible(true);
	}

	public void IngresarMouseAfuera(MouseEvent evt) {
		Ingresar.setForeground(new Color(153, 153, 153, 255));
		Ingresar.setBackground(Color.red);
		BotonIngresar.setVisible(false);
	}

	public void IngresarMousePresionado(MouseEvent evt) {
		Ingresar.setForeground(Color.darkGray);
	}

	public void IngresarMouseSuelto(MouseEvent evt) {
		Ingresar.setForeground(Color.lightGray);
		BotonIngresar.setForeground(Color.red);
	}

	public void RegistroMouseAdentro(MouseEvent evt) {
		Registro.setForeground(Color.lightGray);
		BotonRegistro.setVisible(true);
	}

	public void RegistroMouseAfuera(MouseEvent evt) {
		Registro.setForeground(new Color(153, 153, 153, 255));
		Registro.setBackground(Color.red);
		BotonRegistro.setVisible(false);
	}

	public void RegistroMousePresionado(MouseEvent evt) {
		BotonRegistro.setForeground(Color.red);
		Registro.setForeground(Color.darkGray);
	}

	public void RegistroMouseSuelto(MouseEvent evt) {
		Registro.setForeground(Color.lightGray);
		GuiLoginVisible(false);
		GuiRegister guiRegister = new GuiRegister();
		guiRegister.InterfazR();
	}

	public void ConfirmarDatos(MouseEvent evt) {
		Ventana.AnimacionNotificacion();
		if (CampoNombre.getText().length() >= 4 && CampoNombre.getText().length() <= 20) {
			Ventana.TextoNotificacion("");
			DBUsuarios.ConsultarDB("nick", CampoNombre.getText());
			if (CampoNombre.getText().equals(DBUsuarios.ObtenerDatoRepetido())) {
				Ventana.TextoNotificacion("");
				CorreoCorrecto = true;
				if (CampoContra.getText().length() >= 8 && CampoContra.getText().length() <= 30) {
					Ventana.TextoNotificacion("");
					DBUsuarios.ConsultarDB("contraseña", CampoContra.getText());
					if (CampoContra.getText().equals(DBUsuarios.ObtenerDatoRepetido())) {
						Ventana.TextoNotificacion("");
						ContraCorrecta = true;
						DBUsuarios.ConsultarNombreContraFotoDB(CampoNombre.getText(), CampoContra.getText());
						if (CorreoCorrecto == true && ContraCorrecta == true && CampoNombre.getText().equals(DBUsuarios.ObtenerDatoNombreDB()) && CampoContra.getText().equals(DBUsuarios.ObtenerDatoContraDB())) {
							Ventana.TextoNotificacion("Ingresando...");
							CambiarFotoLogin(DBUsuarios.ObtenerDatoFotoDB());
							AnimacionFoto();
							if (Recordar.getText().equals("Recordar datos: Activado")) {
								RecordarDatosDB(CampoNombre.getText(), CampoContra.getText());
							} else if (Recordar.getText().equals("Recordar datos: Desactivado")) {
								DBUsuarios.ConectarRecordarDB("", "", "Recordar datos: Desactivado");
							}
						} else {
							Ventana.TextoNotificacion("La cuenta ingresada no existe.");
						}
					} else {
						Ventana.TextoNotificacion("La contraseña es incorrecta");
						ContraCorrecta = false;
					}
				} else {
					Ventana.TextoNotificacion("La contraseña debe tener entre 8 y 30 caracteres.");
				}
			} else {
				Ventana.TextoNotificacion("El usuario ingresado no se encuentra registrado");
				CorreoCorrecto = false;
			}

		} else {
			Ventana.TextoNotificacion("El usuario debe contener almenos 4 caracteres");
		}
	}

	public void AnimAlphaOn() {
		Mov1 = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (Ventana.ObtenerPosY() >= Ventana.ObtenerPosOriginal()) {
					Alpha = Alpha + 10;
					IniciarSesion.setForeground(new Color(153, 153, 153, Alpha));
					PanelNombre.setBackground(new Color(51, 51, 51, Alpha));
					PanelContra.setBackground(new Color(51, 51, 51, Alpha));
					Recordar.setForeground(new Color(51, 51, 51, Alpha));
					Ingresar.setForeground(new Color(153, 153, 153, Alpha));
					Registro.setForeground(new Color(153, 153, 153, Alpha));
					if (Alpha >= 250) {
						Alpha = 255;
						Foto.setVisible(true);
						AnimacionFoto();
						Mov1.stop();
					}
				}
			}
		});
		Mov1.start();
	}

	public int ObtenerAlphaLogin() {
		return Alpha;
	}

	public void GuiLoginVisible(boolean ToF) {
		IniciarSesion.setVisible(ToF);
		Foto.setVisible(ToF);
		PanelNombre.setVisible(ToF);
		CampoNombre.setVisible(ToF);
		PanelContra.setVisible(ToF);
		CampoContra.setVisible(ToF);
		Recordar.setVisible(ToF);
		BotonIngresar.setVisible(ToF);
		Ingresar.setVisible(ToF);
		BotonRegistro.setVisible(ToF);
		Registro.setVisible(ToF);
		Ventana.setVisible(ToF);
	}

	public void CambiarFotoLogin(String Icono) {
		ImageIcon ImgFoto = new ImageIcon(this.getClass().getResource(Icono));
		Image EscalarImgFoto = ImgFoto.getImage().getScaledInstance(118, 118, Image.SCALE_SMOOTH);
		ImgFoto = new ImageIcon(EscalarImgFoto);
		Foto.setIcon(ImgFoto);
	}

	public void IniciarRecordarDatosDB(String Nombre, String Contra) {
		CampoNombre.setText(Nombre);
		CampoContra.setText(Contra);
	}

	public void RecordarDatosDB(String Usuario, String Contraseña) {
		if (!Usuario.equals("") && !Contraseña.equals("")) {
			DBUsuarios.ConsultarNombreContraFotoDB(Usuario, Contraseña);
			if (Usuario.equals(DBUsuarios.ObtenerDatoNombreDB()) && Contraseña.equals(DBUsuarios.ObtenerDatoContraDB())) {
				DBUsuarios.ConectarRecordarDB(Usuario, Contraseña, "Recordar datos: Activado");
			}
		}
	}

	public void AnimacionFoto() {
		PosXFoto = -200;
		TimerPosFoto = new Timer(10, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PosXFoto = PosXFoto + 10;
				System.out.println(PosXFoto);
				Foto.setLocation(PosXFoto, Foto.getY());
				Foto.setHorizontalAlignment(CENTER);
				if (PosXFoto >= 135) {
					PosXFoto = 135;
					Foto.setLocation(PosXFoto, Foto.getY());
					TimerPosFoto.stop();
				}

			}
		});
		TimerPosFoto.start();
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GuiLogin();
				new DBUsuarios().obtenerConexion();
			}
		});
	}
}
