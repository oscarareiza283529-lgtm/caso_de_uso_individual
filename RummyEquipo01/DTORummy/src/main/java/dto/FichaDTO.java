
package dto;

import enumeradores.ColorFichaDTO;

/**
 *
 * @author ramon
 */
public abstract class FichaDTO {
   private ColorFichaDTO color;
   private int id;
   private String tipo;

    public FichaDTO(ColorFichaDTO color, int id, String tipo) {
        this.color = color;
        this.id = id;
        this.tipo = tipo;
    }

    public ColorFichaDTO getColor() {
        return color;
    }

    public void setColor(ColorFichaDTO color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
   
   
}
 