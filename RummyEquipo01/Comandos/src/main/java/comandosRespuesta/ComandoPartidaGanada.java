
package comandosRespuesta;

import interfaces.ICommand;

public class ComandoPartidaGanada implements ICommand{
    
    private String type = "ComandoPartidaGanada";
    private String nombreJugador;
    private String mensaje;

    public ComandoPartidaGanada(String nombreJugador, String mensaje) {
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
