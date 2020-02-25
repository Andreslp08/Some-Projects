package Gui;

import drogueria.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

public class GuiRegistrarUsuarios extends GuiMedicamentos {

    private JPasswordField contra;
    private String usuario, pass;
    private ArrayList usuarios;
    private DefaultListModel modelo;
    private JList lista;
    private JScrollPane scroll;
    private Object rowSeleccionado;

    public GuiRegistrarUsuarios(ArrayList usuarios) {
        this.usuarios = usuarios;
        contra = new JPasswordField();
        configGui();
        usuario = "";
        pass = "";
        modelo = new DefaultListModel();
        lista = new JList(modelo);
        scroll = new JScrollPane(lista);
        scroll.setBounds(300, 60, 260, 130);
        scroll.setBackground(Color.darkGray);
        scroll.setFocusable(false);
        scroll.setBorder(null);
        lista.setBackground(new Color(60, 60, 60, 255));
        lista.setFont(new Font("Arial Narrow", 1, 15));
        lista.setForeground(Color.white);
        lista.setAutoscrolls(true);
        lista.setBorder(null);
        lista.setSelectionBackground(new Color(70, 70, 70, 255));
        lista.setSelectionForeground(Color.white);
        lista.setFocusable(false);
        getPanel().add(scroll);
        rowSeleccionado = lista.getSelectedValue();
        lista.addMouseListener(new MouseAdapter() {
            public void mouseReleased( MouseEvent e ){
                rowSeleccionado = lista.getSelectedValue();
            }
});
    }

    public void configGui() {
        ocultarTodo();
        getTitulo().setSize(650, getTitulo().getHeight());
        getTitulo().setText("Registrar Usuarios");
        getVentana().setSize(650, 250);
        getBotones()[0].setText("Guardar");
        getBotones()[1].setText("Actualizar");
        getBotones()[1].setVisible(true);
        getBotones()[2].setText("Eliminar");
        getBotones()[2].setVisible(true);
        for (JLabel i : getBotones()) {
            switch (i.getText()) {
                case "x":
                    i.setLocation(getTitulo().getWidth() - 30, i.getY());
                    i.setVisible(true);
                    break;
                case "-":
                    i.setLocation(getTitulo().getWidth() - 60, i.getY());
                    i.setVisible(true);
                    break;
                case "Guardar":
                    i.setLocation(100, 200);
                    i.setVisible(true);
                    break;
                case "Actualizar":
                    i.setLocation(255, 200);
                    i.setVisible(true);
                    break;
                case "Eliminar":
                    i.setLocation(410, 200);
                    i.setVisible(true);
                    break;
            }
            getLabelCampos()[0].setText("Usuario");
            getLabelCampos()[0].setLocation(0, 40);
            getLabelCampos()[0].setSize(400, getLabelCampos()[0].getHeight());
            getLabelCampos()[0].setVerticalAlignment(CENTER);
            getLabelCampos()[0].setHorizontalAlignment(CENTER);
            getLabelCampos()[0].setVisible(true);
            getCampos()[0].setLocation(125, 70);
            getCampos()[0].setVisible(true);
            getCampos()[0].setHorizontalAlignment(CENTER);

            getLabelCampos()[1].setText("Contraseña");
            getLabelCampos()[1].setLocation(0, 115);
            getLabelCampos()[1].setSize(400, getLabelCampos()[0].getHeight());
            getLabelCampos()[1].setVerticalAlignment(CENTER);
            getLabelCampos()[1].setHorizontalAlignment(CENTER);
            getLabelCampos()[1].setVisible(true);
        }
        contra.setBounds(125, 70 * 2, getCampos()[0].getWidth(), getCampos()[0].getHeight());
        contra.setVisible(true);
        contra.setHorizontalAlignment(CENTER);
        contra.setForeground(Color.white);
        contra.setBackground(Color.darkGray);
        contra.setFont(new Font("Arial Narrow", 1, 25));
        contra.setBorder(null);
        contra.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(55, 55, 55, 255)));
        contra.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
                JTextField componente = (JTextField) e.getComponent();
                componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 87, 51, 255)));

            }

            public void mouseExited(MouseEvent e) {
                JTextField componente = (JTextField) e.getComponent();
                componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(55, 55, 55, 255)));

            }
        });
        getPanel().add(contra);
    }

    public JPasswordField getContra() {
        return contra;
    }

    public JLabel getBotonRegistro() {
        return getBotones()[0];
    }

    public ArrayList getListaUsuarios() {
        return usuarios;
    }

    public boolean validarUsuarioRegistro() {
        Usuario auxUsuario = null;
        for (int i = 0; i < getListaUsuarios().size(); i++) {
            auxUsuario = (Usuario) getListaUsuarios().get(i);
            System.err.println(auxUsuario.getContra());
            if (auxUsuario.getUsuario().equals(getCampos()[0].getText())) {
                return false;
            }
        }
        return true;
    }

    public DefaultListModel getModelo() {
        return modelo;
    }

    public JList getLista() {
        return lista;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public Object getRowSeleccionado() {
        return rowSeleccionado;
    }

}
