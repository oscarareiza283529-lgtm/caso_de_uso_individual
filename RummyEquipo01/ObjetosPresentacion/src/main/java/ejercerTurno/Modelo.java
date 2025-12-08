package ejercerTurno;

import comandosRespuesta.ComandoCambioTurno;
import comandosRespuesta.ComandoFinPartida;
import comandosRespuesta.ComandoIniciarTurno;
import comandosRespuesta.ComandoJugadorAbandonoPartida;
import comandosRespuesta.ComandoJugadorPartidaGanada;
import comandosRespuesta.ComandoPartidaGanada;
import comandosRespuesta.ComandoRespuestaAbandonar;
import comandosRespuesta.ComandoRespuestaConfirmacionSolicitarFin;
import comandosRespuesta.ComandoRespuestaMovimiento;
import comandosRespuesta.ComandoRespuestaReestablecer;
import comandosRespuesta.ComandoRespuestaSolicitarFin;
import comandosRespuesta.ComandoRespuestaTomarFicha;
import comandosRespuesta.ComandoTableroInvalido;
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
import dto.ColorFichaPresentacionDTO;
import dto.FichaPresentacionDTO;
import dto.JugadorExternoPresentacionDTO;
import dto.JugadorPrincipalPresentacionDTO;
import dto.MontonPresentacionDTO;
import dto.TableroPresentacionDTO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import dto.ComodinPresentacionDTO;
import dto.FichaComodinDTO;
import dto.FichaDTO;
import dto.FichaNormalDTO;
import dto.FichaNormalPresentacionDTO;
import dto.GrupoDTO;
import dto.JugadorDTO;
import dto.MontonDTO;
import dto.TableroDTO;
import enumeradores.ColorFichaDTO;

import interfaces.ICommand;
import interfaces.IFiltro;

/**
 * Clase Modelo que representa la parte lógica del patrón MVC para el caso de
 * uso "Ejercer Turno".
 *
 * Se encarga de coordinar la comunicación entre la vista y la capa de negocio
 * (a través de la fachada), manejar el estado actual del tablero y notificar a
 * las vistas suscritas cuando hay cambios.
 *
 * En esta versión, la fachada funciona como un mock que simula la lógica del
 * tablero real, permitiendo probar el flujo completo del caso de uso sin
 * depender todavía de las entidades finales.
 */
public class Modelo implements IPublicador, IModelo, IFiltro {
    
    private final String CODIGO_MENSAJE_INCIO_TURNO = "IT: ";
    private final String CODIGO_MENSAJE_CAMBIO_TURNO = "CT: ";
    private final String CODIGO_MENSAJE_RESPUESTA_MOVIMIENTO = "RM: ";
    private final String CODIGO_MENSAJE_TABLERO_INVALIDO = "TI: ";
    private final String CODIGO_MENSAJE_SOLICITUD_ABANDONO = "RA: ";
    private final String CODIGO_MENSAJE_ABANDONO_JUGADOR = "JA: ";
    private final String CODIGO_MENSAJE_RESPONDER_SOLICITUD_FIN = "RF: ";
    private final String CODIGO_MENSAJE_RESPONDER_CONFIRMACION_SOLICITUD_FIN = "RC: ";
    private final String CODIGO_MENSAJE_PARTIDA_GANADA = "PG: ";
    private final String CODIGO_MENSAJE_JUGADOR_PARTIDA_GANADA = "JG: ";
    
    /**
     * Lista de suscriptores del modelo para notificar cambios a la vista.
     */
    private List<ISuscriptor> suscriptores = new ArrayList<>();
    
    /**
     * Indica si el tablero actual es inválido.
     */
    
    private boolean tableroInvalido;
    /**
     * Indica si la vista está habilitada para interactuar.
     */
    
    private boolean vistaHabilitada;
    /**
     * Indica si el último movimiento realizado fue inválido.
     */
    
    private boolean movimientoInvalido;
    
    private boolean nuevoTurno;
    
