package proye1.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import proye1.*;

public class PanelLogin extends JPanel {

    private VentanaPrincipal ventana;   // Referencia al JFrame principal
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMensaje;

    public PanelLogin(VentanaPrincipal ventana) {
        this.ventana = ventana;

        // --- Configuración de layout ---
        setLayout(new GridBagLayout());
        setBackground(new Color(230, 245, 255)); // azul claro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Inicio de Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(20, 60, 90));

        JLabel lblUsuario = new JLabel("Usuario:");
        JLabel lblPassword = new JLabel("Contraseña:");
        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);

        txtUsuario = new JTextField(15);
        txtPassword = new JPasswordField(15);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBackground(new Color(100, 180, 255));
        btnLogin.setFocusPainted(false);

        // --- Posicionar componentes ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblUsuario, gbc);

        gbc.gridx = 1;
        add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblPassword, gbc);

        gbc.gridx = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnLogin, gbc);

        gbc.gridy = 4;
        add(lblMensaje, gbc);

        // --- Acción del botón ---
        btnLogin.addActionListener(e -> validarLogin());
    }

    // ===================================
    // 🔎 VALIDAR USUARIO Y CONTRASEÑA
    // ===================================
    private void validarLogin() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Por favor, ingrese usuario y contraseña.");
            return;
        }

        // ⭐ OBTENER LA LISTA DEL ADMINISTRATIVO
        Administrativo admin = ventana.getAdministrativo();
        
        if (admin == null) {
            lblMensaje.setText("Error: Sistema no inicializado correctamente.");
            return;
        }
        
        // Verificar si existe el personal con esas credenciales
        for (Personal p : admin.getPersonal()) {
            if (p.getUsuario().equalsIgnoreCase(usuario) && p.getPassword().equals(password)) {
                lblMensaje.setText("");
                JOptionPane.showMessageDialog(this,
                        "Bienvenido " + p.getNombre() + " (" + p.getCargo() + ")",
                        "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);

                // Limpiar campos después del login exitoso
                txtUsuario.setText("");
                txtPassword.setText("");
                
                // Mostrar panel de menú principal
                ventana.mostrarPanel("Menu");
                return;
            }
        }

        lblMensaje.setText("Usuario o contraseña incorrectos.");
    }
}