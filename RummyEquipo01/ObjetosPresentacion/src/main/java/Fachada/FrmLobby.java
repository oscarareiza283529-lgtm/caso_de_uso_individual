package Fachada;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import UnirsePartida.logica.ControladorUnirsePartida;
import UnirsePartida.logica.IObservador;

public class FrmLobby extends JFrame implements IObservador {

    private JPanel panelLista;
    private ControladorUnirsePartida controlador;

    /**
     * Constructor del Lobby.
     */
    public FrmLobby(ControladorUnirsePartida controlador, List<String> jugadoresIniciales) {
        this.controlador = controlador;
        this.controlador.getModelo().suscribir(this);

        setTitle("Rummy - Lobby");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // ESTILOS
        Color azulFondo = new Color(33, 150, 190);
        Color amarilloPanel = new Color(255, 237, 153);
        getContentPane().setBackground(azulFondo);

        // LOGO
        JLabel logoLabel = new JLabel(new ImageIcon("icono.png")); 
        logoLabel.setBounds(20, 10, 150, 60);
        add(logoLabel);

        JPanel contenedor = new JPanel();
        contenedor.setBackground(amarilloPanel);
        contenedor.setLayout(null);
        contenedor.setBounds(120, 90, 460, 260);
        contenedor.setBorder(BorderFactory.createLineBorder(amarilloPanel, 18));
        add(contenedor);

        // LISTA
        panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(amarilloPanel);

        JScrollPane scroll = new JScrollPane(panelLista);
        scroll.setBounds(30, 20, 400, 150);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(amarilloPanel);
        contenedor.add(scroll);

        // ELEMENTOS VISUALES
        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(160, 180, 140, 40);
        btnIniciar.setBackground(new Color(67, 160, 71));
        btnIniciar.setForeground(Color.WHITE);
        contenedor.add(btnIniciar);

        JLabel lblEsperando = new JLabel("Esperando más jugadores...");
        lblEsperando.setBounds(145, 220, 200, 20);
        contenedor.add(lblEsperando);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(560, 350, 90, 40);
        btnSalir.setBackground(new Color(239, 83, 80));
        btnSalir.setForeground(Color.WHITE);
        add(btnSalir);

        // CARGA INICIAL
        if (jugadoresIniciales != null) {
            for (String nombre : jugadoresIniciales) {
                addPlayer(nombre, "avatarDefault.png");
            }
        }
    }

    public void addPlayer(String nombre, String avatarRuta) {
        JPanel item = new JPanel(new BorderLayout());
        item.setPreferredSize(new Dimension(380, 45));
        item.setMaximumSize(new Dimension(380, 45));
        item.setBackground(Color.WHITE);
        item.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        item.add(new JLabel(new ImageIcon(avatarRuta)), BorderLayout.WEST);
        JLabel lblName = new JLabel(nombre);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblName.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        item.add(lblName, BorderLayout.CENTER);

        panelLista.add(item);
        panelLista.revalidate();
        panelLista.repaint();
    }

    @Override
    public void actualizar(Object comando) {
        // Lógica futura de actualizaciones
    }

    // =========================================================================
    // LÓGICA DE ACEPTACIÓN (Host)
    // =========================================================================
    
    /**
     * Muestra el Popup para Aceptar/Rechazar.
     */
    public boolean mostrarConfirmacionAcceso(String nombreSolicitante) {
        this.setVisible(true); // Aseguramos que se vea
        this.toFront();
        
        int opcion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "El jugador '" + nombreSolicitante + "' quiere unirse.\n¿Aceptar?",
            "Solicitud de Acceso",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        return opcion == javax.swing.JOptionPane.YES_OPTION;
    }

    /**
     * Añade al jugador visualmente tras aceptar.
     */
    public void agregarJugadorAceptado(String nombre) {
        addPlayer(nombre, "avatarDefault.png");
    }

    // =========================================================================
    // MAIN PROPIO DEL LOBBY (Para pruebas individuales o ser Host)
    // =========================================================================
    public static void main(String[] args) {
        // Setup visual básico
        try { javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception e) {}

        // Creamos un modelo dummy para que el Lobby pueda arrancar solo
        UnirsePartida.logica.ModeloCliente modelo = new UnirsePartida.logica.ModeloCliente("Anfitrion");
        ControladorUnirsePartida ctrl = new ControladorUnirsePartida(modelo);
        
        java.util.List<String> lista = new java.util.ArrayList<>();
        lista.add("Anfitrion");

        java.awt.EventQueue.invokeLater(() -> {
            new FrmLobby(ctrl, lista).setVisible(true);
        });
    }
}