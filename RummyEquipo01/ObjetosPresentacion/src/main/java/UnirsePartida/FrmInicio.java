package UnirsePartida;

import Fachada.FrmLobby;
import UnirsePartida.logica.ControladorUnirsePartida;
import UnirsePartida.logica.IObservador;
import comandosRespuesta.ComandoRespuestaUnirsePartida;
import comandosSolicitud.ComandoUnirsePartida;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FrmInicio extends JFrame implements IObservador {

    private JPanel panelPrincipal;
    private ControladorUnirsePartida controlador;

    public FrmInicio(ControladorUnirsePartida controlador) {
        this.controlador = controlador;
        this.controlador.getModelo().suscribir(this);

        setTitle("Rummy - Inicio");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(33, 150, 190));

        mostrarPantallaInicio();
    }

    // =========================================================================
    // LÓGICA DE RESPUESTA DEL SERVIDOR
    // =========================================================================
    @Override
    public void actualizar(Object comando) {
        if (comando instanceof ComandoRespuestaUnirsePartida) {
            ComandoRespuestaUnirsePartida respuesta = (ComandoRespuestaUnirsePartida) comando;

            if (respuesta.isExito()) {
                // CASO ACEPTADO: 
                // 1. Cerramos Inicio. 
                // 2. Abrimos Lobby con la lista actualizada.
                this.setVisible(false);
                
                FrmLobby lobby = new FrmLobby(controlador, respuesta.getNombresJugadores());
                lobby.setVisible(true);
                
                this.dispose(); 
            } else {
                // CASO RECHAZADO:
                // Solo mostramos mensaje y nos quedamos aquí.
                JOptionPane.showMessageDialog(this, 
                        "No se pudo unir a la partida:\n" + respuesta.getMensaje(), 
                        "Acceso Denegado", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // =========================================================================
    // UI - PANTALLAS
    // =========================================================================
    private void mostrarPantallaInicio() {
        if (panelPrincipal != null) remove(panelPrincipal);
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBounds(0, 0, 650, 420);
        add(panelPrincipal);

        // Estilos rápidos
        Color amarillo = new Color(255, 237, 153);
        
        ImageIcon logo = new ImageIcon("iconoVentanaEjercerTurno.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(20, 15, 150, 60);
        panelPrincipal.add(logoLabel);

        JPanel panel = new JPanel();
        panel.setBackground(amarillo);
        panel.setBounds(60, 100, 520, 200);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(amarillo, 20));
        panelPrincipal.add(panel);

        JButton btnUnirse = new JButton("Unirse a partida");
        btnUnirse.setBounds(140, 80, 240, 40); // Centrado
        btnUnirse.setBackground(new Color(239, 83, 80));
        btnUnirse.setForeground(Color.WHITE);
        btnUnirse.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnUnirse.addActionListener(e -> mostrarPantallaIP());
        panel.add(btnUnirse);

        revalidate(); repaint();
    }

    private void mostrarPantallaIP() {
        if (panelPrincipal != null) remove(panelPrincipal);
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBounds(0, 0, 650, 420);
        add(panelPrincipal);

        Color amarillo = new Color(255, 237, 153);
        
        ImageIcon logo = new ImageIcon("..\\src.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(20, 15, 150, 60);
        panelPrincipal.add(logoLabel);

        JPanel panel = new JPanel();
        panel.setBackground(amarillo);
        panel.setBounds(60, 100, 520, 220);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(amarillo, 20));
        panelPrincipal.add(panel);

        JLabel lblTexto = new JLabel("Ingrese IP de partida LAN:", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTexto.setBounds(40, 20, 440, 40);
        panel.add(lblTexto);

        JTextField txtIP = new JTextField("127.0.0.1");
        txtIP.setHorizontalAlignment(JTextField.CENTER);
        txtIP.setFont(new Font("Segoe UI", Font.BOLD, 20));
        txtIP.setBounds(130, 80, 260, 40);
        panel.add(txtIP);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(280, 150, 150, 40);
        btnContinuar.setBackground(new Color(30, 136, 229));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        // ENVIO DE SOLICITUD
        btnContinuar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Esperando respuesta del anfitrión...", "Conectando", JOptionPane.INFORMATION_MESSAGE);
            
            String nombre = controlador.getModelo().getNombreJugador();
            ComandoUnirsePartida comando = new ComandoUnirsePartida(nombre);
            controlador.recibirComando(comando);
        });
        panel.add(btnContinuar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(90, 150, 150, 40);
        btnCancelar.setBackground(new Color(239, 83, 80));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCancelar.addActionListener(e -> mostrarPantallaInicio());
        panel.add(btnCancelar);

        revalidate(); repaint();
    }

    // =========================================================================
    // MAIN: SIMULACIÓN DE INTEGRACIÓN (Anfitrión vs Invitado)
    // =========================================================================
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {}

        // 1. CREAMOS AL ANFITRIÓN ("JugadorPrueba")
        UnirsePartida.logica.ModeloCliente modeloHost = new UnirsePartida.logica.ModeloCliente("JugadorPrueba");
        ControladorUnirsePartida controladorHost = new ControladorUnirsePartida(modeloHost);
        
        java.util.List<String> listaHost = new java.util.ArrayList<>();
        listaHost.add("JugadorPrueba"); // Está solo en su sala
        
        FrmLobby ventanaAnfitrion = new FrmLobby(controladorHost, listaHost);
        ventanaAnfitrion.setTitle("ANFITRIÓN (JugadorPrueba)");
        ventanaAnfitrion.setLocation(100, 100);
        ventanaAnfitrion.setVisible(true);

        // 2. CREAMOS AL INVITADO ("Don Oscar")
        UnirsePartida.logica.ModeloCliente modeloGuest = new UnirsePartida.logica.ModeloCliente("Don Oscar");
        ControladorUnirsePartida controladorGuest = new ControladorUnirsePartida(modeloGuest);

        // 3. FILTRO DE SIMULACIÓN (Actúa como Servidor y Red)
        interfaces.IFiltro filtroSimulado = new interfaces.IFiltro() {
            @Override
            public void ejecutar(interfaces.ICommand comando) {
                if (comando instanceof comandosSolicitud.ComandoUnirsePartida) {
                    
                    // A) Recibimos petición de Don Oscar
                    String solicitante = comando.getNombreJugador();
                    
                    // B) Preguntamos al Anfitrión (Bloqueante)
                    boolean aceptado = ventanaAnfitrion.mostrarConfirmacionAcceso(solicitante);
                    
                    if (aceptado) {
                        // C) SI ACEPTA:
                        
                        // 1. Actualizamos ventana del Anfitrión
                        ventanaAnfitrion.agregarJugadorAceptado(solicitante);
                        
                        // 2. Respondemos ÉXITO a Don Oscar con la lista completa
                        java.util.List<String> listaFinal = new java.util.ArrayList<>();
                        listaFinal.add("JugadorPrueba"); // Primero Host
                        listaFinal.add(solicitante);     // Luego Don Oscar
                        
                        comandosRespuesta.ComandoRespuestaUnirsePartida resp = 
                            new comandosRespuesta.ComandoRespuestaUnirsePartida(true, "Bienvenido", listaFinal);
                        
                        modeloGuest.ejecutar(resp);
                        
                    } else {
                        // D) SI RECHAZA:
                        
                        // Respondemos FALLO a Don Oscar
                        comandosRespuesta.ComandoRespuestaUnirsePartida resp = 
                            new comandosRespuesta.ComandoRespuestaUnirsePartida(false, "El anfitrión rechazó tu solicitud.", null);
                        
                        modeloGuest.ejecutar(resp);
                    }
                }
            }
        };
        
        // Conectamos a Don Oscar al simulador
        modeloGuest.setFiltroSalida(filtroSimulado);

        // 4. LANZAMOS VENTANA DE DON OSCAR
        java.awt.EventQueue.invokeLater(() -> {
            FrmInicio ventanaInvitado = new FrmInicio(controladorGuest);
            ventanaInvitado.setTitle("INVITADO (Don Oscar)");
            ventanaInvitado.setLocation(800, 100);
            ventanaInvitado.setVisible(true);
        });
    }
}