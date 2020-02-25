/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import arbolesbinarios.Arboles;
import arbolesbinarios.Nodo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Andrés
 */
public class GuiArbol extends JFrame {

	private Hoja hojaRaiz;
	private static final int W_VENTANA = 1000, H_VENTANA = 800;
	private JLabel panel;
	private ImageIcon icono;
	private Image imagenFondo;
	private String[] textoBotones = {"Agregar hoja", "Nuevo arbol"};
	private JLabel[] botones;
	private Arboles arboles;
	private int cantidad = 0;

	public GuiArbol() {
		//arboles
		arboles = new Arboles();
		this.setTitle("Arboles");
		this.setVisible(true);
		this.setSize(W_VENTANA, H_VENTANA);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//panel
		panel = new JLabel();
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		panel.setLayout(null);
		// img
		icono = new ImageIcon(getClass().getResource("/img/fondo.jpg"));
		imagenFondo = icono.getImage().getScaledInstance(W_VENTANA, H_VENTANA, Image.SCALE_SMOOTH);
		icono = new ImageIcon(imagenFondo);
		panel.setIcon(icono);
		this.add(panel);
		//botones
		crearBotones();

	}

	public void addhoja(int llave) {
		Hoja hojaAux = hojaRaiz;
		Hoja hojaNueva = null;
		Hoja hojaPadre;
		if (cantidad <= 0) {
			if (hojaRaiz == null) {
				hojaRaiz = new Hoja( arboles );
				hojaRaiz.setLocation((this.getWidth() - hojaRaiz.getWidth()) / 2, 10);
				hojaRaiz.agregarRaices(panel);
				hojaRaiz.setText(llave + "");
				panel.add(hojaRaiz);
				cantidad++;
			}
		} else {
			while (true) {
				hojaPadre = hojaAux;
				if (llave < Integer.parseInt(hojaAux.getText())) {
					hojaAux = hojaAux.getHojaIzquierda();
					if (hojaAux == null) {
						hojaNueva = new Hoja( arboles );
						hojaNueva.setLocation((hojaPadre.getX() - hojaPadre.getWidth()) - 30, hojaPadre.getY() + hojaPadre.getHeight() * 2);
						hojaNueva.setText(llave + "");
						hojaNueva.agregarRaices(panel);
						hojaPadre.setHojaIzquierda(hojaNueva);
						panel.add(hojaNueva);
						cantidad++;
						return;
					}
				} else  if (llave > Integer.parseInt(hojaAux.getText())){
					hojaAux = hojaAux.getHojaDerecha();
					if (hojaAux == null) {
						hojaNueva = new Hoja( arboles );
						hojaNueva.setLocation((hojaPadre.getX() + hojaPadre.getWidth()) + 30, hojaPadre.getY() + hojaPadre.getHeight() * 2);
						hojaNueva.agregarRaices(panel);
						hojaNueva.setText(llave + "");
						hojaPadre.setHojaDerecha(hojaNueva);
						panel.add(hojaNueva);
						cantidad++;
						return;

					}

				}
				else{
					JOptionPane.showMessageDialog(null, "La llave ya se encuentra en el arbol, intente con una diferente.");
					return;
				}

			}
		}
	}

	public void crearBotones() {
		int x = 10;
		int y = 10;
		botones = new JLabel[textoBotones.length];
		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JLabel(textoBotones[i]);
			botones[i].setBounds(x, y, 150, 30);
			botones[i].setBackground(new Color(255, 255, 255, 100));
			botones[i].setOpaque(true);
			botones[i].setHorizontalAlignment(CENTER);
			botones[i].setVerticalAlignment(CENTER);
			botones[i].setFont(new Font("Century Gothic", 3, 20));
			panel.add(botones[i]);
			y = y + 40;
			botones[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel boton = (JLabel) e.getComponent();
					boton.setBackground(new Color(255, 255, 255, 120));
				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel boton = (JLabel) e.getComponent();
					boton.setBackground(new Color(255, 255, 255, 150));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JLabel boton = (JLabel) e.getComponent();
					boton.setBackground(new Color(255, 255, 255, 120));
					switch (boton.getText()) {
						case "Agregar hoja":
							int llave = 0;
							try {
								llave = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la llave:"));
								String dato = JOptionPane.showInputDialog("Ingrese el dato que guardara la hoja: ");
								arboles.agregarNodo(llave, dato);
								addhoja(llave);
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "La llave solo puede contener numeros enteros.");
							}

							break;
						case "Nuevo arbol":
							new GuiArbol();
							dispose();
							break;

					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel boton = (JLabel) e.getComponent();
					boton.setBackground(new Color(255, 255, 255, 100));
				}

			});
		}
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g); //To change body of generated methods, choose Tools | Templates.
		repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Gui Arbol
				GuiArbol guiArbol = new GuiArbol();
			}
		});
	}

}
