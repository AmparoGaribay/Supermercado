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
public class Carrito {//metodos pila 
    NodoPila productoPila;
    Producto salida;

    public Producto getSalida() {
        return salida;
    }
    
    

    public NodoPila getProductoPila() {
        return productoPila;
    }

    public void setProductoPila(NodoPila productoPila) {
        this.productoPila = productoPila;
    }
   
    
     public NodoPila insertarPila(String producto, float precio, NodoPila i){//insertar pila l final-(push)
        
        NodoPila Nw= new NodoPila(producto, precio);
        
        if (i== null){// n representa lista
            i= Nw;
        }else {
               
            i.setSig(insertarPila(producto, precio, i.getSig()));
            //manda llamar al metodo y al a lista se se llama siguiente.
        }
        return i;
    }
    
    
       public String Mostrar(NodoPila i){
           if (i==null) return "";
           else 
           if (i.getSig()==null){
                    return(i.getProducto().getNombre()+"#"+
                           i.getProducto().getPrecio());
          }else{
               return(i.getProducto().getNombre()+"#"+
                      i.getProducto().getPrecio()+" "+Mostrar(i.getSig()));
               
           } 
       } 
        
   
    public int  buscarValor(NodoPila pila, String valor, int poinci, int contrador){
        if(pila==null){
            return-1;
        }else{
            if(contrador>=poinci){
                if (valor.equals(pila.getProducto().getNombre())){
                    return contrador;
                }else{
                    contrador=buscarValor(pila.getSig(), valor, poinci, contrador+1);
                }
                   
            }else {
                contrador=buscarValor(pila.getSig(), valor, poinci, contrador+1);
            }
            return contrador;
        }
    }
    
    public String BuscarIndice(NodoPila pila, int indice, int cont){
        if(pila==null){
            return "";
        }else{
                if(indice == cont){
                    return pila.getProducto().getNombre();
                   
                 }else {
                    return BuscarIndice(pila.getSig(), indice, cont+1);
                 }
            }
    }
    
 
  
     public int contar(NodoPila pila,int cont){
        if (pila!= null) 
            
         if(pila.sig!=null){
            //return -1;
            cont=contar(pila.getSig(), cont+1);
            return cont ;
        }else{
              return cont+1;
            }
        return cont;
    }
     
     public NodoPila sacarPila(NodoPila pila){
         if (pila!=null)
             
        if (pila.getSig()==null){//se
            salida= pila.getProducto();
            pila=null;
        }else{
            pila.setSig(sacarPila(pila.getSig()));
        } 
          return pila;  
    }
   
   
    
    
    
    
}
