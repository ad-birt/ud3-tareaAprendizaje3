package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ud3TareaAprendizaje3Ejercicio4 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "birt";
        String password = "birt";

        String consulta = "insert into city (name, countrycode, district, population) values (?,?,?,?)";
        
        try (Connection miConexion = DriverManager.getConnection(url, user, password);
             PreparedStatement prStatement = miConexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
            
            prStatement.setString(1, "Lucca");
            prStatement.setString(2, "ITA");
            prStatement.setString(3, "Toscana");
            prStatement.setInt(4, 88397);
            
            prStatement.executeUpdate();

            try (ResultSet rs = prStatement.getGeneratedKeys()) {
                if (rs != null && rs.next()) {
                    System.out.println("La nueva ciudad introducida tiene el id: " + rs.getInt(1));
                } else {
                    System.out.println("No se pudo obtener el ID de la nueva ciudad.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error en la operación de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}