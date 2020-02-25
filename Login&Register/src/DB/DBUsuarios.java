package DB;

import java.sql.*;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.TabableView;

public class DBUsuarios {

	private Connection conectar;
	private ResultSet RS;
	private ResultSet RS2;
	private Statement ST;
	private PreparedStatement PS;
	private String TablaUsuarios;
	private String DatoObtenido = "";
	private String DatoNombreDB;
	private String DatoContraDB;
	private String DatoFotoDB;
	private String TablaRecordar;
	private String DatoNombreRecordar;
	private String DatoContraRecordar;
	private String DatoEstado;

	public DBUsuarios() {
	}

	public Connection obtenerConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			System.out.println("Data base: Se ha realizado la conexion exitosamente.");
		} catch (Exception e) {
			System.out.println("Date base: error al intentar conectar con la base de datos.(  " + e + " )");
		} finally {
			try {
				conectar.close();

			} catch (SQLException ex) {
				System.out.println("" + ex);
			}
		}
		return conectar;
	}

	public void ConectarDB(String Usuario, String Correo, String PN, String SN, String PA, String SA, String Telefono, String Dia, String Mes, String Año, String Sexo, String Pais, String Contra, String Foto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ST = conectar.createStatement();
			TablaUsuarios = "insert into usuario( nick, correo, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono,  dia, mes, año, sexo, pais, contraseña, foto )"
					+ " values ( '" + Usuario + "' ,'" + Correo + "' ,'" + PN + "' ,'" + SN + "' ,'" + PA + "' ,'" + SA + "','" + Telefono + "','" + Dia + "','" + Mes + "','" + Año + "','" + Sexo + "','" + Pais + "','" + Contra + "','" + Foto + "')";
			ST.executeUpdate(TablaUsuarios);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontro la base de datos. " + ex);
		}
	}

	public void ConsultarDB(String Campo, String Dato) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ST = conectar.createStatement();
			RS = ST.executeQuery("SELECT " + Campo + " FROM usuario WHERE " + Campo + " = '" + Dato + "'");
			while (RS.next()) {
				DatoObtenido = (String) RS.getString(Campo);
				System.out.println("En DB Primero " + RS.getString(Campo));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void ConsultarNombreContraFotoDB(String Usuario, String Contraseña) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}

			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ST = conectar.createStatement();
			RS = ST.executeQuery("SELECT nick, contraseña, foto FROM usuario WHERE nick = '" + Usuario + "' AND contraseña = '" + Contraseña + "' ");
			while (RS.next()) {
				DatoNombreDB = RS.getString("nick");
				DatoContraDB = RS.getString("contraseña");
				DatoFotoDB = RS.getString("foto");
				System.out.println("Datos encontrados: " + RS.getString("nick") + " " + RS.getString("contraseña") + " " + RS.getString("foto"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void ConsultarFotoDB(String Usuario, String Contraseña) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ST = conectar.createStatement();
			RS = ST.executeQuery("SELECT foto FROM usuario WHERE nick = '" + Usuario + "' AND contraseña = '" + Contraseña + "' ");
			while (RS.next()) {
				DatoNombreDB = RS.getString("nick");
				DatoContraDB = RS.getString("contraseña");
				DatoFotoDB = RS.getString("foto");
				System.out.println("Datos encontrados: " + RS.getString("nick") + " " + RS.getString("contraseña") + " " + RS.getString("foto"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void ConectarRecordarDB(String Usuario, String Contraseña, String Estado) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ST = conectar.createStatement();
			TablaRecordar = "UPDATE recordar SET nick ='" + Usuario + "' , contraseña = '" + Contraseña + "', estado = '" + Estado + "' ";
			ST.executeUpdate(TablaRecordar);
			RS = ST.executeQuery("SELECT nick, contraseña, estado FROM recordar");
			while (RS.next()) {
				DatoNombreRecordar = RS.getString("nick");
				DatoContraRecordar = RS.getString("contraseña");
				DatoEstado = RS.getString("estado");
				System.out.println(DatoNombreRecordar + " " + DatoContraRecordar + " " + DatoEstado);

			}
		} catch (SQLException ex) {
			Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void ConsultarRecordarDB() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
			}
			conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios?serverTimezone=" + TimeZone.getDefault().getID(), "root", "");
			ST = conectar.createStatement();
			RS = ST.executeQuery("SELECT nick, contraseña, estado FROM recordar");
			while (RS.next()) {
				DatoNombreRecordar = RS.getString("nick");
				DatoContraRecordar = RS.getString("contraseña");
				DatoEstado = RS.getString("estado");
				System.out.println(DatoNombreRecordar + " " + DatoContraRecordar + " " + DatoEstado);

			}
		} catch (SQLException ex) {
			Logger.getLogger(DBUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public String ObtenerDatoRepetido() {
		return DatoObtenido;
	}

	public String ObtenerDatoNombreDB() {
		return DatoNombreDB;
	}

	public String ObtenerDatoContraDB() {
		return DatoContraDB;
	}

	public String ObtenerDatoFotoDB() {
		return DatoFotoDB;
	}

	public String ObtenerNombreRecordar() {
		return DatoNombreRecordar;
	}

	public String ObtenerContraRecordar() {
		return DatoContraRecordar;
	}

	public String ObtenerEstadoRecordar() {
		return DatoEstado;
	}
}
