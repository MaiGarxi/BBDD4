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
    
    public ResultSet ConsultaAlojamiento_Nombre(String Localidad, String Alojamiento)
    {      
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
               String query = "SELECT alojamiento.Cod_alojamiento,IFNULL(COUNT(reserva.DNI),0) AS popularidad,alojamiento.Nombre FROM alojamiento LEFT JOIN reserva on reserva.Cod_alojamiento=alojamiento.Cod_alojamiento inner JOIN ubicacion on ubicacion.Cod_alojamiento=alojamiento.Cod_alojamiento WHERE alojamiento.Cod_alojamiento like  '"+Alojamiento+"' and ubicacion.Localidad = '"+Localidad+"' GROUP by alojamiento.Cod_alojamiento ORDER by popularidad DESC, alojamiento.Nombre DESC";
             //   String query="SELECT Cod_alojamiento ,Nombre from alojamiento where Cod_alojamiento in (Select Cod_alojamiento from ubicacion where ubicacion.Cod_alojamiento like '"+Alojamiento+"' and ubicacion.Localidad = '"+Localidad+"')";
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
    
 
    
    
    
    public void InsertarReserva(double Precio, int Cod_hotel, String entrada,String salida)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO `reserva`(`Precio`, `Fecha_entrada`, `Fecha_salida`, `Cod_hotel`) VALUES('"+Precio+"','"+entrada+"','"+salida+"','"+Cod_hotel+"')");      
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
