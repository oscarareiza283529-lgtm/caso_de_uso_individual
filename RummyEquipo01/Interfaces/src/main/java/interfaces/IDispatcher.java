
package interfaces;

/**
 * Interfaz de dispatcher que envía los mensajes.
 * @author juanpheras
 */
public interface IDispatcher {
    
    /**
     * Método para agregar un mensaje a la cola para enviarlo.
     * @param mensaje mensaje en JSON a añadir a la cola.
     * @param direccion arreglo de Strings donde contiene su IP y el puerto.
     */
    abstract void agregarMensaje(String mensaje, String[] direccion);
}
