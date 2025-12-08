
package dto;

import java.util.List;


public class JugadorDTO {
    private List<FichaDTO> fichas;
    private String avatar;
    private String nombre;

    public JugadorDTO(List<FichaDTO> fichas, String avatar, String nombre) {
        this.fichas = fichas;
        this.avatar = avatar;
        this.nombre = nombre;
    }

    public List<FichaDTO> getFichas() {
        return fichas;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setFichas(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }
    
    
}
