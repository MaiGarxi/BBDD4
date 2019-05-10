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
    
   public ResultSet  ConsultaAlojamiento_Nombre(String Localidad, String Alojamiento,String fecha_inicio,String fecha_fin,int personas)
   {
         try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
          String query =   " SELECT alojamiento.Cod_alojamiento,alojamiento.Nombre,alojamiento.Capacidad,IFNULL(COUNT(reserva.DNI),0) AS popularidad FROM alojamiento LEFT JOIN reserva on reserva.Cod_alojamiento=alojamiento.Cod_alojamiento INNER JOIN ubicacion ON ubicacion.Cod_alojamiento=alojamiento.Cod_alojamiento WHERE alojamiento.Cod_alojamiento NOT in(SELECT DISTINCT reserva.Cod_alojamiento FROM reserva INNER JOIN ubicacion ON reserva.Cod_alojamiento = ubicacion.Cod_alojamiento WHERE '"+fecha_inicio+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida OR '"+fecha_fin+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida) AND ubicacion.Localidad='"+Localidad+"' AND alojamiento.Cod_alojamiento like '"+Alojamiento+"' AND alojamiento.Capacidad>= '"+personas+"' GROUP by alojamiento.Cod_alojamiento ORDER by popularidad DESC, alojamiento.Nombre DESC ";
                Statement sentencia = reg.createStatement(); 
                ResultSet resultado=sentencia.executeQuery(query); 
                return resultado;
            }                   
        }catch (SQLException ex) 
        {
            System.err.println("Hubo un Error_2");
            return null;
        }   
   }
    
    public void InsertarReserva(String entrada,String salida,String Cod_alojamiento,String Cod_habitacion,String DNI,double Precio)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO reserva ( Fecha_entrada, Fecha_salida, Cod_alojamiento, DNI,Cod_habitacion,Precio) VALUES ('"+entrada+"','"+salida+"','"+Cod_alojamiento+"','"+DNI+"','"+Cod_habitacion+"','"+Precio+"')");      
            }
        }catch (Exception e){ 
            System.err.println("Hubo un Error"); 
            System.err.println(e.getMessage()); 
        } 
    }   

    public  ResultSet alojamiento_para_reservar(String Nombre)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                  String query="SELECT alojamiento.Cod_alojamiento as codigo from alojamiento where alojamiento.Nombre like '"+Nombre+"'";
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
    
        
     public  ResultSet habitaciones_casa_apar(String cod_alojamiento)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                  String query="SELECT habitacion.tipo,habitacion.Descripcion FROM habitacion WHERE habitacion.Cod_alojamiento in (SELECT alojamiento.Cod_alojamiento FROM alojamiento where alojamiento.Nombre='"+cod_alojamiento+"' )";
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
    
     public  ResultSet consultar_camas_disponibles(String nombre_hotel,String fecha_inicio,String fecha_fin)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                  String query="SELECT cama.* FROM habitacion INNER JOIN cama ON cama.Cod_habitacion=habitacion.Cod_habitacion WHERE habitacion.Cod_habitacion NOT in(SELECT DISTINCT reserva.Cod_habitacion FROM reserva INNER JOIN alojamiento ON reserva.Cod_alojamiento = alojamiento.Cod_alojamiento INNER JOIN habitacion ON alojamiento.Cod_alojamiento = habitacion.Cod_alojamiento WHERE '"+fecha_inicio+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida OR '"+fecha_fin+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida) AND habitacion.Cod_alojamiento in (select Cod_alojamiento from alojamiento where Nombre='"+nombre_hotel+ "') ORDER BY habitacion.Cod_habitacion DESC";
                Statement sentencia = reg.createStatement(); 
                ResultSet resultado=sentencia.executeQuery(query); 
                System.out.println("estoy en la consulta");
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
    
    public  ResultSet ComprobarFestivos(String fecha_inicio,String fecha_fin) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT COUNT(Fecha_festivo) FROM festivo where Fecha_festivo BETWEEN '"+fecha_inicio+"' AND '"+fecha_fin+"'";
                Statement sentencia= reg.createStatement();
                ResultSet resultado=sentencia.executeQuery(query);
                return resultado;   
            }                         
        }catch (Exception e) 
        { 
            System.err.println(e.getMessage());
        }
       return null;
    }
}
