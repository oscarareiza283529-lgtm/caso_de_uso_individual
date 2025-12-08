
package comandosRespuesta;

import interfaces.ICommand;


public class ComandoJugadorAbandonoPartida implements ICommand{
    
    private String type = "ComandoJugadorAbandonoPartida";
    private String nombreJugador;
    private String mensaje;

    public ComandoJugadorAbandonoPartida(String nombreJugador, String mensaje) {
        this.nombreJugador = nombreJugador;
        this.mensaje = mensaje;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
    
}
