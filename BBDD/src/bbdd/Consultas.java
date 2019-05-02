package bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Consultas {
    
    public static Conectar conex;
    public static Connection reg;

    public Consultas() {
        Conectar conexi=new Conectar();
        conex=conexi.Devolver();
        reg=conex.conexion();
    }
        
    public  ResultSet  ConsultaDestino()
    {             
        try{
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                
         return null;
            }
            else{
                String query="SELECT DISTINCT Localidad from ubicacion order by Localidad ASC";
                Statement sentencia = reg.createStatement(); 
                ResultSet resultado=sentencia.executeQuery(query);     
                return resultado;
            }
        }  

        catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
            return null; 
        }             
    }   
    
    public ResultSet ConsultaHoteles_Nombre(String Localidad)
    {      
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
                String query="SELECT Nombre from hotel INNER JOIN ubicacion ON hotel.Cod_hotel = ubicacion.Cod_hotel where Localidad = '"+Localidad+"'";
                Statement sentencia = reg.createStatement(); 
                ResultSet resultado=sentencia.executeQuery(query); 
                return resultado;
            }                   
        }catch (SQLException ex) 
        {
            System.err.println("Hubo un Error");
            return null;
        }               
    } 
    
    public void InsertarReserva(double Precio, int Cod_hotel, String entrada,String salida, String dni, int Cod_habitacion)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO `reserva`(`Precio`, `Fecha_entrada`, `Fecha_salida`, `Cod_hotel`, `DNI`, `Cod_habitacion`) VALUES('"+Precio+"','"+entrada+"','"+salida+"','"+Cod_hotel+"','"+dni+"','"+Cod_habitacion+"')");      
            }
        }catch (Exception e){ 
            System.err.println("Hubo un Error"); 
            System.err.println(e.getMessage()); 
        } 
    }   

    public  ResultSet hotel_para_reservar(String Nombre)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT Cod_hotel from hotel where Nombre = '"+Nombre+"'";
                Statement sentencia = reg.createStatement(); 
                ResultSet resultado=sentencia.executeQuery(query); 
                return resultado;
            }
        }catch (SQLException ex) 
        {
            System.err.println("Hubo un Error ");
            return null;
        }          
    } 
    
    public  ResultSet ObtenerUsuario(String us,String pass) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="select * from usuario where DNI='"+us+"' AND Contraseña='"+pass+"'";
                Statement sentencia= reg.createStatement();
                ResultSet resultado=sentencia.executeQuery(query);
                return resultado;   
            }                         
        }catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"error"); 
            System.err.println("Hubo un Error ");
            System.err.println(e.getMessage());
        }
       return null;
    }
    
    public  ResultSet ComprobarUsuario(String dni) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="select * from usuario where DNI='"+dni+"'";
                Statement sentencia= reg.createStatement();
                ResultSet resultado=sentencia.executeQuery(query);
                return resultado;   
            }                         
        }catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"error"); 
            System.err.println("Hubo un Error ");
            System.err.println(e.getMessage());
        }
       return null;
    }
    
    public void BorrarUsuario(String us,String pass)
    {       
        try 
        {               
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
            }else{
                Statement st = reg.createStatement();
                st.executeUpdate("DELETE from usuario where DNI='"+us+"' AND contraseña='"+pass+"'");                 
            }
        }catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }       
    }
        
    public void ActualizarUsuario( String dni,String nombre,String apellidos, String fecha, String sexo, String contraseña)
    {
        try 
        {          
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
            }else{
                Statement st = reg.createStatement();
                st.executeUpdate("UPDATE `usuario` SET `Nombre`='"+nombre+"',`Apellidos`='"+apellidos+"',`Fecha_nac`='"+fecha+"',`Sexo`='"+sexo+"',`Contraseña`='"+contraseña+"' WHERE DNI='"+dni+"'");           
            }            
        } catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }        
    }  
    
    public void InsertarUsuario( String dni,String nombre,String apellidos,String contraseña, String sexo, String fecha)
    {
        try 
        {   
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
            }else{
            Statement st = reg.createStatement();
            st.executeUpdate("INSERT INTO usuario (DNI, Nombre, Apellidos, Fecha_nac, Sexo, Contraseña) VALUES ('"+dni+"', '"+nombre+"', '"+apellidos+"','"+fecha+"','"+sexo+"', '"+contraseña+"')");             
            }
        } catch (Exception e) { 
            System.err.println("cannot insert!"+fecha); 
            System.err.println(e.getMessage()); 
        } 
    }
    
  
}
