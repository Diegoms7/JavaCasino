
package Casino;


public class Partida {
    
    private int id;
    private int idJuego;
    private double bet;
    private double balance;
    private String fechaHora;

    public Partida() {
    }

    public Partida(int id, int idJuego, double bet, double balance, String fechaHora) {
        this.id = id;
        this.idJuego = idJuego;
        this.bet = bet;
        this.balance = balance;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public double getBet() {
        return bet;
    }

    public double getBalance() {
        return balance;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Partida{" + "id=" + id + ", idJuego=" + idJuego + ", bet=" + bet + ", balance=" + balance + ", fechaHora=" + fechaHora + '}';
    }
    
    

}