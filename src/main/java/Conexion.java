import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class Conexion {

    private Connection conn;
    private Statement stmt;
    private String url = "jdbc:mysql://localhost:3306/tp4";
    private String user = "tp4";
    private String pass = "tp4tp4";


    public Conexion() {
    }

    public List obtenerAlbum() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stmt = (Statement) conn.createStatement();
            ResultSet resultAlbum = stmt.executeQuery("SELECT * FROM ALBUM");
            return Arrays.asList(resultAlbum);
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return null;
    }


    public List obtenerArtista() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stmt = (Statement) conn.createStatement();
            ResultSet resultArtista = stmt.executeQuery("SELECT * FROM ALBUM");
            return Arrays.asList(resultArtista);
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return null;
    }

    public ResultSet obtenerTema() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stmt = (Statement) conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM TEMA");
            return result;
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return null;
    }
}