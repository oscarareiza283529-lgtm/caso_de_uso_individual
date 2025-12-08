package cliente;

import cliente.Cliente;
import conexion.Mensaje;
import interfaces.ISuscriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GestorConexiones implements ISuscriptor{
    
    private Map<String, Cliente> conexionesActivas = new HashMap<>();

    @Override
    public void notificar(Mensaje mensaje) {
        
        String ip = mensaje.getIp();
        int puerto = mensaje.getPuerto();
        String contenido = mensaje.getContenido();
        
        String direccionDestino = ip + ":" + puerto;
        
        Cliente cliente = conexionesActivas.get(direccionDestino);
        
        if (cliente == null) {

            try {

                cliente = new Cliente(ip, puerto);
                
                conexionesActivas.put(direccionDestino, cliente);
                
            } catch (IOException e) {
                
                System.out.println(e.getMessage());

                System.err.println("Error al crear conexión para " + direccionDestino);
                return;
            }
        }
        
        boolean exito = cliente.enviarMensaje(contenido);
        
        if (!exito) {
            conexionesActivas.remove(direccionDestino);
            System.out.println("Conexión a " + direccionDestino + " falló y fue eliminada del pool de conexiones.");
        }
    }
    

    public void cerrarTodasLasConexiones() {
        for (Cliente cliente: conexionesActivas.values()) {
            cliente.cerrarConexion();
        }
        conexionesActivas.clear();
    }
}
