
package objetos_negocio;

import enumeradores.ColorFicha;
import java.util.Objects;


/**
 * Clase que representa una ficha del juego rummy.
 * @author juanpheras
 */
public abstract class Ficha {
        
    /**
     * Identicador de cada ficha de Rummy(no es el valor solo su identificador).
     */
    private Integer id;
    
    private ColorFicha color;
    
    private boolean esComodin;
    
    private Grupo grupo;


    /**
     * Constructor de la clase ficha, donde se inicia su grupo como nulo ya que no pertenece a ninguno.
     * @param id 
     */
    public Ficha(Integer id, ColorFicha color, boolean esComodin) {
        this.id = id;
        this.color = color;
        this.esComodin = esComodin;
    }

    /**
     * Getter del id de la ficha.
     * @return id de la ficha.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter del id nuevo de la ficha a ajustar.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter del valor que determina si la ficha tiene grupo.
     * @return Valor booleano que determina si la ficha tiene grupo.
     */
    public boolean isTieneGrupo() {
        return grupo != null;
    }

    public ColorFicha getColor() {
        return color;
    }

    public void setColor(ColorFicha color) {
        this.color = color;
    }

    public boolean isEsComodin() {
        return esComodin;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
    
    
    
}
