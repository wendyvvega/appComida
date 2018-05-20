package com.example.supdude.appcomidita;

import android.graphics.Bitmap;

import java.io.Serializable;

public class datosLocal implements Serializable {
    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String nombreLocal;
    public String mail;
    public String contra;
    public String direccion;
    public String calle1;
    public double lat,longitud;
    public String tel1,tel2;
    public String horario;

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public datosLocal(String nombreLocal, String mail, String direccion, String tel1) {
        this.nombreLocal = nombreLocal;
        this.mail = mail;
        this.direccion = direccion;
        this.tel1 = tel1;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String calle2;
    public String numInt;
    public String numExt;
    public String sitio;
    public int logo;

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public datosLocal(String nombreLocal, String sitio, String direccion, int logo, String tel1, String horario){
        this.nombreLocal = nombreLocal;
        this.sitio = sitio;
        this.direccion = direccion;
        this.logo = logo;
        this.tel1 = tel1;
        this.horario = horario;
    }

    public datosLocal(String nombreLocal, String mail, String contra, String direccion, String calle1, String tel1, String tel2, String calle2, String numInt, String numExt, String sitio, String tipoLocal, int tipoCuenta) {
        this.nombreLocal = nombreLocal;
        this.mail = mail;
        this.contra = contra;
        this.direccion = direccion;
        this.calle1 = calle1;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.calle2 = calle2;
        this.numInt = numInt;
        this.numExt = numExt;
        this.sitio = sitio;
        this.tipoLocal = tipoLocal;
        this.tipoCuenta = tipoCuenta;
    }

    public String getTipoLocal() {
        return tipoLocal;

    }

        String desayuno="Desayuno";
    String comida = "Comida";
    String cena="Cena";
    String cafe="Café";
    String bares="bares";

    public datosLocal(String desayuno, String comida, String cena, String cafe, String bares) {
        this.desayuno = desayuno;
        this.comida = comida;
        this.cena = cena;
        this.cafe = cafe;
        this.bares = bares;
    }

    public String getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(String desayuno) {
        this.desayuno = desayuno;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }

    public String getBares() {
        return bares;
    }

    public void setBares(String bares) {
        this.bares = bares;
    }

    public void setTipoLocal(String tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public String tipoLocal;



    public int tipoCuenta;

    public int getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(int tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    public String getNombComida() {
        return nombComida;
    }

    public void setNombComida(String nombComida) {
        this.nombComida = nombComida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    String nombComida, descripcion,categoria;

<<<<<<< HEAD
   /* public datosLocal(String nombComida, String descripcion, String categoria, int precio) {
=======
    /*public datosLocal(String nombComida, String descripcion, String categoria, int precio) {
>>>>>>> 7ebdef73ae5209c23b198d5d2a675079050bb45e
        this.nombComida = nombComida;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }*/

    int precio;
}