    private String mensaje;
    
    private TableroDTO tablero;
    
    /**
     * Filtro al que se enviará la solicitud.
     */
    private IFiltro filtroEnvioMensaje;
    
//    private TableroDTO tableroQuitar;
    
    /**
     * Nombre del jugador.
     */
    private String nombreJugador;
    
    public Modelo(String nombreJugador){
        this.nombreJugador = nombreJugador;
    }

    public void setFiltroEnvioMensaje(IFiltro filtroEnvioMensaje) {
        this.filtroEnvioMensaje = filtroEnvioMensaje;
    }

    /**
     * Obtiene la lista de vistas suscritas al modelo.
     *
     * @return lista de objetos que implementan {@link ISuscriptor}.
     */
    public List<ISuscriptor> getSuscriptores() {
        return suscriptores;
    }

    /**
     * Asigna la lista de vistas suscritas al modelo.
     *
     * @param suscriptores lista de suscriptores.
     */
    public void setSuscriptores(List<ISuscriptor> suscriptores) {
        this.suscriptores = suscriptores;
    }

    /**
     * Indica si el tableroQuitar es inválido.
     *
     * @return {@code true} si el tableroQuitar no cumple las reglas, {@code false} en
     * caso contrario.
     */
    @Override
    public boolean isTableroInvalido() {
        return tableroInvalido;
    }

    /**
     * Define si el tableroQuitar es inválido.
     *
     * @param tableroInvalido valor booleano indicando si el tableroQuitar es
 inválido.
     */
    public void setTableroInvalido(boolean tableroInvalido) {
        this.tableroInvalido = tableroInvalido;
    }

    /**
     * Indica si la vista está habilitada para interactuar.
     *
     * @return {@code true} si está habilitada, {@code false} en caso contrario.
     */
    public boolean isVistaHabilitado() {
        return vistaHabilitada;
    }

    /**
     * Define si la vista está habilitada para la interacción del jugador.
     *
     * @param vistaHabilitado valor booleano que indica si la vista está activa.
     */
    public void setVistaHabilitado(boolean vistaHabilitado) {
        this.vistaHabilitada = vistaHabilitado;
    }

    /**
     * Indica si el último movimiento fue inválido.
     *
     * @return {@code true} si el movimiento no fue válido, {@code false} en
     * caso contrario.
     */
    @Override
    public boolean isMovimientoInvalido() {
        return movimientoInvalido;
    }

    /**
     * Define si el último movimiento fue inválido.
     *
     * @param movimientoInvalido valor booleano que indica si el movimiento fue
     * inválido.
     */
    public void setMovimientoInvalido(boolean movimientoInvalido) {
        this.movimientoInvalido = movimientoInvalido;
    }

    /**
     * Simula la selección de fichas del tableroQuitar.
     *
     * @param posicionesFichas arreglo con las posiciones seleccionadas.
     */
    public void seleccionarFichasTablero(Integer[] posicionesFichas) {
        
        ICommand comandoSeleccionarFichasTablero = new ComandoSeleccionarFichasTablero(posicionesFichas, nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoSeleccionarFichasTablero);
        
    }

    /**
     * Simula la acción de quitar fichas del jugador actual.
     *
     * @param posicionesFichas arreglo de posiciones de fichas a eliminar.
     */
    public void quitarFichasJugador(Integer[] posicionesFichas) {
        
        ICommand comandoQuitarFichasJugador = new ComandoQuitarFichasJugador(posicionesFichas, nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoQuitarFichasJugador);
        
    }

    /**
     * Quita fichas del tableroQuitar según los identificadores proporcionados.
     *
     * @param idFichas colección de IDs de fichas a eliminar.
     */
    public void quitarFichasTablero(Integer[] idsFichas) {
        
        ICommand comandoQuitarFichasTablero = new ComandoQuitarFichasTablero(idsFichas, nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoQuitarFichasTablero);

    }

