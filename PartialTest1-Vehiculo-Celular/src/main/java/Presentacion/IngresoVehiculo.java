
package Presentacion;

/**
 *
 * @author COPASO
 */

import Clases.*;
import Logica.VehiculoServicio;

import javax.swing.*;

public class IngresoVehiculo extends JFrame {
    private JTextField fieldCedula, fieldNombre, fieldApellido, fieldPlaca, fieldMarca;
    private JButton btnGuardar, btnReservarTurno;
    private final VehiculoServicio servicio;
    private Propietario propietario;

    public IngresoVehiculo() {
        servicio = new VehiculoServicio();
        initComponents();
        setTitle("Ingreso de Vehículo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(null);

        JLabel lblCedula = new JLabel("Cédula Propietario:");
        lblCedula.setBounds(20, 20, 150, 25);
        add(lblCedula);

        fieldCedula = new JTextField();
        fieldCedula.setBounds(170, 20, 200, 25);
        add(fieldCedula);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(380, 20, 100, 25);
        btnBuscar.addActionListener(e -> buscarPropietario());
        add(btnBuscar);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 50, 150, 25);
        add(lblNombre);

        fieldNombre = new JTextField();
        fieldNombre.setBounds(170, 50, 200, 25);
        fieldNombre.setEditable(false);
        add(fieldNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(20, 80, 150, 25);
        add(lblApellido);

        fieldApellido = new JTextField();
        fieldApellido.setBounds(170, 80, 200, 25);
        fieldApellido.setEditable(false);
        add(fieldApellido);

        JLabel lblPlaca = new JLabel("Placa Vehículo:");
        lblPlaca.setBounds(20, 110, 150, 25);
        add(lblPlaca);

        fieldPlaca = new JTextField();
        fieldPlaca.setBounds(170, 110, 200, 25);
        add(fieldPlaca);

        JLabel lblMarca = new JLabel("Marca Vehículo:");
        lblMarca.setBounds(20, 140, 150, 25);
        add(lblMarca);

        fieldMarca = new JTextField();
        fieldMarca.setBounds(170, 140, 200, 25);
        add(fieldMarca);

        btnGuardar = new JButton("Guardar Vehículo");
        btnGuardar.setBounds(170, 170, 150, 30);
        btnGuardar.addActionListener(e -> guardarVehiculo());
        add(btnGuardar);

        btnReservarTurno = new JButton("Reservar Turno");
        btnReservarTurno.setBounds(330, 170, 150, 30);
        btnReservarTurno.addActionListener(e -> abrirReservaTurno());
        add(btnReservarTurno);

        setSize(500, 250);
    }

    private void buscarPropietario() {
        try {
            String cedula = fieldCedula.getText().trim();
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una cédula.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            propietario = servicio.buscarPropietarioPorCedula(cedula);
            fieldNombre.setText(propietario.getNombre());
            fieldApellido.setText(propietario.getApellido());
        } catch (Exception e) {
            int opcion = JOptionPane.showConfirmDialog(this, "Propietario no encontrado. ¿Desea registrarlo?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                fieldNombre.setEditable(true);
                fieldApellido.setEditable(true);
            } else {
                propietario = null;
                fieldNombre.setText("");
                fieldApellido.setText("");
            }
        }
    }

    private void guardarVehiculo() {
        try {
            String cedula = fieldCedula.getText().trim();
            String nombre = fieldNombre.getText().trim();
            String apellido = fieldApellido.getText().trim();
            String placa = fieldPlaca.getText().trim();
            String marca = fieldMarca.getText().trim();

            if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || placa.isEmpty() || marca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (propietario == null) {
                propietario = new Propietario(cedula, nombre, apellido);
                servicio.insertarPropietario(propietario);
            }

            Vehiculo vehiculo = new Vehiculo(placa, marca);
            servicio.insertarVehiculo(vehiculo, propietario.getIdProp());
            JOptionPane.showMessageDialog(this, "Vehículo registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirReservaTurno() {
        new ReservaTurno().setVisible(true);
        this.dispose();
    }

    private void limpiarCampos() {
        fieldCedula.setText("");
        fieldNombre.setText("");
        fieldApellido.setText("");
        fieldPlaca.setText("");
        fieldMarca.setText("");
        fieldNombre.setEditable(false);
        fieldApellido.setEditable(false);
        propietario = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IngresoVehiculo().setVisible(true));
    }
}
