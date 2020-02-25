package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

public class GuiRondas extends JLabel {

	private final int W = 400, H = 800;
	private JLabel labelInfo[];
	private String textoInfo[] = {"Rondas totales", "Ronda actual", "n rondas para ganar", "Tiempo transcurrido"};
	private Color[] coloresBackground = {new Color( 20, 20, 20, 255 ), new Color( 25, 25, 25, 255 ), new Color( 30, 30, 30, 255 ), new Color( 35, 35, 35, 255 )};
	private Color[] coloresForeground = {new Color( 140, 140, 140, 255 ),  new Color( 145, 145, 145, 255 ), new Color( 150, 150, 150, 255 ), new Color( 155, 155, 155, 155 )};
	private int posY = 0;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// resoluciones
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int UW = (int) screenSize.getWidth(), UH = (int) screenSize.getHeight();
	private final int MW = 1920, MH = 1080;

	//getters resolucion
	public int intW(int num) {
		return ((num) * UW) / MW;
	}

	public int intH(int num) {
		return ((num) * UH) / MH;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public GuiRondas(GuiJuego guiJuego) {
		setSize(intW(W), intH(H));
		guiJuego.add(this);
		setVerticalAlignment(CENTER);
		setHorizontalAlignment(CENTER);
		setLayout(null);
		//info
		labelInfo = new JLabel[textoInfo.length];
		for (int i = 0; i < labelInfo.length; i++) {
			labelInfo[i] = new JLabel(textoInfo[i]);
			labelInfo[i].setBounds(intW(0), intH(posY), intW(400), intH(200));
			labelInfo[i].setVerticalAlignment(CENTER);
			labelInfo[i].setHorizontalAlignment(CENTER);
			labelInfo[i].setForeground(coloresForeground[i]);
			labelInfo[i].setFont(new Font("Impact", 0, intW(35)));
			labelInfo[i].setOpaque(true);
			labelInfo[i].setBackground(coloresBackground[i]);
			this.add(labelInfo[i]);
			posY = posY + 200;
		}
		
	}

	public JLabel[] getLabelInfo() {
		return labelInfo;
	}
	

}
