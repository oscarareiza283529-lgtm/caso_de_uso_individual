
package comandosRespuesta;

import dto.TableroDTO;
import interfaces.ICommand;


/**
 *
 * Comando de respuesta que notifica el cambio de turno hacia el jugador indicado.
 * 
 * @author pedro
 */
public class ComandoCambioTurno implements ICommand {
    
    private String nombreJugador;
    private final String type = "ComandoCambioTurno";
    private TableroDTO tablero;
    private String mensaje;

    public ComandoCambioTurno(TableroDTO tablero, String nombreJugador, String mensaje) {
        this.nombreJugador = nombreJugador;
        this.tablero = tablero;
        this.mensaje = mensaje;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    @Override
    public String getType() {
        return type;
    }

    public TableroDTO getTablero() {
        return tablero;
    }
    
}
