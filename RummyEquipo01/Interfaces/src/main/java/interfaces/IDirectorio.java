
package interfaces;

/**
 * Interfaz de Directorio para evitar problemas. 
 * @author juanpheras
 */
public interface IDirectorio {
    
    /***
     * Método para agregar a un jugador al directorio de jugadores registrados.
     * @param nombreJugador nombre del jugador a agregar.
     * @param direccion arreglo de Strings donde contiene su IP y el puerto.
     * */
    abstract void agregarJugador(String nombreJugador, String[] direccion);
    
    /**
     * Método para enviar mensaje a los Jugadores.
     * @param respuesta que se envia al jugador que envio el mensaje.
     */
    abstract void enviarMensajeJugadores(String respuesta);
    
    
}
