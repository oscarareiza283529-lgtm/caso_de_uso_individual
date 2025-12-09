package UnirsePartida.logica; // O el paquete donde lo tengas

import java.util.ArrayList;
import java.util.List;

import interfaces.ICommand;
import interfaces.IFiltro;

public class ModeloCliente implements IFiltro {

    private String nombreJugador;
    private IFiltro filtroEnvio; 
    
    // IMPORTANTE: La lista debe ser de la Interfaz, no de una clase concreta
    private List<IObservador> observadores; 

    public ModeloCliente(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.observadores = new ArrayList<>();
    }

    public void setFiltroSalida(IFiltro filtro) {
        this.filtroEnvio = filtro;
    }

    public void enviarComando(ICommand comando) {
        if (filtroEnvio != null) {
            filtroEnvio.ejecutar(comando);
        } else {
            System.err.println("Error: No hay conexión de salida configurada en ModeloCliente.");
        }
    }

    @Override
    public void ejecutar(ICommand comando) {
        notificarObservadores(comando);
    }

    // --- CORRECCIÓN AQUÍ ---
    // El parámetro debe ser IObservador, NO FrmLobby
    public void suscribir(IObservador observador) {
        observadores.add(observador);
    }

    private void notificarObservadores(ICommand comando) {
        for (IObservador obs : observadores) {
            obs.actualizar(comando);
        }
    }
    
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
}