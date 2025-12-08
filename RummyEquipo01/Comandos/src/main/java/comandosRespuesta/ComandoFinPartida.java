
package comandosRespuesta;

import interfaces.ICommand;


public class ComandoFinPartida implements ICommand{
    
    private String type = "ComandoFinPartida";
    
    private String nombreJugador;

    public ComandoFinPartida(String nombreJugador) {
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
