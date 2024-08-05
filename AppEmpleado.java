import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class AppEmpleado extends JFrame implements ActionListener {

    private JTextField campoId;
    private JTextField campoNombre;
    private JTextField campoHoras;
    private JTextField campoTarifa;
    private JTextField campoDeduccion;

    private JTextArea areaResultado;
    private List<Empleado> listaEmpleados;

    public AppEmpleado() {
        super("Gestión de Empleados");

        listaEmpleados = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        // panel de formulario para agregar empleados
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel etiquetaId = new JLabel("ID: ");
        etiquetaId.setFont(new Font("Arial", Font.BOLD, 14));
        campoId = new JTextField();
        campoId.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel etiquetaNombre = new JLabel("Nombre: ");
        etiquetaNombre.setFont(new Font("Arial", Font.BOLD, 14));
        campoNombre = new JTextField();
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel etiquetaHoras = new JLabel("Horas Trabajadas: ");
        etiquetaHoras.setFont(new Font("Arial", Font.BOLD, 14));
        campoHoras = new JTextField();
        campoHoras.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel etiquetaTarifa = new JLabel("Tarifa por Hora: ");
        etiquetaTarifa.setFont(new Font("Arial", Font.BOLD, 14));
        campoTarifa = new JTextField();
        campoTarifa.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel etiquetaDeduccion = new JLabel("Deducción (%): ");
        etiquetaDeduccion.setFont(new Font("Arial", Font.BOLD, 14));
        campoDeduccion = new JTextField();
        campoDeduccion.setFont(new Font("Arial", Font.PLAIN, 14));

        panelFormulario.add(etiquetaId);
        panelFormulario.add(campoId);
        panelFormulario.add(etiquetaNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(etiquetaHoras);
        panelFormulario.add(campoHoras);
        panelFormulario.add(etiquetaTarifa);
        panelFormulario.add(campoTarifa);
        panelFormulario.add(etiquetaDeduccion);
        panelFormulario.add(campoDeduccion);

        // boton añadir Empleado
        JButton botonAgregar = new JButton("Añadir");
        botonAgregar.addActionListener(this);
        botonAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        botonAgregar.setBackground(new Color(70, 130, 180));
        botonAgregar.setForeground(Color.WHITE);
        botonAgregar.setPreferredSize(new Dimension(180, 30));

        // boton Mostrar Empleados
        JButton botonMostrar = new JButton("Mostrar Empleados");
        botonMostrar.addActionListener(this);
        botonMostrar.setFont(new Font("Arial", Font.BOLD, 14));
        botonMostrar.setBackground(new Color(255, 140, 0));
        botonMostrar.setForeground(Color.WHITE);
        botonMostrar.setPreferredSize(new Dimension(180, 30));

        panelFormulario.add(botonAgregar);
        panelFormulario.add(botonMostrar);

        // area de resultados
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Arial", Font.PLAIN, 14));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setBackground(new Color(255, 250, 240));
        areaResultado.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(areaResultado);
        scrollPane.setPreferredSize(new Dimension(620, 200));

        // panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(panelPrincipal);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Añadir")) {
            String id = campoId.getText();
            String nombreCompleto = campoNombre.getText();
            String horasStr = campoHoras.getText();
            float horasTrabajadas = Float.parseFloat(horasStr);
            String tarifaStr = campoTarifa.getText();
            float tarifaHora = Float.parseFloat(tarifaStr);
            String deduccionStr = campoDeduccion.getText();
            float deduccionPorcentaje = Float.parseFloat(deduccionStr);

            if (!id.isEmpty() && !nombreCompleto.isEmpty() && !horasStr.isEmpty() && !tarifaStr.isEmpty() && !deduccionStr.isEmpty()) {
                agregarEmpleado(id, nombreCompleto, horasTrabajadas, tarifaHora, deduccionPorcentaje);
                campoId.setText("");
                campoNombre.setText("");
                campoHoras.setText("");
                campoTarifa.setText("");
                campoDeduccion.setText("");

                areaResultado.setText("Empleado añadido exitosamente.");
            } else {
                areaResultado.setText("Por favor, complete toda la información del empleado.");
            }
        } else if (e.getActionCommand().equals("Mostrar Empleados")) {
            areaResultado.setText("");
            List<String> inventario = obtenerListaEmpleados();
            if (inventario.isEmpty()) {
                areaResultado.setText("No hay empleados registrados.");
            } else {
                for (String empleado : inventario) {
                    areaResultado.append(empleado + "\n");
                }
            }
        }
    }

    public void agregarEmpleado(String id, String nombreCompleto, float horasTrabajadas, float tarifaHora, float deduccionPorcentaje) {
        Empleado nuevoEmpleado = new Empleado(id, nombreCompleto, horasTrabajadas, tarifaHora, deduccionPorcentaje);
        listaEmpleados.add(nuevoEmpleado);
    }

    public List<String> obtenerListaEmpleados() {
        List<String> inventario = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            inventario.add(empleado.toString());
        }
        return inventario;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception ex) {
                Logger.getLogger(AppEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            new AppEmpleado();
        });
    }
}