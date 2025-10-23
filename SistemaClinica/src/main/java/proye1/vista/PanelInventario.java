package proye1.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import proye1.InsumoMedico;

public class PanelInventario extends JPanel {

    private VentanaPrincipal ventana;
    private ArrayList<InsumoMedico> insumos;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaInsumos;

    private JTextField txtNombre, txtDosis, txtPrecio, txtCantidad;
    private JCheckBox chkRecetado;
    private JButton btnAgregar, btnVolver;

    public PanelInventario(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.insumos = new ArrayList<>();

        setLayout(new BorderLayout());
        setBackground(new Color(240, 250, 255));

        // ---- Título ----
        JLabel lblTitulo = new JLabel("Inventario Médico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(20, 60, 90));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // ---- Panel central ----
        modeloLista = new DefaultListModel<>();
        listaInsumos = new JList<>(modeloLista);
        listaInsumos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        JScrollPane scroll = new JScrollPane(listaInsumos);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Insumos"));
        add(scroll, BorderLayout.CENTER);

        // ---- Panel inferior: agregar insumo ----
        JPanel panelInferior = new JPanel(new GridLayout(6, 2, 10, 10));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        panelInferior.setBackground(new Color(240, 250, 255));

        panelInferior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelInferior.add(txtNombre);

        panelInferior.add(new JLabel("Dosis / Presentación:"));
        txtDosis = new JTextField();
        panelInferior.add(txtDosis);

        panelInferior.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelInferior.add(txtPrecio);

        panelInferior.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelInferior.add(txtCantidad);

        panelInferior.add(new JLabel("¿Requiere receta?"));
        chkRecetado = new JCheckBox();
        chkRecetado.setBackground(new Color(240, 250, 255));
        panelInferior.add(chkRecetado);

        btnAgregar = new JButton("Agregar Insumo");
        btnAgregar.setBackground(new Color(100, 180, 255));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);

        btnVolver = new JButton("⬅ Volver al Menú");
        btnVolver.setBackground(new Color(100, 180, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);

        panelInferior.add(btnAgregar);
        panelInferior.add(btnVolver);

        add(panelInferior, BorderLayout.SOUTH);

        // ---- Eventos ----
        btnAgregar.addActionListener(e -> agregarInsumo());
        btnVolver.addActionListener(e -> ventana.mostrarPanel("Menu"));
    }

    // ======================
    // 🩺 MÉTODOS LÓGICOS
    // ======================
    private void agregarInsumo() {
        try {
            String nombre = txtNombre.getText().trim();
            String dosis = txtDosis.getText().trim();
            int precio = Integer.parseInt(txtPrecio.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            boolean recetado = chkRecetado.isSelected();

            if (nombre.isEmpty() || dosis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            InsumoMedico nuevo = new InsumoMedico(nombre, dosis, precio, cantidad, recetado);
            insumos.add(nuevo);

            modeloLista.addElement("💊 " + nombre + " | " + dosis + " | ₡" + precio + " | Cant: " + cantidad + (recetado ? " | Recetado" : ""));
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDosis.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        chkRecetado.setSelected(false);
    }

    public ArrayList<InsumoMedico> getInsumos() {
        return insumos;
    }
}
