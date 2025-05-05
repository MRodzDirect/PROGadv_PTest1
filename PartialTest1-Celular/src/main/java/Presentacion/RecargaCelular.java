
package Presentacion;

/**
 *
 * @author COPASO
 */

import Clases.*;
import Logica.CelularServicio;
import javax.swing.*;


public class RecargaCelular extends JFrame {
    private JTextField fieldNumero, fieldValor, fieldSaldo;
    private JComboBox<String> comboTipoRecarga;
    private JButton btnRecargar, btnVolver;
    private final CelularServicio servicio;
    private Celular celular;

    public RecargaCelular() {
        servicio = new CelularServicio();
        initComponents();
        setTitle("Realizar Recarga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(null);

        JLabel lblNumero = new JLabel("Número Celular:");
        lblNumero.setBounds(20, 20, 150, 25);
        add(lblNumero);

        fieldNumero = new JTextField();
        fieldNumero.setBounds(170, 20, 200, 25);
        add(fieldNumero);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(380, 20, 100, 25);
        btnBuscar.addActionListener(e -> buscarCelular());
        add(btnBuscar);

        JLabel lblTipo = new JLabel("Tipo de Recarga:");
        lblTipo.setBounds(20, 50, 150, 25);
        add(lblTipo);

        comboTipoRecarga = new JComboBox<>(new String[]{"Manual", "Automática (2/3 Saldo, 1/3 Megas)"});
        comboTipoRecarga.setBounds(170, 50, 200, 25);
        comboTipoRecarga.addActionListener(e -> actualizarFormulario());
        add(comboTipoRecarga);

        JLabel lblValor = new JLabel("Valor Recarga:");
        lblValor.setBounds(20, 80, 150, 25);
        add(lblValor);

        fieldValor = new JTextField();
        fieldValor.setBounds(170, 80, 200, 25);
        add(fieldValor);

        JLabel lblSaldo = new JLabel("Saldo a Asignar:");
        lblSaldo.setBounds(20, 110, 150, 25);
        add(lblSaldo);

        fieldSaldo = new JTextField();
        fieldSaldo.setBounds(170, 110, 200, 25);
        add(fieldSaldo);

        btnRecargar = new JButton("Recargar");
        btnRecargar.setBounds(170, 140, 150, 30);
        btnRecargar.addActionListener(e -> realizarRecarga());
        add(btnRecargar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(330, 140, 150, 30);
        btnVolver.addActionListener(e -> volver());
        add(btnVolver);

        setSize(500, 220);
        actualizarFormulario();
    }

    private void buscarCelular() {
        try {
            String numero = fieldNumero.getText().trim();
            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            celular = servicio.buscarCelularPorNumero(numero);
            JOptionPane.showMessageDialog(this, "Celular encontrado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            celular = null;
        }
    }

    private void actualizarFormulario() {
        boolean esManual = comboTipoRecarga.getSelectedIndex() == 0;
        fieldSaldo.setEnabled(esManual);
    }

    private void realizarRecarga() {
        try {
            if (celular == null) {
                JOptionPane.showMessageDialog(this, "Busque un celular válido primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String valorStr = fieldValor.getText().trim();
            if (valorStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el valor de la recarga.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int valor = Integer.parseInt(valorStr);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "El valor debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (comboTipoRecarga.getSelectedIndex() == 0) {
                // Recarga manual
                String saldoStr = fieldSaldo.getText().trim();
                if (saldoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el saldo a asignar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int saldo = Integer.parseInt(saldoStr);
                servicio.realizarRecargaManual(celular.getIdCel(), valor, saldo);
            } else {
                // Recarga automática
                servicio.realizarRecargaAutomatica(celular.getIdCel(), valor);
            }

            JOptionPane.showMessageDialog(this, "Recarga realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor y el saldo deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volver() {
        new IngresoCelular().setVisible(true);
        this.dispose();
    }

    private void limpiarCampos() {
        fieldNumero.setText("");
        fieldValor.setText("");
        fieldSaldo.setText("");
        comboTipoRecarga.setSelectedIndex(0);
        celular = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecargaCelular().setVisible(true));
    }
}