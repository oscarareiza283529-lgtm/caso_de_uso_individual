package comunicacion;

import dto.TableroNegocioDTO;


/**
 * Contrato que define qué debe hacer una vista cuando recibe una notificación.
 */
public interface IComunicacion {
    
    public abstract void inicioTurno(TableroNegocioDTO tableroNegocio);
    
    public abstract void avisarFinalizacionTurno(TableroNegocioDTO tableroNegocio);
    
}
