package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ud3TareaAprendizaje3Ejercicio1 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/world";
        String user = "birt";
        String password = "birt";

        // Utilizando try-with-resources para asegurar el cierre de recursos
        try (Connection miConexion = DriverManager.getConnection(url, user, password);
             Statement miStatement = miConexion.createStatement();
             ResultSet miResultSet = miStatement.executeQuery("Select * from country")) {

            while (miResultSet.next()) {
                System.out.println(miResultSet.getString(2));
            }
        } catch (Exception e) {
            System.err.println("Error de conexion: " + e.getMessage());
        }
    }
}