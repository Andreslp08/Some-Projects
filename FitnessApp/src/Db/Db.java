package Db;

import Gui.GuiInformacion;
import com.sun.org.apache.bcel.internal.Constants;
import java.sql.*;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Db {

	private Connection conexion;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	private String direccion = "jdbc:mysql://localhost:3302/fitness?serverTimezone=" + TimeZone.getDefault().getID();

	public Connection obtenerConexion() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection(direccion, "root", "");
				System.out.println("Conexion exitosa con la base de datos.");
			} catch (Exception ex) {
				System.out.println("No se pudo establecer conexion con la base de datos: " + ex);
			} finally {
				try {
					conexion.close();
				} catch (SQLException ex) {
					System.out.println("Error al interntar cerrar la conexion: " + ex);
				}
			}
		} catch (Exception e) {
			int confirmacion = JOptionPane.showConfirmDialog(null, "El sistema no esta conectado a la base de datos por lo tanto la información ingresada no se guardara,\nintente conectarse a la base de datos y reiniciar el programa.\n¿ Continuar de todos modos ?", "Aviso de confirmación", 1);
			if (confirmacion != JOptionPane.OK_OPTION) {
				System.exit(0);
			}

		}
		return conexion;
	}

	public void nuevoRegistro(String query) {
		try {
			conexion = DriverManager.getConnection(direccion, "root", "");
			st = conexion.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println("No se pudo establecer conexion con la base de datos: " + ex);
		} finally {
			try {
				conexion.close();
			} catch (SQLException ex) {
				System.out.println("Error al interntar cerrar la conexion: " + ex);
			}
		}
	}
	int cantidad = 0;
	public  String[] mostrarDatos( String query, GuiInformacion guiInformacion ){
		String[] datos = new String[18];
		try {
			conexion = DriverManager.getConnection(direccion, "root", "");
			st = conexion.createStatement();
			rs = st.executeQuery(query);
			while( rs.next() ){
				for (int i = 0; i < 18; i++) {
					datos[i] = rs.getNString(i+2);
		guiInformacion.getDatosInfo()[i].setText( guiInformacion.getDatos()[i][0] + datos[i] + guiInformacion.getDatos()[i][1]);
					System.out.println( datos[0] + " " + datos[1] );
				}	
			}
			
			
		} catch (Exception ex) {
			System.out.println("No se pudo establecer conexion con la base de datos: " + ex);
		} finally {
			try {
				conexion.close();
			} catch (SQLException ex) {
				System.out.println("Error al interntar cerrar la conexion: " + ex);
			}
		}
		return datos;
	}
	public String consultarFecha(String query, Gui.GuiInformacion guiInformacion ) {
		String dato = "";
		try {
			conexion = DriverManager.getConnection(direccion, "root", "");
			st = conexion.createStatement();
			rs = st.executeQuery(query);
			while( rs.next() ){
				dato = rs.getString("Fecha");
				guiInformacion.getRegistro()[cantidad].setText(dato);
				cantidad = cantidad + 1;
				
			}
			
			
		} catch (Exception ex) {
			System.out.println("No se pudo establecer conexion con la base de datos: " + ex);
		} finally {
			try {
				conexion.close();
			} catch (SQLException ex) {
				System.out.println("Error al interntar cerrar la conexion: " + ex);
			}
		}
		return dato;
	}

	public int getCantidadRegistros() {
		int cantidad = -1;
		try {
			conexion = DriverManager.getConnection(direccion, "root", "");
			st = conexion.createStatement();
			rs = st.executeQuery("SELECT COUNT(*) FROM infoFisico");
			rs.next();
			cantidad = rs.getInt(1);

		} catch (Exception ex) {
			System.out.println("No se pudo establecer conexion con la base de datos: " + ex);
		} finally {
			try {
				conexion.close();
			} catch (SQLException ex) {
				System.out.println("Error al interntar cerrar la conexion: " + ex);
			}
		}
		return cantidad;
	}
}
