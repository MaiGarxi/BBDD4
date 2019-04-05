
package bbdd;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Conectar {

    Connection conectar=null;

    public Connection conexion(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
              conectar=DriverManager.getConnection("jdbc:mysql://localhost/reto4","root","");
                System.out.println("Conexion establecida");
                JOptionPane.showMessageDialog(null,"conexion establecida");
                }
        catch(Exception e){
          System.out.println(e.getMessage());}
                       return conectar;
    }
    
    public ArrayList<String> consultar_destinos()
    {
    ArrayList<String> Destinos= new ArrayList<String>();
    
    return Destinos;
    }
}

    
