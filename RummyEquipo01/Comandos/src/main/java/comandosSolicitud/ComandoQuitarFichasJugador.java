
package comandosSolicitud;

import interfaces.ICommand;

/**
 *
 * Comando que pide quitar las fichas indicadas del jugador.
 * 
 * @author pedro
 */
public class ComandoQuitarFichasJugador implements ICommand{
    private String type = "ComandoQuitarFichasJugador";
    private String nombreJugador;
    
    private Integer[] idsFichas;

    public ComandoQuitarFichasJugador(Integer[] idsFichas, String nombreJugador) {
        this.idsFichas = idsFichas;
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

    public Integer[] getIdsFichas() {
        return idsFichas;
    }
    
    
    
    
}
