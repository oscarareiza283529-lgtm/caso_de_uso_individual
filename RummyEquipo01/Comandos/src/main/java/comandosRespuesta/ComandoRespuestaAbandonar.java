
package comandosRespuesta;

import interfaces.ICommand;


public class ComandoRespuestaAbandonar implements ICommand{
    
    private String type = "ComandoRespuestaAbandonar";
    private String nombreJugador;
    private String mensaje;

    public ComandoRespuestaAbandonar(String nombreJugador, String mensaje) {
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
