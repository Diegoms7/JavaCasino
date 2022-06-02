
/**
 *
 * @author HMartin
 */
public class Partida {
    
    private static final double PRIME_PER_PLAY = 1.5;
    
    private int idPartida;
    private NumWin numGanador;
    private int mystery = 0;
    private double prime = 1;
    private Apuesta apuesta;
    
    public Partida(){
        
        
    }

    
    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public NumWin getNumGanador() {
        return numGanador;
    }

    public void setNumGanador(NumWin numGanador) {
        this.numGanador = numGanador;
    }

    public int getMystery() {
        return mystery;
    }

    public void setMystery(int mystery) {
        this.mystery = mystery;
    }

    public double getPrime() {
        return prime;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }
    
    
    
    

    
    
    
    
    
}
