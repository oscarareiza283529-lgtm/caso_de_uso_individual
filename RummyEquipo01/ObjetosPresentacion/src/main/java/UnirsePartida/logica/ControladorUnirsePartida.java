package UnirsePartida.logica;


import interfaces.ICommand;

/**
 * Intermediario entre la vista FrmInicio y el ModeloCliente.
 */
public class ControladorUnirsePartida {

    private ModeloCliente modelo;

    public ControladorUnirsePartida(ModeloCliente modelo) {
        this.modelo = modelo;
    }

    public void recibirComando(ICommand comando) {
        modelo.enviarComando(comando);
    }

    public ModeloCliente getModelo() {
        return modelo;
    }
}