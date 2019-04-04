
package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {

    Connection conectar=null;

    public Connection conexion(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
              conectar=DriverManager.getConnection("jdbc:mysql://localhost/reto4","root","");
                System.out.println("Conexion establecida");
                }
        catch(Exception e){
          System.out.println(e.getMessage());}
                       return conectar;
    }
}

    
