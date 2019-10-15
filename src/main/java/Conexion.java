import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;

public class Conexion {

    private Connection conn;
    private Statement stmt;

    public Conexion() {
        String url = "jdbc:mysql://localhost:3306/tp4";
        String user = "tp4";
        String pass = "tp4tp4";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            stmt = (Statement) conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM ARTISTA");
            while (result.next()){
                System.out.println(result.getInt(1) + ", " + result.getString(2) + ", "+
                        result.getString(3) + ", "+ result.getString(4));
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }
}
