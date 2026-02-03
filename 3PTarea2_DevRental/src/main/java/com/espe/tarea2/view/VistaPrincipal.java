package com.espe.tarea2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.espe.tarea2.controller.AlquilerController;
import com.espe.tarea2.service.VehiculoService;
import com.espe.tarea2.repository.IVehiculoRepository;
import com.espe.tarea2.repository.impl.MongoVehiculoRepository;
import com.espe.tarea2.model.*;
import com.espe.tarea2.connection.ConexionMongo;

public class VistaPrincipal extends JFrame {

    private AlquilerController controller;
    private Agencia agencia;
    private JTextField txtCedula, txtNombre, txtTelefono, txtEmail;
    private JComboBox<String> comboVehiculos;
    private JTextField txtDias;
    private JTextArea txtResultado;
    private JButton btnRegistrar;
    private JLabel lblEstado;

    public VistaPrincipal() {
        configurarArquitectura();
        configurarInterfaz();
        cargarDatosIniciales();
    }

    private void configurarArquitectura() {
        System.out.println("=== INICIANDO SISTEMA DEV RENTAL ===");


        agencia = new Agencia("DevRental - Sistema de Alquiler");

        try {
            // 2. Crear repositorio MongoDB
            IVehiculoRepository repository = new MongoVehiculoRepository();

            // 3. Crear servicio con repositorio inyectado
            VehiculoService service = new VehiculoService(repository);

            // 4. Crear controlador con servicio inyectado
            controller = new AlquilerController(service);

            System.out.println("Arquitectura configurada correctamente");

        } catch (Exception e) {
            System.err.println("⚠Advertencia: " + e.getMessage());
            System.err.println("El sistema funcionará en modo local (sin base de datos)");
        }
    }

