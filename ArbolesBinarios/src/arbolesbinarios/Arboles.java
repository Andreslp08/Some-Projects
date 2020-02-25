package arbolesbinarios;

import Frames.Hoja;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Arboles {

	private Nodo nodoRaiz;
	private int cantidad;
	private ImageIcon icono;
	private Image imagen;

	public Arboles() {
	}

	public void agregarNodo(int llave, Object objeto) {
		Nodo nodoAux = nodoRaiz;
		Nodo nuevoNodo = null;
		Nodo nodoPadre;
		if (cantidad <= 0) {
			if (nodoRaiz == null) {
				nodoRaiz = new Nodo(llave, objeto);
				cantidad++;
			}
		} else {
			while (true) {
				nodoPadre = nodoAux;
				if (llave < nodoAux.getLlave()) {
					nodoAux = nodoAux.getNodoIzquierda();
					if (nodoAux == null) {
						nuevoNodo = new Nodo(llave, objeto);
						nodoPadre.setNodoIzquierda(nuevoNodo);
						cantidad++;
						return;
					}
				} else if( llave > nodoAux.getLlave())  {
					nodoAux = nodoAux.getNodoDerecha();
					if (nodoAux == null) {
						nuevoNodo = new Nodo(llave, objeto);
						nodoPadre.setNodoDerecha(nuevoNodo);
						cantidad++;
						return;
					}
				}
				else{
					return;
				}
			}
		}

	}

	public void preOrden(Nodo nodo) {
		if (nodo != null) {
			System.out.print(nodo.getLlave() + " ");
			preOrden(nodo.getNodoIzquierda());
			preOrden(nodo.getNodoDerecha());
		}
	}

	public void inOrden(Nodo nodo) {
		if (nodo != null) {
			preOrden(nodo.getNodoIzquierda());
			System.out.print(nodo.getLlave() + " ");
			preOrden(nodo.getNodoDerecha());
		}
	}

	Nodo nodoObtener;
	public void calcularNodo(Nodo nodo, int num) {
		if (nodo != null) {
			if (nodo.getLlave() == num) {
				nodoObtener = nodo;
				return;
			}
			calcularNodo(nodo.getNodoIzquierda(), num);
			calcularNodo(nodo.getNodoDerecha(), num);
		}
	}
	
	public Nodo obtenerNodo(Nodo nodo, int num){
		calcularNodo(nodo, num);
		return nodoObtener;
	}

	public int getCantidad() {
		return cantidad;
	}

	public Nodo getNodoRaiz() {
		return nodoRaiz;
	}
}
