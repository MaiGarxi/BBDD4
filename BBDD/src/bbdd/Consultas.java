package bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Consultas {
    
    Conectar con =new Conectar(); 
    Connection reg=con.conexion();
        
    public  ResultSet  ConsultaDestino()
    {             
        try{
            String query="SELECT DISTINCT Localidad from hotel order by Localidad ASC";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query);     
            return resultado;
        }  

        catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
            return null; 
        }       
          
    }   
    
    public ResultSet ConsultaHoteles_Nombre(String Localidad)
    {      
        try{
            String query="SELECT Nombre from hotel where Localidad = '"+Localidad+"'";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query); 
            return resultado;
        }   catch (SQLException ex) 
            {
                System.err.println("Hubo un Error");
                return null;
            }               
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

    public  ResultSet hotel_para_reservar(String Nombre)
    {         
        try{
            String query="SELECT Cod_hotel from hotel where Nombre = '"+Nombre+"'";
            Statement sentencia = reg.createStatement(); 
            ResultSet resultado=sentencia.executeQuery(query); 
            return resultado;
            } 
        catch (SQLException ex) 
            {
                System.err.println("Hubo un Error ");
                return null;
            }          
    } 
    
    public  ResultSet ObtenerUsuario(String us,String pass) 
    {
        try 
        {
            String query="select * from usuario where DNI='"+us+"' AND Contraseña='"+pass+"'";
            Statement sentencia= reg.createStatement();
            ResultSet resultado=sentencia.executeQuery(query);
            return resultado;                         
        }catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"error"); 
            System.err.println("Hubo un Error ");
            System.err.println(e.getMessage());
        }
       return null;
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
    
    public void InsertarCliente( String dni,String nombre,String apellidos,String contraseña, String sexo, String fecha)
    {
        try {             
            Statement st = reg.createStatement();
            st.executeUpdate("INSERT INTO cliente (DNI, Nombre, Apellidos, Fecha_nac, Sexo, Contraseña) VALUES ('"+dni+"', '"+nombre+"', '"+apellidos+"','"+fecha+"','"+sexo+"', '"+contraseña+"')");             
        } catch (Exception e) { 
            System.err.println("cannot insert!"+fecha); 
            System.err.println(e.getMessage()); 
        } 
    }
}
