// Por Marco Abarca y Byron Cordova
// ===============================
// VISTA - VistaConsola.java
// ===============================
package Vista;

public class VistaConsola {

    public void mostrarInicio() {
        System.out.println("Iniciando procesamiento de numeros primos desde la base de datos...");
    }

    public void mostrarMatriz(int[][] matriz) {
        System.out.println("Matriz cargada desde la base de datos:");
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(matriz[i][j]);
            }
            System.out.println();
        }
    }

    public void mostrarError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }
}