    /**
     * Agrega fichas a un grupo ya existente del jugador.
     *
     * @param idsFichas colección de IDs de fichas a agregar.
     * @param numeroGrupo número del grupo al que se agregarán las fichas.
     */
    public void agregarFichasJugador(Integer[] idsFichas) {
        
        ICommand comandoAgregarFichasJugador = new ComandoAgregarFichasJugador(idsFichas, nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoAgregarFichasJugador);
        
    }
    
    /**
     * Agrega fichas a un grupo ya existente del tablero.
     *
     * @param idsFichas colección de IDs de fichas a agregar.
     * @param numeroGrupo número del grupo al que se agregarán las fichas.
     */
    public void agregarFichasTablero(Integer[] idsFichas, Integer[] idsFichasGrupo) {
        
        ICommand comandoAgregarFichasTableroGrupo = new ComandoAgregarFichasTableroGrupo(idsFichas, idsFichasGrupo, nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoAgregarFichasTableroGrupo);
        
    }

    /**
     * Agrega fichas al tableroQuitar que no pertenecen a un grupo existente.
     *
     * @param idsFichas colección de IDs de fichas a agregar.
     */
    public void agregarFichasTablero(Integer[] idsFichas) {
        
        ICommand comandoAgregarFichasTablero = new ComandoAgregarFichasTablero(idsFichas, nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoAgregarFichasTablero);
        
    }
    
    public void tomarFicha(){
        
        ICommand comandoTomarFicha = new ComandoTomarFicha(nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoTomarFicha);
        
    }
    
    public void reestablecerTablero(){
        
        ICommand comandoReestablecerTablero = new ComandoReestablecerTablero(nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoReestablecerTablero);
    }
    
    /**
     * Finaliza el turno actual y pasa al siguiente jugador. Si el tablero no es
     * válido, se marca el estado correspondiente.
     */
    public void terminarTurno() {
        
        ICommand comandoFinalizarTurno = new ComandoTerminarTurno(nombreJugador);
        filtroEnvioMensaje.ejecutar(comandoFinalizarTurno);

    }
    
    public void abandonarPartida(){
        
        ComandoAbandonar comandoAbandonar = new ComandoAbandonar(nombreJugador);
        filtroEnvioMensaje.ejecutar(comandoAbandonar);
        
    }
    
    public void finalizarPartida(){
        
        ComandoSolicitarFin comandoSolicitarFin = new ComandoSolicitarFin(nombreJugador);
        
        filtroEnvioMensaje.ejecutar(comandoSolicitarFin);
        
    }
    
    public void confirmarAbandonoPartida(boolean confirmacion){
        
        ComandoConfirmacionAbandonar comandoConfirmacionAbandonar 
                = new ComandoConfirmacionAbandonar(nombreJugador, confirmacion);
        
        filtroEnvioMensaje.ejecutar(comandoConfirmacionAbandonar);
        
    }
    
    public void confirmarSolicitudFin(boolean confirmacion){
        
        ComandoConfirmacionSolicitarFin comandoConfirmacionSolicitarFin 
                = new ComandoConfirmacionSolicitarFin(nombreJugador, confirmacion);
        
        filtroEnvioMensaje.ejecutar(comandoConfirmacionSolicitarFin);
        
    }
    

    public void iniciarTurno(TableroDTO tablero, String mensaje){
    
        this.nuevoTurno = true;
        this.tablero = tablero;
        vistaHabilitada = true;
        this.mensaje = CODIGO_MENSAJE_INCIO_TURNO + mensaje;
        
        notificar();
        
    }
    
    private void cambiarTurno(TableroDTO tablero, String mensaje){
        
        this.nuevoTurno = true;
        this.tablero = tablero;
        vistaHabilitada = false;
        this.mensaje = CODIGO_MENSAJE_CAMBIO_TURNO + mensaje;
        
        notificar();
        
    }
    
