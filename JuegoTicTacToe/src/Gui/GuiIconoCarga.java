
package Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.*;

/**
 * Componente que dibuja una foto y permite rotarla.
 * @author chuidiang
 *
 */
public class GuiIconoCarga extends JFrame {
    
    /**
     * Devuelve como tamaño preferido el de la foto.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(icono.getIconWidth(), icono.getIconHeight());
    }

    /** La foto */
    private ImageIcon icono = null;

    /**
     * Carga la foto y la guarda
     * @param ficheroImagen
     */
    public GuiIconoCarga(String ficheroImagen) {
        icono = new ImageIcon(getClass().getResource(ficheroImagen));
		this.setBounds(0, 0, 500, 500);
		this.setVisible(true);
    }

    /**
     * Cuanto queremos que se rote la foto, en radianes.
     */
    private double rotacion = 0.0;

    /**
     * Dibujo de la foto rotandola.
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // AffineTransform realiza el giro, usando como eje de giro el centro
        // de la foto (width/2, height/2) y el angulo que indica el atributo
        // rotacion.
        AffineTransform tx = AffineTransform.getRotateInstance(rotacion, 
                icono.getIconWidth()/2, icono.getIconHeight()/2);
        
        // dibujado con la AffineTransform de rotacion
        g2d.drawImage(icono.getImage(), tx, this);
    }

    /**
     * Devuelve la rotacion actual.
     * @return rotacion en radianes
     */
    public double getRotacion() {
        return rotacion;
    }

    /**
     * Se le pasa la rotación deseada.
     * @param rotacion La rotacion en radianes.
     */
    public void setRotacion(double rotacion) {
        this.rotacion = rotacion;
    }
}