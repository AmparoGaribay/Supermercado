/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Caja;
import Modelo.Carrito;
import Modelo.Producto;
import Modelo.SuperClassC;
import Vista.CatalogoProductos;
import Vista.CobroCaja2;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author AMPARO
 */
public class Contr_Cobro implements ActionListener{
    CobroCaja2 cajaCobro = new CobroCaja2();
    CatalogoProductos catalogo;
    SuperClassC su= new SuperClassC();
    Caja caja1;
    Caja caja2;
      int contador =0;
        Carrito carrito= new Carrito();
     
    //----------------------------->
   
    
    public Contr_Cobro(CobroCaja2 e, CatalogoProductos p, Caja c1, Caja c2, Carrito carrit) {
        cajaCobro =e;
        catalogo=p;

        //------------------------------------------------>
         cajaCobro.ListaProduto2.addActionListener(this);
        cajaCobro.Salir.addActionListener(this);
        cajaCobro.Cobrar1.addActionListener(this);
        cajaCobro.Cobro2.addActionListener(this);
        cajaCobro.CorteCaja.addActionListener(this);
        cajaCobro.CorteCaja1.addActionListener(this);
         //------------------------------------------------>
        
         //------------------------------------------------>

        
        cajaCobro.setTitle("SUPERMERCADO");
         //------------------------------------------------>
         caja1=c1;
         caja2=c2;
         carrito = carrit;
         cargar_datos();
         
         
        
    }

     public void cargar_datos(){   //carga los datos desde el constructor.
       
            cajaCobro.Ncajera1.setText(caja1.getNombre_cajera());
            cajaCobro.Ncaja1.setText(Integer.toString(caja1.getNum_caja()));
            cajaCobro.Ncajera2.setText(caja2.getNombre_cajera());
            cajaCobro.Ncaja2.setText(Integer.toString(caja2.getNum_caja()));
            cajaCobro.carritosEnd1.setText(String.valueOf(caja1.contar(caja1.getFilaCarritos(),contador )));//numero carritos formados
            cajaCobro.Carritosend2.setText(String.valueOf(caja2.contar(caja2.getFilaCarritos(), contador)));//numero de carritos formados
            cajaCobro.Recaudacion1.setText(String.valueOf("$ "+ caja1.getRecaudacion()));//dinero recaudado
            cajaCobro.recaudacion2.setText(String.valueOf("$ "+caja2.getRecaudacion()));//dinero recaudado
            
     }
     
     
     
     

     
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
            JButton Jb= (JButton)e.getSource();
            
            if(Jb.equals(cajaCobro.ListaProduto2)){//permite ir atras o adelante;
                catalogo.setVisible(true);
                cajaCobro.setVisible(false);
               
                String cont= "Carrito"+String.valueOf(contador);
               
                
                
            }
            
            
            
            if(Jb.equals(cajaCobro.Salir)){
                if(JOptionPane.showConfirmDialog(null, "¿Desea Salir?")== JOptionPane.YES_OPTION){
                     System.exit(0);  
                }
            }
            
            

            if(Jb.equals(cajaCobro.Cobrar1)){
                try{
                    int n_=caja1.getFilaCarritos().getCarrito().contar(carrito.getProductoPila(), contador);
                    float valor_ac=0;
                    caja1.setFilaCarritos(caja1.sacarCola(caja1.getFilaCarritos()));//saca un carrito de la fila
                    Carrito carActual= caja1.getSalida();
                    String ticket=""; 
                    int proCar= carActual.contar(carActual.getProductoPila(),0);
                    
                    for(int i=0; i<proCar;i++){        
                               carActual.setProductoPila(carActual.sacarPila(carActual.getProductoPila()));//saca los productos;
                                Producto proActual= carActual.getSalida();
                                float valor_actual=proActual.getPrecio();
                                String descrip=proActual.getNombre();
                                valor_ac=valor_actual+valor_ac;
                                ticket+= descrip+" .................... "+valor_actual+"\n";
                     }
                            JOptionPane.showMessageDialog(null,"--------------------------------------------"+"\n"
                                                             + "**************CAJA 1**************"+"\n"
                                                             + "*IMPORTE:................................"+"\n"
                                                              +ticket+"\n"
                                                             + "************************************", "<<CUENTA>>",JOptionPane.INFORMATION_MESSAGE );
                            cajaCobro.Recaudacion1.setText(String.valueOf("$ " +valor_ac));
                            cajaCobro.carritosEnd1.setText(String.valueOf(caja1.contar(caja1.getFilaCarritos(),contador )));//aclualiza la etiqueta de carritos formados                            
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"No hay carritos en fila Caja 1, por favor forme un carrito. " ,"ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
                }
            }//if
            
            
            
            if(Jb.equals(cajaCobro.Cobro2)){
                try{
                    int n_=caja2.getFilaCarritos().getCarrito().contar(carrito.getProductoPila(), contador);
                    float valor_ac=0;
                    caja2.setFilaCarritos(caja2.sacarCola(caja2.getFilaCarritos()));//saca un carrito de la fila
                    Carrito carActual= caja2.getSalida();
                    String ticket=""; 
                    int proCar= carActual.contar(carActual.getProductoPila(),0); 
                    
                        for(int i=0; i<proCar;i++){        
                                   carActual.setProductoPila(carActual.sacarPila(carActual.getProductoPila()));//saca los productos;
                                    Producto proActual= carActual.getSalida();
                                    float valor_actual=proActual.getPrecio();
                                    String descrip=proActual.getNombre();
                                    valor_ac=valor_actual+valor_ac;
                                    ticket+= descrip+" .................... "+valor_actual+"\n";
                        }
                        
                            cajaCobro.recaudacion2.setText(String.valueOf("$ " +valor_ac));
                            cajaCobro.Carritosend2.setText(String.valueOf(caja2.contar(caja2.getFilaCarritos(),contador )));//aclualiza la etiqueta de carritos formados  
                    
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"No hay carritos en fila Caja 2, por favor forme un carrito. " ,"ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
                }    
            }
            
            
            
            if(Jb.equals(cajaCobro.CorteCaja)){
               try{
                   float recaudo_total= Float.valueOf(cajaCobro.Recaudacion1.getText());//guarda el recaudo total
                    JOptionPane.showMessageDialog(null, "Se han recaudado $"+recaudo_total+" hasta el momento");
                    cajaCobro.Recaudacion1.setText(" $ 0.0");//pone en 0.0 la caja
               }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"La Caja 1 Actualmente no ha recaudado fondos" ,"ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
               }
            }
            
            
            
            if(Jb.equals(cajaCobro.CorteCaja1)){
                try{
                    float recaudo_total2= Float.valueOf(cajaCobro.recaudacion2.getText());
                    JOptionPane.showMessageDialog(null, "Se han recaudado $"+recaudo_total2+" hasta el momento");
                    cajaCobro.recaudacion2.setText("$ 0.0");                    
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"La Caja 2 Actualmente no ha recaudado fondos" ,"ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
                }
            }
            
            
            
           
        
    }//accionperformed
    
}//actionlistener
