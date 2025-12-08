
package interfaces;

/**
 * Interfaz de Command para implementar en comandos concretos.
 * @author Juan Heras
 */
public interface ICommand {
    
    abstract String getType();
    
    abstract String getNombreJugador();
}
