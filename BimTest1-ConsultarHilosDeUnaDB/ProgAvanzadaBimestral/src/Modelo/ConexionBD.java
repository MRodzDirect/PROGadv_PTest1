// Por Marco Abarca y Byron Cordova
// ===============================
// MODELO - ConexionBD.java
// ===============================
package modelo;

import modelo.ConfiguracionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private static final Logger LOGGER = Logger.getLogger(ConexionBD.class.getName());
    
    public static Connection obtenerConexion() throws SQLException {
        try {
            return DriverManager.getConnection(
                ConfiguracionBD.getUrl(),
                ConfiguracionBD.getUsuario(), 
                ConfiguracionBD.getContrasena()
            );
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw ex;
        }
    }
}

