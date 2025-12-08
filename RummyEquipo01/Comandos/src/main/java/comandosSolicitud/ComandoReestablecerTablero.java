
package comandosSolicitud;

import interfaces.ICommand;

/**
 *
 * Comando que solicita restablecer el tablero para el jugador indicado.
 * 
 * @author pedro
 */
public class ComandoReestablecerTablero implements ICommand{
    private final String type = "ComandoReestablecerTablero";
    private String nombreJugador;
    
    public ComandoReestablecerTablero(String nombreJugador) {
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
