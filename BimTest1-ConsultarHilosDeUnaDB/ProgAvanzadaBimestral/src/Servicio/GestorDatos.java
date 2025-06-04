// Por Marcco Abarca y Byron Cordova
// ===============================
// SERVICIO - GestorDatos.java
// ===============================
package servicio;

import Modelo.DatosDAO;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorDatos extends Thread {
    private static final Logger LOGGER = Logger.getLogger(GestorDatos.class.getName());
    
    private final int[][] matriz;
    private final DatosDAO datosDAO;
    
    public GestorDatos(int[][] matriz) {
        this.matriz = matriz;
        this.datosDAO = new DatosDAO();
    }
    
    @Override
    public void run() {
        datosDAO.cargarDatosEnMatriz(matriz);
    }
    
    public void procesarDatosConcurrentes() {
        var numCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numCores);
        CountDownLatch endController = new CountDownLatch(12);
        
        // Aquí se procesarían las tareas concurrentes
        // El código original no completaba esta implementación
    }
}