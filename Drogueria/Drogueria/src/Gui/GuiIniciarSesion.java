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

public class GuiIniciarSesion extends GuiRegistrarUsuarios {

	private JPasswordField contra;
	private String usuario, pass;
	private ArrayList usuarios;
	public GuiIniciarSesion(  ArrayList usuarios ) {
		super( usuarios );
		this.usuarios = usuarios;
		getTitulo().setText("Iniciar Sesión");
		getBotones()[0].setText("Ingresar");
                getVentana().setSize(400, 250);
                getTitulo().setSize(400, 30);
                getScroll().setVisible(false);
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
                case "Ingresar":
                    i.setLocation(125, 200);
                    i.setVisible(true);
                    break;
                case "Actualizar":
                    i.setLocation(125+130, 200);
                    i.setVisible(false);
                    break;
                case "Eliminar":
                    i.setLocation(125+260, 200);
                    i.setVisible(false);
                    break;
            }
                }
	}

	public boolean validarIngreso() {
	Usuario auxUsuario2 = null;
		for (int i = 0; i < usuarios.size(); i++) {
			auxUsuario2 = (Usuario)usuarios.get(i);
			if( auxUsuario2.getContra().equals( getContra().getText() ) && auxUsuario2.getUsuario().equals( getCampos()[0].getText() )){
				return true;
			}
		}
		return false;
	}
	
	public void verUsuarios(){
		
	}
}
