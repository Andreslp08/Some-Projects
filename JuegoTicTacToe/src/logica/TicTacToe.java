package logica;

import Gui.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TicTacToe {

	private final String X = "X", O = "O";
	private String turno[] = {X, O};
	private int toquesX = 0, toquesO = 0;
	private int ultimo = 0;
	private GuiJuego guiJuego;
	private int num = 0;
	private GuiDialog guiDialog;
	private GuiDialogGanador guiDialogGanador;
	private int numRondas = 1;
	private Audio audio;
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

	public TicTacToe(GuiJuego guiJuego) {
		//audio
		audio = new Audio();
		// jdialogos
		guiDialog = new GuiDialog();
		guiDialogGanador = new GuiDialogGanador();
		guiDialogGanador.getBotones()[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
//				new GuiPrincipal();
//				guiJuego.getGuiPrincipal().dispose();
				guiJuego.getGuiPrincipal().nuevoJuego();
				guiJuego.setVisible(false);
				guiJuego.getCancion().detenerSonido();
				guiJuego.getGuiPrincipal().getGuiMenu().setVisible(true);
				guiJuego.getGuiPrincipal().getGuiMenu().getGuiPartida().setVisible(false);
				guiJuego.getGuiPrincipal().getGuiMenu().getGuiPartida().restaurarTodo();
				guiJuego.getGuiPrincipal().getGuiMenu().getHilo().stop();
				guiJuego.getGuiPrincipal().getGuiMenu().reiniciarHilo();
				numRondas = 1;
			}

		});
		for (int i = 0; i < guiDialog.getBotones().length; i++) {
			guiDialog.getBotones()[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					reiniciar();
				}

			});
		}
		this.guiJuego = guiJuego;
	}

	public boolean comprobarGanador() {
		if (diagonalIzquierda() == true) {
			audio.reproducirSonido("/sonidos/rondaGanada.wav", false);
			return true;
		}
		if (diagonalDerecha() == true) {
			audio.reproducirSonido("/sonidos/rondaGanada.wav", false);
			return true;
		}
		if (horizontales() == true) {
			audio.reproducirSonido("/sonidos/rondaGanada.wav", false);
			return true;
		}
		if (verticales() == true) {
			audio.reproducirSonido("/sonidos/rondaGanada.wav", false);
			return true;
		}
		return false;
	}

	public boolean comprobarTurno(JLabel cuadro) {
		if (num > 1) {
			num = 0;
		}
		if (cuadro.getText().equals("")) {
			cuadro.setText(turno[num]);
			if (turno[num].equals(X)) {
				guiJuego.getTurnoDe().setText("<html>Turno de: <font  color = \"#00FF00\">" + guiJuego.getGuiJugador2().getText() + "</font></html>");
			} else if (turno[num].equals(O)) {
				guiJuego.getTurnoDe().setText("<html>Turno de: <font  color = \"#00FF00\">" + guiJuego.getGuiJugador1().getText() + "</font></html>");

			}
			switch (cuadro.getText()) {
				case X:
					cuadro.setIcon(guiJuego.getEquisIcono());
					break;
				case O:
					cuadro.setIcon(guiJuego.getCirculoIcono());
					break;
			}
			num++;
			return true;
		}
		return false;
	}

	public boolean diagonalIzquierda() {
		toquesX = 0;
		toquesO = 0;
		//diagonnal izquierda
		for (int col = 0; col < guiJuego.getCantidad(); col++) {
			for (int fila = 0; fila < guiJuego.getCantidad(); fila++) {
				if (fila == col && guiJuego.getCuadros()[col][fila].getText().equals(X)) {
					toquesX = toquesX + 1;
					if (toquesX == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">"+ guiJuego.getGuiJugador1().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador1().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador1().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador1().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador1().getJugador().comprobarVictoria(numRondas)) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador1().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						return true;
					}
				} else if (fila == col && guiJuego.getCuadros()[col][fila].getText().equals(O)) {
					toquesO = toquesO + 1;
					if (toquesO == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador2().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador2().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador2().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador2().getJugador().comprobarVictoria(numRondas) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						return true;
					}
				}
			}

		}

		return false;
	}

	public boolean diagonalDerecha() {
		toquesX = 0;
		toquesO = 0;
		ultimo = guiJuego.getCantidad();
		//diagonnal izquierda
		for (int col = 0; col < guiJuego.getCantidad(); col++) {
			ultimo = ultimo - 1;
			for (int fila = 0; fila < guiJuego.getCantidad(); fila++) {
				if (ultimo == fila && guiJuego.getCuadros()[col][fila].getText().equals(X)) {
					toquesX = toquesX + 1;
					if (toquesX == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">" + guiJuego.getGuiJugador1().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador1().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador1().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador1().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador1().getJugador().comprobarVictoria(numRondas) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador1().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}						
						return true;
					}
				}
				if (ultimo == fila && guiJuego.getCuadros()[col][fila].getText().equals(O)) {
					toquesO = toquesO + 1;
					if (toquesO == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador2().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador2().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador2().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador2().getJugador().comprobarVictoria(numRondas) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						return true;
					}
				}
			}

		}

		return false;
	}

	public boolean horizontales() {
		toquesX = 0;
		toquesO = 0;
		for (int col = 0; col < guiJuego.getCantidad(); col++) {
			toquesX = 0;
			toquesO = 0;
			for (int fila = 0; fila < guiJuego.getCantidad(); fila++) {
				if (guiJuego.getCuadros()[col][fila].getText().equals(X)) {
					toquesX = toquesX + 1;
					if (toquesX == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">" + guiJuego.getGuiJugador1().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador1().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador1().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador1().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador1().getJugador().comprobarVictoria(numRondas) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador1().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						col++;
						return true;
					}
				}
				if (guiJuego.getCuadros()[col][fila].getText().equals(O)) {
					toquesO = toquesO + 1;
					if (toquesO == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador2().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador2().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador2().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador2().getJugador().comprobarVictoria(numRondas) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						col++;
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean verticales() {
		int col = 0;
		int fila = 0;
		toquesX = 0;
		toquesO = 0;
		for (fila = 0; fila < guiJuego.getCantidad(); fila++) {
			for (col = 0; col < guiJuego.getCantidad(); col++) {
				if (guiJuego.getCuadros()[col][fila].getText().equals(X)) {
					toquesX = toquesX + 1;
					if (toquesX == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">" + guiJuego.getGuiJugador1().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador1().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador1().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador1().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador1().getJugador().comprobarVictoria(numRondas) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador1().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						fila++;
						return true;
					}
				}
				if (guiJuego.getCuadros()[col][fila].getText().equals(O)) {
					toquesO = toquesO + 1;
					if (toquesO == guiJuego.getCantidad()) {
						numRondas++;
						guiJuego.getGuiRondas().getLabelInfo()[1].setText("<html>Ronda actual<p align=\"center\">" + numRondas + "</p></html>");
						guiDialog.setVisible(true);
						guiDialog.configDialog("<html><p align=\"center\"><font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getText() + "</p></font> <p align=\"center\">ha ganado la ronda.</p></html>", new Color(20, 20, 20, 230), "Arial", 1, intH(45));
						guiJuego.getGuiJugador2().getJugador().aumentarRoondasGanadas();
						guiJuego.getGuiJugador2().getLabelInfo().setText("<html><p>Rondas ganadas:" + guiJuego.getGuiJugador2().getJugador().getRondasGanadas() + "</p></html>");
						if (guiJuego.getGuiJugador2().getJugador().comprobarVictoria(numRondas) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡<font  color = \"#0064FA\">" + guiJuego.getGuiJugador2().getJugador().getNombre() + "</font> ha ganado!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						if (guiJuego.getGuiJugador1().getJugador().sinGanador(numRondas, guiJuego.getGuiJugador1().getJugador(), guiJuego.getGuiJugador2().getJugador()) == true) {
							guiDialog.setVisible(false);
							guiDialogGanador.setVisible(true);
							guiDialogGanador.configDialog("<html>¡Partida finalizada<font  color = \"#0064FA\"> no hubo ganador</font>!</html>", new Color(0, 0, 0, 100), "Impact", 0, intW(80));
						}
						fila++;
						return true;
					}
				}
			}
			toquesX = 0;
			toquesO = 0;
		}
		return false;
	}

	public GuiJuego getGuiJuego() {
		return guiJuego;
	}

	public void reiniciar() {
		int col = 0;
		int fila = 0;
		for (fila = 0; fila < guiJuego.getCantidad(); fila++) {
			for (col = 0; col < guiJuego.getCantidad(); col++) {
				guiJuego.getCuadros()[col][fila].setText("");
				guiJuego.getCuadros()[col][fila].setIcon(null);
			}
		}
	}

}
