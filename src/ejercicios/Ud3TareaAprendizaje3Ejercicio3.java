package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ud3TareaAprendizaje3Ejercicio3 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/world";
        String user = "birt";
        String password = "birt";

        try (Connection miConexion = DriverManager.getConnection(url, user, password)) {
            
            // Actualizar poblaci�n
            String consulta = "Update country set population = ? where code = ?";
            try (PreparedStatement prStatement = miConexion.prepareStatement(consulta)) {

                System.out.println("Introduce el c�digo del pa�s para actualizar sus habitantes:");
                String codpais = Consola.readString();
                System.out.println("Introduce el nuevo n�mero de habitantes:");
                int pobla = Consola.readInt();

                prStatement.setInt(1, pobla);
                prStatement.setString(2, codpais);

                int resultado = prStatement.executeUpdate();

                if (resultado == 1) {
                    // Obtener nombre del pa�s
                    String consulta2 = "Select name from country where code = ?";
                    try (PreparedStatement prStatement2 = miConexion.prepareStatement(consulta2);
                         ResultSet miResultSet = prStatement2.executeQuery()) {

                        prStatement2.setString(1, codpais);
                        if (miResultSet.next()) {
                            String nombrepais = miResultSet.getString(1);
                            System.out.println("Se ha modificado correctamente la poblaci�n de " + nombrepais);
                        }
                    }
                } else {
                    System.out.println("El c�digo introducido no es correcto o no hay cambios en la poblaci�n.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}