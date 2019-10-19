import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.ResultSet;

public class Conexion {

    private Connection conn;
    private Statement stmt;
    private String url = "jdbc:mysql://localhost:3306/tp4";
    private String user = "tp4";
    private String pass = "tp4tp4";
    private ResultSet listaAlbum, listaArtista, listaTemas;

    public Conexion() {
        super();
        this.listaAlbum = obtenerAlbum();
        this.listaArtista = obtenerArtista();
        this.listaTemas = obtenerTema();
    }

    public ResultSet obtenerAlbum() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stmt = (Statement) conn.createStatement();
            return stmt.executeQuery("SELECT * FROM ALBUM");
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return null;
    }


    public ResultSet obtenerArtista() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stmt = (Statement) conn.createStatement();
            return stmt.executeQuery("SELECT * FROM ARTISTA");
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
            return stmt.executeQuery("SELECT * FROM TEMA");
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return null;
    }
}