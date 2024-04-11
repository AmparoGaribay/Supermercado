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
public class NodoCola {//carritos
    Carrito carrito;
    NodoCola sig;

    public NodoCola(Carrito casr) {
        carrito=casr;
        sig=null;
    }
    
    
    

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public NodoCola getSig() {
        return sig;
    }

    public void setSig(NodoCola sig) {
        this.sig = sig;
    }
    
    
    
    
}