    private void configurarInterfaz() {
        setTitle("DevRental - Sistema de Alquiler de Vehículos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(240, 245, 250));

        // Panel superior
        panelPrincipal.add(crearPanelSuperior(), BorderLayout.NORTH);

        // Panel central (izquierda: formulario, derecha: resultado)
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 15, 0));
        panelCentral.add(crearPanelFormulario());
        panelCentral.add(crearPanelResultado());
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior (estado del sistema)
        panelPrincipal.add(crearPanelEstado(), BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(25, 118, 210));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblTitulo = new JLabel("SISTEMA DE ALQUILER DEV RENTAL");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel("Registro y gestión de alquileres de vehículos");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(200, 230, 255));

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblSubtitulo, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("DATOS DEL ALQUILER"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Título del formulario
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel lblInstrucciones = new JLabel("Complete todos los campos:");
        lblInstrucciones.setFont(new Font("Arial", Font.BOLD, 13));
        lblInstrucciones.setForeground(new Color(33, 33, 33));
        panel.add(lblInstrucciones, gbc);

        // Campos del formulario
        gbc.gridwidth = 1;
        gbc.gridy = 1;

        // Cédula
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Cédula *:"), gbc);
        gbc.gridx = 1;
        txtCedula = new JTextField(18);
        txtCedula.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtCedula, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Nombre *:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(18);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtNombre, gbc);

        // Teléfono
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefono = new JTextField(18);
        txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtTelefono, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(18);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtEmail, gbc);

        // Separador
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        panel.add(new JSeparator(), gbc);

        // Vehículo
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Vehículo *:"), gbc);
        gbc.gridx = 1;
        comboVehiculos = new JComboBox<>();
        comboVehiculos.setFont(new Font("Arial", Font.PLAIN, 12));
        comboVehiculos.setPreferredSize(new Dimension(220, 28));
        panel.add(comboVehiculos, gbc);

        // Días
        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(new JLabel("Días *:"), gbc);
        gbc.gridx = 1;
        txtDias = new JTextField(8);
        txtDias.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(txtDias, gbc);

        // Botón de registro
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        btnRegistrar = new JButton("REGISTRAR ALQUILER");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(33, 150, 243));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // Acción del botón - ¡AHORA GUARDA DATOS REALES!
        btnRegistrar.addActionListener(e -> registrarAlquilerCompleto());

        panel.add(btnRegistrar, gbc);

        // Nota
        gbc.gridy = 10; gbc.gridwidth = 2;
        JLabel lblNota = new JLabel("* Campos obligatorios");
        lblNota.setFont(new Font("Arial", Font.ITALIC, 10));
        lblNota.setForeground(Color.GRAY);
        panel.add(lblNota, gbc);

        return panel;
    }

    private JPanel crearPanelResultado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("RESULTADO DEL ALQUILER"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(Color.WHITE);

        txtResultado = new JTextArea(18, 35);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtResultado.setBackground(new Color(250, 250, 250));
        txtResultado.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        JScrollPane scrollPane = new JScrollPane(txtResultado);
        panel.add(scrollPane);

        return panel;
    }

    private JPanel crearPanelEstado() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        lblEstado = new JLabel("🔵 Sistema listo - Complete el formulario");
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 11));

        panel.add(lblEstado);
        return panel;
    }

    private void cargarDatosIniciales() {
        comboVehiculos.removeAllItems();

        for (Vehiculo v : agencia.getVehiculosDisponibles()) {
            String info = v.getPlaca() + " - " + v.getMarca() + " " + v.getModelo() +
                    " - $" + String.format("%.2f", v.getPrecioDia()) + "/día - " +
                    v.getInfoDetallada();
            comboVehiculos.addItem(info);
        }

        // Datos de prueba pre-cargados
        txtCedula.setText("2350666984");
        txtNombre.setText("Kevin Parraga");
        txtTelefono.setText("0959788838");
        txtEmail.setText("kevinparraga5@gmail.com");
        txtDias.setText("3");

        if (comboVehiculos.getItemCount() > 0) {
            comboVehiculos.setSelectedIndex(0);
        }

        txtResultado.setText("=".repeat(55) + "\n");
        txtResultado.append("          SISTEMA DE ALQUILER DEV RENTAL\n");
        txtResultado.append("=".repeat(55) + "\n\n");
        txtResultado.append("Bienvenido al sistema de alquiler de vehículos.\n\n");
        txtResultado.append("Vehículos disponibles: " + agencia.getVehiculos().size() + "\n");
        txtResultado.append("Complete el formulario y presione 'REGISTRAR ALQUILER'\n\n");
        txtResultado.append("=".repeat(55) + "\n");
    }


    private void registrarAlquilerCompleto() {
        // Limpiar resultado anterior
        txtResultado.setText("");
        lblEstado.setText("Procesando solicitud...");

        try {
            txtResultado.append("=".repeat(55) + "\n");
            txtResultado.append("        PROCESANDO REGISTRO DE ALQUILER\n");
            txtResultado.append("=".repeat(55) + "\n\n");

            // 1. VALIDAR CAMPOS OBLIGATORIOS
            if (txtCedula.getText().trim().isEmpty()) {
                mostrarError("La cédula es obligatoria");
                return;
            }

            if (txtNombre.getText().trim().isEmpty()) {
                mostrarError("El nombre es obligatorio");
                return;
            }

            if (txtDias.getText().trim().isEmpty()) {
                mostrarError("Los días de alquiler son obligatorios");
                return;
            }

            // 2. VALIDAR DÍAS
            int dias;
            try {
                dias = Integer.parseInt(txtDias.getText().trim());
                if (dias <= 0) {
                    mostrarError("Los días deben ser mayor a 0");
                    return;
                }
                if (dias > 365) {
                    mostrarError("Máximo 365 días de alquiler");
                    return;
                }
            } catch (NumberFormatException e) {
                mostrarError("Los días deben ser un número válido");
                return;
            }

            // 3. CREAR CLIENTE
            Cliente cliente = new Cliente(
                    txtCedula.getText().trim(),
                    txtNombre.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtEmail.getText().trim()
            );

            txtResultado.append("CLIENTE REGISTRADO\n");
            txtResultado.append("   Cédula: " + cliente.getCedula() + "\n");
            txtResultado.append("   Nombre: " + cliente.getNombre() + "\n");
            txtResultado.append("   Teléfono: " + cliente.getTelefono() + "\n");
            txtResultado.append("   Email: " + cliente.getEmail() + "\n\n");

            // 4. OBTENER VEHÍCULO SELECCIONADO
            String itemSeleccionado = comboVehiculos.getSelectedItem().toString();
            String placa = extraerPlaca(itemSeleccionado);

            if (placa.isEmpty()) {
                mostrarError("No se pudo obtener la placa del vehículo");
                return;
            }

            txtResultado.append("BUSCANDO VEHÍCULO\n");
            txtResultado.append("   Placa: " + placa + "\n");

            // 5. REGISTRAR ALQUILER EN AGENCIA
            Alquiler alquiler = agencia.registrarAlquiler(cliente, placa, dias);

            txtResultado.append("ALQUILER REGISTRADO EN SISTEMA\n");
            txtResultado.append("   ID Alquiler: " + alquiler.getId() + "\n");
            txtResultado.append("   Días: " + alquiler.getDias() + "\n");
            txtResultado.append("   Costo Total: $" + String.format("%.2f", alquiler.getCostoTotal()) + "\n\n");

            // 6. OBTENER EL VEHÍCULO DEL ALQUILER PARA GUARDAR EN MONGODB
            Vehiculo vehiculoAlquilado = alquiler.getVehiculo();

            txtResultado.append(" GUARDANDO EN BASE DE DATOS...\n");
            txtResultado.append("   Vehículo: " + vehiculoAlquilado.getPlaca() + "\n");
            txtResultado.append("   Tipo: " + vehiculoAlquilado.getClass().getSimpleName() + "\n\n");

            // 7. USAR LA ARQUITECTURA DIP PARA GUARDAR EN MONGODB
            // Esto activará el Controlador → Servicio → Repository → MongoDB
            guardarVehiculoEnBaseDatos(vehiculoAlquilado);

            // 8. MOSTRAR RESUMEN FINAL
            txtResultado.append("=".repeat(55) + "\n");
            txtResultado.append("         REGISTRO COMPLETADO EXITOSAMENTE\n");
            txtResultado.append("=".repeat(55) + "\n\n");
            txtResultado.append("RESUMEN DEL ALQUILER:\n");
            txtResultado.append("   Cliente: " + cliente.getNombre() + "\n");
            txtResultado.append("   Vehículo: " + vehiculoAlquilado.getPlaca() + " - " +
                    vehiculoAlquilado.getMarca() + " " + vehiculoAlquilado.getModelo() + "\n");
            txtResultado.append("   Período: " + dias + " día" + (dias > 1 ? "s" : "") + "\n");
            txtResultado.append("   Total a pagar: $" + String.format("%.2f", alquiler.getCostoTotal()) + "\n");
            txtResultado.append("   Fecha: " + new java.util.Date() + "\n\n");
            txtResultado.append("¡Gracias por usar DevRental! \n");

            lblEstado.setText("Alquiler registrado exitosamente");

            // 9. (OPCIONAL) Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    "¡Alquiler registrado exitosamente!\n\n" +
                            "Cliente: " + cliente.getNombre() + "\n" +
                            "Vehículo: " + vehiculoAlquilado.getPlaca() + "\n" +
                            "Días: " + dias + "\n" +
                            "Total: $" + String.format("%.2f", alquiler.getCostoTotal()),
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            txtResultado.append("\nERROR EN EL PROCESO:\n");
            txtResultado.append("   " + e.getMessage() + "\n\n");
            txtResultado.append("Por favor, verifique los datos e intente nuevamente.\n");

            lblEstado.setText("Error: " + e.getMessage());

            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error en el Registro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private void guardarVehiculoEnBaseDatos(Vehiculo vehiculo) {
        try {

            ActionEvent event = new ActionEvent(vehiculo, ActionEvent.ACTION_PERFORMED, "GUARDAR_VEHICULO");

            controller.actionPerformed(event);

            txtResultado.append("Vehículo guardado en base de datos MongoDB\n");

        } catch (Exception e) {
            txtResultado.append("⚠Advertencia: " + e.getMessage() + "\n");
            txtResultado.append("   El alquiler se registró localmente pero no en la base de datos.\n");
        }
    }


    private String extraerPlaca(String texto) {

        if (texto == null || texto.isEmpty()) {
            return "";
        }

        String[] partes = texto.split(" - ");
        if (partes.length > 0) {
            return partes[0].trim();
        }

        return "";
    }

    /**
     * Muestra un error en la interfaz
     */
    private void mostrarError(String mensaje) {
        txtResultado.append("\nERROR DE VALIDACIÓN:\n");
        txtResultado.append("   " + mensaje + "\n\n");
        txtResultado.append("Por favor, corrija el dato e intente nuevamente.\n");

        lblEstado.setText("mensaje");

        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error de Validación",
                JOptionPane.WARNING_MESSAGE);
    }
}