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
public class Caja {
    NodoCola filaCarritos;
    String nombre_cajera;
    int num_caja;
    float recaudacion;
    int numero_carros;
    Carrito salida;

    public Carrito getSalida() {
        return salida;
    }
    
    

    public Caja(String nombr, int num_caj) {
        filaCarritos=null;
        nombre_cajera=nombr;
        num_caja=num_caj;
        recaudacion=0;
        numero_carros=0;
    }

    public NodoCola getFilaCarritos() {
        return filaCarritos;
    }

    public void setFilaCarritos(NodoCola filaCarritos) {
        this.filaCarritos = filaCarritos;
    }

    public String getNombre_cajera() {
        return nombre_cajera;
    }

    public void setNombre_cajera(String nombre_cajera) {
        this.nombre_cajera = nombre_cajera;
    }

    public int getNum_caja() {
        return num_caja;
    }

    public void setNum_caja(int num_caja) {
        this.num_caja = num_caja;
    }

    public float getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(float recaudacion) {
        this.recaudacion = recaudacion;
    }

    public int getNumero_carros() {
        return numero_carros;
    }

    public void setNumero_carros(int numero_carros) {
        this.numero_carros = numero_carros;
    }
    
    //------------------------------------------------->
    
        
    public NodoCola insertarCola( Carrito nom,NodoCola i){//insertar pila l final-(push)
        
        NodoCola nuevoN= new NodoCola(nom);//nodo nuevo 
        
        if (i== null){// n representa lista
            i= nuevoN;
        }else {
            i.setSig(insertarCola(nom, i.getSig()));
            //manda llamar al metodo y al a lista se se llama siguiente.
        }
        return i;
    }
    
    public String MostrarC(NodoCola i){
        if (i==null)return  "Vacio";
        else 
            if(i.getSig()==null){
                return (i.getCarrito().getProductoPila().getProducto().getNombre()+ " # "+
                        i.getCarrito().getProductoPila().getProducto().getPrecio());
            }else {
                return (i.getCarrito().getProductoPila().getProducto().getNombre()+ " # "+
                        i.getCarrito().getProductoPila().getProducto().getPrecio()+ " "+ MostrarC(i.getSig()));                
            }
        
    }
    

   
    public int  buscarValor(NodoCola pila, String valor, int poinci, int contrador){
        if(pila==null){
            return-1;
        }else{
            if(contrador>=poinci){
                if (valor.equals(pila.getCarrito())){
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
    /*
    public String BuscarIndice(Nodo pila, int indice, int cont){
        if(pila==null){
            return "";
        }else{
                if(indice == cont){
                    return pila.getNombre();
                   
                 }else {
                    return BuscarIndice(pila.getSig(), indice, cont+1);
                 }
            }
    }
    
 */
  
     public int contar(NodoCola pila,int cont){
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
    
     
         public NodoCola sacarCola(NodoCola pila){
         if (pila!=null)
             
        if (pila.getSig()==null){
             salida= pila.getCarrito();
            pila=null;
        }else{
            pila.setSig(sacarCola(pila.getSig()));
        } 
          return pila;  
    }
    
    
}
 