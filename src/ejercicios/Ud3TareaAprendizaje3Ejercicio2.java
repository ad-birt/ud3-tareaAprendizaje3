package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ud3TareaAprendizaje3Ejercicio2 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/world";
        String user = "birt";
        String password = "birt";
        String consulta = "select * from country where continent=?";

        // Utilizando try-with-resources para asegurar el cierre de recursos
        try (Connection miConexion = DriverManager.getConnection(url, user, password);
             PreparedStatement prStatement = miConexion.prepareStatement(consulta)) {

            System.out.println("Introduce el nombre del continente en inglés:");
            String conti = Consola.readString();
            prStatement.setString(1, conti);

            try (ResultSet miResultSet = prStatement.executeQuery()) {
                while (miResultSet.next()) {
                    System.out.println(miResultSet.getString(2));
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}