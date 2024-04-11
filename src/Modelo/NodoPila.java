/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author AMPARO
 */
public class NodoPila {
    NodoPila sig;
    Producto producto;

    public NodoPila(String nombre, float precio) {
       producto = new Producto(nombre, precio);
       sig=null;
    }

    public NodoPila getSig() {
        return sig;
    }

    public void setSig(NodoPila sig) {
        this.sig = sig;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    
}
