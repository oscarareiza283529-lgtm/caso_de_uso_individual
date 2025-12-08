

package dto;

import enumeradores.ColorFichaDTO;

/**
 *
 * @author 
 */
public class FichaNormalDTO extends FichaDTO{
    
    private int numero;
    
    public FichaNormalDTO(ColorFichaDTO color, int id, int numero, String tipo) {
        super(color, id, tipo);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
    
}
