// Por Marco Abarca y Byron Cordova

// ===============================
// MODELO - DatosDAO.java
// ===============================
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBD;


public class DatosDAO {
    private static final Logger LOGGER = Logger.getLogger(DatosDAO.class.getName());
    
    public boolean cargarDatosEnMatriz(int[][] matriz) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             Statement statement = conexion.createStatement()) {
            
            String query = "SELECT * FROM bimestral.valores;";
            ResultSet rs = statement.executeQuery(query);
            
            int fila = 0;
            while (rs.next() && fila < 500) {
                for (int columna = 0; columna < 4; columna++) {
                    matriz[fila][columna] = rs.getInt(columna + 1);
                }
                fila++;
            }
            return true;
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al cargar datos desde la base de datos", ex);
            return false;
        }
    }
}
