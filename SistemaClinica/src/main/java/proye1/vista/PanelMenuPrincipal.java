package proye1.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import proye1.*;

public class PanelMenuPrincipal extends JPanel {
    
    private VentanaPrincipal ventana;
    private JButton btnPacientes, btnCitas, btnExpedientes, btnInventario, btnPersonal, btnSalir;
    private Personal usuarioLogueado;
    
    public PanelMenuPrincipal(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.usuarioLogueado = null;
        
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
        btnPersonal = crearBoton("👥 Registrar Personal");
        btnSalir = crearBoton("🚪 Cerrar Sesión");
        
        panelBotones.add(btnPacientes);
        panelBotones.add(btnCitas);
        panelBotones.add(btnExpedientes);
        panelBotones.add(btnInventario);
        panelBotones.add(btnPersonal);
        panelBotones.add(btnSalir);
        
        add(panelBotones, BorderLayout.CENTER);
        
        // Eventos de botones
        btnPacientes.addActionListener(e -> abrirPanel("Pacientes"));
        btnCitas.addActionListener(e -> abrirPanel("Citas"));
        btnExpedientes.addActionListener(e -> abrirPanel("Expediente"));
        btnInventario.addActionListener(e -> abrirPanel("Inventario"));
        btnPersonal.addActionListener(e -> verificarAccesoRegistroPersonal());
        btnSalir.addActionListener(e -> cerrarSesion());
        
        // Inicializar estado de botones
        actualizarBotonesSegunPermisos();
    }
    
    // ⭐ NUEVO MÉTODO: Verificar acceso al registro de personal
    private void verificarAccesoRegistroPersonal() {
        if (usuarioLogueado instanceof Administrativo) {
            abrirPanel("RegistroPersonal");
        } else {
            JOptionPane.showMessageDialog(this,
                "❌ Acción restringida\n\nSolo el personal administrativo puede registrar nuevo personal.",
                "Permiso denegado",
                JOptionPane.WARNING_MESSAGE);
        }
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
            usuarioLogueado = null;
            ventana.mostrarPanel("Login");
        }
    }
    
    // ⭐ NUEVO MÉTODO: Establecer usuario logueado
    public void setUsuarioLogueado(Personal usuario) {
        this.usuarioLogueado = usuario;
        actualizarBotonesSegunPermisos();
    }
    
    // ⭐ NUEVO MÉTODO: Actualizar permisos de botones
    private void actualizarBotonesSegunPermisos() {
        if (usuarioLogueado != null) {
            // Solo los administrativos pueden registrar personal
            boolean esAdministrativo = usuarioLogueado instanceof Administrativo;
            btnPersonal.setEnabled(esAdministrativo);
            
            // Mostrar tooltip explicativo
            if (!esAdministrativo) {
                btnPersonal.setToolTipText("Acción restringida: Solo disponible para personal administrativo");
                // Cambiar color cuando está deshabilitado
                btnPersonal.setBackground(new Color(180, 180, 180));
            } else {
                btnPersonal.setToolTipText("Registrar nuevo personal en el sistema");
                btnPersonal.setBackground(new Color(100, 180, 255));
            }
        } else {
            btnPersonal.setEnabled(false);
            btnPersonal.setToolTipText("Inicie sesión para ver permisos");
            btnPersonal.setBackground(new Color(180, 180, 180));
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
        
        // Efecto hover solo si está habilitado
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (boton.isEnabled()) {
                    boton.setBackground(new Color(70, 150, 255));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (boton.isEnabled()) {
                    boton.setBackground(new Color(100, 180, 255));
                }
            }
        });
        
        return boton;
    }
}