    private void responderMovimiento(TableroDTO tablero, boolean movimientoValido, String mensaje){
        
        this.nuevoTurno = false;
        this.tablero = tablero;
        this.movimientoInvalido = !movimientoValido;
        
        if(mensaje != null){
            this.mensaje = CODIGO_MENSAJE_RESPUESTA_MOVIMIENTO + mensaje;
        } else{
            this.mensaje = null;
        }
        
        notificar();
    }
    
    private void tomarFicha(TableroDTO tablero){
        
        this.nuevoTurno = false;
        this.tablero = tablero;
        this.movimientoInvalido = false;
        
        this.mensaje = null;
        
        notificar();
        
    }
    
    private void reestablecerTablero(TableroDTO tablero){
        
        this.nuevoTurno = true;
        this.tablero = tablero;
        this.movimientoInvalido = false;
        
        this.mensaje = null;
        
        notificar();
        
    }
    
    private void avisarTableroInvalido(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = true;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_TABLERO_INVALIDO + mensaje;
        
        notificar();
        
    }
    
    private void responderSolicitudAbandono(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_SOLICITUD_ABANDONO + mensaje;
        
        notificar();
        
    }
   
    private void notificarAbandonoJugador(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_ABANDONO_JUGADOR + mensaje;
        
        notificar();
        
    }
    
    public void responderSolicitudFin(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_RESPONDER_SOLICITUD_FIN + mensaje;
        
        notificar();
        
    }
    
    private void responderConfirmacionSolicitudFin(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_RESPONDER_CONFIRMACION_SOLICITUD_FIN + mensaje;
        
        notificar();
        
    }
    
    private void notificarPartidaGanada(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_PARTIDA_GANADA + mensaje;
        
        notificar();
        
    }
    
    private void notificarJugadorPartidaGanada(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_JUGADOR_PARTIDA_GANADA + mensaje;
        
        notificar();
        
    }
    
    private void terminarJuego(){
        
        System.exit(0);
        
    }
    
    
    /**
     * Suscribe una nueva vista (suscriptor) al modelo.
     *
     * @param suscriptor instancia que implementa {@link ISuscriptor}.
     */
    @Override
    public void suscribirse(ISuscriptor suscriptor) {
        suscriptores.add(suscriptor);
    }

    /**
     * Elimina una vista (suscriptor) de la lista de notificación.
     *
     * @param suscriptor instancia que implementa {@link ISuscriptor}.
     */
    @Override
    public void desuscribirse(ISuscriptor suscriptor) {
        suscriptores.remove(suscriptor);
    }

    /**
     * Notifica a todas las vistas suscritas los cambios realizados en el
     * modelo.
     */
    @Override
    public void notificar() {
        for (ISuscriptor suscriptor : suscriptores) {
            suscriptor.actualizar(this);
        }
    }

    @Override
    public JugadorPrincipalPresentacionDTO obtenerJugadorPrincipal() {
        
        List<FichaDTO> fichasDTO = tablero.getJugadorTurno().getFichas();
        
        List<FichaPresentacionDTO> fichasPresentacion = new LinkedList<>();
        
        for (FichaDTO fichaDTO : fichasDTO) {
            
            FichaPresentacionDTO fichaPresentacion = obtenerFichaPresentacionDTO(fichaDTO);
            fichasPresentacion.add(fichaPresentacion);
            
        }
        
        FichaPresentacionDTO[] fichasArreglo = fichasPresentacion.toArray(new FichaPresentacionDTO[0]);
        
        return new JugadorPrincipalPresentacionDTO(fichasArreglo);
    }

    @Override
    public MontonPresentacionDTO obtenerMontonPresentacion() {
        
        return obtenerMontonPresentacionDTO(tablero.getMonton());
        
    }
    
    private MontonPresentacionDTO obtenerMontonPresentacionDTO(MontonDTO montonDTO){
        
        return new MontonPresentacionDTO(montonDTO.getCantidadFichas());
        
    }

    @Override
    public String obtenerMensaje() {
        
        return mensaje;

    }

