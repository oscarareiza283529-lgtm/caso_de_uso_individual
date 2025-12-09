package comandosSolicitud;

import interfaces.ICommand;

/**
 * Comando que representa la solicitud de un cliente para unirse a una partida.
 */
public class ComandoUnirsePartida implements ICommand {

    private final String type = "ComandoUnirsePartida";
    private String nombreJugador;

    public ComandoUnirsePartida(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
}