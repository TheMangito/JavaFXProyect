package com.example.ddtech;

import java.util.*;

public class Cliente implements java.io.Serializable{

    private String telefono;
    private String correoElectronico;
    private String domicilio;
    private String rfc;
    private boolean vigente;
    private String nombre;
    private String usuario;
    private String password;
    private Venta carrito;

    public Cliente(String telefono, String correoElectronico, String domicilio, String rfc, boolean vigente, String nombre, String usuario, String password, Venta carrito) {
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.domicilio = domicilio;
        this.rfc = rfc;
        this.vigente = vigente;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.carrito = carrito;
    }

    public String quienSoy(){
        return "Cliente";
    }

    public Cliente() {
    }
    @Override
    public String toString() {
        String usuarioTruncate;
        if (usuario.length()>33){
            usuarioTruncate = usuario.substring(1, 50)+"...";
        }else{
            usuarioTruncate = usuario;
        }
        return usuarioTruncate;
    }
    public Venta getCarrito(){
        return carrito;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCosto(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public boolean isVigente() {
        return vigente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
