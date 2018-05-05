package com.example.supdude.appcomidita;

public class local {

    private String nombre;
    private String telefono;
    private String direccion;
    private String sitioWeb;

    public local(){
        nombre = "";
        telefono = "";
        direccion = "";
        sitioWeb = "";
    }

    public local(String n, String t, String d, String s){
        nombre = n;
        telefono = t;
        direccion = d;
        sitioWeb = s;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
}
