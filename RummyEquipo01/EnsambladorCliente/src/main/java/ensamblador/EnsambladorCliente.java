
package ensamblador;

import cliente.ColaMensajesEnviar;
import cliente.GestorConexiones;
import com.formdev.flatlaf.FlatIntelliJLaf;
import deserializador.Deserializador;
import directorioServidor.DirectorioServidor;
import ejercerTurno.Controlador;
import ejercerTurno.IReceptorEventos;
import ejercerTurno.Modelo;
import ejercerTurno.VistaMesaJuego;
import interfaces.ISuscriptor;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import objetosPresentacion.GestorEventos;
import objetosPresentacion.IComponente;
import objetosPresentacion.IGestorEventos;
import objetosPresentacion.PanelCasilla;
import objetosPresentacion.PanelJugadorExterno;
import objetosPresentacion.PanelJugadorPrincipal;
import objetosPresentacion.PanelMesaJuego;
import objetosPresentacion.PanelMonton;
import objetosPresentacion.PanelMovimiento;
import objetosPresentacion.PanelTablero;
import objetosPresentacion.PosicionPanel;
import serializador.Serializador;
import servidor.ColaMensajesRecibidos;
import servidor.Servidor;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class EnsambladorCliente {

    private static final int TOTAL_CASILLAS_TABLERO =500;
    private static final int TOTAL_CASILLAS_MANO = 14;
    
    public static void main(String[] args) {
        
        try {
            FlatIntelliJLaf.setup(); 

        } catch (Exception e) {}
        
        Scanner escaner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del Jugador: ");
        String nombreJugador = escaner.nextLine();
        
//        System.out.print("Ingrese la dirección IP del servidor: ");
//        String ipServidor = escaner.nextLine();

        String ipServidor = "127.0.0.1";
        
        String puertoCliente = null;
        
        String nombreJugador2 = "a";
        String direccionImagenAvatarJugador2 = null;
        
        if(nombreJugador.equals("Francisco34")){
            
            puertoCliente = "51000";
            
            nombreJugador2 = "Sandy43";
            direccionImagenAvatarJugador2 = "avatar2.png";
        }
        
        if(nombreJugador.equals("Sandy43")){
            
            puertoCliente = "52000";
            
            nombreJugador2 = "Francisco34";
            direccionImagenAvatarJugador2 = "avatar1.png";
            
        }
        
        // Creación de clases de componentes
        
        PanelCasilla[] panelesCasillaTablero = new PanelCasilla[TOTAL_CASILLAS_TABLERO];

        for (int i = 0; i < TOTAL_CASILLAS_TABLERO; i++) {
            panelesCasillaTablero[i] = new PanelCasilla(i + 1);
        }
        
        
        IComponente panelTablero = new PanelTablero(panelesCasillaTablero);
        
        List<PanelCasilla> panelesCasillaJugador = new LinkedList<>();
         
        for (int i = 0; i < TOTAL_CASILLAS_MANO; i++) {
            panelesCasillaJugador.add(new PanelCasilla(i + 1));
        }
        
        IComponente panelJugadorPrincipal = new PanelJugadorPrincipal(panelesCasillaJugador);
        IComponente panelJugadorExterno1 = new PanelJugadorExterno(PosicionPanel.CENTRO_ARRIBA, nombreJugador2, direccionImagenAvatarJugador2);
//        IComponente panelJugadorExterno2 = new PanelJugadorExterno(PosicionPanel.DERECHA_CENTRO);
//        IComponente panelJugadorExterno3 = new PanelJugadorExterno(PosicionPanel.IZQUIERDA_CENTRO);
        
        IComponente panelMesaJuego = new PanelMesaJuego();
        IComponente panelMonton = new PanelMonton();
        
        panelMesaJuego.agregarComponente(panelMonton);
        panelMesaJuego.agregarComponente(panelTablero);
        panelMesaJuego.agregarComponente(panelJugadorExterno1);
//        panelMesaJuego.agregarComponente(panelJugadorExterno2);
//        panelMesaJuego.agregarComponente(panelJugadorExterno3);
        panelMesaJuego.agregarComponente(panelJugadorPrincipal);

        Map<Integer, Color> mapaColoresSeleccionados = Map.of(
            1, Color.RED,
            2, Color.BLUE,
            3, Color.GREEN,
            4, Color.DARK_GRAY
        );      
        
        Map<Integer,Integer> mapaIdsCasillasPanelesJugador = new HashMap<>();
        
        
        Map<Integer,Integer> mapaIdsCasillasPanelesTablero = new HashMap<>();
        
        for (int i = 0; i < TOTAL_CASILLAS_TABLERO; i++) {
            mapaIdsCasillasPanelesTablero.put(i + 1, null);
        }
        
        Modelo modelo = new Modelo(nombreJugador);
        Controlador controlador = new Controlador(modelo);
        
        IComponente panelMovimiento = new PanelMovimiento();
        
        VistaMesaJuego vistaMesaJuego = new VistaMesaJuego(
                controlador,
                panelMesaJuego, 
                panelMovimiento,
                mapaColoresSeleccionados,
                mapaIdsCasillasPanelesTablero,
                mapaIdsCasillasPanelesJugador);
        
        modelo.suscribirse(vistaMesaJuego);
                
        IGestorEventos gestorEventos = new GestorEventos(vistaMesaJuego, (IReceptorEventos)vistaMesaJuego);
        
        ((PanelMesaJuego)panelMesaJuego).setGestorEventos(gestorEventos);
        ((PanelTablero) panelTablero).setGestorEventos(gestorEventos);
        ((PanelJugadorPrincipal) panelJugadorPrincipal).setGestorEventos(gestorEventos);
        ((PanelMonton)panelMonton).setGestorEventos(gestorEventos);
        ((PanelMovimiento) panelMovimiento).setGestorEventos(gestorEventos);

        
        Serializador serializadorCliente = new Serializador();

        DirectorioServidor directorioServidor = new DirectorioServidor(new String[]{ipServidor, "50000"});
 
        ISuscriptor gestorConexiones = new GestorConexiones(); 
        
        ColaMensajesEnviar colaMensajesEnviar = new ColaMensajesEnviar();
        
        colaMensajesEnviar.setSuscriptor(gestorConexiones);
        
        new Thread(colaMensajesEnviar).start();
        
        ColaMensajesRecibidos colaMensajesRecibidos = new ColaMensajesRecibidos();
        
        Servidor servidor = new Servidor(Integer.valueOf(puertoCliente));
        
        servidor.setReceptor(colaMensajesRecibidos);
        
        new Thread(servidor).start();
        new Thread(colaMensajesRecibidos).start();

        Deserializador deserializadorCliente = new Deserializador();
        
        
        // Conexión de componentes (Envío):
        modelo.setFiltroEnvioMensaje(serializadorCliente);
        
        serializadorCliente.setFiltroSiguiente(directorioServidor);
        
        directorioServidor.setDispatcher(colaMensajesEnviar);
        
        // Conexión de componentes (Recepción):
        colaMensajesRecibidos.setReceptor(deserializadorCliente);
        
        deserializadorCliente.setFiltroSiguiente(modelo);
        
    }
}
