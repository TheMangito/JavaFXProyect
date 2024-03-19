package com.example.ddtech;

import java.util.*;
public class Detalle implements java.io.Serializable{
    private double precio;
    private Articulo articulo;
    private int cantidad;

    public Detalle(){

    }
    public Detalle(double precio, Articulo articulo, int cantidad){
        this.precio = precio;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }
    public Detalle(Detalle detalle){
        this.precio = detalle.getPrecio();
        this.articulo = detalle.getArticulo();
        this.cantidad = detalle.getCantidad();
    }
    public String mostrar() {
        return "$"+precio + "  " + articulo +     "  [" + cantidad+"]";
    }
    public void mostrarf(){
        System.out.printf("%-35s %-25s %-25s %n",articulo,cantidad, precio);
    }
    public void capturar(Articulo[] articulo, int cArticulos) {
        Scanner dato = new Scanner(System.in);
        System.out.print("Precio            : ");
        precio = dato.nextFloat();
        dato.nextLine();
        System.out.print("Cantidad          : ");
        cantidad = dato.nextInt();
        dato.nextLine();
        System.out.println("Selecciona uno de los Articulos disponibles: ");
        capturarArticulo(articulo, cArticulos);

    }
    public void modificar(Articulo[] articulo, int cArticulos) {
        Scanner dato = new Scanner(System.in);
        System.out.println("¿Qué desea modificar? \n 1.-Precio    2.-Cantidad    3.-Articulo");
        int opcion = dato.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Introduzca el nuevo precio");
                precio = dato.nextInt();
                dato.nextLine();
                break;
            case 2:
                System.out.println("Introduzca la cantidad");
                cantidad = dato.nextInt();
                dato.nextLine();
                break;
            case 3:
                capturarArticulo(articulo, cArticulos);
                break;
        }
    }
    public void capturarArticulo(Articulo[] articulo, int cArticulos){
        Scanner dato = new Scanner(System.in);
        System.out.println("---- Articulos ----");
        for (int i = 0; i < cArticulos; i++) {
            if (articulo[i] != null && articulo[i].getVigente()) {
                System.out.println((i + 1) + ".- " + articulo[i]);
            }
        }
        int opcion = dato.nextInt();
        dato.nextLine();
        this.articulo = articulo[opcion-1];
    }

    public String toString(){
        return articulo+" "+cantidad+" "+precio;
    }
    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public Articulo getArticulo(){
        return articulo;
    }
    public void setArticulo(Articulo articulo){
        this.articulo = articulo;
    }
    public int getCantidad(){
        return cantidad;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}