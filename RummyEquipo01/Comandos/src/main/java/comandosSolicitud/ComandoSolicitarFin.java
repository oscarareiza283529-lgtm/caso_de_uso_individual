
package comandosSolicitud;

import interfaces.ICommand;


public class ComandoSolicitarFin implements ICommand{
    
    private String type = "ComandoSolicitarFin";
    private String nombreJugador;

    public ComandoSolicitarFin(String nombreJugador) {
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
