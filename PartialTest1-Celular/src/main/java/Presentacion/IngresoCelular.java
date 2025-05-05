
package Presentacion;

/**
 *
 * @author COPASO
 */

import Clases.*;
import Logica.CelularServicio;


import javax.swing.*;

public class IngresoCelular extends JFrame {
    private JTextField fieldCedula, fieldNombres, fieldApellidos, fieldNumero;
    private JComboBox<String> comboEstado;
    private JButton btnGuardar, btnRecargar;
    private final CelularServicio servicio;
    private Cliente cliente;

    public IngresoCelular() {
        servicio = new CelularServicio();
        initComponents();
        setTitle("Ingreso de Celular");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(null);

        JLabel lblCedula = new JLabel("Cédula Cliente:");
        lblCedula.setBounds(20, 20, 150, 25);
        add(lblCedula);

        fieldCedula = new JTextField();
        fieldCedula.setBounds(170, 20, 200, 25);
        add(fieldCedula);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(380, 20, 100, 25);
        btnBuscar.addActionListener(e -> buscarCliente());
        add(btnBuscar);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(20, 50, 150, 25);
        add(lblNombres);

        fieldNombres = new JTextField();
        fieldNombres.setBounds(170, 50, 200, 25);
        fieldNombres.setEditable(false);
        add(fieldNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(20, 80, 150, 25);
        add(lblApellidos);

        fieldApellidos = new JTextField();
        fieldApellidos.setBounds(170, 80, 200, 25);
        fieldApellidos.setEditable(false);
        add(fieldApellidos);

        JLabel lblNumero = new JLabel("Número Celular:");
        lblNumero.setBounds(20, 110, 150, 25);
        add(lblNumero);

        fieldNumero = new JTextField();
        fieldNumero.setBounds(170, 110, 200, 25);
        add(fieldNumero);

        JLabel lblEstado = new JLabel("Estado (0/1):");
        lblEstado.setBounds(20, 140, 150, 25);
        add(lblEstado);

        comboEstado = new JComboBox<>(new String[]{"0 - Inactivo", "1 - Activo"});
        comboEstado.setBounds(170, 140, 200, 25);
        add(comboEstado);

        btnGuardar = new JButton("Guardar Celular");
        btnGuardar.setBounds(170, 170, 150, 30);
        btnGuardar.addActionListener(e -> guardarCelular());
        add(btnGuardar);

        btnRecargar = new JButton("Realizar Recarga");
        btnRecargar.setBounds(330, 170, 150, 30);
        btnRecargar.addActionListener(e -> abrirRecarga());
        add(btnRecargar);

        setSize(500, 250);
    }

    private void buscarCliente() {
        try {
            String cedula = fieldCedula.getText().trim();
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una cédula.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            cliente = servicio.buscarClientePorCedula(cedula);
            fieldNombres.setText(cliente.getNombres());
            fieldApellidos.setText(cliente.getApellidos());
        } catch (Exception e) {
            int opcion = JOptionPane.showConfirmDialog(this, "Cliente no encontrado. ¿Desea registrarlo?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                fieldNombres.setEditable(true);
                fieldApellidos.setEditable(true);
            } else {
                cliente = null;
                fieldNombres.setText("");
                fieldApellidos.setText("");
            }
        }
    }

    private void guardarCelular() {
        try {
            String cedula = fieldCedula.getText().trim();
            String nombres = fieldNombres.getText().trim();
            String apellidos = fieldApellidos.getText().trim();
            String numero = fieldNumero.getText().trim();
            int estado = comboEstado.getSelectedIndex();

            if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cliente == null) {
                cliente = new Cliente(cedula, nombres, apellidos);
                servicio.insertarCliente(cliente);
            }

            Celular celular = new Celular(numero, estado);
            servicio.insertarCelular(celular, cliente.getIdCli());
            JOptionPane.showMessageDialog(this, "Celular registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRecarga() {
        new RecargaCelular().setVisible(true);
        this.dispose();
    }

    private void limpiarCampos() {
        fieldCedula.setText("");
        fieldNombres.setText("");
        fieldApellidos.setText("");
        fieldNumero.setText("");
        comboEstado.setSelectedIndex(0);
        fieldNombres.setEditable(false);
        fieldApellidos.setEditable(false);
        cliente = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IngresoCelular().setVisible(true));
    }
}