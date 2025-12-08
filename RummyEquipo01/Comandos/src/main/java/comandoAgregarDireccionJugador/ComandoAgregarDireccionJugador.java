
package comandoAgregarDireccionJugador;

import interfaces.ICommand;


public class ComandoAgregarDireccionJugador implements ICommand{
    
    private final String type = "ComandoAgregarDireccionJugador";
    private String[] direccion;
    private String nombreJugador;

    public ComandoAgregarDireccionJugador(String[] direccion, String nombreJugador) {
        this.direccion = direccion;
        this.nombreJugador = nombreJugador;
    }

    @Override
    public String getType() {
        return type;
    }

    public String[] getDireccion() {
        return direccion;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
    
}
