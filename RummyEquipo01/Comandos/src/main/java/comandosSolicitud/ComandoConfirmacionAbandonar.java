
package comandosSolicitud;

import interfaces.ICommand;


public class ComandoConfirmacionAbandonar implements ICommand{
    
    private String nombreJugador;
    private final String type = "ComandoConfirmacionAbandonar";
    private boolean confirmacion;

    public ComandoConfirmacionAbandonar(String nombreJugador, boolean confirmacion) {
        this.nombreJugador = nombreJugador;
        this.confirmacion = confirmacion;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public boolean isConfirmacion() {
        return confirmacion;
    }

    @Override
    public String getType() {
        return type;
    }
    
}
