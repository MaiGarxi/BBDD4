
package bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Consultas {
    
    Conectar con =new Conectar(); //SOLO ESTO AL INSTANCIAR, ES NECESARIA PARA HACER LA CONEXIÒN//
    Connection reg=con.conexion();// SOLO ESTO AL INSTACIAR, ES NECESARIA PARA HACER LA CONEXIÒN//
    //DEBES CREAR EL METODO DEL MISMO TIPO QUE LA CLASE DEL OBJETO QUE DEBES DEVOLVER//
        
    public ArrayList<String> ConsultaDestino()
    {
        ArrayList<String> destinos=new ArrayList();
          //SELECT         
        try{
            String query="SELECT nombre, localidad from hotel order by nombre";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query);            
            }  

        catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
        }       
           return destinos;
    }   
}
