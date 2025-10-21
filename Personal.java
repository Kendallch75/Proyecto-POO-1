package Modelo;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Personal {

    private String nombre;
    private String cargo;
    private String jornada;
    private String cedula;
    private  String usuario; 
    private String password; 
    
    //Constructor vacio
    public Personal() {
        this.nombre = ""; 
        this.cargo = "";
        this.jornada = "";
        this.cedula = "";
        this.usuario = "";
        this.password = "";
    }
    //Constructor parametrico
    public Personal(String nombre, String cargo, String jornada, String cedula, String usuario, String password){
        this.nombre = nombre; 
        this.cargo = cargo;
        this.jornada = jornada;
        this.cedula = cedula;
        this.usuario = usuario;
        this.password = password;
    }

   //Setters y Getters 
    
    public void setNombre(String nombre) {
        this.nombre = nombre; 
    }
    public String getNombre() {
        return nombre; 
    }
    public void setCargo(String cargo) {
        this.cargo = cargo; 
    }
    public String getCargo() {
        return cargo; 
    }
    public void setJornada(String jor) {
        this.jornada = jor; 
    }
    public String getJornada() {
        return jornada; 
    }
    public void setUsuario(String usu) {
        this.usuario = usu; 
    }
    public String getUsuario() {
        return usuario; 
    }
    public void setPassword(String pass) {
        this.password = pass; 
    }
    public String getPassword() {
        return password; 
    }
}
