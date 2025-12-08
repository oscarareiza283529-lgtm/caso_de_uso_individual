package directorioServidor;

import comandoEnvolvente.ComandoEnvolvente;
import interfaces.ICommand;
import interfaces.IDispatcher;
import interfaces.IFiltro;

/**
 *
 * @author pedro
 * 
 * Filtro que recibe un comando envolvente, le saca el mensaje ya serializado
 * y se lo pasa al dispatcher para enviarlo.
 * 
 */
public class DirectorioServidor implements IFiltro {

    private String[] direccion;
    private IDispatcher dispatcher;

    
    
    
    public DirectorioServidor(String[] direccion) {
        this.direccion = direccion;
    }

    @Override
    public void ejecutar(ICommand comando) {
        ComandoEnvolvente envolvente = (ComandoEnvolvente) comando;
        dispatcher.agregarMensaje(envolvente.getMensajeSerializado(), direccion);
    }

    public void setDispatcher(IDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    

}
