/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Caja;
import Modelo.Carrito;
import Modelo.Conexion;
import Modelo.SuperClassC;
import Vista.CatalogoProductos;
import Vista.CobroCaja2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * sí no reservo espacio en memoria me arrojará null pointer exception,
 * sí no completo los datos del constructor no puedo esperar que aparesca la ventana;
 * Sí no mando llamar la clase no puedo guardar un producto dentro de la cola,
 * Sí me estreso el programa no me sale, debo recordar respirar y calmarme.
 * Sí no pongo punto y coma se esperara el error ;(no necesariamente en la linea exacta);
 * Las propiedades de una o mas variables como es el caso de la caja pueden ser llamadas desde el main sin ninguna necesidad de contruirlas aparte (no duplicar)
 * Sí me sale que "Exception in thread "AWT-EventQueue-0" java.lang.NumberFormatException: For input string: "$ 0.0"" es por que le falta el try y catch debido a que la variable esta vacia;
 * Para actualizar un campo es necesario emplear la propiedad de la pila o cola set. 
 * 
 * @author AMPARO
 */
public class Contr_Catalogo implements ActionListener {
    DefaultTableModel modelo=new DefaultTableModel();
    CatalogoProductos p;
    CobroCaja2 cc;
    Conexion bd = new Conexion();
    
    
    //-----------------carrito------------>
    Carrito carrito = new Carrito();
    //------------------caja-------------->
    SuperClassC uC = new SuperClassC();
    Caja caja1;
    Caja caja2;
    
    
    
    public Contr_Catalogo(CatalogoProductos e, CobroCaja2 c, Caja c1, Caja c2, Carrito carrit) {
        try {
            p=e;
            cc=c;
            
            //-------------------------------------->
            modelo.addColumn("Nombre");
            modelo.addColumn("Costo");
            
            p.Tproductos.setModel(modelo);
            llenartablaproductos();
            //-------------------------------------->
            p.inicio.addActionListener(this);
            p.SacarCarrito.addActionListener(this);
            p.CargarCarrito.addActionListener(this);
            p.Carritolleno.addActionListener(this);
            //-------------------------------------->
            p.setTitle("PRODUCTOS DISPONIBLES");
            //-------------------------------------->
            caja1=c1;
            caja2=c2;
            carrito=carrit;//==
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Contr_Catalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void llenartablaproductos() throws SQLException{
        ResultSet rows=bd.datos();
        
         //actualizar y mostrar
         int f=modelo.getRowCount();
         for(int x=0; x<f;x++){
               
            modelo.removeRow(0);
          }
        
        Object datos[]= new Object[4];//num colum cons
         
         while(rows.next()){
             for (int i=0; i<2;i++){
                 datos[i]=rows.getObject(i+1);
             }
             modelo.addRow(datos);
         } 
    }
    
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JButton jb= (JButton)e.getSource();
        int contador =0;
        
        
        if(jb.equals(p.inicio)){
            cc.setVisible(true);
            p.setVisible(false);
        }
        
        if(jb.equals(p.CargarCarrito)){
                try{    
                    
                        
                         int row=p.Tproductos.getSelectedRow();//SELECCIONA EL NUMERO DE FILA
                         String nombre= p.Tproductos.getValueAt(row, 0).toString();//VARIABLE DE VALOR
                         String precio = p.Tproductos.getValueAt(row,1).toString();//VARIABLE DE VALOR
                         float precio2 = Float.valueOf(precio);//ASIGNACION DE PRECIO
                         //todo se va a llamr con la propiedad pilaproducto;
                         carrito.setProductoPila(carrito.insertarPila(nombre, precio2, carrito.getProductoPila()));//METE A LA PILA LOS PRODUCTOS;
                         System.out.println(carrito.Mostrar(carrito.getProductoPila()));//IMPRIME LOS PRODUCTOS COMPRADOS;

                }catch(Exception ex){
                     JOptionPane.showMessageDialog(null,"Ups!... Se ha incurrido en un error."+"\n"+"No Se ha seleccionado ningun producto de la lista " ,"ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
                } 
        }
       
        if(jb.equals(p.SacarCarrito)){
            carrito.setProductoPila(carrito.sacarPila(carrito.getProductoPila()));//SACA LOS PRODUCTOS DE LA PILA 
            System.out.println(carrito.Mostrar(carrito.getProductoPila()));//IMPRIME LOS PRODUCTOS ACTUALES DENTRO DE LA PILA,
        }
        
          
        
        if(jb.equals(p.Carritolleno)){
            try{
                    if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea formar carrito?")== JOptionPane.YES_OPTION){
                        int option= 0;//VARIABLE DE VALOR 
                        option = Integer.valueOf(JOptionPane.showInputDialog(null,"Elija la caja en la que desea enfilar el carrito"));
                          
                            if(option==1){
                              caja1.setFilaCarritos(caja1.insertarCola(carrito, caja1.getFilaCarritos()));//MANDAR CARRITO A FILA 1
                               Carrito carrito= new Carrito();
                               this.carrito=carrito;
//                               
                            }else{
                              caja2.setFilaCarritos(caja2.insertarCola(carrito, caja2.getFilaCarritos()));//MANDAR CARRITO A FILA 2
                                Carrito carrito= new Carrito();
                              this.carrito=carrito;

                            }
                                cc.setVisible(true);//MUESTRA VISIBLE LA VENTANA DE COBRO CAJA
                                cc.Carritosend2.setText(String.valueOf(caja2.contar(caja2.getFilaCarritos(), contador)));//ACTUALIZA ETIQUETAS DE NUMERO DE CARRITOS CAJA 2
                                cc.carritosEnd1.setText(String.valueOf(caja1.contar(caja1.getFilaCarritos(), contador)));//ACTUALIZA ETIQUETAS DE NUMERO DE CARRITOS CAJA 1
                                
                                p.setVisible(false);//ESCONDE LA VENTANA PRODUCTOS
                                    System.out.println("---------------------------Número actual de carritos-------------------------------------------");
                                    System.out.println("numero de carritos caja 1: "+caja1.contar(caja1.getFilaCarritos(),contador));//MUESTRA EL NUMERO ACTUAL DE CARRITOS
                                    System.out.println("numero de carritos caja 2: "+caja2.contar(caja2.getFilaCarritos(), contador));//MUESTRA EL NUMERO ACTUAL DE CARRITOS
                                    System.out.println("---------------------------Productos dentro del carrito----------------------------------------");
                                    System.out.println("caja 2 "+caja2.MostrarC(caja2.getFilaCarritos()));//MUESTRA PRODUCTOS:
                                    System.out.println("caja 1 "+caja1.MostrarC(caja1.getFilaCarritos()));//MUESTRA PRODUCTOS:
                                    System.out.println("----------------------------------------------------------------------");
                    }//if 

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Ups!... Se ha incurrido en un error."+"\n"+ ex);
   
            }
                    
        }
    }
    
}
