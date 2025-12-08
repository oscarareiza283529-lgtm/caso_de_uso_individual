
package comandosRespuesta;

import comandosSolicitud.CommandType;
import dto.TableroDTO;
import interfaces.ICommand;

/**
 *
 * Comando de respuesta que envía el tablero restablecido al jugador.
 * 
 * @author pedro
 */
public class ComandoRespuestaReestablecer implements ICommand {
    private TableroDTO tablero;
    private final String type = "ComandoReestablecerRespuesta";
    private String nombreJugador;

    public ComandoRespuestaReestablecer(TableroDTO tablero, String nombreJugador) {
        this.tablero = tablero;
        this.nombreJugador = nombreJugador;
    }

    public TableroDTO getTablero() {
        return tablero;
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
