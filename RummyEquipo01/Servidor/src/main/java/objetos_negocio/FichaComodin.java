package objetos_negocio;

import enumeradores.ColorFicha;

/**
 *
 * @author 
 */
public class FichaComodin extends Ficha{
    
    private String valor;

    public FichaComodin(Integer id, ColorFicha color, boolean esComodin, String valor) {
        super(id, color, esComodin);
        this.valor = valor;
    }

    

    public String getValor() {
        return valor;
    }
    
    
}
