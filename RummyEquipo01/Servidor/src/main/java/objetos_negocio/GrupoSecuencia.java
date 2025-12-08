
package objetos_negocio;

import excepciones.RummyException;
import java.util.List;

/**
 *
 * @author juanpheras
 */
public class GrupoSecuencia extends Grupo{

    public GrupoSecuencia(List<Ficha> fichas) {
        super(fichas);
    }
    
    public GrupoSecuencia(Integer numero, List<Ficha> fichas, boolean primerTurno) {
        super(numero, fichas, primerTurno);
    }

    
    
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
    
    @Override
    protected void determinarValidezFichas(List<Ficha> fichas) throws RummyException{
        
        Grupo.validarGrupoSecuencia(fichas);
        
    }
    
}