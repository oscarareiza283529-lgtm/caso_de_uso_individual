
package comandosSolicitud;

import interfaces.ICommand;

public class ComandoConfirmacionSolicitarFin implements ICommand{
    
    private String nombreJugador;
    private final String type = "ComandoConfirmacionSolicitarFin";
    private boolean confirmacion;

    public ComandoConfirmacionSolicitarFin(String nombreJugador, boolean confirmacion) {
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
