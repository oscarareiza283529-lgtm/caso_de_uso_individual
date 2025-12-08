
package comandosSolicitud;

import interfaces.ICommand;

/**
 *
 * Comando que agrega las fichas indicadas al tablero para el jugador.
 * 
 * @author pedro
 */
public class ComandoAgregarFichasTablero implements ICommand{
    
    private Integer[] idsFichas;
    private final String type = "ComandoAgregarFichasTablero";
    private String nombreJugador;

    public ComandoAgregarFichasTablero(Integer[] idFichas, String nombreJugador) {
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
