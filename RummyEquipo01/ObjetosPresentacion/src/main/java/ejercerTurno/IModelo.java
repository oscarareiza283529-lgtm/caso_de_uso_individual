
package ejercerTurno;

import dto.JugadorExternoPresentacionDTO;
import dto.JugadorPrincipalPresentacionDTO;
import dto.MontonPresentacionDTO;
import dto.TableroPresentacionDTO;


public interface IModelo {
    
    public abstract JugadorPrincipalPresentacionDTO obtenerJugadorPrincipal();
    
    public abstract JugadorExternoPresentacionDTO[] obtenerJugadoresExternos();
    
    public abstract MontonPresentacionDTO obtenerMontonPresentacion();
    
    public abstract TableroPresentacionDTO obtenerTablero();
    
    public abstract String obtenerMensaje();
    
    public abstract boolean isVistaHabilitada();
    
    public abstract boolean isTableroInvalido();
    
    public abstract boolean isMovimientoInvalido();
    
    public abstract boolean isNuevoTurno();
    
}
