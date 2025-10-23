package proye1.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelMenuPrincipal extends JPanel {
    
    private VentanaPrincipal ventana;
    private JButton btnPacientes, btnCitas, btnExpedientes, btnInventario, btnPersonal, btnSalir;
    
    public PanelMenuPrincipal(VentanaPrincipal ventana) {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(new Color(230, 245, 255));
        
        // Título superior
        JLabel lblTitulo = new JLabel("Menú Principal del Sistema Clínico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(20, 60, 90));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);
        
        // Panel central con botones (ahora 6 botones)
        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 300, 20, 300));
        panelBotones.setBackground(new Color(230, 245, 255));
        
        btnPacientes = crearBoton("👤 Gestionar Pacientes");
        btnCitas = crearBoton("📅 Gestionar Citas");
        btnExpedientes = crearBoton("📋 Ver Expedientes");
        btnInventario = crearBoton("💊 Inventario Médico");
        btnPersonal = crearBoton("👥 Registrar Personal");  // ⭐ NUEVO BOTÓN
        btnSalir = crearBoton("🚪 Cerrar Sesión");
        
        panelBotones.add(btnPacientes);
        panelBotones.add(btnCitas);
        panelBotones.add(btnExpedientes);
        panelBotones.add(btnInventario);
        panelBotones.add(btnPersonal);  // ⭐ AGREGAR AL PANEL
        panelBotones.add(btnSalir);
        
        add(panelBotones, BorderLayout.CENTER);
        
        // Eventos de botones
        btnPacientes.addActionListener(e -> abrirPanel("Pacientes"));
        btnCitas.addActionListener(e -> abrirPanel("Citas"));
        btnExpedientes.addActionListener(e -> abrirPanel("Expediente"));
        btnInventario.addActionListener(e -> abrirPanel("Inventario"));
        btnPersonal.addActionListener(e -> abrirPanel("RegistroPersonal"));  // ⭐ NUEVO EVENTO
        btnSalir.addActionListener(e -> cerrarSesion());
    }
    
    private void abrirPanel(String nombre) {
        ventana.mostrarPanel(nombre);
    }
    
    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea cerrar sesión?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ventana.mostrarPanel("Login");
        }
    }
    
    // ==========================
    // 🎨 ESTILO DE BOTONES
    // ==========================
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.setBackground(new Color(100, 180, 255));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(70, 150, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(100, 180, 255));
            }
        });
        
        return boton;
    }
}