
package interfaces;

/**
 * Interfaz de Filtro para implementar en filtros concretos y usar el método ejecutar.
 * @author Juan Heras
 */
public interface IFiltro {
    
    abstract void ejecutar(ICommand comando);
}
