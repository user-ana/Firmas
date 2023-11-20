package com.uth.Ejercicio.models;

import android.graphics.Bitmap;

public class cSignature {
    private Bitmap firma_digital;
    private String descripcion;

    public cSignature(){}

    public Bitmap getFirma_digital() {
        return firma_digital;
    }

    public void setFirma_digital(Bitmap firma_digital) {
        this.firma_digital = firma_digital;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}