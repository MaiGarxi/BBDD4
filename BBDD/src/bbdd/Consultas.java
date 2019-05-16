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
           String query=   " SELECT hotel.Nombre AS Nombre, IFNULL(COUNT(reserva_hotel_habitacion.Cod_reserva), 0) AS popularidad FROM hotel LEFT JOIN reserva_hotel_habitacion ON hotel.Cod_hotel = reserva_hotel_habitacion.Cod_hotel LEFT JOIN reserva on reserva.Cod_reserva=reserva_hotel_habitacion.Cod_reserva INNER JOIN ubicacion on hotel.Cod_hotel=ubicacion.Cod_hotel where ubicacion.Localidad='"+Localidad+"' GROUP BY hotel.Cod_hotel order by popularidad Desc,hotel.nombre Desc";
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
   
    public ResultSet  Consultacasa_Nombre(String Localidad,String fecha_inicio,String fecha_fin,int personas)
    {
         try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
                String query="SELECT casa.Cod_casa, casa.Nombre, casa.Capacidad,IFNULL(COUNT(reserva_casa.Cod_reserva), 0) AS popularidad FROM casa LEFT join reserva_casa on casa.Cod_casa=reserva_casa.Cod_casa left join reserva on reserva.Cod_reserva=reserva_casa.Cod_reserva inner join ubicacion on casa.Cod_casa=ubicacion.Cod_casa where casa.Cod_casa not in( SELECT DISTINCT reserva_casa.Cod_casa FROM reserva_casa INNER join reserva on reserva_casa.Cod_reserva=reserva.Cod_reserva WHERE '"+fecha_inicio+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida OR '"+fecha_fin+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida ) AND ubicacion.Localidad='"+Localidad+"' AND casa.Capacidad>= '"+personas+"' GROUP BY casa.Cod_casa order by popularidad Desc,casa.nombre Desc ";
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
   
    public ResultSet  Consulta_apartamento_Nombre(String Localidad,String fecha_inicio,String fecha_fin,int personas)
    {
         try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }
            else{
                String query="SELECT apartamento.Cod_apartamento, apartamento.Nombre, apartamento.Capacidad,IFNULL(COUNT(reserva_apartamento.Cod_reserva), 0) AS popularidad FROM apartamento LEFT join reserva_apartamento on apartamento.Cod_apartamento=reserva_apartamento.Cod_apartamento left join reserva on reserva.Cod_reserva=reserva_apartamento.Cod_reserva inner join ubicacion on apartamento.Cod_apartamento=ubicacion.Cod_apartamento where apartamento.Cod_apartamento not in( SELECT DISTINCT reserva_apartamento.Cod_apartamento FROM reserva_apartamento INNER join reserva on reserva_apartamento.Cod_reserva=reserva.Cod_reserva WHERE '"+fecha_inicio+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida OR '"+fecha_fin+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida ) AND ubicacion.Localidad='"+Localidad+"' AND apartamento.Capacidad>= '"+personas+"' GROUP BY apartamento.Cod_apartamento order by popularidad Desc,apartamento.nombre Desc ";
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
   
    public  ResultSet habitaciones_casa(String nombre_casa)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT habitacion.Tipo ,habitacion.Descripcion FROM habitacion INNER JOIN casa ON casa.Cod_casa = habitacion.Cod_casa WHERE casa.Nombre='"+nombre_casa+"'";
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
     
    public  ResultSet habitaciones_apartamento(String nombre_apartamento)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                
                String query="SELECT habitacion.Tipo ,habitacion.Descripcion FROM habitacion INNER JOIN apartamento ON apartamento.Cod_apartamento = habitacion.Cod_apartamento WHERE apartamento.Nombre='"+nombre_apartamento+"'";
                Statement sentencia = reg.createStatement(); 
                ResultSet resultado=sentencia.executeQuery(query); 
                return resultado;   
            }
        }catch (SQLException ex) 
        {
            System.err.println("Hubo un Error_3 ");
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
                String query="Select cama.* from cama inner join habitacion_hotel on cama.Cod_habitacion_hotel = habitacion_hotel.Cod_habitacion_hotel where habitacion_hotel.Cod_habitacion_hotel not in (Select Distinct reserva_hotel_habitacion.Cod_habitacion_hotel from reserva_hotel_habitacion inner join reserva ON reserva.Cod_reserva=reserva_hotel_habitacion.Cod_reserva WHERE '"+fecha_inicio+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida OR '"+fecha_fin+"' BETWEEN reserva.Fecha_entrada AND reserva.Fecha_salida) and habitacion_hotel.Cod_hotel in (Select hotel.Cod_hotel from hotel where hotel.Nombre='"+nombre_hotel+"') order by habitacion_hotel.Cod_habitacion_hotel Desc ";
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
      
    public  ResultSet PrecioApartamento(String localidad) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT  apartamento.Precio FROM apartamento WHERE apartamento.Nombre like '"+localidad+"'";
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
    
    public  ResultSet PrecioCasa(String localidad) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT  casa.Precio FROM casa WHERE casa.Nombre like '"+localidad+"'";
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
     
        public void InsertarReserva(String entrada, String salida,String DNI,double precio )
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO reserva ( Fecha_entrada, Fecha_salida,DNI,Precio) VALUES ('"+entrada+"','"+salida+"','"+DNI+"','"+precio+"')");      
            }
        }catch (Exception e){ 
            System.err.println("Hubo un Error"); 
            System.err.println(e.getMessage()); 
        } 
    } 
    
    public void Insertar_casa(String Cod_casa)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO reserva_casa(Cod_reserva,Cod_casa) VALUES ((Select Cod_reserva from reserva order by Cod_reserva DESC Limit 1),'"+Cod_casa+"')");      
            }
        }catch (Exception e){ 
            System.err.println("Hubo un Error"); 
            System.err.println(e.getMessage()); 
        } 
    }   
    
    public void Insertar_apartamento(String Cod_apartamento)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO reserva_apartamento(Cod_reserva,Cod_casa) VALUES ((Select Cod_reserva from reserva order by Cod_reserva DESC Limit 1),'"+Cod_apartamento+"')");      
            }
        }catch (Exception e){ 
            System.err.println("Hubo un Error"); 
            System.err.println(e.getMessage()); 
        } 
    }
    
    public void Insertar_hotel(String Cod_hotel,String Cod_habitacion)
    {
        try 
        {  
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");        
            }else {
                Statement st = reg.createStatement(); 
                st.executeUpdate("INSERT INTO reserva_hotel_habitacion ( Cod_reserva, Cod_hotel,Cod_habitacion_hotel) VALUES ((Select Cod_reserva from reserva order by Cod_reserva DESC Limit 1),'"+Cod_hotel+"','"+Cod_habitacion+"')");      
            }
        }catch (Exception e){ 
            System.err.println("Hubo un Error_5"); 
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

    public  ResultSet hotel_para_reservar(String Nombre)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                  String query="SELECT hotel.Cod_hotel as codigo from hotel where hotel.Nombre like '"+Nombre+"'";
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
    
    public  ResultSet casa_para_reservar(String Nombre)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                  String query="SELECT casa.Cod_casa as codigo from casa where casa.Nombre like '"+Nombre+"'";
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
    
    public  ResultSet apartamento_para_reservar(String Nombre)
    {         
        try
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT apartamento.Cod_apartamento as codigo from apartamento where apartamento.Nombre like '"+Nombre+"'";
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
                   st.executeUpdate("DELETE from reserva_hotel_habitacion where reserva_hotel_habitacion.Cod_reserva in( SELECT reserva.Cod_reserva FROM `reserva` WHERE reserva.DNI='"+us+"') ");   
                   st.executeUpdate("DELETE from reserva_casa where reserva_casa.Cod_reserva in( SELECT reserva.Cod_reserva FROM `reserva` WHERE reserva.DNI='"+us+"') ");   
                   st.executeUpdate("DELETE from reserva_apartamento where reserva_apartamento.Cod_reserva in( SELECT reserva.Cod_reserva FROM `reserva` WHERE reserva.DNI='"+us+"')");   
                st.executeUpdate("DELETE from usuario where DNI='"+us+"'");              
                 st.executeUpdate("DELETE from reserva where DNI='"+us+"'");     
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
                 st.executeUpdate("DELETE from reserva_hotel_habitacion where reserva_hotel_habitacion.Cod_reserva in( SELECT reserva.Cod_reserva FROM `reserva` WHERE reserva.DNI='"+us+"') ");   
                  st.executeUpdate("DELETE from reserva_casa where reserva_casa.Cod_reserva in( SELECT reserva.Cod_reserva FROM `reserva` WHERE reserva.DNI='"+us+"') ");   
                   st.executeUpdate("DELETE from reserva_apartamento where reserva_apartamento.Cod_reserva in( SELECT reserva.Cod_reserva FROM `reserva` WHERE reserva.DNI='"+us+"')");     
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
    
   
    
    public  ResultSet ObtenerReserva(String dni) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT * FROM reserva WHERE DNI like '"+dni+"'";
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
    
    public  ResultSet Promocion(String dni, int codigo) 
    {
        try 
        {
            if(reg.isClosed())
            {
                System.out.println("Sesion terminada");
                return null;
            }else{
                String query="SELECT IFNULL(count(Cod_promocion),0) AS cantidad, Valor FROM promocion WHERE DNI like '"+dni+"' and Cod_promocion like "+codigo+"";
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
