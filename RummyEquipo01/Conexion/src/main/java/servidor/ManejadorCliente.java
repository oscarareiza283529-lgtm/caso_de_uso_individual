
package servidor;

import conexion.Mensaje;
import interfaces.IReceptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    
    private Socket socketCliente;
    private IReceptor receptor;
    
    public ManejadorCliente(Socket socket, IReceptor receptor) {
        this.socketCliente = socket;
        this.receptor = receptor;
    }

    @Override
    public void run() {

        String ipCliente = socketCliente.getInetAddress().getHostAddress();
        int puertoCliente = socketCliente.getPort();
        System.out.println("Hilo iniciado para: " + ipCliente);

        
        try (
            Socket socket = this.socketCliente;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String lineaDeMensaje;
            
            while ((lineaDeMensaje = in.readLine()) != null) {

                Mensaje mensaje = new Mensaje(ipCliente, puertoCliente, lineaDeMensaje);
                
                receptor.agregarMensaje(mensaje.getContenido());

            }
            
        } catch (IOException e) {

            System.out.println("Cliente " + ipCliente + " se desconectó: " + e.getMessage());
        }
        
        System.out.println("Hilo terminado para: " + ipCliente);
    }
}
