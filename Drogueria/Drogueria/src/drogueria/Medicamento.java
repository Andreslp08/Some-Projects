package drogueria;

import java.util.ArrayList;

public class Medicamento {

    private String referencia, nombre, laboratorio, presentacion;
    private int precio;
    private ArrayList medicamentos = new ArrayList<Medicamento>();
    private Medicamento nuevoMedicamento;

    public Medicamento() {
    }

    public Medicamento(String referencia, String nombre, String laboratorio, int precio, String presentacion) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.presentacion = presentacion;
        this.precio = precio;
    }

    public void agregarMedicamento(String referencia, String nombre, String laboratorio, int precio, String presentacion) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.presentacion = presentacion;
        this.precio = precio;
        nuevoMedicamento = new Medicamento(getReferencia(), getNombre(), getLaboratorio(), getPrecio(), getPresentacion());
        medicamentos.add(nuevoMedicamento);
    }

    public void actualizarMedicamento( String referencia, String nombre, String laboratorio, int precio, String presentacion) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.presentacion = presentacion;
        this.precio = precio;
    }

    public void eliminarMedicamento(Medicamento medicamento) {
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).equals(medicamento)) {
                medicamentos.remove(i);
            }
        }
        System.out.println(medicamentos);
    }

    public Object obtenerMedicamento() {
       return "<html><font color = #747271 >Referencia:</font> " + getReferencia()+ " <font color = #747271 >Nombre:</font>" + getNombre() + " <font color = #747271 >Laboratorio:</font> " +  getLaboratorio() + " <font color = #747271 >Precio:</font> $" + getPrecio() + " <font color = #747271 >Presentacion:</font> " +  getPresentacion() + "</html>" ;
    }

    public Medicamento obtenerMedicamentos(int i) {
        return (Medicamento) medicamentos.get(i);
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public int getPrecio() {
        return precio;
    }

    public String  obtenerInfo(){
        return "<html><font color = #747271 >Referencia:</font> " + getReferencia()+ " <font color = #747271 >Nombre:</font> " + getNombre() + " <font color = #747271 >Laboratorio:</font> " +  getLaboratorio() + " <font color = #747271 >Precio:</font> $" + getPrecio()+ " <font color = #747271 >Presentacion:</font> " +  getPresentacion() + "</html>";
    }
    
    public ArrayList getArray(){
        return medicamentos;
    }
	
	public String getInfo(){
		return "<html><font color = #747271 >Referencia:</font> " + getReferencia()+ " <font color = #747271 >Nombre:</font> " + getNombre() +" <font color = #747271 >Precio:$</font> " + getPrecio()+"</html>" ;
	}
	
}
