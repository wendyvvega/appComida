package com.example.supdude.appcomidita;

public class datosMenu {
    public String nombrePlato;
    public String descripcion;
    public double precio;
    public int imagenComida;
    public int cantidad;

    public datosMenu(){
        nombrePlato="";
        descripcion="";
        precio= 0.0;
        imagenComida=0;
        cantidad=0;
    }

    public datosMenu(String nombrePlato, String descripcion, double precio, int imagenComida, int cantidad){
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenComida = imagenComida;
        this.cantidad = cantidad;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImagenComida() {
        return imagenComida;
    }

    public void setImagenComida(int imagenComida) {
        this.imagenComida = imagenComida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
