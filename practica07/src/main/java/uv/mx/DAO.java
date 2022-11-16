package uv.mx;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DAO {
    public Conexion cx = new Conexion();
    
    public static List<Usuario> getUsuarios(){
        Statement stm = null;
        ResultSet rs = null;
        List<Usuario> resultado = new ArrayList<>();
        Connection c = null;

        try{
            c = Conexion.getConnection();
            String sql = "SELECT * FROM usuario";
            stm = (Statement) c.createStatement();
            rs = stm.executeQuery(sql);

            while(rs.next()){
                Usuario u = new Usuario(rs.getString("id"), rs.getString("nombre"), rs.getString("pass"));
                resultado.add(u);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException sqlEx){
                    sqlEx.printStackTrace();
                }

                rs = null;
            }

            if(stm != null){
                try{
                    stm.close();
                }catch(SQLException sqlEx){
                    sqlEx.printStackTrace();
                }
                
                stm = null;
            }
            
            try{
                c.close();
                System.out.println("Closed  connection!");
            }catch(SQLException sqlEx){
                sqlEx.printStackTrace();
            }
        }
        
        return resultado;
    }

    public static String addUsuario(Usuario u){
        java.sql.PreparedStatement stm = null;
        Connection c = null;
        String msj = "";

        c = Conexion.getConnection();
        
        try{
            String sql = "INSERT INTO usuario (id, nombre, password) VALUES (?, ?, ?, ?)";
            stm = (PreparedStatement) c.prepareStatement(sql);
            stm.setString(1, u.getId());
            stm.setString(2, u.getNombre());
            stm.setString(3, u.getPassword());

            if(stm.executeUpdate() > 0) msj = "El usuario se agrego";
            else msj = "El usuario no se agrego";
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if(stm != null){
                try{
                    stm.close();
                }catch(SQLException sqlEx){
                    sqlEx.printStackTrace();
                }

                stm = null;
            }
            
            try{
                c.close();
                System.out.println("Closed  connection!");
            }catch(SQLException sqlEx){
                sqlEx.printStackTrace();
            }
        }

        return msj;
    }
}