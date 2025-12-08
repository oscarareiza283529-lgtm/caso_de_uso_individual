package directorio;

import comandoAgregarDireccionJugador.ComandoAgregarDireccionJugador;
import comandoEnvolvente.ComandoEnvolvente;
import comandosSolicitud.CommandType;
import interfaces.ICommand;
import interfaces.IDispatcher;
import interfaces.IFiltro;
import java.util.Map;

/**
 *
 * Filtro encargado de enviar el mensaje de un comando envolvente a la
 * dirección del jugador correspondiente. 
 * 
 * 
 * 
 * @author ramon
 */
public class DirectorioJugadores implements IFiltro {

    private Map<String, String[]> jugadoresDirecciones =
    Map.of(
        "Francisco34", new String[]{"127.0.0.1", "51000"},
        "Sandy43", new String[]{"127.0.0.1", "52000"}
    );
    private IDispatcher dispatcher;

    public void agregarJugador(String nombreJugador, String[] direccion) {
        jugadoresDirecciones.put(nombreJugador, direccion);
    }

    public void setDispatcher(IDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public void agregarDireccionNombreJugador(String nombreJugador, String[] direccionJugador){
        
        jugadoresDirecciones.put(nombreJugador, direccionJugador);
        
    }

    @Override
    public void ejecutar(ICommand comando) {
        
        CommandType tipoComando = CommandType.fromNombre(comando.getType());
        
        switch (tipoComando) {

            case CommandType.COMANDO_AGREGAR_DIRECCION_JUGADOR:

                ComandoAgregarDireccionJugador comandoAgregarDireccionJugador = (ComandoAgregarDireccionJugador) comando;

                agregarDireccionNombreJugador(comandoAgregarDireccionJugador.getNombreJugador(), comandoAgregarDireccionJugador.getDireccion());

                break;

            case CommandType.COMANDO_ENVOLVENTE:

                ComandoEnvolvente envolvente = (ComandoEnvolvente) comando;

                dispatcher.agregarMensaje(envolvente.getMensajeSerializado(), jugadoresDirecciones.get(comando.getNombreJugador()));

                break;
        }
    }

}
