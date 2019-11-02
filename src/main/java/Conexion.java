import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {

    private Connection conn;
    private Statement stmt;
    private String url = "jdbc:mysql://localhost:3306/tp4?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "tp4";
    private String pass = "";
    private ResultSet listaAlbum, listaArtista, listaTemas;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            System.out.println("Error Conexion, Excepcion "+ ex + ": "+ ex.getMessage() + ", causa :" + ex.getCause());
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

    public ResultSet obtenerArtistaById(int id) {
        listaArtista = null;
        try {
            stmt = (Statement) conn.createStatement();
            return stmt.executeQuery("SELECT * FROM artista where "+id);
        } catch (Exception ex) {
            System.out.println("Error obtenerArtista: Excepcion " + ex.getMessage());
        }
        return listaArtista;
    }

    public ResultSet obtenerArtista() {
        listaArtista = null;
        try {
            stmt = (Statement) conn.createStatement();
            return stmt.executeQuery("SELECT * FROM artista ORDER BY NRO_ARTISTA ASC");
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

    public ResultSet obtenerTemaByIdAlbum(int id) {
        listaTemas = null;
        try {
            stmt = (Statement) conn.createStatement();
            listaTemas = stmt.executeQuery("SELECT * FROM tema where NRO_ALBUM="+id);
        } catch (Exception ex) {
            System.out.println("Error  obtenerTemaByIdAlbum: Excepcion " + ex.getMessage());
        }
        return listaTemas;
    }

    public ResultSet obtenerTemaByArtista(int artista) {
        listaTemas = null;
        try {
            stmt = (Statement) conn.createStatement();
            listaTemas = stmt.executeQuery("SELECT * FROM tema where NRO_ALBUM="+ artista);
        } catch (Exception ex) {
            System.out.println("Error  obtenerTemaByIdAlbum: Excepcion " + ex.getMessage());
        }
        return listaTemas;
    }
}
