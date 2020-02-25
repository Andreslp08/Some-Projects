package logica;

import Gui.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TicTacToeConsola {

	private int cantidad;
	private String[][] cuadros;
	private StringBuilder matriz;
	private int insertarCol, insertarFila;
	private String input;
	private final String X = "X", O = "O";
	private String turno[] = {X, O};
	private int toquesX = 0, toquesO = 0;
	private int ultimo = 0;

	public TicTacToeConsola(int cantidad) {
		matriz = new StringBuilder();
		cuadros = new String[cantidad][cantidad];
		this.cantidad = cantidad;
		ultimo = cantidad;
		for (int col = 0; col < cantidad; col++) {
			for (int fila = 0; fila < cantidad; fila++) {
				cuadros[col][fila] = "[ ]";
				matriz.append(cuadros[col][fila]);
			}
			matriz.append("\n");
		}
		System.err.println(matriz);
		ingresar();

	}

	public void actualizarCuadricula() {
		matriz = new StringBuilder();
		for (int col = 0; col < cantidad; col++) {
			for (int fila = 0; fila < cantidad; fila++) {
				matriz.append(cuadros[col][fila]);
			}
			matriz.append("\n");
		}
		System.err.println(matriz);
	}

	public boolean ganar() {
		if (diagonalIzquierda()== true) {
			return true;
		}		
		if (diagonalDerecha()== true) {
			return true;
		}
		if (horizontales() == true) {
			return true;
		}
		if (verticales() == true) {
			return true;
		}
		return false;
	}

	public void ingresar() {
		int num = 0;
		while (ganar() == false) {
			try {
				insertarCol = Integer.parseInt(JOptionPane.showInputDialog("TURNO: " + turno[num] + "\nIngresa la columna:"));
				insertarFila = Integer.parseInt(JOptionPane.showInputDialog("TURNO :" + turno[num] + "\ningrese la fila"));
				if (cuadros[insertarCol][insertarFila].equals("[ ]")) {
					cuadros[insertarCol][insertarFila] = "[" + turno[num] + "]";
					actualizarCuadricula();
					num = num + 1;
				} else {
					JOptionPane.showMessageDialog(null, "La posicion seleccionada ya fue usada, ingrese otra por favor.");
				}
				if (num > 1) {
					num = 0;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Solo puede ingresar numeros enteros.");
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(null, "La posicion ingresada en la fila o la columna no existe, por favor ingrese otra.");
			}

		}
	}

	public boolean diagonalIzquierda() {
		toquesX = 0;
		toquesO = 0;
		//diagonnal izquierda
		for (int col = 0; col < cantidad; col++) {
			for (int fila = 0; fila < cantidad; fila++) {
				if (fila == col && cuadros[col][fila].equals("[" + X + "]")) {
					toquesX = toquesX + 1;
					if (toquesX == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + X + " ha ganado la partida.");
						return true;
					}
				} else if (fila == col && cuadros[col][fila].equals("[" + O + "]")) {
					toquesO = toquesO + 1;
					if (toquesO == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + O + " ha ganado la partida.");
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
		ultimo = cantidad;
		//diagonnal izquierda
		for (int col = 0; col < cantidad; col++) {
			ultimo = ultimo - 1;
			for (int fila = 0; fila < cantidad; fila++) {
				if (ultimo == fila && cuadros[col][fila].equals("[" + X + "]")) {
					toquesX = toquesX + 1;
					if (toquesX == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + X + " ha ganado la partida.");
						return true;
					}
				}
				if (ultimo == fila && cuadros[col][fila].equals("[" + O + "]")) {
					toquesO = toquesO + 1;
					if (toquesO == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + O + " ha ganado la partida.");
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
		for (int col = 0; col < cantidad; col++) {
			toquesX = 0;
			toquesO = 0;
			for (int fila = 0; fila < cantidad; fila++) {
				if (cuadros[col][fila].equals("[" + X + "]")) {
					toquesX = toquesX + 1;
					if (toquesX == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + X + " ha ganado la partida.");
						col++;
						return true;
					}
				}
				if (cuadros[col][fila].equals("[" + O + "]")) {
					toquesO = toquesO + 1;
					if (toquesO == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + O + " ha ganado la partida.");
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
		for (fila = 0; fila < cantidad; fila++) {
			for (col = 0; col < cantidad; col++) {
				if (cuadros[col][fila].equals("[" + X + "]")) {
					toquesX = toquesX + 1;
					if (toquesX == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + X + " ha ganado la partida.");
						fila++;
						return true;
					}
				}
				if (cuadros[col][fila].equals("[" + O + "]")) {
					toquesO = toquesO + 1;
					if (toquesO == cantidad) {
						JOptionPane.showMessageDialog(null, "EL jugador " + O + " ha ganado la partida.");
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

	public static void main(String[] args) {
		new TicTacToeConsola(3);
	}
}
