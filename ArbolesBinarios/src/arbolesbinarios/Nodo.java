package arbolesbinarios;

import Frames.Hoja;
import Frames.Raiz;

public class Nodo {

	private Nodo nodoIzquierda, nodoDerecha;
	private int llave;
	private Object objeto;

	public Nodo(int llave, Object objeto) {
		this.llave = llave;
		this.objeto = objeto;
		nodoIzquierda = null;
		nodoDerecha = null;

	}

	public void setNodoIzquierda(Nodo nodoIzquierda) {
		this.nodoIzquierda = nodoIzquierda;
	}

	public void setNodoDerecha(Nodo nodoDerecha) {
		this.nodoDerecha = nodoDerecha;
	}

	public void setLlave(int llave) {
		this.llave = llave;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public Nodo getNodoIzquierda() {
		return nodoIzquierda;
	}

	public Nodo getNodoDerecha() {
		return nodoDerecha;
	}

	public int getLlave() {
		return llave;
	}

	public Object getObjeto() {
		return objeto;
	}

	public String getHijoIzquierdo() {
		if (nodoIzquierda != null) {
			return "Hoja #" + nodoIzquierda.getLlave()+", " + nodoIzquierda.getObjeto();
		} else {
			return "No";
		}
	}

	public String getHijoDerecho() {
		if (nodoDerecha != null) {
			return "Hoja #" + nodoDerecha.getLlave() + ", " + nodoDerecha.getObjeto();
		} else {
			return "No";
		}
	}

}
