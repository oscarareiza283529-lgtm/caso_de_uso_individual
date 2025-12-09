

package UnirsePartida.logica;

/**
 * Interfaz para implementar el patrón Observador.
 * Debe ser PUBLIC para que FrmInicio pueda verla.
 */
public interface IObservador {
    
    /**
     * Notifica a la vista sobre un cambio o mensaje recibido.
     * @param comando El comando o mensaje que causó la actualización.
     */
    void actualizar(Object comando);
}
