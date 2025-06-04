// Por Marco Abarca y Byron Cordova

// ===============================
// SERVICIO - CalculadorPrimos.java
// ===============================
package servicio;

public class CalculadorPrimos {
    
    public static void filtrarNumerosPrimosPorFilas(int[] fila) {
        int contadorPrimos = 0;
        for (int i = 0; i < fila.length; i++) {
            if (fila[i] % 2 == 1) {
                contadorPrimos++;
            } else {
                System.out.println(fila[i] + " no es primo.");
            }
        }
    }
    
    private static int[] matrizAFila(int[] arrayResultado) {
        for (int j = 0; j < arrayResultado.length; j++) {
            int k = arrayResultado[j];
            arrayResultado[j] = k;
        }
        return arrayResultado;
    }
}
