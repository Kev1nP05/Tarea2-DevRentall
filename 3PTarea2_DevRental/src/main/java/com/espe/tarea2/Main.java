
package com.espe.tarea2;

import com.espe.tarea2.view.VistaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("INICIANDO SISTEMA DEV RENTAL CON DIP");

        SwingUtilities.invokeLater(() -> {
            try {
                VistaPrincipal ventana = new VistaPrincipal();
                ventana.setVisible(true);
                ventana.setLocationRelativeTo(null);
                System.out.println("Interfaz iniciada");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}