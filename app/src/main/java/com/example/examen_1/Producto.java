package com.example.examen_1;

import java.io.Serializable;

public class Producto implements Serializable {
    private String marca;
    private String tipo;
    private String serie;
    private float precio;
    private float descuento;

    Producto() {}

    Producto(String serie, String marca, float  precio, float descuento , String tipo ) {
        this.serie = serie;
        this.marca = marca;
        this.precio = precio;
        this.descuento = descuento;
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
}
