/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import arbolesbinarios.Arboles;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Andrés
 */
public class Hoja extends JLabel {

	private Color color = Color.green;
	private static final int W = 60, H = 60;
	private int w_img = W, h_img = H;
	private ImageIcon icono;
	private Image imagen;
	private Raiz raizIzq, raizDer;
	private Hoja hojaIzquierda;
	private Hoja hojaDerecha;

	public Hoja( Arboles arboles ) {
		this.hojaDerecha = null;
		this.hojaIzquierda = null;
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(BOTTOM);
		this.setSize(W, H);
		this.setForeground(Color.BLACK);
		this.setFont(new Font("Century Gothic", 1, 20));
		//Imagen fondo
		icono = new ImageIcon(getClass().getResource("/img/hoja2.png"));
		imagen = icono.getImage().getScaledInstance(W, H, Image.SCALE_SMOOTH);
		icono = new ImageIcon(imagen);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setFont(new Font("Century Gothic", 1, 25));
				w_img = W + 10;
				h_img = H + 10;
				setLocation(getX() - 10, getY());
				setSize(getWidth() + 10, getHeight() + 10);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setFont(new Font("Century Gothic", 1, 20));
				w_img = W;
				h_img = H;
				setLocation(getX() + 10, getY());
				setSize(getWidth() - 10, getHeight() - 10);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Información de la hoja\nClave: " + getText() + 
					"\nDato almacenado: " + arboles.obtenerNodo(arboles.getNodoRaiz(), Integer.parseInt(getText())).getObjeto() + 
					"\nHijo izquierdo: "  + arboles.obtenerNodo(arboles.getNodoRaiz(), Integer.parseInt(getText())).getHijoIzquierdo() +
					"\nHijo derecho:" + arboles.obtenerNodo(arboles.getNodoRaiz(), Integer.parseInt(getText())).getHijoDerecho()
						);
			}
			
			

		});
	}
	
	

	public Hoja getHojaIzquierda() {
		return hojaIzquierda;
	}

	public Hoja getHojaDerecha() {
		return hojaDerecha;
	}

	public void setHojaIzquierda(Hoja hojaIzquierda) {
		this.hojaIzquierda = hojaIzquierda;
	}

	public void setHojaDerecha(Hoja hojaDerecha) {
		this.hojaDerecha = hojaDerecha;
	}

	public void agregarRaices(JComponent componente) {
		// raiz izquierda
		raizIzq = new Raiz(0, this);
		raizIzq.setBounds(this.getX() - this.getWidth(), this.getY() + this.getHeight(), W, H);
		componente.add(raizIzq);
		//raiz derecha
		raizDer = new Raiz(1, this);
		raizDer.setBounds(this.getX() + this.getWidth(), this.getY() + this.getHeight(), W, H);
		componente.add(raizDer);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0, 0, 0, 100));
		g.drawImage(imagen, 0, 0, w_img + 5, h_img + 5, null);
		super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
		repaint();

	}

}
