
package objetos_negocio;

import enumeradores.ColorFicha;
import excepciones.RummyException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un conjunto de fichas
 * @author juanpheras
 */
public abstract class Grupo {
    
    /**
     * Número identificador del grupo.
     */
    private Integer numero;
    
    /**
     * Lista de fichas pertenecientes al grupo.
     */
    protected List<Ficha> fichas;
    
    private boolean primerTurno;

    
    public static void validarCreacionGrupo(List<Ficha> fichas, boolean primerTurno, int maximoNumeroFicha) throws RummyException{
        
        if(primerTurno){
            
            int sumaNumerosFichas = 0;
            
            for(Ficha ficha: fichas){
                
                if(ficha.isEsComodin()){
                    
                    sumaNumerosFichas += maximoNumeroFicha;
                    
                } else{   
                    sumaNumerosFichas += ((FichaNormal)ficha).getNumero(); 
                }
                
            }
            
            if(sumaNumerosFichas < 30){
                throw new RummyException("Los números deben sumar al menos 30 puntos.");
            }
            
        }
        
    }
    
    
    public static void validarGrupoColores(List<Ficha> fichas) throws RummyException{
        
        Integer numeroFichaGrupo = null;
        
        List<ColorFicha> coloresUsados = new LinkedList<>();
        
        for(Ficha ficha: fichas){
            
            if(!ficha.isEsComodin()){
                
                FichaNormal fichaNormal = (FichaNormal)ficha;
                
                if(numeroFichaGrupo == null){
                    numeroFichaGrupo = ((FichaNormal)ficha).getNumero();
                    
                } else{
                    
                    if(fichaNormal.getNumero() != numeroFichaGrupo){
                        throw new RummyException("Todas las fichas del grupo deben tener el mismo número.");
                    }
                    
                    if(coloresUsados.contains(  fichaNormal.getColor())){
                        throw new RummyException("Todas las fichas deben ser de colores diferentes.");
                    }
                    
                }
                
                coloresUsados.add(fichaNormal.getColor());
                
            }
        }
        
    }
    
    public static void validarGrupoSecuencia(List<Ficha> fichas) throws RummyException{

        // Se ordenan las fichas de menor a mayor por número.
        fichas.sort((ficha1, ficha2) -> {

            if (ficha1.isEsComodin() && ficha2.isEsComodin()){
                return 0;
            }
            if (ficha1.isEsComodin()){
                return 1;
            }
            if (ficha2.isEsComodin()){
                return -1;
            }
            
            return 0;
        
        });
        
        List<Ficha> listaComodines = new LinkedList<>();
        
        for(Ficha ficha: fichas){
            
            if(ficha.isEsComodin()){
                listaComodines.add(ficha);
            }
            
        }
        
        List<Ficha> listaFinalFichas = new LinkedList<>();
        
        int numeroSecuenciaSiguiente = ((FichaNormal)fichas.get(0)).getNumero();
        ColorFicha color = fichas.get(0).getColor();
        
        for(Ficha ficha: fichas){
            
            if(!ficha.isEsComodin()){
                
                if(((FichaNormal)ficha).getNumero() == numeroSecuenciaSiguiente && ficha.getColor() == color){
                
                    listaFinalFichas.add(ficha);

                    numeroSecuenciaSiguiente++;

                } else if(ficha.getColor() == color){

                    if(listaComodines.isEmpty()){
                       throw new RummyException("Los números de las fichas deben seguir una secuencia.");
                    }
                    
                    int cantidadComodinesNecesarios = ((FichaNormal)ficha).getNumero() - numeroSecuenciaSiguiente;

                    if(cantidadComodinesNecesarios <= 0 || cantidadComodinesNecesarios > listaComodines.size()){
                        throw new RummyException("Los números de las fichas deben seguir una secuencia.");
                    }
                    
                    for(int i = 0; i < cantidadComodinesNecesarios; i++){
                        
                        listaFinalFichas.add(listaComodines.get(i));    
                    
                    }
                    
                    for(int i = 0; i < cantidadComodinesNecesarios; i++){
                        
                        listaComodines.remove(0); 
                    
                    }
                    
                    listaFinalFichas.add(ficha);
                                       
                    numeroSecuenciaSiguiente+= cantidadComodinesNecesarios + 1;

                } else{
                    
                    throw new RummyException("Las fichas deben ser del mismo color.");
                    
                }
                
            } 
                  
        }
        
    }
    
