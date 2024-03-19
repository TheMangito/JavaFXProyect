package com.example.ddtech;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Venta implements java.io.Serializable {
    private LocalDate fecha;
    private String folio;
    private List<Detalle> detalles = new ArrayList<>();
    private Cliente clientes;

    public Venta(){}
    public Venta(Cliente cliente, LocalDate fecha, String folio) {
        this.fecha = fecha;
        this.folio = folio;
        this.clientes = cliente;
    }
    public Venta(Venta carrito, String folio){
        this.fecha = LocalDate.now();
        this.folio = folio;
        this.clientes = carrito.getCliente();

    }
    @Override
    public String toString() {
        return "\n    Folio   :        " + folio + "\n    Fecha   :        " + fecha + "\n    Cliente :        " + clientes.getNombre();
    }

    public Cliente getCliente() {
        return clientes;
    }

    public void setCliente(Cliente clientes) {
        this.clientes = clientes;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

}