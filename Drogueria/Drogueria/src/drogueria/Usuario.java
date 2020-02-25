package drogueria;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Usuario {

	private String usuario, contra;
	private  ArrayList usuarios;
	private Usuario nuevoUsuario;


	public Usuario() {
		usuarios = new ArrayList<Usuario>();
		usuarios.add( new Usuario("Admin", "12345") );

	}

	public Usuario(String usuario, String contra) {
		this.usuario = usuario;
		this.contra = contra;
	}

	public void agregarUsuario(String usuario, String contra) {
		this.usuario = usuario;
		this.contra = contra;
		nuevoUsuario = new Usuario(usuario, contra);
		usuarios.add(nuevoUsuario);
	}
 	public void actualizarUsuario(String usuario, String contra) {
		this.usuario = usuario;
		this.contra = contra;
	}
        public void eliminarUsuario( int i ){
           usuarios.remove(i);
        }
	public String getUsuario() {
		return usuario;
	}

	public String getContra() {
		return contra;
	}

	public ArrayList getUsuarios() {
		return usuarios;
	}

        public String getInfo(){
            return "<html><font color = #747271 >Usuario: </font>" + getUsuario() + " <font color = #747271 >Contraseña:</font>" + getContra() + "</html>";
        }
	
	

}
