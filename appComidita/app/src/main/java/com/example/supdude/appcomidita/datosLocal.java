package com.example.supdude.appcomidita;

import android.graphics.Bitmap;

public class datosLocal {
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

    public datosLocal(String nombreLocal, String mail, String contra, String direccion, String calle1, String calle2, String numInt, String numExt, String sitio, int tipoCuenta) {
        this.nombreLocal = nombreLocal;
        this.mail = mail;
        this.contra = contra;
        this.direccion = direccion;
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.numInt = numInt;
        this.numExt = numExt;
        this.sitio = sitio;
        this.tipoCuenta = tipoCuenta;
    }

    public int tipoCuenta;

    public int getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(int tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}