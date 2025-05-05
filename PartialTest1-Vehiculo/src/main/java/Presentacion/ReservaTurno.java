
package Presentacion;

/**
 *
 * @author COPASO
 */

import Clases.Vehiculo;
import Clases.Turno;
import Logica.VehiculoServicio;

import javax.swing.*;

public class ReservaTurno extends JFrame {
    private JTextField fieldPlaca, fieldAnden, fieldDia, fieldHora;
    private JButton btnReservar, btnVolver;
    private final VehiculoServicio servicio;
    private Vehiculo vehiculo;

    public ReservaTurno() {
        servicio = new VehiculoServicio();
        initComponents();
        setTitle("Reserva de Turno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(null);

        JLabel lblPlaca = new JLabel("Placa Vehículo:");
        lblPlaca.setBounds(20, 20, 150, 25);
        add(lblPlaca);

        fieldPlaca = new JTextField();
        fieldPlaca.setBounds(170, 20, 200, 25);
        add(fieldPlaca);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(380, 20, 100, 25);
        btnBuscar.addActionListener(e -> buscarVehiculo());
        add(btnBuscar);

        JLabel lblAnden = new JLabel("Andén:");
        lblAnden.setBounds(20, 50, 150, 25);
        add(lblAnden);

        fieldAnden = new JTextField();
        fieldAnden.setBounds(170, 50, 200, 25);
        add(fieldAnden);

        JLabel lblDia = new JLabel("Día (1-31):");
        lblDia.setBounds(20, 80, 150, 25);
        add(lblDia);

        fieldDia = new JTextField();
        fieldDia.setBounds(170, 80, 200, 25);
        add(fieldDia);

        JLabel lblHora = new JLabel("Hora (0-23):");
        lblHora.setBounds(20, 110, 150, 25);
        add(lblHora);

        fieldHora = new JTextField();
        fieldHora.setBounds(170, 110, 200, 25);
        add(fieldHora);

        btnReservar = new JButton("Reservar Turno");
        btnReservar.setBounds(170, 140, 150, 30);
        btnReservar.addActionListener(e -> reservarTurno());
        add(btnReservar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(330, 140, 150, 30);
        btnVolver.addActionListener(e -> volver());
        add(btnVolver);

        setSize(500, 220);
    }

    private void buscarVehiculo() {
        try {
            String placa = fieldPlaca.getText().trim();
            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una placa.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            vehiculo = servicio.buscarVehiculoPorPlaca(placa);
            JOptionPane.showMessageDialog(this, "Vehículo encontrado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            vehiculo = null;
        }
    }

    private void reservarTurno() {
        try {
            if (vehiculo == null) {
                JOptionPane.showMessageDialog(this, "Busque un vehículo válido primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String andenStr = fieldAnden.getText().trim();
            String diaStr = fieldDia.getText().trim();
            String horaStr = fieldHora.getText().trim();

            if (andenStr.isEmpty() || diaStr.isEmpty() || horaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int anden = Integer.parseInt(andenStr);
            int dia = Integer.parseInt(diaStr);
            int hora = Integer.parseInt(horaStr);

            if (dia < 1 || dia > 31 || hora < 0 || hora > 23) {
                JOptionPane.showMessageDialog(this, "Día debe estar entre 1 y 31, y hora entre 0 y 23.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Turno turno = new Turno(anden, dia, hora);
            servicio.reservarTurno(turno, vehiculo.getIdVehi());
            JOptionPane.showMessageDialog(this, "Turno reservado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Andén, día y hora deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volver() {
        new IngresoVehiculo().setVisible(true);
        this.dispose();
    }

    private void limpiarCampos() {
        fieldPlaca.setText("");
        fieldAnden.setText("");
        fieldDia.setText("");
        fieldHora.setText("");
        vehiculo = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservaTurno().setVisible(true));
    }
}
