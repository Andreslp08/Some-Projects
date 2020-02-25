package Gui;

import drogueria.Medicamento;
import drogueria.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class Dashboard extends GuiMedicamentos {

    private JPanel panel;
    private JLabel titulo;
    private String[] textoBotones = {"Registrar medicamentos", "Registrar cliente", "Nueva factura", "Ver facturas"};
    private JLabel[] botones;
    private int posXbotones;
    private ArrayList usuarios;
    private JLabel nombreUsuario;
    private GuiMedicamentos guiMedicamentos = new GuiMedicamentos();
    private GuiClientes guiClientes = new GuiClientes();
    private GuiFactura guiFactura;
    private GuiFacturas guiFacturas;
    private JLabel fotoUsuario;
    private ImageIcon icono;
    private Image img;
    private String[] textoOpcionesU = {"Cerrar sesión", "Iniciar sesión", "Crear usuario"};
    private JLabel[] opcionesUsuario;
    private int posYopcU;
    private GuiRegistrarUsuarios guiRegistrarUsuarios;
    private GuiIniciarSesion guiIniciarSesion;
    private Usuario usuario = new Usuario();
    private Usuario auxUsuario = null;

    public Dashboard() {
        usuarios = usuario.getUsuarios();
        System.err.println(usuarios);
        nombreUsuario = new JLabel("Usuario: ");
        guiFactura = new GuiFactura(guiClientes.getThisClientes(), nombreUsuario);
        guiFacturas = new GuiFacturas(guiFactura.getThisFactura(), guiFactura.getThisDetalleFactura(), guiFactura.getDatos());
        guiRegistrarUsuarios = new GuiRegistrarUsuarios(usuarios);
        guiIniciarSesion = new GuiIniciarSesion(usuarios);
        guiIniciarSesion.abrirGui();
        fotoUsuario = new JLabel();
        icono = new ImageIcon(this.getClass().getResource("/img/FotoDePerfil.png"));
        img = icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icono = new ImageIcon(img);
        fotoUsuario.setIcon(icono);
        opcionesUsuario = new JLabel[textoOpcionesU.length];
        posYopcU = 325 - 45;
        configDashboard();
        for (int i = 0; i < textoOpcionesU.length; i++) {
            opcionesUsuario[i] = new JLabel(textoOpcionesU[i]);
            opcionesUsuario[i].setBounds(0, posYopcU, 350, 40);
            opcionesUsuario[i].setHorizontalAlignment(CENTER);
            opcionesUsuario[i].setVerticalAlignment(CENTER);
            opcionesUsuario[i].setOpaque(true);
            opcionesUsuario[i].setBackground(new Color(55, 55, 55, 255));
            opcionesUsuario[i].setForeground(Color.lightGray);
            opcionesUsuario[i].setFont(new Font("Arial Narrow", 1, 20));
            posYopcU = posYopcU + 40;
            getPanel().add(opcionesUsuario[i]);
            opcionesUsuario[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    e.getComponent().setBackground(new Color(200, 60, 40, 255));

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    e.getComponent().setBackground(new Color(255, 87, 51, 255));
                    JLabel componente = (JLabel) e.getComponent();
                    switch (componente.getText()) {
                        case "Cerrar sesión":
                            getVentana().setSize(getVentana().getWidth(), 280);
                            getPanel().setSize(getVentana().getWidth(), 280);
                            nombreUsuario.setText(" Usuario: Ninguno");
                            break;
                        case "Iniciar sesión":
                            guiIniciarSesion.setVisible(true);
                            getVentana().setSize(getVentana().getWidth(), 280);
                            getPanel().setSize(getVentana().getWidth(), 280);
                            break;
                        case "Crear usuario":
                            guiRegistrarUsuarios.abrirGui();
                            getVentana().setSize(getVentana().getWidth(), 280);
                            getPanel().setSize(getVentana().getWidth(), 280);
                            break;
                    }

                }

                @Override

                public void mouseEntered(MouseEvent e) {
                    e.getComponent().setBackground(new Color(255, 87, 51, 255));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    e.getComponent().setBackground(new Color(55, 55, 55, 255));

                }
            }
            );

        }
        guiIniciarSesion.getBotonRegistro().addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (guiIniciarSesion.getTitulo().getText().equals("Iniciar Sesión")) {
                    if (!guiIniciarSesion.getCampos()[0].getText().equals("") && !guiIniciarSesion.getContra().getText().equals("")) {
                        if (getNombreUsuario().getText().equals(" Usuario: Ninguno")) {
                            if (guiIniciarSesion.validarIngreso() == true) {
                                abrirGui();
                                getVentana().setVisible(true);
                                guiIniciarSesion.getVentana().setVisible(false);
                                getNombreUsuario().setText(" Usuario: " + guiIniciarSesion.getCampos()[0].getText());
                            } else {
                                JOptionPane.showMessageDialog(null, "Usuario incorrecto o contraseña incorrecta.");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Debe cerrar primero esta sesión.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios.");
                    }
                }
                guiIniciarSesion.getCampos()[0].setText("");
                guiIniciarSesion.getContra().setText("");
            }

        });
        for (int i = 0; i < guiRegistrarUsuarios.getBotones().length; i++) {
            guiRegistrarUsuarios.getBotones()[i].addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    JLabel componente = (JLabel) e.getComponent();
                    switch (componente.getText()) {
                        case "Guardar":
                            if (guiRegistrarUsuarios.getTitulo().getText().equals("Registrar Usuarios")) {
                                if (!guiRegistrarUsuarios.getCampos()[0].getText().equals("") && !guiRegistrarUsuarios.getContra().getText().equals("")) {
                                    if (guiRegistrarUsuarios.validarUsuarioRegistro() == true) {

                                        usuario.agregarUsuario(guiRegistrarUsuarios.getCampos()[0].getText(), guiRegistrarUsuarios.getContra().getText());
                                        guiRegistrarUsuarios.getModelo().addElement("<html><font color = #747271 >Usuario: </font>" + usuario.getUsuario() + " <font color = #747271 >Contraseña:</font>" + usuario.getContra() + "</html>");
                                        System.err.println(usuario.getUsuarios());
                                        getNombreUsuario().setText(" Usuario: " + guiIniciarSesion.getCampos()[0].getText());
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El usuario ya existe.");
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios.");
                                }
                            }
                            guiRegistrarUsuarios.getCampos()[0].setText("");
                            guiRegistrarUsuarios.getContra().setText("");
                            break;
                        case "Actualizar":
                            auxUsuario = null;
                            for (int i = 0; i < usuarios.size(); i++) {
                                auxUsuario = (Usuario) usuario.getUsuarios().get(i);
                                if (auxUsuario.getInfo().equals(guiRegistrarUsuarios.getRowSeleccionado())) {
                                    auxUsuario.actualizarUsuario(guiRegistrarUsuarios.getCampos()[0].getText(), guiRegistrarUsuarios.getContra().getText());
                                    guiRegistrarUsuarios.getModelo().set(i-1, auxUsuario.getInfo());

                                }
                            }
                            
                            break;
                        case "Eliminar":
                            auxUsuario = null;
                            for (int i = 0; i < usuario.getUsuarios().size(); i++) {
                                auxUsuario = (Usuario) usuario.getUsuarios().get(i);
                                if (auxUsuario.getInfo().equals(guiRegistrarUsuarios.getRowSeleccionado())) {
                                    guiRegistrarUsuarios.getModelo().removeElement(auxUsuario.getInfo());
                                    usuario.eliminarUsuario(i);
                                    System.err.println(usuario.getUsuarios());
                                }
                            }
                            break;
                    }
                }
            });

        }
        fotoUsuario.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                getVentana().setSize(getVentana().getWidth(), 280);
                getPanel().setSize(getVentana().getWidth(), 280);
            }
        });
    }

    public void configDashboard() {
        for (JLabel boton : getBotones()) {
            if (boton.getText().equals("Guardar")) {
                boton.setText("Registrar medicamentos");
                boton.setBounds(80, 45, 200, 40);
            } else if (boton.getText().equals("Actualizar")) {
                boton.setText("Registrar clientes");
                boton.setBounds(80, 90, 200, 40);
            } else if (boton.getText().equals("Eliminar")) {
                boton.setText("Registrar facturas");
                boton.setBounds(80, 135, 200, 40);
            } else if (boton.getText().equals("Limpiar")) {
                boton.setText("Ver facturas");
                boton.setBounds(80, 180, 200, 40);
            }
            boton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    e.getComponent().setBackground(new Color(200, 60, 40, 255));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JLabel componente = (JLabel) e.getComponent();
                    e.getComponent().setBackground(new Color(255, 87, 51, 255));
                    if (componente.getText().equals("Registrar medicamentos")) {
                        guiMedicamentos.abrirGui();
                    } else if (componente.getText().equals("Registrar facturas")) {
                        guiFactura.abrirGui();
                        guiFactura.abrirGuiFactura();
                        guiFactura.agregarMedicamentosCB(guiMedicamentos.getArrayMedicamentos());
                        guiFactura.getArrayMedicamentos(guiMedicamentos.getArrayMedicamentos());
                    } else if (componente.getText().equals("Ver facturas")) {
                        guiFacturas.configGuiFacturas();
                        guiFacturas.agregarFacturas(guiFactura.getThisFactura());
                    } else if (componente.getText().equals("Registrar clientes")) {
                        guiClientes.abrirGui();
                    } else if (componente.getText().equals("x")) {
                        System.exit(0);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    e.getComponent().setBackground(new Color(255, 87, 51, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    e.getComponent().setBackground(new Color(55, 55, 55, 255));
                }
            });

        }
        ocultarTodo();
        getVentana().setSize(350, 280);
        getTitulo().setText("Drogueria");
        getTitulo().setBounds(0, 0, getVentana().getWidth(), 30);
        for (int i = 0; i < getBotones().length; i++) {
            if (getBotones()[i].getText().equals("x")) {
                getBotones()[i].setBounds(getTitulo().getWidth() - 30, 0, 30, 30);
            } else if (getBotones()[i].getText().equals("-")) {
                getBotones()[i].setBounds(getTitulo().getWidth() - 60, 0, 30, 30);
            }
        }

        fotoUsuario.setBounds(0, 280 - 40, 40, 40);
        fotoUsuario.setOpaque(true);
        fotoUsuario.setBackground(new Color(55, 55, 55, 255));
        getPanel().add(fotoUsuario);
        nombreUsuario.setBounds(40, 280 - 40, 310, 40);
        nombreUsuario.setText(" Usuario: Ninguno");
        nombreUsuario.setVerticalAlignment(CENTER);
        nombreUsuario.setForeground(Color.lightGray);
        nombreUsuario.setFont(new Font("Arial Narrow", 1, 20));
        nombreUsuario.setOpaque(true);
        nombreUsuario.setBackground(new Color(45, 45, 45, 255));
        getPanel().add(nombreUsuario);
        nombreUsuario.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(35, 35, 35, 255));
                fotoUsuario.setBackground(new Color(35, 35, 35, 255));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                e.getComponent().setBackground(new Color(45, 45, 45, 255));
                fotoUsuario.setBackground(new Color(45, 45, 45, 255));
                if (getVentana().getHeight() == 280) {
                    getVentana().setSize(getVentana().getWidth(), 400);
                    getPanel().setSize(getVentana().getWidth(), 400);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setBackground(new Color(45, 45, 45, 255));
                fotoUsuario.setBackground(new Color(45, 45, 45, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(new Color(55, 55, 55, 255));
                fotoUsuario.setBackground(new Color(55, 55, 55, 255));
            }
        });
    }

    public JLabel getNombreUsuario() {
        return nombreUsuario;
    }

}
