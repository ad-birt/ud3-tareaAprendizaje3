package ejercicios;

import java.sql.*;

public class Ud3TareaAprendizaje3Ejercicio6 {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String DB_USER = "birt";
    private static final String DB_PASS = "birt";
    private static final String QUERY = "select * from world.city where CountryCode ='ESP' order by population;";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = statement.executeQuery(QUERY)) {

            // Actualiza la poblaci�n de la �ltima ciudad
            rs.last();
            rs.updateInt("Population", 3223335);
            rs.updateRow();
            System.out.println("Se ha actualizado correctamente la poblaci�n de " + rs.getString("Name"));

            // Actualiza la poblaci�n de la pen�ltima ciudad
            rs.previous();
            rs.updateInt("Population", 1620344);
            rs.updateRow();
            System.out.println("Se ha actualizado correctamente la poblaci�n de " + rs.getString("Name"));

        } catch (SQLException e) {
            System.err.println("Error en la operaci�n de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}