    @Override
    public boolean isVistaHabilitada() {
        return vistaHabilitada;
    }
    
    @Override
    public boolean isNuevoTurno() {
        return nuevoTurno;
    }

    @Override
    public JugadorExternoPresentacionDTO[] obtenerJugadoresExternos() {
        
        JugadorDTO[] jugadores = tablero.getJugadores();
        
        List<JugadorExternoPresentacionDTO> jugadoresExternosPresentacionDTO = new LinkedList<>();
        
        for(JugadorDTO jugador: jugadores){

            List<FichaDTO> fichasDTO = jugador.getFichas();
           
            int cantidadFichasRestante = fichasDTO.size();
            
            jugadoresExternosPresentacionDTO.add(new JugadorExternoPresentacionDTO(
                    jugador.getAvatar(), 
                    jugador.getNombre(),
                    cantidadFichasRestante));
            
        }

        JugadorExternoPresentacionDTO[] jugadoresExternosPresentacion 
                = jugadoresExternosPresentacionDTO.toArray(new JugadorExternoPresentacionDTO[0]);
        
        return jugadoresExternosPresentacion;
        
 
    }

    @Override
    public TableroPresentacionDTO obtenerTablero() {
        
        GrupoDTO[] grupos = tablero.getGrupos();
        
        List<FichaPresentacionDTO> fichasPresentacion = new LinkedList<>();
        
        for(GrupoDTO grupo: grupos){
            
            List<FichaDTO> fichasDTO = grupo.getFichas();
            
            fichasPresentacion.add(null);
            for(FichaDTO fichaDTO: fichasDTO){
                
                fichasPresentacion.add(obtenerFichaPresentacionDTO(fichaDTO));
                
            }
            
        }
        
        TableroPresentacionDTO tableroPresentacion 
                = new TableroPresentacionDTO(fichasPresentacion.toArray(new FichaPresentacionDTO[0]));
        
        return tableroPresentacion;
        
    }
    
    private FichaPresentacionDTO obtenerFichaPresentacionDTO(FichaDTO fichaDTO){

        FichaPresentacionDTO fichaPresentacionDTO;
        
        if(fichaDTO instanceof FichaNormalDTO){
            
            FichaNormalDTO fichaNormalDTO = (FichaNormalDTO) fichaDTO;
            
            ColorFichaDTO colorFicha = fichaNormalDTO.getColor();
            ColorFichaPresentacionDTO colorFichaPresentacion;

            switch (colorFicha) {

                case ColorFichaDTO.COLOR_A:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_A;
                    break;
                case ColorFichaDTO.COLOR_B:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_B;
                    break;   
                case ColorFichaDTO.COLOR_C:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_C;
                    break;  
                case ColorFichaDTO.COLOR_D:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_D;
                    break;

                default:
                    throw new AssertionError();
            }
        
        fichaPresentacionDTO = new FichaNormalPresentacionDTO(
                fichaNormalDTO.getNumero(), 
                fichaNormalDTO.getId(),
                colorFichaPresentacion);
        
            
        } else{
            
            FichaComodinDTO fichaComodinDTO = (FichaComodinDTO) fichaDTO;
             
            fichaPresentacionDTO = new ComodinPresentacionDTO(
                fichaComodinDTO.getValor(), 
                fichaComodinDTO.getId());
            
        }
        
        
        
        return fichaPresentacionDTO;
        
    }