    /**
     * Crea un nuevo grupo con un número identificador y 
     * una lista inicial de fichas.
     * 
     * @param numero identificador del grupo.
     * @param fichas lista de fichas que forman parte del grupo.
     */
    public Grupo(Integer numero, List<Ficha> fichas, boolean primerTurno) {
        this.numero = numero;
        this.fichas = fichas;
        this.primerTurno = primerTurno;
        
        for(Ficha ficha: fichas){
            ficha.setGrupo(this);
        }
        
    }

    public Grupo(List<Ficha> fichas) {
        this.fichas = fichas;
        numero++;
    }

    /**
     * Getter de la lista de fichas del grupo.
     * @return lista de ntidades Fichas que pertenecen al grupo.
     */
    public List<Ficha> getFichas() {
        return fichas;
    }

    /***
     * Setter de la lista de fichas.
     * @param fichas lista de fichas a actualizar el grupo.  
     */
    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }
    
    
    /**
     * Comprueba si el grupo es válido de acuerdo a las reglas del juego.
     * La validación específica será implementada por las subclases.
     * 
     * @param esPrimerTurno
     * @return true si el grupo cumple con las reglas; false en caso contrario.
     */
    public abstract boolean comprobarValidez();

    
    
    public void agregarFichasInicio(List<Ficha> fichas) throws RummyException {
        
        List<Ficha> fichasComprobar = new LinkedList<>();
        
        for(Ficha ficha: fichas){
            fichasComprobar.add(ficha);
        }
        
        for(Ficha ficha: this.fichas){
            fichasComprobar.add(ficha);
        }
        
        determinarValidezFichas(fichasComprobar);
        
        if(!primerTurno){
            for(Ficha ficha: fichasComprobar){
                ficha.setGrupo(this);
            }
        }

        this.fichas = fichasComprobar;

    }
    
    public void agregarFichasFinal(List<Ficha> fichas) throws RummyException {
        
        List<Ficha> fichasComprobar = new LinkedList<>();
        
        for(Ficha ficha: this.fichas){
            fichasComprobar.add(ficha);
        }
        
        for(Ficha ficha: fichas){
            fichasComprobar.add(ficha);
        }
        
        determinarValidezFichas(fichasComprobar);
        
        if(!primerTurno){
            for(Ficha ficha: fichasComprobar){
                ficha.setGrupo(this);
            }
        }

        this.fichas = fichasComprobar;

    }
    
    protected abstract void determinarValidezFichas(List<Ficha> fichas) throws RummyException;
    
    
    public void quitarFichas(List<Ficha> fichasQuitar){
        
        for(Ficha ficha: fichas){
            ficha.setGrupo(null);
        }
        
        fichas.removeAll(fichas);
        
    }
    
    public void quitarFicha(Ficha fichaQuitar){
        
        fichaQuitar.setGrupo(null);
        
        fichas.remove(fichaQuitar);

    }
    

    /**
     * Devuelve el número identificador del grupo.
     * 
     * @return el número de grupo.
     */
    public Integer getNumero() {
        return numero;
    }

    public boolean isPrimerTurno() {
        return primerTurno;
    }
    
    public void setPrimerTurno(boolean primerTurno) {
        this.primerTurno = primerTurno;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.numero);
        hash = 97 * hash + Objects.hashCode(this.fichas);
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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return Objects.equals(this.fichas, other.fichas);
    }

    
    
    
    
   
    
    
}
