package uv.mx;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/ejemplo80640";
    private static String DriverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "liamps";
    private static Connection connection = null;

    public static Connection getConnection(){
        try{
            Class.forName(DriverName);
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Conectado");
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e2){
            System.out.println("No encuentro el Driver");
        }

        return connection;
    }
}