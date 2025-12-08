
package comandosRespuesta;

import interfaces.ICommand;


public class ComandoJugadorPartidaGanada implements ICommand{
    
    private String type = "ComandoJugadorPartidaGanada";
    private String nombreJugador;
    private String mensaje;

    public ComandoJugadorPartidaGanada(String nombreJugador, String mensaje) {
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
