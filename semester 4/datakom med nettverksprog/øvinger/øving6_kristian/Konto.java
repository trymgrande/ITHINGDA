import javax.persistence.*;

@Entity
@Table (name = "konto")
public class Konto {
    @Id
    @GeneratedValue(generator = "incrementor")
    private int id;
    private double saldo;

    public int getId() {
        return id;
    }
    @Column(name = "eier")
    private String eier;

    private void trekk(double beloep){
        setSaldo(getSaldo() - beloep);
    }
    public String getEier() {
        return eier;
    }

    public void setEier(String eier) {
        this.eier = eier;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
