package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.awt.Win32GraphicsConfig;

public class DB {

	Connection conectar = null;
	PreparedStatement ps;
	ResultSet rs;
	Statement cs;

	String id;
	String titulo;
	String año;
	String portada;
	String genero;
	String plataforma;
	String puntuacion;
	String descripcion;
	String requisitosM;
	String requisitosR;
	int cantidadJuegos;
	private DB nuevoDato;
	private ArrayList datos;

	public DB(String id, String titulo, String año, String portada, String genero, String plataforma, String puntuacion, String descripcion, String requisitosM, String requisitosR, int cantidadJuegos) {
		this.id = id;
		this.titulo = titulo;
		this.año = año;
		this.portada = portada;
		this.genero = genero;
		this.plataforma = plataforma;
		this.puntuacion = puntuacion;
		this.descripcion = descripcion;
		this.requisitosM = requisitosM;
		this.requisitosR = requisitosR;
		this.cantidadJuegos = cantidadJuegos;
	}

	public DB() {
		datos = new ArrayList<DB>();
	}

	public Connection obtenerConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3302/juegos?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			System.out.println("Data base: Se ha realizado la conexion exitosamente.");
		} catch (Exception e) {
			System.out.println("Date base: error al intentar conectar con la base de datos.(  " + e + " )");
		} finally {
			try {
				conectar.close();
				
			} catch (SQLException ex) {
				
			}
		}
		return conectar;
	}

	public void consultarVideojuegoDB(String query) {
		datos.clear();
		try {
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3302/juegos?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ps = conectar.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
				titulo = rs.getString("titulo");
				año = rs.getString("año");
				portada = rs.getString("portada");
				genero = rs.getString("id_genero");
				plataforma = rs.getString("id_plataforma");
				puntuacion = rs.getString("puntuacion");
				descripcion = rs.getString("descripcion");
				requisitosM = rs.getString("requisitosM");
				requisitosR = rs.getString("requisitosR");
				nuevoDato = new DB(id, titulo, año, portada, genero, plataforma, puntuacion, descripcion, requisitosM, requisitosR, cantidadJuegos);
				datos.add(nuevoDato);

			}
			switch (plataforma) {
				case "1":
					plataforma = "Xbox One";
					break;
				case "2":
					plataforma = "Play Station 4";
					break;
				case "3":
					plataforma = "PC";
					break;
			}
			switch (genero) {
				case "1":
					genero = "Battle Royale";
					break;
				case "2":
					genero = "Shooter";
					break;
				case "3":
					genero = "MOBA";
					break;
				case "4":
					genero = "Accion-Aventura";
					break;
				case "5":
					genero = "Carreras";
					break;
				case "6":
					genero = "Survival Horror";
					break;
			}
			System.out.println("Juegos encontrados:");
			System.out.println("-------------------------------");
			System.out.println("ID: " + id + " Titulo: " + titulo + " Año: " + año + " Portada: " + portada + " Genero: " + genero + " Plataforma: " + plataforma + " Puntuacion: "
					+ puntuacion + " Descripcion: " + descripcion + " Requisitos minimos: " + requisitosM + " Requisitos Recomendados: " + requisitosR);

		} catch (Exception e) {
			System.out.println("Date base: error al intentar conectar con la base de datos.(  " + e + " )");
		} finally {
			try {
				conectar.close();
				rs.close();
				ps.close();
			} catch (SQLException ex) {
				Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void consultarVideojuegoDB(String query, boolean xd) {

		try {
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3302/juegos?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ps = conectar.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
				titulo = rs.getString("titulo");
				año = rs.getString("año");
				portada = rs.getString("portada");
				genero = rs.getString("id_genero");
				plataforma = rs.getString("id_plataforma");
				puntuacion = rs.getString("puntuacion");
				descripcion = rs.getString("descripcion");
				requisitosM = rs.getString("requisitosM");
				requisitosR = rs.getString("requisitosR");
			}
			switch (plataforma) {
				case "1":
					plataforma = "Xbox One";
					break;
				case "2":
					plataforma = "Play Station 4";
					break;
				case "3":
					plataforma = "PC";
					break;
			}
			switch (genero) {
				case "1":
					genero = "Battle Royale";
					break;
				case "2":
					genero = "Shooter";
					break;
				case "3":
					genero = "MOBA";
					break;
				case "4":
					genero = "Accion-Aventura";
					break;
				case "5":
					genero = "Carreras";
					break;
				case "6":
					genero = "Survival Horror";
					break;
			}
			System.out.println("Juegos encontrados:");
			System.out.println("-------------------------------");
			System.out.println("ID: " + id + " Titulo: " + titulo + " Año: " + año + " Portada: " + portada + " Genero: " + genero + " Plataforma: " + plataforma + " Puntuacion: "
					+ puntuacion + " Descripcion: " + descripcion + " Requisitos minimos: " + requisitosM + " Requisitos Recomendados: " + requisitosR);

		} catch (Exception e) {
			System.out.println("Date base: error al intentar conectar con la base de datos.(  " + e + " )");
		} finally {
			try {
				conectar.close();
				rs.close();
				ps.close();
			} catch (SQLException ex) {
				Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public int obtenerCantidadJuegos(String query) {
		try {
			cantidadJuegos = -1;
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3302/juegos?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			cs = conectar.createStatement();
			rs = cs.executeQuery(query);
			rs.next();
			cantidadJuegos = rs.getInt(1);
		} catch (SQLException ex) {
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				conectar.close();
				cs.close();
				rs.close();
			} catch (SQLException ex) {
				Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return cantidadJuegos;
	}

	public String obtenerIdJuego() {
		return id;
	}

	public String obtenerTituloJuego() {
		return titulo;
	}

	public String obtenerAñoJuego() {
		return año;
	}

	public String obtenerPortada() {
		return portada;
	}

	public String obtenerGenero() {
		return genero;
	}

	public String obtenerPlataforma() {
		return plataforma;
	}

	public String obtenerPuntuacion() {
		return puntuacion;
	}

	public String obtenerDescripcion() {
		return descripcion;
	}

	public String obtenerRm() {
		return requisitosM;
	}

	public String obtenerRr() {
		return requisitosR;
	}

	public ResultSet obtenerRs() {
		return rs;
	}

	public DB obtenerListaDatos(int i) {
		return (DB) datos.get(i);
	}
}
