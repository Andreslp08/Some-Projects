package Gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.*;
import Gui.GuiJuego.*;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiPrincipal extends JFrame {

	private ImageIcon icono;
	private GuiMenu guiMenu;
	private Cursor cursor;
	private Toolkit toolkit;
	private ImageIcon iconoCursor;
	private GuiJuego guiJuego;
	private Thread hilo;
	
	public GuiPrincipal() {
		//gui
		this.setUndecorated(true);
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		//Cursor
		iconoCursor = new ImageIcon(getClass().getResource("/img/cursor.png"));
		toolkit = Toolkit.getDefaultToolkit();
		cursor = toolkit.createCustomCursor(iconoCursor.getImage(), new Point(0, 0), "cursor");
		setCursor(cursor);
		// icono barra de tareas
		icono = new ImageIcon(getClass().getResource("/img/icono.png"));
		this.setIconImage(icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
////		// Gui Menu
		guiMenu = new GuiMenu(this);
		//Gui juego
		guiJuego = new GuiJuego(this);
//		guiJuego.configCuadricula(TipoCuadricula.TRESxTRES);
	}

//	@Override
//	public void run() {
//		while (true) {
//			if (guiMenu != null) {
//				System.err.println("");
//				if (guiMenu.isVisible() == false) {
//					System.err.println("");
//					guiJuego.setVisible(true);
//				}
//			}
//			else{
//				System.err.println("");
//			}
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException ex) {
//				Logger.getLogger(GuiPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//			}
//		}
//	}
	public JFrame getThis() {
		return this;
	}

	public GuiJuego getGuiJuego() {
		return guiJuego;
	}

	public void nuevoJuego() {
		guiJuego = new GuiJuego(this);
	}

	public GuiMenu getGuiMenu() {
		return guiMenu;
	}

	public GuiJuego getNuevoJuego() {
		return guiJuego;
	}

}
