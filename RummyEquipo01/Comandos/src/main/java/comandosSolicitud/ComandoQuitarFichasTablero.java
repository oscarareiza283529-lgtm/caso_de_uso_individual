
package comandosSolicitud;

import interfaces.ICommand;

/**
 *
 * Comando que solicita quitar fichas del tablero y del grupo indicado para un jugador.
 * 
 * @author pedro
 */
public class ComandoQuitarFichasTablero implements ICommand{
    private Integer[] idsFichas;
    private final String type = "ComandoQuitarFichasTablero";
    private String nombreJugador;

    public ComandoQuitarFichasTablero(Integer[] idFichas, String nombreJugador) {
        this.idsFichas = idFichas;
        this.nombreJugador = nombreJugador;
    }

    public Integer[] getIdsFichas() {
        return idsFichas;
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
