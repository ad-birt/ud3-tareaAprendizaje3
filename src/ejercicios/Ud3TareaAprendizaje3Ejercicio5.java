package ejercicios;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ud3TareaAprendizaje3Ejercicio5 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "birt";
        String password = "birt";
        String procedureCall = "{call actu_pobla(?,?,?)}";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
            
            callableStatement.setString(1, "ALB");
            callableStatement.setInt(2, 2);
            callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);

            callableStatement.execute();

            int rowsUpdated = callableStatement.getInt(3);
            System.out.println("Se han actualizado " + rowsUpdated + " filas");

        } catch (SQLException e) {
            System.err.println("Error en la operación de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}