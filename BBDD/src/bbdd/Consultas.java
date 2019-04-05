
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
          //SELECT         
        try{
            ArrayList<String> NombreHoteles=new ArrayList();
            String query="SELECT Nombre from hotel where Localidad = '"+Localidad+"'";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query); 
            while (resultado.next()){                
                NombreHoteles.add(resultado.getString("Nombre"));                 
            }
            return NombreHoteles;
            }  

        catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
        }       
           return null;
    } 
    
    public void InsertarReserva( int Cod_reserva, double Precio, int Cod_hotel)
    {
            try {  
            Statement st = reg.createStatement(); 
            st.executeUpdate("INSERT INTO `reserva`(`Cod_reserva`, `Precio`, `Cod_hotel`) VALUES ('"+Cod_reserva+"','"+Precio+"','"+Cod_hotel+"'))"); 
            
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 
    }
    
        public ArrayList<String> hotel_para_reservar(String Nombre)
    {
          //SELECT         
        try{
            ArrayList<String> NombreHoteles=new ArrayList();
            String query="SELECT Cod_hotel from hotel where Nombre = '"+Nombre+"'";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query); 
            while (resultado.next()){                
                NombreHoteles.add(resultado.getString("Cod_hotel"));                 
            }
            return NombreHoteles;
            }  

        catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
        }       
           return null;
    } 
        
}
