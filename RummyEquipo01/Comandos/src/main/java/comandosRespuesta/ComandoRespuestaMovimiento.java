
package comandosRespuesta;

import comandosSolicitud.CommandType;
import dto.TableroDTO;
import interfaces.ICommand;

/**
 *
 * Comando de respuesta que informa si el movimiento fue válido y envía el tablero actualizado.
 * 
 * @author pedro
 */
public class ComandoRespuestaMovimiento implements ICommand{
    private TableroDTO tablero;
    private boolean movimientoValido;
    private final String type = "ComandoRespuestaMovimiento";
    private String nombreJugador;
    private String mensaje;

    public ComandoRespuestaMovimiento(TableroDTO tablero, boolean movimientoValido, String nombreJugador, String mensaje) {
        this.tablero = tablero;
        this.movimientoValido = movimientoValido;
        this.nombreJugador = nombreJugador;
        this.mensaje = mensaje;
    }
    
    public ComandoRespuestaMovimiento(TableroDTO tablero, boolean movimientoValido, String nombreJugador) {
        this.tablero = tablero;
        this.movimientoValido = movimientoValido;
        this.nombreJugador = nombreJugador;
    }

    public TableroDTO getTablero() {
        return tablero;
    }

    public boolean isMovimientoValido() {
        return movimientoValido;
    }

    public String getMensaje() {
        return mensaje;
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
