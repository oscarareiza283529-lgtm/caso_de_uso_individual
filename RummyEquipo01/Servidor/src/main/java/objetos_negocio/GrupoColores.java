
package objetos_negocio;


import excepciones.RummyException;
import java.util.List;

/**
 *
 * @author juanpheras
 */
public class GrupoColores extends Grupo {

    /**
     * Constructor de el grupo de colores.
     *
     * @param numero numero identificador del grupo.
     * @param fichas Fichas con las que se iniciara en el grupo.
     */
    public GrupoColores(Integer numero, List<Ficha> fichas, boolean primerTurno) {
        super(numero, fichas, primerTurno);
    }
    
    /**
     * Comprueba la validez total del grupo.
     * @return 
     */
    @Override
    public boolean comprobarValidez() {
        
        // 3 fichas mínimo
        if (this.fichas.size() < 3) {
            return false;
        }

        try {

            determinarValidezFichas(this.fichas);

        } catch (RummyException ex) {
            
            return false;
        }
        
        return true;
    }
    
    /**
     * Comprueba la validez de fichas a añadir al grupo.
     * @param fichas
     * @throws RummyException 
     */
    @Override
    protected void determinarValidezFichas(List<Ficha> fichas) throws RummyException{
        
        if (fichas.size() > 4) {
            throw new RummyException("El grupo que quieres formar no puede ser de más de 4 fichas.");
        }
        
        Grupo.validarGrupoColores(fichas);
        
    }

}
