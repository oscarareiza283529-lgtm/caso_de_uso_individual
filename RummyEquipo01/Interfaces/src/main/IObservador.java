public interface IObservador {
    /**
     * Notifica a la vista sobre un cambio o mensaje recibido.
     * @param comando El comando o mensaje que causó la actualización.
     */
    void actualizar(Object comando);
}