    @Override
    public void ejecutar(ICommand comando) {

        
        CommandType tipoComando = CommandType.fromNombre(comando.getType());
        
        switch (tipoComando) {
            case CommandType.INICIAR_TURNO:
                
                ComandoIniciarTurno comandoIniciarTurno = (ComandoIniciarTurno) comando;
                iniciarTurno( 
                        comandoIniciarTurno.getTablero(), 
                        comandoIniciarTurno.getMensaje());
                
                break;
                
            case CommandType.CAMBIO_TURNO:
                
                ComandoCambioTurno comandoCambioTurno = (ComandoCambioTurno) comando;
                cambiarTurno(
                        comandoCambioTurno.getTablero(),
                        comandoCambioTurno.getMensaje());

                break;
                
            case CommandType.RESPUESTA_MOVIMIENTO:
                
                ComandoRespuestaMovimiento comandoRespuestaMovimiento = (ComandoRespuestaMovimiento) comando;
                
                responderMovimiento(
                        comandoRespuestaMovimiento.getTablero(), 
                        comandoRespuestaMovimiento.isMovimientoValido(),
                        comandoRespuestaMovimiento.getMensaje());
                
                break;
                
            case CommandType.COMANDO_TABLERO_INVALIDO:
                
                ComandoTableroInvalido comandoTableroInvalido = (ComandoTableroInvalido) comando;
                
                String mensaje = comandoTableroInvalido.getMensaje();
                
                avisarTableroInvalido(mensaje);
                
                break;
                
            case CommandType.RESPUESTA_TOMAR_FICHA:
                
                ComandoRespuestaTomarFicha comandoRespuestaTomarFicha = (ComandoRespuestaTomarFicha) comando;
                tomarFicha(
                        comandoRespuestaTomarFicha.getTablero());

                break;
                
            case CommandType.RESPUESTA_REESTABLECER:
                
                ComandoRespuestaReestablecer comandoRespuestaReestablecer = (ComandoRespuestaReestablecer) comando;
                reestablecerTablero(
                        comandoRespuestaReestablecer.getTablero());

                break;
                
                
            case CommandType.COMANDO_RESPUESTA_ABANDONAR:
                
                ComandoRespuestaAbandonar comandoRespuestaAbandonar = (ComandoRespuestaAbandonar) comando;
                
                responderSolicitudAbandono(comandoRespuestaAbandonar.getMensaje());
                
                break;
                 
            case CommandType.COMANDO_JUGADOR_ABANDONO:
                
                ComandoJugadorAbandonoPartida comandoJugadorAbandonoPartida = (ComandoJugadorAbandonoPartida) comando;
                
                notificarAbandonoJugador(
                        comandoJugadorAbandonoPartida.getMensaje());
                
                break;
                
            case CommandType.COMANDO_RESPUESTA_SOLICITAR_FIN:
                
                ComandoRespuestaSolicitarFin comandoRespuestaSolicitarFin = (ComandoRespuestaSolicitarFin) comando;
                
                comandoRespuestaSolicitarFin.getMensaje();
                
                responderSolicitudFin(comandoRespuestaSolicitarFin.getMensaje());
                
                break;
                
            case CommandType.COMANDO_RESPUESTA_CONFIRMACION_SOLICITAR_FIN:
                
                ComandoRespuestaConfirmacionSolicitarFin comandoRespuestaConfirmacionSolicitarFin = (ComandoRespuestaConfirmacionSolicitarFin) comando;
                
                comandoRespuestaConfirmacionSolicitarFin.getMensaje();
                
                responderConfirmacionSolicitudFin(comandoRespuestaConfirmacionSolicitarFin.getMensaje());
                
                break;
                
            case CommandType.COMANDO_FIN_PARTIDA:
                
                ComandoFinPartida comandoFinPartida = (ComandoFinPartida) comando;
                
                terminarJuego();
                
                break;
                
            case CommandType.COMANDO_PARTIDA_GANADA:
                
                ComandoPartidaGanada comandoPartidaGanada = (ComandoPartidaGanada) comando;
                
                notificarPartidaGanada(comandoPartidaGanada.getMensaje());
                 
                break;
                
            case CommandType.COMANDO_JUGADOR_PARTIDA_GANADA:
                
                ComandoJugadorPartidaGanada comandoJugadorPartidaGanada = (ComandoJugadorPartidaGanada) comando;
                
                notificarJugadorPartidaGanada(comandoJugadorPartidaGanada.getMensaje());
                 
                break;


                
            default:
                throw new AssertionError();
        }
        
    }


}
