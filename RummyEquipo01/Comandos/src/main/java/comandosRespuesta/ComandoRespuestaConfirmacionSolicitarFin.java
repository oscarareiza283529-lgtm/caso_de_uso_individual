
package comandosRespuesta;

import interfaces.ICommand;


public class ComandoRespuestaConfirmacionSolicitarFin implements ICommand{
    
    private String type = "ComandoRespuestaConfirmacionSolicitarFin";
    private String nombreJugador;
    private String mensaje;

    public ComandoRespuestaConfirmacionSolicitarFin(String nombreJugador, String mensaje) {
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
