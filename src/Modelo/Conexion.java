/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMPARO
 */
public class Conexion {
      public ResultSet resultado =null;
         public Connection conn =null;
    
    public void conectarP() {    
    String cadenaConex="jdbc:postgresql://127.0.0.1/supermercado?"+"user=postgres&password=tecnica87";
    try {
        Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection(cadenaConex);
            //JOptionPane.showMessageDialog(null,"Conectado");
        }catch(Exception e){
        System.err.println("Error:"+e.getMessage()+e.getStackTrace());
        conn=null;
        }
    
    }
    
        public ResultSet datos() throws SQLException{//llena jcombox y listbox
        ResultSet resultados =null;
        Connection conns =null;
        String cadenaConex="jdbc:postgresql://127.0.0.1/supermercado?"+"user=postgres&password=tecnica87";
     
        try {
             Class.forName("org.postgresql.Driver");// TIPO DE CONEXION
             conns=DriverManager.getConnection(cadenaConex);
                  Statement sentencia = null;
                  sentencia = conns.createStatement();
                 resultados=sentencia.executeQuery("SELECT nombre, precio FROM producto;");
            } catch (ClassNotFoundException ex) {
                  Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
                  try {
                      conns.close();
                  } catch (SQLException ex) {
                      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
        
        return  resultados;
    }//metodo
    
}
