
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
          //SELECT         
        try{
            ArrayList<String> destinos=new ArrayList();
            String query="SELECT DISTINCT Localidad from hotel order by Localidad ASC";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query);     
            while (resultado.next()){                
                destinos.add(resultado.getString("Localidad"));                 
            }
            return destinos;
        }  

        catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
        }       
          return null; 
    }   
    
    public ArrayList<String> ConsultaHoteles_Nombre(String Localidad)
    {
        ArrayList<String> NombreHoteles=new ArrayList();
          //SELECT         
        try{
            String query="SELECT Nombre from hotel where Localidad = '"+Localidad+"'";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query);            
            }  

        catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
        }       
           return NombreHoteles;
    } 
        
}
