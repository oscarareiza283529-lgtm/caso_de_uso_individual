
package servidor;

import interfaces.IReceptor;
import interfaces.IReceptorExterno;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ColaMensajesRecibidos implements Runnable, IReceptor{
    
    private BlockingQueue<String> colaMensajesRecibidos = new LinkedBlockingQueue<>();
    private IReceptorExterno receptor;
    private boolean estaCorriendo = true;

    public ColaMensajesRecibidos() {

    }

    @Override
    public void agregarMensaje(String mensaje) {
        colaMensajesRecibidos.add(mensaje);
    }
    
    @Override
    public void run() {

        try {
            while (estaCorriendo) {

                String mensaje = colaMensajesRecibidos.take();
                
                receptor.notificar(mensaje);
            }
        } catch (InterruptedException e) {
            System.err.println("Hilo interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
    
    public void detener() {
        this.estaCorriendo = false;
    }

    public void setReceptor(IReceptorExterno receptor) {
        this.receptor = receptor;
    }
    
    

}
