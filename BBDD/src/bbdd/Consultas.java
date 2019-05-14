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
    
  
         /*Nuevas migraciones Africanas*/
   public ResultSet  Consultahotel_Nombre(String Localidad)
   {
         try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
           String query=   " SELECT hotel.Cod_hotel, hotel.Nombre, hotel.Capacidad, IFNULL(COUNT(reserva_hotel_habitacion.Cod_reserva), 0) AS popularidad FROM hotel LEFT JOIN reserva_hotel_habitacion ON hotel.Cod_hotel = reserva_hotel_habitacion.Cod_hotel LEFT JOIN reserva on reserva.Cod_reserva=reserva_hotel_habitacion.Cod_reserva INNER JOIN ubicacion on hotel.Cod_hotel=ubicacion.Cod_hotel where ubicacion.Localidad='"+Localidad+" GROUP BY hotel.Cod_hotel order by popularidad Desc,hotel.nombre Desc";
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
   
     public ResultSet  Consultacasa_Nombre(String Localidad, String Alojamiento,String fecha_inicio,String fecha_fin,int personas)
   {
         try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
                String query="SELECT casa.Cod_casa, casa.Nombre, casa.Capacidad,IFNULL(COUNT(reserva_casa.Cod_reserva), 0) AS popularidad FROM casa LEFT join reserva_casa on casa.Cod_casa=reserva_casa.Cod_casa left join reserva on reserva.Cod_reserva=reserva_casa.Cod_reserva inner join ubicacion on casa.Cod_casa=ubicacion.Cod_casa where casa.Cod_casa not in( SELECT DISTINCT reserva_casa.Cod_casa FROM reserva_casa INNER join reserva on reserva_casa.Cod_reserva=reserva.Cod_reserva WHERE '"+fecha_inicio+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida OR '"+fecha_fin+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida ) AND ubicacion.Localidad='"+Localidad+" AND casa.Capacidad>= '"+personas+"' GROUP BY casa.Cod_casa order by popularidad Desc,casa.nombre Desc ";
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
   
     public ResultSet  Consulta_apartamento_Nombre(String Localidad, String Alojamiento,String fecha_inicio,String fecha_fin,int personas)
   {
         try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
                String query="SELECT apartamento.Cod_apartamento, apartamento.Nombre, apartamento.Capacidad,IFNULL(COUNT(reserva_apartamento.Cod_reserva), 0) AS popularidad FROM apartamento LEFT join reserva_apartamento on apartamento.Cod_apartamento=reserva_apartamento.Cod_apartamento left join reserva on reserva.Cod_reserva=reserva_apartamento.Cod_reserva inner join ubicacion on apartamento.Cod_apartamento=ubicacion.Cod_apartamento where apartamento.Cod_apartamento not in( SELECT DISTINCT reserva_apartamento.Cod_apartamento FROM reserva_apartamento INNER join reserva on reserva_apartamento.Cod_reserva=reserva.Cod_reserva WHERE '"+fecha_inicio+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida OR '"+fecha_fin+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida ) AND ubicacion.Localidad='"+Localidad+" AND apartamento.Capacidad>= '"+personas+"' GROUP BY apartamento.Cod_apartamento order by popularidad Desc,apartamento.nombre Desc ";
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
   
      /*Nuevas migraciones Africanas*/
    public void InsertarReservaHotel(String entrada,String salida,String Cod_alojamiento,String DNI,String Cod_habitacion,double Precio)
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
    
    public void InsertarReservaCasaApartamento(String entrada,String salida,String Cod_alojamiento,String DNI,double Precio)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO reserva ( Fecha_entrada, Fecha_salida, Cod_alojamiento, DNI,Precio) VALUES ('"+entrada+"','"+salida+"','"+Cod_alojamiento+"','"+DNI+"','"+Precio+"')");      
            }
        }catch (Exception e){ 
            System.err.println("Hubo un Error"); 
            System.err.println(e.getMessage()); 
        } 
    } 
    
    public void InsertarBasesLegales(String DNI)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate(" INSERT INTO `Baseslegales` ( `DNI`, `Fecha_Insercion`) VALUES ('"+DNI+"',Now())");      
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
                  String query="SELECT habitacion.tipo as tipito,habitacion.Descripcion as descri FROM habitacion inner join alojamiento on alojamiento.Cod_alojamiento=habitacion.Cod_alojamiento where alojamiento.Nombre='"+cod_alojamiento+"' ";
                Statement sentencia = reg.createStatement(); 
                ResultSet resultado=sentencia.executeQuery(query); 
                return resultado;   
            }
        }catch (SQLException ex) 
        {
            System.err.println("Hubo un Error_2 ");
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
    
    public void BorrarUsuario(String us)
    {       
        try 
        {               
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
            }else{
                Statement st = reg.createStatement();
                st.executeUpdate("DELETE from usuario where DNI='"+us+"'");                 
            }
        }catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }       
    }
    
    public void BorrarReserva(String us)
    {       
        try 
        {               
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
            }else{
                Statement st = reg.createStatement();
                st.executeUpdate("DELETE from reserva where DNI='"+us+"'");                 
            }
        }catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }       
    }
    
    public void BorrarBasesLegales(String us)
    {       
        try 
        {               
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
            }else{
                Statement st = reg.createStatement();
                st.executeUpdate("DELETE from basesLegales where DNI='"+us+"'");                 
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
                String query="SELECT COUNT(Fecha_festivo) as numeroFestivos FROM festivo where Fecha_festivo BETWEEN '"+fecha_inicio+"' AND '"+fecha_fin+"'";
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
    
    public  ResultSet PrecioCasaApartamento(String localidad) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT COUNT(habitacion.Cod_habitacion) as numeroHab, habitacion.Precio FROM habitacion INNER JOIN alojamiento ON alojamiento.Cod_alojamiento = habitacion.Cod_alojamiento  WHERE alojamiento.Nombre like '"+localidad+"'";
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
    
    public  ResultSet ObtenerReserva(String dni) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT * FROM reserva WHERE dni like '"+dni+"'";
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
