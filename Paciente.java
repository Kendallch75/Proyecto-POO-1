package proye1;

public class Paciente {

    private String nombre;
    private int edad;
    private Expediente expe;
    
    public Paciente(String name, int age) {
        this.nombre = name;
        this.edad = age;
        this.expe = new Expediente(name); 
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEdad(int age) {
        this.edad = age;
    }

    public int getEdad() {
        return edad;
    }

    public Expediente getExpediente() {
        return expe;
    }

}
