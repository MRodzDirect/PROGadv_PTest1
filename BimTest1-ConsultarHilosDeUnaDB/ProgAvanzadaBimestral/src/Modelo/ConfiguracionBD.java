// Por Marco Abarca y Byron Cordova
// ===============================
// MODELO - ConfiguracionBD.java
// ===============================
package modelo;

public class ConfiguracionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/bimestral";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "~toor..3";
    
    public static String getUrl() {
        return URL;
    }
    
    public static String getUsuario() {
        return USUARIO;
    }
    
    public static String getContrasena() {
        return CONTRASENA;
    }
}



