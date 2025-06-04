// Por Marco Abarca y Byron Cordova
// ===============================
// CONTROLADOR - ControladorPrincipal.java
// ===============================
package controlador;

import Vista.VistaConsola;
import servicio.GestorDatos;
// import vista.VistaConsola;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPrincipal {
    private static final Logger LOGGER = Logger.getLogger(ControladorPrincipal.class.getName());
    
    private final VistaConsola vista;
    private final int[][] matriz;
    
    public ControladorPrincipal() {
        this.vista = new VistaConsola();
        this.matriz = new int[500][4];
    }
    
    public void ejecutar() {
        vista.mostrarInicio();
        
        GestorDatos gestorDatos = new GestorDatos(matriz);
        gestorDatos.start();
        
        try {
            gestorDatos.join();
            vista.mostrarMatriz(matriz);
            gestorDatos.procesarDatosConcurrentes();
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Error en la ejecución del hilo principal", ex);
            Thread.currentThread().interrupt();
        }
    }
}
