package Frames;

import DB.DBUsuarios;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import sun.nio.cs.ext.GB18030;

public class GuiRegister extends JFrame {

	private JLabel Registrarse;
	private int Alpha = 255;
	private JTextField Campos[] = new JTextField[7];
	private String TextoCampos[] = {"Usuario/Nick", "Correo", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Telefono/Celular"};
	private int NumCampo = 0;
	private int PosYCampos = 230;
	private JComponent Componente;
	private JTextField ComponenteTipoCampo;
	private JPasswordField CampoContra;
	private VentanaPrincipal Ventana = new VentanaPrincipal();
	private ComboBox DiaCB = new ComboBox();
	private ComboBox MesCB = new ComboBox();
	private ComboBox AñoCB = new ComboBox();
	private ComboBox SexoCB = new ComboBox();
	private ComboBox PaisCB = new ComboBox();
	private JLabel BotonRegistro;
	private GuiFotos GuiFotos = new GuiFotos();
	private String UrlFoto = "/Img/FotoDePerfil.png";
	private char Digito;
	private boolean NumTelefonoReal = false;
	private Timer Timer;
	private DBUsuarios DBUsuarios = new DBUsuarios();
	private JLabel BotonIngresar;

	public GuiRegister() {

	}

	public void InterfazR() {
		Ventana.GuiVentana();
		Ventana.TamañoDeVentana(860);
		//Titulo
		Registrarse = new JLabel();
		Registrarse.setBounds(0, 60, 860, 30);
		Registrarse.setForeground(new Color(153, 153, 153, Alpha));
		Registrarse.setHorizontalAlignment(CENTER);
		Registrarse.setFont(new Font("Dialog", 3, 24));
		Registrarse.setText("Registro");
		Ventana.ObtenerVentana().add(Registrarse);
		Registrarse.setVisible(true);
		// FotoDePerfil
		GuiFotos.IconoFoto();
		Ventana.ObtenerVentana().add(GuiFotos.ObtenerFotoPerfil());
		GuiFotos.PanelFotos();
		Ventana.ObtenerVentana().add(GuiFotos.ObtenerPanelPrincipalFotos());

		// Campos
		for (NumCampo = 0; NumCampo < Campos.length; NumCampo++) {
			Campos[NumCampo] = new JTextField();
			Campos[NumCampo].setBounds(80, PosYCampos, 270, 30);
			Campos[NumCampo].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
			Campos[NumCampo].setOpaque(false);
			Campos[NumCampo].setFont(new Font("Arial Narrow", 1, 20));
			Campos[NumCampo].setForeground(new Color(255, 255, 255, 0));
			Campos[NumCampo].setText(TextoCampos[NumCampo]);
			Campos[NumCampo].setVisible(true);
			Ventana.ObtenerVentana().add(Campos[NumCampo]);
			switch (NumCampo) {
				case 5:
					PosYCampos = Campos[0].getY();
					Campos[5].setBounds(860 - 350, PosYCampos, Campos[NumCampo].getWidth(), Campos[NumCampo].getHeight());
					break;
				case 6:
					PosYCampos = PosYCampos + 50;
					Campos[6].setBounds(860 - 350, PosYCampos, Campos[NumCampo].getWidth(), Campos[NumCampo].getHeight());
					break;
				case 7:
					PosYCampos = PosYCampos + 50;
					Campos[7].setBounds(860 - 350, PosYCampos, Campos[NumCampo].getWidth(), Campos[NumCampo].getHeight());
					break;
				case 8:
					PosYCampos = PosYCampos + 50;
					Campos[8].setBounds(860 - 350, PosYCampos, Campos[NumCampo].getWidth(), Campos[NumCampo].getHeight());
					break;
				default:
					PosYCampos = PosYCampos + 50;

			}
			Campos[NumCampo].addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Componente = (JComponent) e.getComponent();
					ComponenteTipoCampo = (JTextField) Componente;
					if (ComponenteTipoCampo.getText().equals(ComponenteTipoCampo.getText())) {
						ComponenteTipoCampo.setText("");
						ComponenteTipoCampo.setForeground(Color.white);
					} else {
						ComponenteTipoCampo.setForeground(Color.white);
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Componente = (JComponent) e.getComponent();
					Componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red));
					ComponenteTipoCampo = (JTextField) Componente;
					MouseAdentro();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Componente = (JComponent) e.getComponent();
					Componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
					ComponenteTipoCampo = (JTextField) Componente;
					MouseAfuera();
				}
			});
		}
		//Dia nacimiento
		DiaCB.NuevoComboBox(860 - 350, 330, 53, 30, "Dia");
		Ventana.ObtenerVentana().add(DiaCB.ObtenerBotonCB());
		String NumerosDia[] = new String[31];
		for (int i = 0; i < NumerosDia.length; i++) {
			NumerosDia[i] = "" + (i + 1);
		}
		DiaCB.CasillasComboBox(true, 0, NumerosDia);
		Ventana.ObtenerVentana().add(DiaCB.ObtenerPanelCasillaCB());
		// Mes nacimiento
		MesCB.NuevoComboBox((860 - 350) + 55, 330, 53, 30, "Mes");
		Ventana.ObtenerVentana().add(MesCB.ObtenerBotonCB());
		String NumerosMes[] = new String[12];
		for (int i = 0; i < NumerosMes.length; i++) {
			NumerosMes[i] = "" + (i + 1);
		}
		MesCB.CasillasComboBox(true, 0, NumerosMes);
		Ventana.ObtenerVentana().add(MesCB.ObtenerPanelCasillaCB());
		// Año Nacimiento
		AñoCB.NuevoComboBox((860 - 350) + 110, 330, 53, 30, "Año");
		Ventana.ObtenerVentana().add(AñoCB.ObtenerBotonCB());
		String NumerosAño[] = new String[50];
		int Año = 1969;
		for (int i = 0; i < NumerosAño.length; i++) {
			Año = Año + 1;
			NumerosAño[i] = "" + Año;
		}
		AñoCB.CasillasComboBox(true, 0, NumerosAño);
		Ventana.ObtenerVentana().add(AñoCB.ObtenerPanelCasillaCB());
		// Sexo
		SexoCB.NuevoComboBox((860 - 350) + 165, 330, 53, 30, "Sexo");
		Ventana.ObtenerVentana().add(SexoCB.ObtenerBotonCB());
		String Sexo[] = {"M", "F", "Otro"};
		SexoCB.CasillasComboBox(true, 53 * 2, Sexo);
		Ventana.ObtenerVentana().add(SexoCB.ObtenerPanelCasillaCB());
		// Pais
		PaisCB.NuevoComboBox((860 - 350) + 220, 330, 53, 30, "Pais");
		Ventana.ObtenerVentana().add(PaisCB.ObtenerBotonCB());
		String Paises[] = {"Afganistán", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda", "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Australia", "Austria", "Azerbaiyán", "Bahamas", "Bangladés", "Barbados", "Baréin", "Bélgica", "Belice", "Benín", "Bielorrusia", "Birmania", "Bolivia", "Bosnia y Herzegovina", "Botsuana", "Brasil", "Brunéi", "Bulgaria", "Burkina Faso", "Burundi", "Bután", "Cabo Verde", "Camboya", "Camerún", "Canadá", "Catar", "Chad", "Chile", "China", "Chipre", "Ciudad del Vaticano", "Colombia", "Comoras", "Corea del Norte", "Corea del Sur", "Costa de Marfil", "Costa Rica", "Croacia", "Cuba", "Dinamarca", "Dominica", "Ecuador", "Egipto", "El Salvador", "Emiratos Árabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia", "España", "Estados Unidos", "Estonia", "Etiopía", "Filipinas", "Finlandia", "Fiyi", "Francia", "Gabón", "Gambia", "Georgia", "Ghana", "Granada", "Grecia", "Guatemala", "Guyana", "Guinea", "Guinea ecuatorial", "Guinea-Bisáu", "Haití", "Honduras", "Hungría", "India", "Indonesia", "Irak", "Irán", "Irlanda", "Islandia", "Islas Marshall", "Islas Salomón", "Israel", "Italia", "Jamaica", "Japón", "Jordania", "Kazajistán", "Kenia", "Kirguistán", "Kiribati", "Kuwait", "Laos", "Lesoto", "Letonia", "Líbano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo", "Madagascar", "Malasia", "Malaui", "Maldivas", "Malí", "Malta", "Marruecos", "Mauricio", "Mauritania", "México", "Micronesia", "Moldavia", "Mónaco", "Mongolia", "Montenegro", "Mozambique", "Namibia", "Nauru", "Nepal", "Nicaragua", "Níger", "Nigeria", "Noruega", "Nueva Zelanda", "Omán", "Países Bajos", "Pakistán", "Palaos", "Panamá", "Papúa Nueva Guinea", "Paraguay", "Perú", "Polonia", "Portugal", "Reino Unido", "República Centroafricana", "República Checa", "República de Macedonia", "República del Congo", "República Democrática del Congo", "República Dominicana", "República Sudafricana", "Ruanda", "Rumanía", "Rusia", "Samoa", "San Cristóbal y Nieves", "San Marino", "San Vicente y las Granadinas", "Santa Lucía", "Santo Tomé y Príncipe", "Senegal", "Serbia", "Seychelles", "Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka", "Suazilandia", "Sudán", "Sudán del Sur", "Suecia", "Suiza", "Surinam", "Tailandia", "Tanzania", "Tayikistán", "Timor Oriental", "Togo", "Tonga", "Trinidad y Tobago", "Túnez", "Turkmenistán", "Turquía", "Tuvalu", "Ucrania", "Uganda", "Uruguay", "Uzbekistán", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Yibuti", "Zambia", "Zimbabue"
		};
		PaisCB.CasillasComboBox(false, 53 * 2, Paises);
		Ventana.ObtenerVentana().add(PaisCB.ObtenerPanelCasillaCB());
		// Contraseña
		CampoContra = new JPasswordField();
		CampoContra.setBounds(860 - 350, 380, 270, 30);
		CampoContra.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
		CampoContra.setOpaque(false);
		CampoContra.setFont(new Font("Arial Narrow", 1, 30));
		CampoContra.setForeground(new Color(255, 255, 255, 0));
		CampoContra.setText("|||Zcod1234ABTERWEO!#>>>>");
		CampoContra.setVisible(true);
		Ventana.ObtenerVentana().add(CampoContra);
		CampoContra.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CampoContraMouseSuelto(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				CampoContraMouseAdentro(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				CampoContraMouseAfuera(e);
			}
		});
		//Boton Registro
		BotonRegistro = new JLabel();
		BotonRegistro.setText("Registrarme");
		BotonRegistro.setFont(new Font("Bernard MT Condensed", 1, 18));
		BotonRegistro.setForeground(new Color(153, 153, 153, Alpha));
		BotonRegistro.setBounds(860 - 350, 420, 132, 40);
		BotonRegistro.setHorizontalAlignment(CENTER);
		BotonRegistro.setVerticalAlignment(CENTER);
		Ventana.ObtenerVentana().add(BotonRegistro);
		BotonRegistro.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				ConfirmarRegistro();
			}

			public void mousePressed(MouseEvent e) {
				BotonRegistro.setForeground(Color.darkGray);
			}

			public void mouseReleased(MouseEvent e) {
				BotonRegistro.setForeground(Color.lightGray);
			}

			public void mouseEntered(MouseEvent e) {
				BotonRegistro.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red));
				BotonRegistro.setForeground(Color.lightGray);
			}

			public void mouseExited(MouseEvent e) {
				BotonRegistro.setBorder(null);
				BotonRegistro.setForeground(new Color(153, 153, 153, 255));
			}
		});
	//Volver al panel de ingresar
		BotonIngresar = new JLabel();
		BotonIngresar.setText("Ingresar");
		BotonIngresar.setFont(new Font("Bernard MT Condensed", 1, 18));
		BotonIngresar.setForeground(new Color(153, 153, 153, Alpha));
		BotonIngresar.setBounds(860 - 350 + 135, 420, 132, 40);
		BotonIngresar.setHorizontalAlignment(CENTER);
		BotonIngresar.setVerticalAlignment(CENTER);
		Ventana.ObtenerVentana().add(BotonIngresar);
		BotonIngresar.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				OcultarGuiRegister( false );
				new GuiLogin();
			}

			public void mousePressed(MouseEvent e) {
				BotonIngresar.setForeground(Color.darkGray);
			}

			public void mouseReleased(MouseEvent e) {
				BotonIngresar.setForeground(Color.lightGray);
			}

			public void mouseEntered(MouseEvent e) {
				BotonIngresar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red));
				BotonIngresar.setForeground(Color.lightGray);
			}

			public void mouseExited(MouseEvent e) {
				BotonIngresar.setBorder(null);
				BotonIngresar.setForeground(new Color(153, 153, 153, 255));
			}
		});

	}

	//Metodos
	public void MouseAdentro() {
		for (int k = 0; k < TextoCampos.length; k++) {
			if (ComponenteTipoCampo.getText().equals(TextoCampos[k])) {
				ComponenteTipoCampo.setForeground(Color.gray);
				return;
			} else {
				ComponenteTipoCampo.setForeground(Color.white);
			}
		}
	}

	public void MouseAfuera() {
		for (int k = 0; k < TextoCampos.length; k++) {
			if (ComponenteTipoCampo.getText().equals(TextoCampos[k])) {
				ComponenteTipoCampo.setForeground(new Color(255, 255, 255, 0));
				return;
			} else {
				ComponenteTipoCampo.setForeground(Color.white);

			}
		}
	}

	public void CampoContraMouseAdentro(MouseEvent evt) {
		CampoContra.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red));
		if (CampoContra.getText().equals("|||Zcod1234ABTERWEO!#>>>>") || CampoContra.getText().equals("")) {
			CampoContra.setText("|||Zcod1234ABTERWEO!#>>>>");
			CampoContra.setForeground(Color.gray);
		} else {
			return;
		}
	}

	public void CampoContraMouseAfuera(MouseEvent evt) {
		CampoContra.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
		if (CampoContra.getText().equals("|||Zcod1234ABTERWEO!#>>>>") || CampoContra.getText().equals("")) {
			CampoContra.setText("");
			CampoContra.setForeground(new Color(255, 255, 255, 0));
		} else {
			return;
		}
	}

	public void CampoContraMouseSuelto(MouseEvent evt) {
		if (CampoContra.getText().equals("|||Zcod1234ABTERWEO!#>>>>")) {
			CampoContra.setText("");
			CampoContra.setForeground(Color.white);
		}
	}

	public void ConfirmarRegistro() {
		Ventana.TextoNotificacion("");
		Ventana.AnimacionNotificacion();
		new DB.DBUsuarios().ConsultarDB("nick", Campos[0].getText());
		if (!Campos[0].getText().equals(TextoCampos[0]) && !Campos[0].getText().equals("")) {
			if (Campos[0].getText().length() >= 4 && Campos[0].getText().length() <= 20 ) {
				if (!Campos[1].getText().equals(TextoCampos[1]) && !Campos[1].getText().equals("")) {
					if (Campos[1].getText().contains("@")) {
						if (Campos[1].getText().contains(".com") || Campos[1].getText().contains(".edu") || Campos[1].getText().contains(".co") || Campos[1].getText().contains(".org") || Campos[1].getText().contains(".gov") || Campos[1].getText().contains(".net")) {
							//Confirmar unico correo
							if (!Campos[2].getText().equals(TextoCampos[2]) && !Campos[2].getText().equals("")) {
								if (Campos[2].getText().length() >= 4 && Campos[2].getText().length() <= 16) {
									//
									if (!Campos[3].getText().equals(TextoCampos[3]) && !Campos[3].getText().equals("")) {
										if (Campos[3].getText().length() >= 4 && Campos[3].getText().length() <= 16) {
											//
											if (!Campos[4].getText().equals(TextoCampos[4]) && !Campos[4].getText().equals("")) {
												if (Campos[4].getText().length() >= 4 && Campos[4].getText().length() <= 16) {
													//
													if (!Campos[5].getText().equals(TextoCampos[5]) && !Campos[5].getText().equals("")) {
														if (Campos[5].getText().length() >= 4 && Campos[5].getText().length() <= 16) {
															//
															if (!Campos[6].getText().equals(TextoCampos[6]) && !Campos[6].getText().equals("")) {
																for (int i = 0; i < Campos[6].getText().length(); i++) {
																	Digito = Campos[6].getText().charAt(i);
																	if (Digito == '0' || Digito == '1' || Digito == '2' || Digito == '3' || Digito == '4' || Digito == '5' || Digito == '6' || Digito == '7' || Digito == '8' || Digito == '9') {
																		NumTelefonoReal = true;
																	} else {
																		NumTelefonoReal = false;
																	}
																}
																if (NumTelefonoReal == true) {
																	if (Campos[6].getText().length() >= 7 && Campos[6].getText().length() <= 10) {
																		//
																		if (!DiaCB.ObtenerBotonCB().getText().equals(DiaCB.ObtenerCasillaDefault().getText())) {
																			//
																			if (!MesCB.ObtenerBotonCB().getText().equals(MesCB.ObtenerCasillaDefault().getText())) {
																				//	
																				if (!AñoCB.ObtenerBotonCB().getText().equals(AñoCB.ObtenerCasillaDefault().getText())) {
																					//
																					if (!SexoCB.ObtenerBotonCB().getText().equals(SexoCB.ObtenerCasillaDefault().getText())) {
																						//
																						if (!PaisCB.ObtenerBotonCB().getText().equals(PaisCB.ObtenerCasillaDefault().getText())) {
																							//
																							if (!CampoContra.getText().equals("|||Zcod1234ABTERWEO!#>>>>")) {
																								if (CampoContra.getText().length() >= 8 && CampoContra.getText().length() <= 30) {
																									if (GuiFotos.ObtenerEstadoFoto() == true) {
																										DBUsuarios.ConsultarDB("nick", Campos[0].getText());
																										if (!DBUsuarios.ObtenerDatoRepetido().equals(Campos[0].getText())   ) {
																											DBUsuarios.ConsultarDB("correo", Campos[1].getText());
																											if (!DBUsuarios.ObtenerDatoRepetido().equals(Campos[1].getText()) ) {
																												DBUsuarios.ConectarDB(Campos[0].getText(), Campos[1].getText(), Campos[2].getText(), Campos[3].getText(), Campos[4].getText(), Campos[5].getText(), Campos[6].getText(), DiaCB.ObtenerBotonCB().getText(), MesCB.ObtenerBotonCB().getText(), AñoCB.ObtenerBotonCB().getText(), SexoCB.ObtenerBotonCB().getText(), PaisCB.ObtenerBotonCB().getText(), CampoContra.getText(), GuiFotos.ObtenerFotoPerfil().getText());
																												Ventana.TextoNotificacion("Registraste tu cuenta correctamente ...espera...");
																												VolverAGuiLogin();
																											} else {
																												Ventana.TextoNotificacion("El correo ' " + Campos[1].getText() + " ' ya esta registrado, intente con uno diferente.");
																											}
																										} else {
																											Ventana.TextoNotificacion("El nick ' " + Campos[0].getText() + " ' ya esta registrado, intente con uno diferente.");
																										}

																									} else {
																										Ventana.TextoNotificacion("Debes escoger una foto de perfil");
																									}

																								} else {
																									Ventana.TextoNotificacion("El campo ´Contraseña' debe tener entre 8 y 30 caracteres.");
																								}

																							} else {
																								Ventana.TextoNotificacion("El campo 'Contraseña' no puede estar vacio.");
																							}
																						} else {
																							Ventana.TextoNotificacion("El campo '" + PaisCB.ObtenerBotonCB().getText() + "' es obligatorio.");
																						}
																					} else {
																						Ventana.TextoNotificacion("El campo '" + SexoCB.ObtenerBotonCB().getText() + "' es obligatorio.");
																					}
																				} else {
																					Ventana.TextoNotificacion("El campo '" + AñoCB.ObtenerBotonCB().getText() + "' es obligatorio.");
																				}
																			} else {
																				Ventana.TextoNotificacion("El campo '" + MesCB.ObtenerBotonCB().getText() + "' es obligatorio.");
																			}
																		} else {
																			Ventana.TextoNotificacion("El campo '" + DiaCB.ObtenerBotonCB().getText() + "' es obligatorio.");
																		}
																	} else {
																		Ventana.TextoNotificacion("El campo '" + TextoCampos[6] + "' debe tener entre 7 y 10 digitos");
																	}
																} else {
																	Ventana.TextoNotificacion("El campo '" + TextoCampos[6] + "' solo puede contener digitos enteros Ej: 0, 1, 2");
																}

															} else {
																Ventana.TextoNotificacion("El campo '" + TextoCampos[6] + "' no puede estar vacio.");
															}
														} else {
															Ventana.TextoNotificacion("El campo '" + TextoCampos[5] + "' debe tener entre 4 y 16 caracteres");
														}

													} else {
														Ventana.TextoNotificacion("El campo '" + TextoCampos[5] + "' no puede estar vacio.");
													}
												} else {
													Ventana.TextoNotificacion("El campo '" + TextoCampos[4] + "' debe tener entre 4 y 16 caracteres");
												}

											} else {
												Ventana.TextoNotificacion("El campo '" + TextoCampos[4] + "' no puede estar vacio.");
											}
										} else {
											Ventana.TextoNotificacion("El campo '" + TextoCampos[3] + "' debe tener entre 4 y 16 caracteres");
										}

									} else {
										Ventana.TextoNotificacion("El campo '" + TextoCampos[3] + "' no puede estar vacio.");
									}
								} else {
									Ventana.TextoNotificacion("El campo'" + TextoCampos[2] + "' debe tener entre 4 y 16 caracteres");
								}

							} else {
								Ventana.TextoNotificacion("El campo '" + TextoCampos[2] + "' no puede estar vacio.");
							}
						} else {
							Ventana.TextoNotificacion("No se encuentra el dominio en el correo ingresado Ej: .com");
						}

					} else {
						Ventana.TextoNotificacion("No se encuentra '@' en el correo ingresado.");
					}

				} else {
					Ventana.TextoNotificacion("El campo '" + TextoCampos[1] + "' no puede estar vacio");
				}
			} else {
				Ventana.TextoNotificacion("El campo '" + TextoCampos[0] + "' debe tener entre 4 y 20 caracteres");
			}
		} else {
			Ventana.TextoNotificacion("El campo '" + TextoCampos[0] + "' no puede estar vacio");
		}
	}

	public void OcultarGuiRegister(boolean VoF) {
		Registrarse.setVisible(VoF);
		DiaCB.ObtenerBotonCB().setVisible(VoF);
		MesCB.ObtenerBotonCB().setVisible(VoF);
		AñoCB.ObtenerBotonCB().setVisible(VoF);
		SexoCB.ObtenerBotonCB().setVisible(VoF);
		PaisCB.ObtenerBotonCB().setVisible(VoF);
		for (int i = 0; i < Campos.length; i++) {
			Campos[i].setVisible(VoF);
		}
		CampoContra.setVisible(VoF);
		BotonRegistro.setVisible(VoF);
		GuiFotos.ObtenerFotoPerfil().setVisible(VoF);
		Ventana.setVisible(VoF);
	}

	int Contador = 0;

	public void VolverAGuiLogin() {
		Contador = 0;
		Timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contador = Contador + 1;
				if (Contador == 6) {
					OcultarGuiRegister(false);
					new GuiLogin();
					Timer.stop();
				}
			}
		});
		Timer.start();
	}
}
