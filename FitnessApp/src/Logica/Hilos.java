package Logica;

import Gui.GuiInformacion;
import Gui.GuiInicio;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Hilos extends Thread {

	private int contador;
	private int posXf = 0;
	private int posYf = 0;
	private int wf = 270;
	private int hf = 600;
	private RoundRectangle2D.Double forma;
	private float transparencia = 0.1f;

	public Hilos() {

	}

	public void animVentana(GuiInicio guiInicio) {
		posXf = guiInicio.getFrame().getX();
		wf = 270;
		System.err.println(posXf);
		while (posXf > 530) {
			posXf = posXf - 5;
			guiInicio.getFrame().setLocation(posXf, guiInicio.getFrame().getY());
		}
		new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					wf = wf + 50;
					if (wf <= 960) {
						guiInicio.getFrame().setSize(wf, guiInicio.getFrame().getHeight());
						forma = new RoundRectangle2D.Double(0, 0, guiInicio.getFrame().getBounds().width, guiInicio.getFrame().getBounds().height, 50, 50);
						AWTUtilities.setWindowShape(guiInicio.getFrame(), forma);
						//guiInicio.getBarra().setSize(wf-guiInicio.getFondo().getWidth(), 30);
						Thread.sleep(5);
					} else {
						Hilos.currentThread().stop();
					}
				} catch (InterruptedException ex) {
					Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);

				}

			}
		}).start();

	}

	public void opacidadVentana(GuiInicio guiInicio) {
		new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				while (transparencia <= 0.99f) {
					try {
						transparencia = transparencia + 0.01f;
						guiInicio.getFrame().setOpacity(transparencia);
						Thread.sleep(5);
					} catch (InterruptedException ex) {
						Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}).start();
	}
	int posYNotificacion;

	public void notificacion(JLabel barraNotificacion, JComponent contenedor, int posInicial, int posFinal) {
		contador = 0;
		barraNotificacion.setLocation(0, posInicial);
		posYNotificacion = posInicial;
		new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador = contador + 1;
				System.err.println(contador);
				if (contador < 300) {
					posYNotificacion = posYNotificacion - 1;
					barraNotificacion.setBounds(barraNotificacion.getX(), posYNotificacion, barraNotificacion.getWidth(), barraNotificacion.getHeight());
					if (posYNotificacion < posFinal) {
						posYNotificacion = posFinal;
						barraNotificacion.setBounds(barraNotificacion.getX(), posYNotificacion, barraNotificacion.getWidth(), barraNotificacion.getHeight());
					}

				} else if (contador >= 300) {
					posYNotificacion = posYNotificacion + 1;
					barraNotificacion.setBounds(barraNotificacion.getX(), posYNotificacion, barraNotificacion.getWidth(), barraNotificacion.getHeight());
					if (posYNotificacion >= posInicial) {
						posYNotificacion = posInicial;
						barraNotificacion.setBounds(barraNotificacion.getX(), posYNotificacion, barraNotificacion.getWidth(), barraNotificacion.getHeight());
						Hilos.currentThread().stop();
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException ex) {
					Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}).start();
	}
	int posXPanelInfo;

	public void animInfo(GuiInformacion guiInformacion) {
		posXPanelInfo = guiInformacion.getPosXPanelPinfo();
		new Timer( 10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				posXPanelInfo = posXPanelInfo - 10;
				guiInformacion.getPanelPrincipalInfo().setLocation(posXPanelInfo, guiInformacion.getPanelPrincipalInfo().getY());
				if (posXPanelInfo <= guiInformacion.getPanelPrincipalLista().getWidth()) {
					posXPanelInfo = guiInformacion.getPanelPrincipalLista().getWidth();
					guiInformacion.getPanelPrincipalInfo().setLocation(posXPanelInfo, guiInformacion.getPanelPrincipalInfo().getY());
					Thread.currentThread().stop();
				}
			}
		}).start();

	}

	public float getOpacity() {
		return transparencia;
	}
}
