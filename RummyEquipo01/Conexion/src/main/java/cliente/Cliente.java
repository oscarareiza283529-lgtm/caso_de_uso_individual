
package cliente;

import conexion.Mensaje;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class Cliente {
    private Socket socketCliente;
    private PrintWriter out;
    
    private String ip;
    private int puerto;

    public Cliente(String ip, int puerto) throws IOException{
        this.ip = ip;
        this.puerto = puerto;
        
        this.socketCliente = new Socket(ip, puerto);
        
        this.out = new PrintWriter(socketCliente.getOutputStream(), true);
        
        System.out.println("Nueva conexión establecida con " + ip + ":" + puerto);
    }

    public boolean enviarMensaje(String mensaje){
        if (out != null) {
            out.println(mensaje);
            
            if (out.checkError()) {
                System.err.println("Error al enviar a " + ip + ":" + puerto + ".");
                cerrarConexion();
                return false;
            }
            return true;
        }
        return false;
    }

    public void cerrarConexion() {
        
        try {
            if (out != null) {
                out.close();
            }
            if (socketCliente != null && !socketCliente.isClosed()) {
                socketCliente.close();
            }
            System.out.println("Conexión cerrada para " + ip + ":" + puerto);
            
        } catch (IOException e) {
            
            System.err.println("Error al cerrar socket: " + e.getMessage());
        }
    }
}
