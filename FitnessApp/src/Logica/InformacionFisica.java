package Logica;

import java.sql.*;
import java.util.*;
import javax.swing.JTextField;

public class InformacionFisica {

	private ArrayList informaciones;
	private InformacionFisica nuevaClase;
	private String[] datos;

	public InformacionFisica(String[] datos) {
		this.datos = datos;
	}

	
	public InformacionFisica(Connection conexion) {
		informaciones = new ArrayList<InformacionFisica>();
		datos = new String[18];
		for (String i : datos) {
			i = new String();
		}
	}

	public void nuevaInfo(JTextField[] arrayInfo) {
		for (int i = 0; i < datos.length; i++) {
			datos[i] = arrayInfo[i].getText();
		}
		nuevaClase = new InformacionFisica(datos);
		for (String i : datos) {
			System.out.println(i);
		}
	}
	
	public InformacionFisica getClase(){
		return nuevaClase;
	}
	
	public String getDatos( int i ){
		return datos[i];
	}
}
