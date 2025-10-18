package com.mycompany.proyectopoo1;


public class InsumoMedico {

    public InsumoMedico() {
    }
    
    public InsumoMedico(String nombre, String dosis, int precio, int cantidad, boolean soloMedico) {
    this.nombre = nombre;
    this.dosis = dosis;
    this.precio = precio;
    this.cantidad = cantidad;
    this.soloMedico = soloMedico;
}

    private String nombre;
    private String dosis;
    private int precio;
    private int cantidad;
    private boolean soloMedico;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isSoloMedico() {
        return soloMedico;
    }

    public void setSoloMedico(boolean soloMedico) {
        this.soloMedico = soloMedico;
    }
    
}
