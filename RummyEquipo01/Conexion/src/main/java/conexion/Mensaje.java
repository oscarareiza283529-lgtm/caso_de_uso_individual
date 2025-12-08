
package conexion;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class Mensaje {
    
    
    private String ip;
    private int puerto;
    private String contenido;

    public Mensaje(String ip, int puerto, String contenido) {
        this.ip = ip;
        this.puerto = puerto;
        this.contenido = contenido;
    }
    
    public String getIp() {
        return ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public String getContenido() {
        return contenido;
    }
    
    
}
