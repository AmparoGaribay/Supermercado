/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Caja;
import Modelo.Carrito;
import Vista.CatalogoProductos;
import Vista.CobroCaja2;

/**
 *
 * @author AMPARO
 */
public class Supermercado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CatalogoProductos catalogo = new CatalogoProductos();
        CobroCaja2 cajak= new CobroCaja2();
        Caja caja1 = new Caja("Diana Flores", 1);
        Caja caja2= new Caja("Manuel Morales", 2);
        Carrito carrito= new  Carrito();
        
        
        //---------------------------------------------->
        
        Contr_Cobro control1 = new Contr_Cobro(cajak, catalogo, caja1, caja2,carrito);
        Contr_Catalogo control2= new Contr_Catalogo(catalogo, cajak,caja1, caja2,carrito);
        //---------------------------------------------->
        
        cajak.setVisible(true);
        
    }
    
}
