
package comandosSolicitud;

import interfaces.ICommand;


public class ComandoTomarFicha implements ICommand{
    
    private final String type = "ComandoTomarFicha";
    private String nombreJugador;
    
    public ComandoTomarFicha(String nombreJugador) {
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
