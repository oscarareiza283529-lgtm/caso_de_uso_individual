
package comandosSolicitud;

import interfaces.ICommand;


public class ComandoAbandonar implements ICommand{
    private String type = "ComandoAbandonar";
    private String nombreJugador;

    public ComandoAbandonar(String nombreJugador) {
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
