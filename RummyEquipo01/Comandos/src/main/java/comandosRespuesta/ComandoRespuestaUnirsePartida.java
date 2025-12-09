package comandosRespuesta;
import java.util.List;

import interfaces.ICommand;

public class ComandoRespuestaUnirsePartida implements ICommand {

    private final String type = "ComandoRespuestaUnirsePartida";
    private boolean exito;       // true = aceptado, false = rechazado (ej. sala llena)
    private String mensaje;      // "Bienvenido" o "Sala llena"
    private List<String> nombresJugadores; // Lista para llenar el Lobby

    public ComandoRespuestaUnirsePartida(boolean exito, String mensaje, List<String> nombresJugadores) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.nombresJugadores = nombresJugadores;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getNombreJugador() {
        return "Servidor";
    }

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public List<String> getNombresJugadores() {
        return nombresJugadores;
    }
}