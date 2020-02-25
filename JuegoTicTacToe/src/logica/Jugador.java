package logica;

import Gui.*;
import java.awt.Color;

public class Jugador {

	private String nombre;
	private int rondasGanadas = 0, victorias = 0;
	private int rondasTotales, rondasDeVictoria;
	private GuiJuego guiJuego;
	private GuiDialog guiDialog;

	public Jugador(GuiJuego guiJuego, String nombre, int rondasTotales, int rondasDeVictoria) {
		this.guiJuego = guiJuego;
		this.nombre = nombre;
		this.rondasTotales = rondasTotales;
		this.rondasDeVictoria = rondasDeVictoria;
		guiDialog = new GuiDialog();
	}

	public boolean comprobarVictoria(int rondaActual) {
		if (rondasGanadas == rondasDeVictoria ) {
			victorias++;
			return true;
		}
		return false;
	}

	public boolean sinGanador(int rondaActual, Jugador jugador1, Jugador jugador2) {
		if (rondaActual > rondasTotales && rondasGanadas != rondasDeVictoria ) {
				return true;			
		}
		return false;
	}

	public int getRondasTotales() {
		return rondasTotales;
	}

	public int getRondasDeVictoria() {
		return rondasDeVictoria;
	}

	public void aumentarRoondasGanadas() {
		rondasGanadas++;
	}

	public String getNombre() {
		return nombre;
	}

	public int getRondasGanadas() {
		return rondasGanadas;
	}

	public int getVictorias() {
		return victorias;
	}

	public GuiJuego getGuiJuego() {
		return guiJuego;
	}

}
