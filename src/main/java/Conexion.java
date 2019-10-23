import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {

    private Connection conn;
    private Statement stmt;
    private String url = "jdbc:mysql://localhost:3306/tp4";
    private String user = "tp4";
    private String pass = "";
    private ResultSet listaAlbum, listaArtista, listaTemas;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            System.out.println("Error Conexion, Excepcion ");
            System.out.println(ex + ": "+ ex.getMessage());
        }
    }

    public ResultSet obtenerAlbum() {
        listaAlbum = null;
        try {
            stmt = (Statement) conn.createStatement();
            listaAlbum = stmt.executeQuery("SELECT * FROM album");
        } catch (SQLException ex) {
            System.out.println("Error obtenerAlbum: Excepcion " + ex.getMessage());
        }
        return listaAlbum;
    }


    public ResultSet obtenerArtista() {
        listaArtista = null;
        try {
            stmt = (Statement) conn.createStatement();
            return stmt.executeQuery("SELECT * FROM artista");
        } catch (Exception ex) {
            System.out.println("Error obtenerArtista: Excepcion " + ex.getMessage());
        }
        return listaArtista;
    }

    public ResultSet obtenerTema() {
        listaTemas = null;
        try {
            stmt = (Statement) conn.createStatement();
            listaTemas = stmt.executeQuery("SELECT * FROM tema");
        } catch (Exception ex) {
            System.out.println("Error  obtenerTema: Excepcion " + ex.getMessage());
        }
        return listaTemas;
    }

}
