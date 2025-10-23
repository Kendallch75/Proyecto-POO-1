package proye1.vista;

import javax.swing.*;
import java.awt.*;
import proye1.*;

public class PanelRegistroPersonal extends JPanel {
    
    private Administrativo admin;
    private JComboBox<String> cmbTipo;
    private JTextField txtNombre, txtCargo, txtJornada, txtCedula;
    private JTextField txtUsuario, txtPassword;
    private JTextField txtEspecialidad, txtColegiado;
    private JButton btnGuardar, btnLimpiar, btnVolver;
    private JPanel panelEspecifico;
    private VentanaPrincipal ventana;
    
    public PanelRegistroPersonal(VentanaPrincipal ventana, Administrativo admin) {
        this.ventana = ventana;
        this.admin = admin;
        setLayout(new BorderLayout());
        setBackground(new Color(230, 245, 255));
        
        // Panel principal con scroll
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel lblTitulo = new JLabel("Registro de Personal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(20, 60, 90));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelFormulario.add(lblTitulo, gbc);
        
        // Separador
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 15, 10);
        panelFormulario.add(new JSeparator(), gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 10, 8, 10);
        
        // Tipo de personal
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblTipo = new JLabel("Tipo de Personal:");

        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelFormulario.add(lblTipo, gbc);
        
        gbc.gridx = 1;
        cmbTipo = new JComboBox<>(new String[]{"Médico", "Enfermero", "Administrativo"});
        cmbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelFormulario.add(cmbTipo, gbc);
        
        // Campos comunes
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(crearLabel("Nombre completo:"), gbc);
        gbc.gridx = 1;
        txtNombre = crearTextField();
        panelFormulario.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(crearLabel("Cargo:"), gbc);
        gbc.gridx = 1;
        txtCargo = crearTextField();
        panelFormulario.add(txtCargo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(crearLabel("Jornada:"), gbc);
        gbc.gridx = 1;
        txtJornada = crearTextField();
        panelFormulario.add(txtJornada, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        panelFormulario.add(crearLabel("Cédula:"), gbc);
        gbc.gridx = 1;
        txtCedula = crearTextField();
        panelFormulario.add(txtCedula, gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        panelFormulario.add(crearLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        txtUsuario = crearTextField();
        panelFormulario.add(txtUsuario, gbc);
        
        gbc.gridx = 0; gbc.gridy = 8;
        panelFormulario.add(crearLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelFormulario.add(txtPassword, gbc);
        
        // Panel para campos específicos
        panelEspecifico = new JPanel(new GridBagLayout());
        panelEspecifico.setBackground(Color.WHITE);
        panelEspecifico.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 180, 255), 2),
            "Datos Específicos",
            0, 0,
            new Font("Segoe UI", Font.BOLD, 13),
            new Color(20, 60, 90)
        ));
        
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 15, 10);
        panelFormulario.add(panelEspecifico, gbc);
        
        // Campos específicos
        txtEspecialidad = crearTextField();
        txtColegiado = crearTextField();
        
        // Actualizar campos según tipo
        cmbTipo.addActionListener(e -> actualizarCamposEspecificos());
        actualizarCamposEspecificos();
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(Color.WHITE);
        
        btnGuardar = crearBoton("💾 Guardar", new Color(100, 180, 255));
        btnLimpiar = crearBoton("🔄 Limpiar", new Color(150, 150, 150));
        btnVolver = crearBoton("⬅️ Volver", new Color(255, 150, 100));
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVolver);
        
        gbc.gridy = 10;
        gbc.insets = new Insets(20, 10, 10, 10);
        panelFormulario.add(panelBotones, gbc);
        
        // Acciones
        btnGuardar.addActionListener(e -> guardarPersonal());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> ventana.mostrarPanel("Menu"));
        
        JScrollPane scroll = new JScrollPane(panelFormulario);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }
    
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }
    
    private JTextField crearTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = color;
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(color.darker());
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(original);
            }
        });
        
        return boton;
    }
    
    private void actualizarCamposEspecificos() {
        panelEspecifico.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        String tipo = (String) cmbTipo.getSelectedItem();
        
        if ("Médico".equals(tipo)) {
            gbc.gridx = 0; gbc.gridy = 0;
            panelEspecifico.add(crearLabel("Especialidad:"), gbc);
            gbc.gridx = 1;
            panelEspecifico.add(txtEspecialidad, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1;
            panelEspecifico.add(crearLabel("Número de Colegiado:"), gbc);
            gbc.gridx = 1;
            panelEspecifico.add(txtColegiado, gbc);
            
        } else if ("Enfermero".equals(tipo)) {
            gbc.gridx = 0; gbc.gridy = 0;
            panelEspecifico.add(crearLabel("Número de Colegiado:"), gbc);
            gbc.gridx = 1;
            panelEspecifico.add(txtColegiado, gbc);
            
        } else {
            // Para Administrativo no hay campos específicos
            JLabel lblInfo = new JLabel("No requiere datos adicionales");
            lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblInfo.setForeground(Color.GRAY);
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            panelEspecifico.add(lblInfo, gbc);
        }
        
        panelEspecifico.revalidate();
        panelEspecifico.repaint();
    }
    
    private void guardarPersonal() {
        try {
            // Validar campos comunes
            String nombre = txtNombre.getText().trim();
            String cargo = txtCargo.getText().trim();
            String jornada = txtJornada.getText().trim();
            String cedula = txtCedula.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String password = txtPassword.getText().trim();
            
            if (nombre.isEmpty() || usuario.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor complete los campos obligatorios:\n- Nombre\n- Usuario\n- Contraseña", 
                    "Campos requeridos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Verificar si el usuario ya existe
            if (admin.buscarPersonalPorUsuario(usuario) != null) {
                JOptionPane.showMessageDialog(this, 
                    "El usuario '" + usuario + "' ya está registrado.\nPor favor elija otro nombre de usuario.", 
                    "Usuario duplicado", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String tipo = (String) cmbTipo.getSelectedItem();
            String especialidad = txtEspecialidad.getText().trim();
            int colegiado = 0;
            
            // Validar número de colegiado si es necesario
            if ((tipo.equals("Médico") || tipo.equals("Enfermero")) && 
                !txtColegiado.getText().trim().isEmpty()) {
                try {
                    colegiado = Integer.parseInt(txtColegiado.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "El número de colegiado debe ser un valor numérico", 
                        "Error de formato", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Crear personal según tipo
            Personal nuevo = admin.crearPersonal(tipo, nombre, cargo, jornada, 
                                                 cedula, usuario, password, 
                                                 especialidad, colegiado);
            
            if (nuevo != null) {
                // ⭐⭐ AGREGAR ESTO: Si es médico, agregarlo a la lista de médicos
                if (nuevo instanceof Medico) {
                    ventana.agregarMedico((Medico) nuevo);
                }
                
                JOptionPane.showMessageDialog(this, 
                    "✅ Personal registrado exitosamente:\n\n" +
                    "Tipo: " + tipo + "\n" +
                    "Nombre: " + nombre + "\n" +
                    "Usuario: " + usuario, 
                    "Registro exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al registrar el personal.\nPor favor intente nuevamente.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error inesperado: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtCargo.setText("");
        txtJornada.setText("");
        txtCedula.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
        txtEspecialidad.setText("");
        txtColegiado.setText("");
        cmbTipo.setSelectedIndex(0);
        actualizarCamposEspecificos();
    }
}