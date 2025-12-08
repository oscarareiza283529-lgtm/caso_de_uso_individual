
package objetos_negocio;

import comandosSolicitud.ComandoAbandonar;
import comandosSolicitud.ComandoAgregarFichasJugador;
import comandosSolicitud.ComandoAgregarFichasTablero;
import comandosSolicitud.ComandoAgregarFichasTableroGrupo;
import comandosSolicitud.ComandoConfirmacionAbandonar;
import comandosSolicitud.ComandoConfirmacionSolicitarFin;
import comandosSolicitud.ComandoQuitarFichasJugador;
import comandosSolicitud.ComandoQuitarFichasTablero;
import comandosSolicitud.ComandoReestablecerTablero;
import comandosSolicitud.ComandoSeleccionarFichasTablero;
import comandosSolicitud.ComandoSolicitarFin;
import comandosSolicitud.ComandoTerminarTurno;
import comandosSolicitud.ComandoTomarFicha;
import comandosSolicitud.CommandType;
import excepciones.RummyException;
import interfaces.ICommand;

/**
 *
 */
public class Partida {
    
    private Tablero tablero;

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    public void ejecutar(ICommand comando) throws RummyException {

        CommandType tipoComando = CommandType.fromNombre(comando.getType());

        switch (tipoComando) {

            case CommandType.SELECCIONAR_FICHAS_TABLERO:

                ComandoSeleccionarFichasTablero comandoSeleccionarFichasTablero = (ComandoSeleccionarFichasTablero) comando;

                tablero.seleccionarFichasTablero(
                        comandoSeleccionarFichasTablero.getIdsFichas(),
                        comandoSeleccionarFichasTablero.getNombreJugador()
                );

                break;

            case CommandType.AGREGAR_FICHAS_TABLERO:

                ComandoAgregarFichasTablero comandoAgregarFichasTablero = (ComandoAgregarFichasTablero) comando;

                tablero.agregarFichasTablero(
                        comandoAgregarFichasTablero.getIdsFichas(),
                        comandoAgregarFichasTablero.getNombreJugador()
                );

                break;

            case CommandType.AGREGAR_FICHAS_JUGADOR:

                ComandoAgregarFichasJugador comandoAgregarFichasJugador = (ComandoAgregarFichasJugador) comando;

                tablero.agregarFichasJugador(
                        comandoAgregarFichasJugador.getIdsFichas(),
                        comandoAgregarFichasJugador.getNombreJugador()
                );

                break;

            case CommandType.AGREGAR_FICHAS_TABLERO_GRUPO:

                ComandoAgregarFichasTableroGrupo comandoAgregarFichasTableroGrupo = (ComandoAgregarFichasTableroGrupo) comando;

                tablero.agregarFichasTableroGrupos(
                        comandoAgregarFichasTableroGrupo.getIdsFichas(),
                        comandoAgregarFichasTableroGrupo.getIdsFichasGrupo(),
                        comandoAgregarFichasTableroGrupo.getNombreJugador());

                break;

            case CommandType.QUITAR_FICHAS_JUGADOR:

                ComandoQuitarFichasJugador comandoQuitarFichasJugador = (ComandoQuitarFichasJugador) comando;

                tablero.quitarFichasJugador(
                        comandoQuitarFichasJugador.getIdsFichas(),
                        comandoQuitarFichasJugador.getNombreJugador());

                break;

            case CommandType.QUITAR_FICHAS_TABLERO:

                ComandoQuitarFichasTablero comandoQuitarFichasTablero = (ComandoQuitarFichasTablero) comando;

                tablero.quitarFichasTablero(
                        comandoQuitarFichasTablero.getIdsFichas(),
                        comandoQuitarFichasTablero.getNombreJugador());

                break;

            case CommandType.TOMAR_FICHA:
                
                ComandoTomarFicha comandoTomarFicha = (ComandoTomarFicha) comando;

                tablero.tomarFicha(comandoTomarFicha.getNombreJugador());
                
                break;
                
            case CommandType.RESTABLECER_TABLERO:
                
                ComandoReestablecerTablero comandoReestablecerTablero = (ComandoReestablecerTablero) comando;

                tablero.reestablecerTablero(comandoReestablecerTablero.getNombreJugador());
                
                break;
                
            case CommandType.TERMINAR_TURNO:

                ComandoTerminarTurno comandoTerminarTurno = (ComandoTerminarTurno) comando;

                tablero.terminarTurno(comandoTerminarTurno.getNombreJugador());

                break;

            case CommandType.COMANDO_ABANDONAR:

                ComandoAbandonar comandoAbandonar = (ComandoAbandonar) comando;

                tablero.solicitarAbandono(comandoAbandonar.getNombreJugador());

                break;
                
            case CommandType.COMANDO_CONFIRMACION_ABANDONAR:

                ComandoConfirmacionAbandonar comandoConfirmacionAbandonar = (ComandoConfirmacionAbandonar) comando;

                tablero.confirmarAbandono(
                        comandoConfirmacionAbandonar.getNombreJugador(),
                        comandoConfirmacionAbandonar.isConfirmacion());

                break;
                
            case CommandType.COMANDO_SOLICITAR_FIN:

                ComandoSolicitarFin comandoSolicitarFin = (ComandoSolicitarFin) comando;

                tablero.solicitarFin(comandoSolicitarFin.getNombreJugador());

                break;
                
            case CommandType.COMANDO_CONFIRMACION_SOLICITAR_FIN:

                ComandoConfirmacionSolicitarFin comandoConfirmacionSolicitarFin = (ComandoConfirmacionSolicitarFin) comando;

                tablero.confirmarSolicitarFin(
                        comandoConfirmacionSolicitarFin.getNombreJugador(), 
                        comandoConfirmacionSolicitarFin.isConfirmacion());

                break;
                
            default:
                throw new AssertionError();
        }

    }
}
