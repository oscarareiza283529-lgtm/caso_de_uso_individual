
package dto;


public class TableroDTO {
    private GrupoDTO[] grupos;
    private MontonDTO monton;
    private JugadorDTO jugadorTurno;
    private JugadorDTO[] jugadores;

    public TableroDTO(GrupoDTO[] grupos, MontonDTO monton, JugadorDTO jugadorTurno, JugadorDTO[] jugadores) {
        this.grupos = grupos;
        this.monton = monton;
        this.jugadorTurno = jugadorTurno;
        this.jugadores = jugadores;
    }

    public GrupoDTO[] getGrupos() {
        return grupos;
    }
    
    public MontonDTO getMonton() {
        return monton;
    }

    public JugadorDTO getJugadorTurno() {
        return jugadorTurno;
    }

    public JugadorDTO[] getJugadores() {
        return jugadores;
    }

    
}
