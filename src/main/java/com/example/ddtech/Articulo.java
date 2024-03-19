package com.example.ddtech;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Articulo {
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("precio")
    private float precio;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("marca")
    private String marca;
    @JsonProperty("garantia")
    private String garantia;
    @JsonProperty("stock")
    private int stock;
    @JsonProperty("modelo")
    private String modelo;
    @JsonProperty("vigente")
    private boolean vigente =true;
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("image")
    private String image;

    public Articulo(String nombre, float precio, String descripcion, String sku, String marca, String garantia,
                    int stock, String modelo, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.sku = sku;
        this.marca = marca;
        this.garantia = garantia;
        this.stock = stock;
        this.modelo = modelo;
        vigente = true;
        this.tipo = tipo;
    }

    public Articulo() {}

    public String descripciones(int lineaDeDescripcion) {
        if (lineaDeDescripcion==1){
            if (descripcion.length()>20)
                return descripcion.substring(0, 20);
            return descripcion;
        }
        if (lineaDeDescripcion==2){
            if (descripcion.length()>20 && descripcion.length()>37)
                return descripcion.substring(20, 37)+"...";
            else if(descripcion.length()>20){
                return descripcion.substring(20);
            }
            return "";
        }
        return descripcion;
    }

    public String getImage(){
        return this.image;
    }
    public boolean buscar(String cadenaABuscar){
        String misDatos = nombre+precio+descripcion+sku+marca+garantia+modelo+tipo;
        return misDatos.contains(cadenaABuscar);
    }
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean getVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getTipo() {
        return tipo;
    }
    public void eliminar(){
        vigente = false;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", sku='" + sku + '\'' +
                ", marca='" + marca + '\'' +
                ", garantia='" + garantia + '\'' +
                ", stock=" + stock +
                ", modelo='" + modelo + '\'' +
                ", vigente=" + vigente +
                ", tipo='" + tipo + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
    /*
    public String toString() {
        String descripcionTruncate = descripcion;
        if (descripcion.length()>50){
            descripcionTruncate = descripcion.substring(0, 50) + "\n"+ descripcion.substring(50);
            if (descripcion.length()>100){
                descripcionTruncate = descripcionTruncate.substring(0, 97)+"...";
            }
        }
        return "[" + nombre + "] \n" + descripcionTruncate + "\n"+ "$ "+precio+" [" + stock
                + "]";
    }
    */

}