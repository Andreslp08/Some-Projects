package Interfaces;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.*;

public interface Temas {

	String[] themes = { "Claro", "Oscuro", "Aqua","Sunny"};
	// colores aqua
	Color[] fondosAqua = {new Color(40, 69, 64, 255), new Color(30, 59, 54, 255)};
	Color[] fuentesAqua = {Color.white, new Color(24, 106, 100, 255)};
	Color[] accesoAqua = {new Color(30, 59, 54, 255), new Color(14, 96, 90, 255), new Color(4, 86, 80, 255)};
	// colores claro
	Color[] fondosClaro = {Color.lightGray, Color.gray};
	Color[] fuentesClaro = {Color.darkGray, Color.gray};
	Color[] accesoClaro = {Color.gray, new Color(100, 100, 100, 255), new Color(30, 30, 30, 255)};
	//colores oscuro
	Color[] fondosOscuro = {new Color(16, 16, 16, 255), new Color(13, 13, 13, 255)};
	Color[] fuentesOscuro = {Color.LIGHT_GRAY, Color.gray};
	Color[] accesoOscuro = {new Color(13, 13, 13, 255), new Color(55, 55, 55, 255), new Color(65, 65, 65, 255)};
	//sunshine
	Color[] fondosSunny = {new Color(255, 240, 180, 255), new Color(255, 238, 128, 255)};
	Color[] fuentesSunny = {new Color(252, 140, 48, 255), Color.orange };
	Color[] accesoSunny = {new Color(255, 238, 128, 255), new Color(255, 218, 108, 255), new Color(255, 208, 98, 255)};

	//temas
	Color[][] fondos = { fondosClaro, fondosOscuro,fondosAqua, fondosSunny};
	Color[][] fuentes = { fuentesClaro, fuentesOscuro, fuentesAqua, fuentesSunny};
	Color[][] acceso = { accesoClaro, accesoOscuro, accesoAqua,accesoSunny };

	public Color obtenerColor(Color[] color, int num);
	public void aplicarTema(String tema, Color[] colorFondo, Color[] colorFuente, Color[] colorAcceso);
	public void cambiarColorBordes(int opcion, JComponent componente, Color color);
	public void cambiarColorCampos(int opcion, JComponent componente, Color fondo, Color letra);
	public void cambiarColorBotones(int opcion, JComponent componente, Color fondo, Color letra);
	public void cambiarColorAccionBoton(int opcion, JLabel componente);
	public void cambiarColorAccionCampo(int opcion, JTextField componente);
	public void cambiarTema();
}
