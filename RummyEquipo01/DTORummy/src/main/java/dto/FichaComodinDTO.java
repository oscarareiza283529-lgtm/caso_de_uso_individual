
package dto;

import enumeradores.ColorFichaDTO;

/**
 *
 * @author 
 */
public class FichaComodinDTO extends FichaDTO{

    private String valor;
    
    public FichaComodinDTO(ColorFichaDTO color, int id, String valor, String tipo) {
        super(color, id, tipo);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
}
