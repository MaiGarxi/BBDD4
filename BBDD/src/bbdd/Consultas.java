
package bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Consultas {
    
    Conectar con =new Conectar(); 
    Connection reg=con.conexion();
        
    public ArrayList<String> ConsultaDestino()
    {             
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
        try{
            ArrayList<String> NombreHoteles=new ArrayList();
            
            String query="SELECT Nombre from hotel where Localidad = '"+Localidad+"'";
            
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query); 
            while (resultado.next()){                
                NombreHoteles.add(resultado.getString("Nombre"));                 
            }
            return NombreHoteles;
        }   catch (SQLException ex) 
            {
                System.err.println("Hubo un Error");
            }       
        return null;
    } 
    
    public void InsertarReserva(double Precio, int Cod_hotel)
    {
        try {  
            Statement st = reg.createStatement(); 
            
            st.executeUpdate("INSERT INTO reserva(Precio, Cod_hotel) VALUES ('"+Precio+"','"+Cod_hotel+"')");         
        
        }   catch (Exception e) { 
                System.err.println("Hubo un Error"); 
                System.err.println(e.getMessage()); 
            } 
    }   

    public ArrayList<String> hotel_para_reservar(String Nombre)
    {         
        try{
            ArrayList<String> NombreHoteles=new ArrayList();

            String query="SELECT Cod_hotel from hotel where Nombre = '"+Nombre+"'";

            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query); 
            while (resultado.next()){                
                NombreHoteles.add(resultado.getString("Cod_hotel"));                 
            }
            return NombreHoteles;
        }   catch (SQLException ex) 
            {
                System.err.println("Hubo un Error ");
            }       
        return null;
    } 
    
    public void Usuario(String us,String pass)
    {
        try 
        {
            String query="select DNI, Contraseña from cliente where DNI='"+us+"' AND contraseña='"+pass+"'";
            Statement sentencia= reg.createStatement();
            ResultSet resultado=sentencia.executeQuery(query);
                         
            while (resultado.next())
            {
                String dni=resultado.getString("DNI");
                String contrasena=resultado.getString("contraseña");
            }                            
        }catch (Exception e)
            {
                System.err.println("Hubo un Error ");
                System.err.println(e.getMessage());
            }
    }
    
    public  void BorrarCliente(String us,String pass)
    {       
        try {               
            Statement st = reg.createStatement();
            st.executeUpdate("DELETE from cliente where DNI='"+us+"' AND contraseña='"+pass+"'");                       
        } catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }       
    }
        
    public void ActualizarCliente( String dni,String nombre,String apellidos, String fecha, String sexo, String contraseña)
    {
        try {             
            Statement st = reg.createStatement();
            st.executeUpdate("UPDATE `cliente` SET `Nombre`='"+nombre+"',`Apellidos`='"+apellidos+"',`Fecha_nac`='"+fecha+"',`Sexo`='"+sexo+"',`Contraseña`='"+contraseña+"' WHERE DNI='"+dni+"'");           
            reg.close(); 
            
        } catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }        
    }       
}
