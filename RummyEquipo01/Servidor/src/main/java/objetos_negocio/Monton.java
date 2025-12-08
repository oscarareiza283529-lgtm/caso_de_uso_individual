
package objetos_negocio;

import java.util.List;

/**
 * Clase que junta muchas instancia de fichas para que el jugador pueda tomar.
 * @author juanpheras
 */
public class Monton {
    
    private List<Ficha> fichasMonton;

    public Monton(List<Ficha> fichasMonton) {
        this.fichasMonton = fichasMonton;
    }

    public int getCantidadFichas(){
        
        return fichasMonton.size();
    }

    public List<Ficha> getFichasMonton() {
        return fichasMonton;
    }
    
    
}
