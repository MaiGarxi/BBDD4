
package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    public Conectar Devolver() {

        Conectar conex=new Conectar();
        return conex;
    }

    Connection conectar=null;
   
    public Connection conexion(){
            
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/reto4","root","");
            System.out.println("Conexion establecida");
        } 
 	catch (ClassNotFoundException e1) {
            //Error si no puedo leer el driver 
            System.out.println("ERROR:No encuentro el driver de la BD: "+e1.getMessage());
	}
	catch (SQLException e2) {
            //Error SQL: login/passwd mal
            System.out.println("ERROR:Fallo en SQL: "+e2.getMessage());
	}
        return conectar;
    }
}
    
