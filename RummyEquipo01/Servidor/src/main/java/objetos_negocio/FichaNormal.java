
package objetos_negocio;

import enumeradores.ColorFicha;

/**
 *
 * @author 
 */
public class FichaNormal extends Ficha{
    
    private int numero;

    public FichaNormal(Integer id, ColorFicha color, boolean esComodin, int numero) {
        super(id, color, esComodin);
        this.numero = numero;
    }



    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
    
    
}
